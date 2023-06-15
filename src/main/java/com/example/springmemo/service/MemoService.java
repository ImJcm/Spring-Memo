package com.example.springmemo.service;

import com.example.springmemo.dto.MemoRequestDto;
import com.example.springmemo.dto.MemoResponseDto;
import com.example.springmemo.entity.Memo;
import com.example.springmemo.repository.MemoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@Service
public class MemoService {
    /*
        @Service : Persistence Context에 의해 관리받는 Service 클래스라고 지정
     */
    private final MemoRepository memoRepository;

    public MemoService(MemoRepository memoRepository) {
        this.memoRepository = memoRepository;
    }

    public List<MemoResponseDto> getMemos() {
        /* 반환 받은 List<Memo>를 반환타입에 맞게 MemoResponseDto(Memo) 생성자를 호출하여 변환 */
        return memoRepository.findAllByOrderByCreatedAtDesc().stream().map(MemoResponseDto::new).toList();
    }

    public MemoResponseDto getMemo(Long id) {
        //return memoRepository.findById(id).map(MemoResponseDto::new).get();
        return new MemoResponseDto(findMemo(id));
    }

    public MemoResponseDto createMemo(MemoRequestDto memoRequestDto) {
        /* MemoRequestDto -> Memo 객체 생성 */
        Memo memo = new Memo(memoRequestDto);

        /* memoRepository 변수로 JPA DB 데이터 생성 = save */
        Memo saveMemo = memoRepository.save(memo);

        MemoResponseDto memoResponseDto = new MemoResponseDto(saveMemo);
        /* MemoResponseDto 생성 및 반환 */
        //return new MemoResponseDto(saveMemo);
        return memoResponseDto;

    }

    /*
        패스워드 검증 후, 정상처리를 확인했지만, 데이터의 적용이 안되는 문제가 있었다.
        이를 해결하기 위해 까먹었던 개념이 있었다. 즉, 데이터의 변동이 있는 작업은
        Transaction(트랜잭션) 환경에서만 적용할 수 있다는 것을 생각하여 트랜잭션 애너테이션을 추가하여
        해결하였다.
     */
    @Transactional
    public Long updateMemo(Long id, MemoRequestDto memoRequestDto) {
        Memo memo = findMemo(id);

        /* 패스워드 검증 */
        if (checkPassword(memo.getPassword(), memoRequestDto.getPassword())) {
            memo.update(memoRequestDto);
            return id;
        } else {
            throw new IllegalArgumentException("비밀번호가 틀립니다.");
        }
    }

    @Transactional
    public Long deleteMemo(Long id, String password) {
        Memo memo = findMemo(id);

        /* 패스워드 검증 */
        if (checkPassword(memo.getPassword(), password)) {
            memoRepository.delete(memo);
            return id;
        } else {
            throw new IllegalArgumentException("비밀번호가 틀립니다.");
        }
    }

    private Memo findMemo(Long id) {
        return memoRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("선택한 메모는 존재하지 않습니다.")
        );
    }

    private Boolean checkPassword(String fromPassword, String toPassword) {
        return fromPassword.equals(toPassword) ? true : false;
    }
}
