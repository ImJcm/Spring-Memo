package com.example.springmemo.service;

import com.example.springmemo.dto.SignupRequestDto;
import com.example.springmemo.entity.User;
import com.example.springmemo.entity.UserRoleEnum;
import com.example.springmemo.jwt.JwtUtil;
import com.example.springmemo.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

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
            throw new IllegalArgumentException("중복된 사용자가 존재합니다.");
        }

        // 사용자 ROLE 확인
        UserRoleEnum role = UserRoleEnum.USER;

        // 사용자 등록
        User user = new User(username, password, role);
        userRepository.save(user);
    }
}
