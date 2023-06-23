package com.example.springmemo.security;

import com.example.springmemo.entity.User;
import com.example.springmemo.repository.MemoRepository;
import com.example.springmemo.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/*
    UserDetailsService는 username/password 인증방식을 사용할 때 사용자를 조회하고 검증한 후
    UserDetails를 반환합니다. Custom하여 Bean으로 등록 후 사용 가능합니다.

     2. 인증정보 받아오기 & 인증객체에 넣기 (UserDetailsServiceImpl.java)
        UsernamePasswordAuthenticationFilter > UserDetailsService 구현 > loadUserByUsername() > User > UserDetails > Authentication (createSuccessAuthentication()에서 만들어짐)
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Not Found " + username));

        return new UserDetailsImpl(user);
    }
}
