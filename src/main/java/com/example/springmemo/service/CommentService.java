package com.example.springmemo.service;

import com.example.springmemo.dto.ApiResponseDto;
import com.example.springmemo.dto.CommentRequestDto;
import com.example.springmemo.dto.CommentResponseDto;
import com.example.springmemo.dto.LikeCommentResponseDto;
import com.example.springmemo.entity.Comment;
import com.example.springmemo.entity.LikeComment;
import com.example.springmemo.exception.CommentNotFoundException;
import com.example.springmemo.exception.CommentOwnerNotException;
import com.example.springmemo.exception.MemoNotFoundException;
import com.example.springmemo.exception.MemoOwnerNotException;
import com.example.springmemo.repository.CommentRepository;
import com.example.springmemo.repository.LikeCommentRepository;
import com.example.springmemo.repository.MemoRepository;
import com.example.springmemo.repository.UserRepository;
import com.example.springmemo.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
@Slf4j(topic = "comment service")
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final MemoRepository memoRepository;
    private final UserRepository userRepository;
    private final LikeCommentRepository likeCommentRepository;
    private final MessageSource messageSource;

    /*public CommentService(CommentRepository commentRepository, MemoRepository memoRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.memoRepository = memoRepository;
        this.userRepository = userRepository;
    }*/

    /*
        memoid에 해당하는 게시글의 모든 댓글을 반환
     */
    public List<CommentResponseDto> getComments(Long memoId) {
        //return commentRepository.findAllByMemoIdOrderByCreatedAtDesc(memoId).stream().map(CommentResponseDto::new).toList();
        return commentRepository.findAllByMemo_MemoIdOrderByCreatedAtDesc(memoId).stream().map(CommentResponseDto::new).toList();
    }

    @Transactional
    public CommentResponseDto createComment(Long memoId, CommentRequestDto commentRequestDto, UserDetailsImpl userDetails) {
        commentRequestDto = updateUsernameAndMemoIdToCommentRequestDto(commentRequestDto,memoId, userDetails.getUsername());

        Comment comment = new Comment(commentRequestDto);

        /* User - 영속성 전이 JPA 연관관계 설정 - save 불필요 */
        comment.setUser(userDetails.getUser());
        //userRepository.save(userDetails.getUser());
        //userRepository.save(userRepository.findById(userDetails.getUser().getUserId()).orElse(null));

        /* Memo - 영속성 전이 JPA 연관관계 설정 - save 불필요 */
        comment.setMemo(memoRepository.findById(memoId).orElseThrow(() -> new MemoNotFoundException(
            messageSource.getMessage(
                   "not.found.Memo",
                    new Long[]{memoId},
                    "Not Found Memo",
                    Locale.getDefault()
            )
        )));
        //comment.setMemo(memoRepository.findById(memoId).orElse(null));
        //memoRepository.save(memoRepository.findById(memoId).orElse(null));

        /* commentRepository 변수로 JPA DB 데이터 생성 */
        Comment saveComment = commentRepository.save(comment);

        /* CommentResponseDto 생성 및 반환 */
        CommentResponseDto commentResponseDto = new CommentResponseDto(saveComment);
        return commentResponseDto;
    }

    /*
        JWT 토큰을 통해 username으로 댓글의 소유자 검증
     */
    @Transactional
    public CommentResponseDto updateComment(Long memoId, Long commentId, CommentRequestDto commentRequestDto, UserDetailsImpl userDetails) {
        Comment comment = findComment(memoId,commentId);
        commentRequestDto = updateUsernameAndMemoIdToCommentRequestDto(commentRequestDto, memoId, userDetails.getUsername());

        //jwt Token username과 comment의 username 비교 검증
        if(checkUser(comment.getUser().getUsername(), commentRequestDto.getUsername()) || userDetails.getAuthoritie().equals("ROLE_ADMIN")) {
            comment.update(commentRequestDto);
            return new CommentResponseDto(comment);
        } else {
            //throw new IllegalArgumentException("댓글의 소유자가 아닙니다.");
            throw new CommentOwnerNotException(
                messageSource.getMessage(
                        "not.owner.Comment",
                        new Long[]{commentId},
                        "Not Comment Owner",
                        Locale.getDefault()
                )
            );
        }
    }



    @Transactional
    public String deleteComment(Long memoId, Long commentId, UserDetailsImpl userDetails) {
        Comment comment = findComment(memoId,commentId);

        if(checkUser(comment.getUser().getUsername(), userDetails.getUsername()) || userDetails.getAuthoritie().equals("ROLE_ADMIN")) {
            commentRepository.delete(comment);
            return "{\"msg\":\"댓글 삭제 성공\",\"statusCode\":\"200\"}";
        } else {
            //throw new IllegalArgumentException("댓글의 소유자가 아닙니다.");
            throw new CommentOwnerNotException(
                    messageSource.getMessage(
                            "not.owner.Comment",
                            new Long[]{commentId},
                            "Not Comment Owner",
                            Locale.getDefault()
                    )
            );
        }
    }

    private CommentRequestDto updateUsernameAndMemoIdToCommentRequestDto(CommentRequestDto commentRequestDto, Long memoId, String username) {
        commentRequestDto.setUsername(username);
        commentRequestDto.setMemoId(memoId);

        return commentRequestDto;
    }

    private boolean checkUser(String comment_username, String jwt_username) {
        return comment_username.equals(jwt_username) ? true : false;
    }

    private Comment findComment(Long memoId, Long commentId) {
        /*return commentRepository.findByMemoIdAndCommentId(memoId,commentId).orElseThrow(() ->
                new IllegalArgumentException("해당하는 댓글은 존재하지 않습니다."));*/
        /*return commentRepository.findByMemo_MemoIdAndCommentId(memoId,commentId).orElseThrow(() ->
                new IllegalArgumentException("해당하는 댓글은 존재하지 않습니다."));*/
        return commentRepository.findByMemo_MemoIdAndCommentId(memoId,commentId).orElseThrow(() ->
                new CommentNotFoundException(
                        messageSource.getMessage(
                                "not.found.Comment",
                                new Long[]{commentId},
                                "Not Found Comment",
                                Locale.getDefault()
                        )
                )
        );
    }

    /*
        commentId에 해당하는 댓글에 좋아요 조회
     */
    public ResponseEntity<ApiResponseDto> getlikescomment(Long commentId) {
        Optional<Comment> comment = commentRepository.findById(commentId);

        if(!comment.isPresent()) {
            throw new CommentNotFoundException(
                    messageSource.getMessage(
                            "not.found.Comment",
                            new Long[]{commentId},
                            "Not Found Comment",
                            Locale.getDefault()
                    )
            );
        }

        List<LikeComment> likeCommentlist = likeCommentRepository.findAllByComment_CommentId(commentId);

        List<LikeCommentResponseDto> newlikeCommentlist = likeCommentlist.stream().map(LikeCommentResponseDto::new).toList();

        return ResponseEntity.status(200).body(new ApiResponseDto(HttpStatus.OK.value(), "댓글 좋아요 조회",newlikeCommentlist));
    }

    @Transactional
    public ResponseEntity<ApiResponseDto> likeComment(Long commentId, UserDetailsImpl userDetails) {
        Optional<Comment> comment = commentRepository.findById(commentId);

        if(!comment.isPresent()) {
            throw new CommentNotFoundException(
                    messageSource.getMessage(
                            "not.found.Comment",
                            new Long[]{commentId},
                            "Not Found Comment",
                            Locale.getDefault()
                    )
            );
        }

        Optional<LikeComment> likeComment = likeCommentRepository.findByComment_CommentIdAndUser_UserId(commentId,userDetails.getUser().getUserId());

        if(!likeComment.isPresent()) {
            LikeComment newlikeComment = new LikeComment();

            newlikeComment.setUser(userDetails.getUser());

            newlikeComment.setComment(comment.get());

            likeCommentRepository.save(newlikeComment);

            return ResponseEntity.status(200).body(new ApiResponseDto(HttpStatus.OK.value(), "댓글 좋아요 성공", null));
        } else {
            likeCommentRepository.delete(likeComment.get());
            return ResponseEntity.status(200).body(new ApiResponseDto(HttpStatus.OK.value(), "댓글 좋아요 취소 성공",null));
        }
    }

}
