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
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;
    private final UserAccountRepository userAccountRepository;

    @Transactional(readOnly = true)
    public List<ChatRoomResponse> getChatRoomList(String searchValue, Pageable pageable) {
        return chatRoomRepository
                .findByTitleContainingIgnoreCaseOrUserAccount_NicknameContainingIgnoreCase(searchValue, searchValue, pageable)
                .stream()
                .map(ChatRoomDto::from)
                .map(ChatRoomResponse::from)
                .toList();
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

}
