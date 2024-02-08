package com.surup.websocket_chat.service;

import com.surup.websocket_chat.domain.ChatRoom;
import com.surup.websocket_chat.dto.ChatRoomDto;
import com.surup.websocket_chat.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional
public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;

    public List<ChatRoomDto> getChatRoomList() {
        List<ChatRoomDto> list = new ArrayList<>();
        ChatRoomDto chatRoomDto = new ChatRoomDto("jhm7509","몽총이","대화 하실분 들어오세요","THISISPASSWORD",2, LocalDateTime.now());
        list.add(chatRoomDto);
        return list;
    }

    // TODO:입력된 id 와 password 와 일치하는 데이터 찾기
    public void isValid(Long id, String password) {
//        chatRoomRepository.findByIdAndPassWord(id, password)
//                .orElseThrow();
    }
}
