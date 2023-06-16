package com.example.springmemo.controller;

import com.example.springmemo.dto.MemoRequestDto;
import com.example.springmemo.dto.MemoResponseDto;
import com.example.springmemo.entity.Memo;
import com.example.springmemo.service.MemoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

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
    /*@GetMapping("/Memos/{id}")
    public String getMemo(@PathVariable Long id) {
        return "view";
    }*/
    @GetMapping("/Memos/{id}")
    public String getMemo(@PathVariable Long id,Model model) {
        model.addAttribute("result", memoService.getMemo(id));
        return "view";
    }

    /*@GetMapping("/Memos/view")
    public String getView(Model model) {
        model.addAttribute("responseDto",)
        return "view";
    }*/

    /*
        메모 작성
     */
    @PostMapping("/Memos")
    @ResponseBody
    public MemoResponseDto createMemo(@RequestBody MemoRequestDto memoRequestDto) {
        return memoService.createMemo(memoRequestDto);
    }

    /*
        메모 수정
     */
    @PutMapping("/Memos/{id}")
    @ResponseBody
    public MemoResponseDto updateMemo(@PathVariable Long id, @RequestBody MemoRequestDto memoRequestDto) {
        return memoService.updateMemo(id,memoRequestDto);
    }

    /*
        메모 삭제
     */
    @DeleteMapping("/Memos/{id}")
    @ResponseBody
    public String deleteMemo(@PathVariable Long id, @RequestBody Map<String,String> map) {
        return memoService.deleteMemo(id,map.get("password"));
    }
    //@ReqeustBody를 사용하면 무조건 JSON형태의 객체로 변환하는것 인줄만 알았는데 객체 변수 중 일부만
    //받아오는 경우, Map<>을 사용해서 처리하면 될 것같다.
    /*@DeleteMapping("/Memos/{id}/{password}")
    @ResponseBody
    public Long deleteMemo(@PathVariable Long id, @PathVariable String password) {
        return memoService.deleteMemo(id,password);
    }*/
}
