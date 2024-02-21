package com.surup.websocket_chat.service;

import com.surup.websocket_chat.constant.ErrorCode;
import com.surup.websocket_chat.domain.UserAccount;
import com.surup.websocket_chat.dto.UserAccountDto;
import com.surup.websocket_chat.dto.request.UserRegisterRequest;
import com.surup.websocket_chat.exception.BaseException;
import com.surup.websocket_chat.repository.UserAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Transactional
@Service
public class UserAccountService {

    private final UserAccountRepository userAccountRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public Optional<UserAccountDto> searchUser(String username) {
        return userAccountRepository.findUserAccountByUserName(username)
                .map(UserAccountDto::from);
    }

    public void createUser(UserRegisterRequest userRegisterDto) {
        try {
            userAccountRepository.save(
                    UserAccount.of(
                            userRegisterDto.username(),
                            passwordEncoder.encode(userRegisterDto.password()),
                            userRegisterDto.nickname())
            );
        } catch (Exception e) {
            throw new BaseException(ErrorCode.USER_REGISTER_ERROR, e);
        }
    }

}
