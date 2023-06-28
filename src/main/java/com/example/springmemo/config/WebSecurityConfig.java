package com.example.springmemo.config;

import com.example.springmemo.jwt.JwtAuthenticationFilter;
import com.example.springmemo.jwt.JwtAuthorizationFilter;
import com.example.springmemo.jwt.JwtUtil;
import com.example.springmemo.security.UserDetailsServiceImpl;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/*
 * 1. 인증설정 (WebSecurityConfig.java)
 * WebSecurityConfig > http.formLogin() > UsernamePasswordAuthenticationFilter > SecurityFilterChain > 요청별 인증수행
 */
@Configuration
@EnableWebSecurity  // Spring Security 지원
@EnableGlobalMethodSecurity(prePostEnabled = true,securedEnabled = true)
public class WebSecurityConfig {
    private final JwtUtil jwtUtil;  //JWT 토큰을 이용하기 위한 Utility

    //Spring security에서 제공하는 UserDetails를 상속받는 UserDetailsImpl객체를 이용을 위한 변수
    private final UserDetailsServiceImpl userDetailsService;
    private final AuthenticationConfiguration authenticationConfiguration;  //Authentication 환경 설정변수


    //Bean 객체를 등록
    public WebSecurityConfig(JwtUtil jwtUtil, UserDetailsServiceImpl userDetailsService, AuthenticationConfiguration authenticationConfiguration) {
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
        this.authenticationConfiguration = authenticationConfiguration;
    }

    //Authentication Configuration을 설정 조작을 위한 Authentication Manager를 반환
    @Bean   //수동 Bean 객체 등록
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    //Jwt 토큰 Autentication(인증)을 위한 Filter 설정
    //적용할 filter = jwtUtil로 지정
    //filter의 authenticationManager 지정
    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() throws Exception {
        JwtAuthenticationFilter filter = new JwtAuthenticationFilter(jwtUtil);
        filter.setAuthenticationManager(authenticationManager(authenticationConfiguration));
        return filter;
    }

    //JWT Authorization(인가)를 위한 filter 지정
    @Bean
    public JwtAuthorizationFilter jwtAuthorizationFilter() {
        return new JwtAuthorizationFilter(jwtUtil, userDetailsService);
    }

    //
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        //CSRF 설정 - Creos site Request Forgery가 불가능하도록 설정
        http.csrf((csrf) -> csrf.disable());

        // spring security session 정책 : Session 방식은 사용하지 않고 JWT 방식을 사용하기 위한 설정
        http.sessionManagement((sessionManagement) -> {
            sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        });

        http.authorizeHttpRequests((authorizeHttpRequests) -> {
            authorizeHttpRequests
                    .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll() // resource 접근 허용
                    .requestMatchers("/api/user/**").permitAll()    //'/api/user/'로 시작하는 요청 모두 허용
                    //.requestMatchers("/api/Memos/").permitAll()   //'/api/Memos/'로 시작하는 요청 모두 허용 = 전체 게시글 조회
                    //.requestMatchers("/api/Memo/**", HttpMethod.GET.name()).permitAll()   //'/api/Memo로 시작하는 GET 요청 모두 허용 = 게시글 선택 조회
                    //.requestMatchers("/api/Comments/**").permitAll()  //'/api/Comments/로 시작하는 GET요청 모두 허용 = 게시글의 모든 댓글 조회
                    //.requestMatchers("/api/Comment/**").permitAll()
                    .anyRequest().authenticated();  // 그 외 모든 요청 인증 과정 거침
        });


        //spring security에서 제공하는 Login에 사용될 view 페이지 설정
        http.formLogin((formLogin) ->
                formLogin
                        .loginPage("/api/user/login-page").permitAll()
        );

        // 필터 관리
        // 인증 이전에 인가를 배치하여 JWT 토큰이 있다면 인가부분에서 인증 처리한다.
        // JwtAuthorizationFilter -> JwtAuthenticationFilter -> UsernamePasswordAuthenticationFilter
        // -> DispatcherServlet -> Controller -> ...
        http.addFilterBefore(jwtAuthorizationFilter(), JwtAuthenticationFilter.class);
        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();

    }
}
