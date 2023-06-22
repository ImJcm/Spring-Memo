package com.example.springmemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class PasswordConfig {

    /*
        Spring Security에서 제공하는 BCrypt Hashing 함수로 패스워드를 암호화해주는 함수
        이때 해싱함수로 암호화한다는 의미는
        plain -> Encode -> encodedPlain : 암호화
        encodedPlain -> decode -> plain : 복호화
        위의 복호화가 불가능한 것을 의미한다.
        이를 단방향 암호화라고 하며, 이를 활용하면 secret Key를 통해 클라이언트에서 입력한 비밀번호를 secret key로
        암호화한 값을 DB에 저장하고, 클라이언트에서 패스워드를 입력 시, 입력값에 secret Key로 암호화한 값과
        DB에 저장된 비밀번호 값을 비교하여 같으면 인증을 허용한다.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
