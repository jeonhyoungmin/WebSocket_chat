package com.surup.websocket_chat.service;

import com.surup.websocket_chat.dto.ChatRoomDto;
import com.surup.websocket_chat.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;

    public List<ChatRoomDto> getChatRoomList() {
        return chatRoomRepository.findAll()
                .stream()
                .map(ChatRoomDto::from)
                .toList();
    }

    // TODO:입력된 id 와 password 와 일치하는 데이터 찾기
    public void isValid(Long id, String password) throws Exception {
        chatRoomRepository.findByIdAndPassword(id, password)
                .orElseThrow(() -> new Exception("채팅방이 존재하지 않거나 비밀번호가 유효하지 않습니다"));
    }
}
