package com.example.springmemo.jwt;

import com.example.springmemo.security.UserDetailsServiceImpl;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j(topic = "JWT 검증 및 인가")
public class JwtAuthorizationFilter extends OncePerRequestFilter {
    private final JwtUtil jwtUtil;
    private final UserDetailsServiceImpl userDetailsService;

    public JwtAuthorizationFilter(JwtUtil jwtUtil, UserDetailsServiceImpl userDetailsService) {
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    /*
        request Header로부터 cookie에서 AUTHENTICATION_HEADER에 해당하는 cookie를 모두 받아온 뒤,

     */
    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain filterChain) throws ServletException, IOException {
        String tokenValue = jwtUtil.getTokenFromRequest(req);

        //문자열 유효성 검사 : null이 아니고, 길이가 0보다 크고, 공백이 아닌문자열인지 검사
        if (StringUtils.hasText(tokenValue)) {
            // JWT 토큰 substring
            tokenValue = jwtUtil.substringToken(tokenValue);
            log.info(tokenValue);

            //Token 오류의 에러처리를 하려면 jwtUtil.validateToken()에서 어떤 오류인지를 식별하고 상태값을 반환한 뒤,
            //아래에서 상태값을 바탕으로 response.setStatus()로 상태코드를 보내면 상태코드 별로 트리거를 수행하면 될 것같다.
            //프론트에서 트리거를 실행할 경우, 상태코드에 따라 api 요청이 달라질 것이고, 서버에서 처리 시, redirect:url로
            //문제가 발생한 부분을 재요청할 것같다.
            //JWT 토큰의 유효성 검사
            if (!jwtUtil.validateToken(tokenValue)) {
                //log.error("Token Error");

                res.setStatus(400);
                //res.sendError(400,"토큰이 유효하지않습니다.");
                //res.sendError(HttpServletResponse.SC_BAD_REQUEST,"토큰이 유효하지 않습니다.");
                return;
            }

            //token에서 username -> setSubject()로 지정된 정보를 가져온다.
            Claims info = jwtUtil.getUserInfoFromToken(tokenValue);

            try {
                setAuthentication(info.getSubject());
            } catch (Exception e) {
                log.error(e.getMessage());
                return;
            }
        }

        filterChain.doFilter(req, res);
    }

    // 인증 처리 - 매개변수로 넘어온 username에 해당하는 token일 경우, 인가시킨다.
    /*
        - UsernamePasswordAuthenticationFilter는 Spring Security의 필터인 AbstractAuthenticationProcessingFilter를 상속한 Filter입니다.
        - 기본적으로 Form Login 기반을 사용할 때 username 과 password 확인하여 인증합니다.
        - 인증 과정
            1. 사용자가 username과 password를 제출하면 UsernamePasswordAuthenticationFilter는 인증된 사용자의 정보가 담기는
               인증 객체인 Authentication의 종류 중 하나인 UsernamePasswordAuthenticationToken을 만들어 AuthenticationManager에게
               넘겨 인증을 시도합니다.
            2. 실패하면 SecurityContextHolder를 비웁니다.
            3. 성공하면 SecurityContextHolder에 Authentication를 세팅합니다.
        - SecurityContext는 인증이 완료된 사용자의 상세 정보(Authentication)을 저장하고 인가시 사용된다.
     */
    public void setAuthentication(String username) {
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        Authentication authentication = createAuthentication(username);
        context.setAuthentication(authentication);

        SecurityContextHolder.setContext(context);
    }

    // 인증 객체 생성
    /*
        UsernamePasswordAuthenticationToken는 Authentication을 implements한 AbstractAuthenticationToken의 하위 클래스로
        인증객체를 만드는데 사용한다.
        Authentication(Principal, Credential, Authorities)
     */
    private Authentication createAuthentication(String username) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }
}
