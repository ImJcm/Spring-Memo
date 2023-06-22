package com.example.springmemo.controller;

import com.example.springmemo.security.UserDetailsImpl;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        //Model 객체를 이용하여 index.html로 데이터를 지정하여 보낼 수 있다.
        //@AuthenticationPrincipal 애너테이션으로 request Header에 포함된 JWT 토큰 값을 이용할 수 있다.
        //이때, UserDetailsImpl 객체로 생성자를 자동으로 생성한다.
        model.addAttribute("username", userDetails.getUsername());
        return "index";
    }
}
