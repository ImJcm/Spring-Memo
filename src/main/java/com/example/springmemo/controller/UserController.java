package com.example.springmemo.controller;

import com.example.springmemo.dto.SignupRequestDto;
import com.example.springmemo.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

//@Slf4j : spring logger, logging에 사용될 프레임워크
@Slf4j
@Controller
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/login-page")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/user/signup")
    public String signupPage() {
        return "signup";
    }

    @PostMapping("/user/signup")
    public String signup(@Valid SignupRequestDto requestDto, BindingResult bindingResult) {
        // Validation 예외처리 - username, password의 양식(format) 검사
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        if(fieldErrors.size() > 0) {
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                log.error(fieldError.getField() + " 필드 : " + fieldError.getDefaultMessage());
            }
            return "redirect:/api/user/signup";
        }

        try {
            userService.signup(requestDto);
        } catch (IllegalArgumentException e) {
            //@ResponseBody를 붙일 수 없기때문에 불가능
            //return "{\"msg\":\"아이디가 중복됩니다.\"}";
            //중복 아이디일 경우 회원가입 창 redirect
            log.error(e.getMessage());
            return "redirect:/api/user/signup";
        }

        //회원가입 성공 response
        return "redirect:/api/user/login-page";
    }
}
