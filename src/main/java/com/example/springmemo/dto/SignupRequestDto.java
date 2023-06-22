package com.example.springmemo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequestDto {
    //@Validation : 소문자 알파벳과 숫자를 포함한 4자 이상 10자 이하의 형식 + 공백 불가
    @Pattern(regexp = "[a-z0-9]{4,10}",message = "사용자명 형식이 옳바르지 않습니다.")
    @NotBlank
    private String username;

    //@Validation : 소문자, 대문자 알파벳과 숫자를 포함한 8자 이상 15자 이하의 형식 + 공백 불가
    @Pattern(regexp = "[a-zA-Z0-9]{8,15}",message = "패스워드 형식이 옳바르지 않습니다.")
    @NotBlank
    private String password;
}
