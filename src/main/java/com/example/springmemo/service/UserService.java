package com.example.springmemo.service;

import com.example.springmemo.dto.SignupRequestDto;
import com.example.springmemo.entity.User;
import com.example.springmemo.entity.UserRoleEnum;
import com.example.springmemo.exception.AdminTokenWrongException;
import com.example.springmemo.exception.UserNotFoundException;
import com.example.springmemo.jwt.JwtUtil;
import com.example.springmemo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Locale;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final MessageSource messageSource;
    private final JwtUtil jwtUtil;

    /*public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }*/

    // ADMIN_TOKEN
    @Value("${admin_token}")
    private String ADMIN_TOKEN;

    /*
        회원가입 시, username, password로 이루어진 SignupRequestDto로 생성자가 만들어진다.
     */
    @Transactional
    public void signup(SignupRequestDto requestDto) {   //@ModelAttribute가 생략된 것으로 보인다.
        String username = requestDto.getUsername();
        String password = passwordEncoder.encode(requestDto.getPassword());

        // 회원 중복 확인
        Optional<User> checkUsername = userRepository.findByUsername(username);
        if (checkUsername.isPresent()) {
            //throw new IllegalArgumentException("중복된 사용자가 존재합니다.");
            throw new UserNotFoundException(
                messageSource.getMessage(
                    "not.found.User",
                        null,
                        "Not Found User",
                        Locale.getDefault()
                )
            );
        }

        // 사용자 ROLE 확인
        UserRoleEnum role = UserRoleEnum.USER;
        if (requestDto.isAdmin()) {
            if (!ADMIN_TOKEN.equals(requestDto.getAdminToken())) {
                //throw new IllegalArgumentException("관리자 암호가 틀려 등록이 불가능합니다.");
                throw new AdminTokenWrongException(
                    messageSource.getMessage(
                        "wrong.AdminToken",
                            null,
                            "Wrong AdminToken",
                            Locale.getDefault()
                    )
                );
            }
            role = UserRoleEnum.ADMIN;
        }

        // 사용자 등록
        User user = new User(username, password, role);
        userRepository.save(user);
    }
}
