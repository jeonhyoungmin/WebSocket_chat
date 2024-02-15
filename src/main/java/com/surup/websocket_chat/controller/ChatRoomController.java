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
public class ChatRoomController {

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

    @GetMapping("/chatRoom")
    public ModelAndView chatRoom(@RequestParam("id") Long id) {
        // TODO: 검증을 api 에서 진행한 후, redirection 으로 뷰 페이지 보여주기
        Map<String, Object> map = new HashMap<>();
        map.put("chatRoomId", id);
        return new ModelAndView("chatRoom", map);
    }
}
