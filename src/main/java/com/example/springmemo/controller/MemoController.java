package com.example.springmemo.controller;

import com.example.springmemo.dto.MemoRequestDto;
import com.example.springmemo.dto.MemoResponseDto;
import com.example.springmemo.security.UserDetailsImpl;
import com.example.springmemo.service.MemoService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api")
public class MemoController {
    /*
        @RestContoller : 반환타입이 모두 JSON형태
        @ReqeustMapping : 동일한 url 부분을 매핑
     */
    private final MemoService memoService;
    private static long visitCount = 0;
    /*
        생성자로 final 변수 Bean 타입 객체 초기화
     */
    public MemoController(MemoService memoService) {
        this.memoService = memoService;
    }

    /*
        메모 전체 목록 조회
     */
    @GetMapping("/Memos")
    @ResponseBody
    public List<MemoResponseDto> getMemos() {
        return memoService.getMemos();
    }

    /*
        선택 메모 조회
     */
    @GetMapping("/Memo/{memoId}")
    public String getMemo(@PathVariable Long memoId,Model model) {
        model.addAttribute("result", memoService.getMemo(memoId));
        return "view";
    }

    /*
        4. 인증객체(Authentication)에서 UserDetails 값 받아오기 (@AuthenticationPrincipal)
            Authentication > getPrincipal() > UserDetails
     */
    /*
        메모 작성
        메모 작성 시, title, contents만 JSON데이터로 넘어오고, JWT Token에서 username 정보를 가져온다.
     */
    @PostMapping("/Memo")
    @ResponseBody
    public MemoResponseDto createMemo(@RequestBody MemoRequestDto memoRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return memoService.createMemo(memoRequestDto, userDetails);
    }

    /*
        메모 수정
        @PathVariable로 id를 받고, title,contets는 @RequestBody:Json, username은 @AuthenticationPrincipal로 JWT토큰으로 받는다.
     */
    @PutMapping("/Memo/{memoId}")
    @ResponseBody
    public MemoResponseDto updateMemo(@PathVariable Long memoId, @RequestBody MemoRequestDto memoRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return memoService.updateMemo(memoId,memoRequestDto,userDetails);
    }

    /*
        메모 삭제
        기존의 패스워드 비교방식이 아닌 JWT 토큰에서 username을 비교한다.
     */
    @DeleteMapping("/Memo/{memoId}")
    @ResponseBody
    public String deleteMemo(@PathVariable Long memoId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return memoService.deleteMemo(memoId, userDetails);
    }
    /*
    @DeleteMapping("/Memo/{id}")
    @ResponseBody
    public String deleteMemo(@PathVariable Long id, @RequestBody Map<String,String> map) {
        return memoService.deleteMemo(id,map.get("password"));
    }
     */
}
