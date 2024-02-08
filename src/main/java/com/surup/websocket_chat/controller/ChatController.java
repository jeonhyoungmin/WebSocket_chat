package com.surup.websocket_chat.controller;

import com.surup.websocket_chat.dto.ChatRoomResponse;
import com.surup.websocket_chat.service.ChatRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Controller
public class ChatController {

    private final ChatRoomService chatRoomService;

    @GetMapping("/chatRoomList")
    public ModelAndView chatRoomList() {
        List<ChatRoomResponse> chatRoom = chatRoomService.getChatRoomList()
                .stream()
                .map(ChatRoomResponse::from)
                .toList();
        Map<String, Object> map = new HashMap<>();
        map.put("chatRoom", chatRoom);
        return new ModelAndView("chatRoomList", map);
    }

    // TODO : room_id 와 password 에 맞는 채팅방으로 이동시키기
    @GetMapping("/chatRoom")
    public ModelAndView chatRoom(@RequestParam("id") Long id,
                                 @RequestParam(value = "password", defaultValue = "") String password) {
        chatRoomService.isValid(id, password);
        Map<String, Object> map = new HashMap<>();
        map.put("chatRoomId", id);
        return new ModelAndView("chatRoom", map);
    }
}
