package com.example.springmemo.entity;

/*
    회원 상세정보 (UserDetailsImpl)에 권한(Authority) 지정 가능
    권한명 규칙
        "ROLE_로 시작한다.
 */

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum UserRoleEnum {
    USER(Authority.USER),   // 사용자 권한
    ADMIN(Authority.ADMIN); // 관리자 권한
    private final String authority;

    UserRoleEnum(String authority) {
        this.authority = authority;
    }

    public String getAuthority() {
        return this.authority;
    }

    public static class Authority {
        public static final String USER = "ROLE_USER";
        public static final String ADMIN = "ROLE_ADMIN";
    }
}
