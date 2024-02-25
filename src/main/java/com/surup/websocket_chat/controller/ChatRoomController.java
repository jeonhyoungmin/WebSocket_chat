package com.surup.websocket_chat.controller;

import com.surup.websocket_chat.dto.UserAccountDto;
import com.surup.websocket_chat.dto.request.ChatRoomPasswordRequest;
import com.surup.websocket_chat.dto.request.CreateChatRoomRequest;
import com.surup.websocket_chat.dto.response.ChatRoomResponse;
import com.surup.websocket_chat.service.ChatRoomService;
import com.surup.websocket_chat.service.PaginationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class ChatRoomController {

    private final ChatRoomService chatRoomService;
    private final PaginationService paginationService;

    @GetMapping("/chatRoomList")
    public String chatRoomList(
            @RequestParam(value = "searchValue", defaultValue = "") String searchValue,
            @PageableDefault(size = 10, page = 0, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable,
            Model model
    ) {
        Page<ChatRoomResponse> chatRoomList = chatRoomService.getChatRoomList(searchValue, pageable);
        List<Integer> barNumbers = paginationService.getPaginationBarNumbers(pageable.getPageNumber(), chatRoomList.getTotalPages());

        model.addAttribute("chatRoomList", chatRoomList);
        model.addAttribute("paginationBarNumbers", barNumbers);
        model.addAttribute("searchValue", searchValue);
        return "chatRoomList/chatRoomList";
    }

    @PostMapping("/enterChatRoom")
    public String enterChatRoom(ChatRoomPasswordRequest chatRoomPasswordRequest,
                                @AuthenticationPrincipal UserAccountDto userAccountDto,
                                Model model
    ) {
        chatRoomService.isValid(chatRoomPasswordRequest);
        model.addAttribute("chatRoomId", chatRoomPasswordRequest.id());
        model.addAttribute("userNickname", userAccountDto.nickname());
        return "chatRoom/chatRoom";
    }

    /*
        유효성 메인 검사 = client
        server 에서 유효성 검사 문제 시, error 페이지로 이동시킨다.
     */
    @PostMapping("/createChatRoom")
    public String createChatRoom(CreateChatRoomRequest createChatRoomRequest, // @ModelAttribute 생략 가능
                                 @AuthenticationPrincipal UserAccountDto userAccountDto,
                                 Model model
    ) {
        ChatRoomResponse chatRoomResponse = ChatRoomResponse
                .from(chatRoomService.createChatRoom(createChatRoomRequest, userAccountDto.id()));
        model.addAttribute("chatRoomId", chatRoomResponse.id());
        model.addAttribute("userNickname", chatRoomResponse.nickname());
        return "chatRoom/chatRoom";
    }

}
