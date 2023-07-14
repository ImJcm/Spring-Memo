package com.example.springmemo.jwt;

import com.example.springmemo.dto.ApiResponseDto;
import com.example.springmemo.dto.LoginRequestDto;
import com.example.springmemo.entity.UserRoleEnum;
import com.example.springmemo.exception.LoginFailException;
import com.example.springmemo.security.UserDetailsImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/*
    Spring에서 제공하는 Spring Security Filter 중 UsernamePasswordAuthenticationFilter를 상속받아서
    필요한 기능에 맞게 커스텀한다.
 */

@Slf4j(topic = "로그인 및 JWT 생성")
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final JwtUtil jwtUtil;

    public JwtAuthenticationFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
        //'/api/user/login' api 요청이 있을 때 JwtAuthenticaionFilter에만 적용되도록 하는 메서드
        setFilterProcessesUrl("/api/user/login");
    }

    //로그인 시도 시, authenticationManager를 통해 username, password의 값으로 하는 로그인 인증과정 수행
    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res) throws AuthenticationException {
        log.info("로그인 시도");
        try {
            LoginRequestDto requestDto = new ObjectMapper().readValue(req.getInputStream(), LoginRequestDto.class);

            /*
                ProviderManager의 authenticate(UsernamePasswordAuthenticationToken()) 수행
                아래 과정에서 ProviderManager가 AuthenticationProvider를 찾아서 인증하는 과정또한 포함
             */
            return getAuthenticationManager().authenticate(
                    new UsernamePasswordAuthenticationToken(
                            requestDto.getUsername(),
                            requestDto.getPassword(),
                            null
                    )
            );
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    /*
        인증 성공, 실패 Handling
        성공 : successfulAuthentication
        실패 : unsuccessfulAuthentication
     */

    //authentication 성공 시, jwtUtil을 통해 username과 role값으로 token을 생성하고 클라이언트에게 token을 생성한다.
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        log.info("로그인 성공 및 JWT 생성");
        String username = ((UserDetailsImpl) authResult.getPrincipal()).getUsername();
        UserRoleEnum role = ((UserDetailsImpl) authResult.getPrincipal()).getUser().getRole();

        String token = jwtUtil.createToken(username, role);
        jwtUtil.addJwtToCookie(token, response);
    }

    //인증 실패 시, responseStatus : 400로 지정 후, 종료 - 실패 핸들링
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest req, HttpServletResponse res, AuthenticationException failed) throws IOException, ServletException {
        log.info("로그인 실패");

        res.setContentType(MediaType.APPLICATION_JSON_VALUE);

        final Map<String, Object> body = new HashMap<>();
        body.put("status", HttpServletResponse.SC_BAD_REQUEST);
        body.put("message", "회원을 찾을 수 없습니다.");

        final ObjectMapper mapper = new ObjectMapper();
        res.setStatus(400);
        mapper.writeValue(res.getOutputStream(), body);
    }
}
