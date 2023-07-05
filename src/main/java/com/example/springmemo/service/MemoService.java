package com.example.springmemo.service;

import com.example.springmemo.dto.MemoRequestDto;
import com.example.springmemo.dto.MemoResponseDto;
import com.example.springmemo.entity.Memo;
import com.example.springmemo.entity.User;
import com.example.springmemo.jwt.JwtUtil;
import com.example.springmemo.repository.MemoRepository;
import com.example.springmemo.repository.UserRepository;
import com.example.springmemo.security.UserDetailsImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j(topic = "memo service")
public class MemoService {
    /*
        @Service : Persistence Context에 의해 관리받는 Service 클래스라고 지정
     */
    private final MemoRepository memoRepository;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    public MemoService(MemoRepository memoRepository, UserRepository userRepository, JwtUtil jwtUtil) {
        this.memoRepository = memoRepository;
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    public List<MemoResponseDto> getMemos() {
        /* 반환 받은 List<Memo>를 반환타입에 맞게 MemoResponseDto(Memo) 생성자를 호출하여 변환 */
        return memoRepository.findAllByOrderByCreatedAtDesc().stream().map(MemoResponseDto::new).toList();
    }

    public MemoResponseDto getMemo(Long memoId) {
        return new MemoResponseDto(findMemo(memoId));
    }

    @Transactional
    public MemoResponseDto createMemo(MemoRequestDto memoRequestDto, UserDetailsImpl userDetails) {
        memoRequestDto = updateUsernameToMemoRequestDto(memoRequestDto, userDetails.getUsername());

        Memo memo = new Memo(memoRequestDto);

        /* User, Memo - 영속성 전이 jpa 연관관계 설정 - save 불필요 */
        memo.setUser(userDetails.getUser());
        //userRepository.save(userDetails.getUser());

        /* Repository 변수로 JPA DB 데이터 생성 */
        Memo saveMemo = memoRepository.save(memo);

        /* MemoResponseDto 생성 및 반환 */
        MemoResponseDto memoResponseDto = new MemoResponseDto(saveMemo);
        return memoResponseDto;

    }

    /*
       기존에 패스워드를 검증하는 방법에서 JWT 토큰의 username과 메모에 저장된 username과 비교하여 메모를 수정한다.
     */
    @Transactional
    public MemoResponseDto updateMemo(Long memoId, MemoRequestDto memoRequestDto, UserDetailsImpl userDetails) {
        Memo memo = findMemo(memoId);
        memoRequestDto = updateUsernameToMemoRequestDto(memoRequestDto, userDetails.getUsername());

        //jwt Token username과 Memo의 username 비교 검증
        if (checkUser(memo.getUser().getUsername(), memoRequestDto.getUsername()) || userDetails.getAuthoritie().equals("ROLE_ADMIN")) {
            memo.update(memoRequestDto);
            return new MemoResponseDto(memo);
        } else {
            throw new IllegalArgumentException("메모의 소유자가 아닙니다.");
        }
    }

    @Transactional
    public String deleteMemo(Long memoId, UserDetailsImpl userDetails) {
        Memo memo = findMemo(memoId);

        //System.out.println(userDetails.getAuthorities().toArray()[0].equals("ROLE_ADMIN"));

        if (checkUser(memo.getUser().getUsername(), userDetails.getUsername()) || userDetails.getAuthoritie().equals("ROLE_ADMIN")) {
            memoRepository.delete(memo);
            return "{\"msg\":\"게시글 삭제 성공\",\"statusCode\":\"200\"}";
        } else {
            throw new IllegalArgumentException("메모의 소유자가 아닙니다.");
        }
    }

    private Memo findMemo(Long memoid) {
        return memoRepository.findById(memoid).orElseThrow(() ->
                new IllegalArgumentException("선택한 메모는 존재하지 않습니다.")
        );
    }

    private boolean checkUser(String memo_username, String jwt_username) {
        return memo_username.equals(jwt_username) ? true : false;
    }

    private MemoRequestDto updateUsernameToMemoRequestDto(MemoRequestDto memoRequestDto, String username) {
        memoRequestDto.setUsername(username);

        return memoRequestDto;
    }


    /* 패스워드 검증 방법 */
    /*if (checkPassword(memo.getPassword(), password)) {
        memoRepository.delete(memo);
        //return id;
        return "{\"success\":\"true\"}";
    } else {
        throw new IllegalArgumentException("비밀번호가 틀립니다.");
    }*/

    /*private Boolean checkPassword(String fromPassword, String toPassword) {
        return fromPassword.equals(toPassword) ? true : false;
    }*/
    /*
        Controller에서 @CookieValue(JwtUtil.AUTHORIZATION_HEADER) String tokenValue의 매개변수로 받는 경우,
        클라이언트로부터 메모의 제목, JSON타입의 내용을 @RequestBody로 받고, 작성자명은 JWT Token에서 추출하여 사용하기 때문에
        새로운 MemoRequestDto를 생성해준다.

        아래와 같이 MemoRequestDto를 변경한 뒤 반환하는 경우 메모 생성, 수정에서는 사용할 수 있지만, 삭제 기능에서는 사용할 수 없다
        따라서 생성, 수정, 삭제 모두 사용할 수 있는 함수를 만들기 위해서는 Claims에서 username만 반환하는 형태까지 구현하고,
        생성, 수정에서 필요한 MemoRequestDto에는 생성자대신 username만 추가하면 되므로, @Setter를 활용하기로 하였다.
     */
    /*private MemoRequestDto updateMemoRequestDto(MemoRequestDto memoRequestDto, String tokenValue) {
        String token = jwtUtil.substringToken(tokenValue);

        if(!jwtUtil.validateToken(token)) {
            throw new IllegalArgumentException("Token Error");
        }

        Claims info = jwtUtil.getUserInfoFromToken(token);
        String username = info.getSubject();

        String title = memoRequestDto.getTitle();
        String contents = memoRequestDto.getContents();

        return new MemoRequestDto(title,username,contents);
    }

    private String getUsernameFromJWT(String tokenValue) {
        //JWT Bearer 제거 = substring
        String token = jwtUtil.substringToken(tokenValue);

        //JWT 검즘
        if(!jwtUtil.validateToken(token)) {
            throw new IllegalArgumentException("Token Error");
        }

        Claims info = jwtUtil.getUserInfoFromToken(token);

        //JWT Token 생성 시, setSubject("username")으로 설정했기 때문에 받을 때는 getSubject()
        return info.getSubject();
    }

    */
}
