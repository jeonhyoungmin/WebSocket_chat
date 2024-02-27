package com.surup.websocket_chat.service;

import com.surup.websocket_chat.constant.ErrorCode;
import com.surup.websocket_chat.domain.ChatRoom;
import com.surup.websocket_chat.domain.UserAccount;
import com.surup.websocket_chat.dto.ChatRoomDto;
import com.surup.websocket_chat.dto.request.ChatRoomPasswordRequest;
import com.surup.websocket_chat.dto.request.CreateChatRoomRequest;
import com.surup.websocket_chat.dto.response.ChatRoomResponse;
import com.surup.websocket_chat.exception.BaseException;
import com.surup.websocket_chat.repository.ChatRoomRepository;
import com.surup.websocket_chat.repository.UserAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional
@Service
public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;
    private final UserAccountRepository userAccountRepository;

    @Transactional(readOnly = true)
    public Page<ChatRoomResponse> getChatRoomList(String searchValue, Pageable pageable) {
        return chatRoomRepository
                .findByTitleContainingIgnoreCaseOrUserAccount_NicknameContainingIgnoreCase(searchValue, searchValue, pageable)
                .map(ChatRoomDto::from)
                .map(ChatRoomResponse::from);
    }

    @Transactional(readOnly = true)
    public void isValid(ChatRoomPasswordRequest chatRoomPasswordRequest) throws BaseException {
        chatRoomRepository.findByIdAndPassword(chatRoomPasswordRequest.id(), chatRoomPasswordRequest.password())
                .orElseThrow(() -> new BaseException(ErrorCode.VALIDATION_ERROR));
    }

    public ChatRoomDto createChatRoom(CreateChatRoomRequest createChatRoomRequest, Long id) {
        try {
            UserAccount userAccount = userAccountRepository.findById(id).get();
            return ChatRoomDto.from(chatRoomRepository.save(
                    ChatRoom.of(userAccount, createChatRoomRequest.title(), createChatRoomRequest.password())));
        } catch (Exception e) {
            throw new BaseException(ErrorCode.DATA_ACCESS_ERROR, e);
        }
    }

    public void increaseCount(Long id) {
        ChatRoom chatRoom = chatRoomRepository.findById(id)
                .orElseThrow(() -> new BaseException(ErrorCode.DATA_ACCESS_ERROR));
        chatRoom.setCount(chatRoom.getCount() + 1);
    }

    public void decreaseCount(Long id, String nickname) {
        ChatRoom chatRoom = chatRoomRepository.findById(id)
                .orElseThrow(() -> new BaseException(ErrorCode.DATA_ACCESS_ERROR));

        try {
            System.out.println("chatRoom.getUserAccount().getNickname() = " + chatRoom.getUserAccount().getNickname());
            System.out.println("nickname = " + nickname);
            if (chatRoom.getUserAccount().getNickname().equals(nickname)) {
                chatRoomRepository.deleteById(id);
            } else {
                chatRoom.setCount(chatRoom.getCount() - 1);
            }

        } catch (Exception e) {
            throw new BaseException(ErrorCode.DATA_ACCESS_ERROR, e);
        }

    }

}
