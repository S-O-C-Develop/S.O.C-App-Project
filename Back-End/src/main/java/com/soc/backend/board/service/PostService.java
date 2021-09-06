package com.soc.backend.board.service;

import com.soc.backend.account.Account;
import com.soc.backend.board.dto.CreatePostReq;
import com.soc.backend.board.dto.GetPostsPageRes;
import com.soc.backend.board.dto.PostDetailRes;
import com.soc.backend.board.entity.Board;
import com.soc.backend.board.entity.Post;
import com.soc.backend.board.repository.BoardRepository;
import com.soc.backend.board.repository.PostRepository;
import com.soc.backend.config.enums.Status;
import com.soc.backend.config.response.exception.CustomException;
import com.soc.backend.config.response.exception.CustomExceptionStatus;
import com.soc.backend.config.security.CustomUserDetails;
import com.soc.backend.subject.Subject;
import com.soc.backend.subject.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.soc.backend.config.enums.Status.*;

@Transactional
@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;
    private final BoardRepository boardRepository;
    private final SubjectRepository subjectRepository;

    @Transactional(readOnly = true)
    public Page<GetPostsPageRes> getPostsByBoard(int page, int size, String sortBy, boolean isAsc, Long boardId)  {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new CustomException(CustomExceptionStatus.NOT_EXIST_BOARD));
        Sort.Direction direction = isAsc ? Sort.Direction.ASC : Sort.Direction.DESC;
        Sort sort = Sort.by(direction, sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);
        return postRepository.findAllByBoard(pageable, board);
    }

    public Long createPostByAccount(CustomUserDetails customUserDetails, CreatePostReq req) {
        Account account = customUserDetails.getAccount();
        Board board = boardRepository.findById(req.getBoardId())
                .orElseThrow(() -> new CustomException(CustomExceptionStatus.NOT_EXIST_BOARD));
        Post save;
        if (req.getSubjectId() != null) {
            Subject subject = subjectRepository.findBySubjectIdAndStatus(req.getSubjectId(), VALID)
                    .orElseThrow(() -> new CustomException(CustomExceptionStatus.NOT_EXIST_SUBJECT));
            save = postRepository.save(Post.createPost(req, board, account, subject));
        }
        else save = postRepository.save(Post.createPost(req, board, account));
        return save.getPostId();
    }


    public Long updatePostByAccount(CustomUserDetails customUserDetails, CreatePostReq req, Long postId) {
        Account account = customUserDetails.getAccount();
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new CustomException(CustomExceptionStatus.NOT_EXIST_POST));
        if (!post.getAccount().equals(account)) throw new CustomException(CustomExceptionStatus.ACCOUNT_NOT_CERTIFICATION);
        post.updatePost(req);
        return post.getPostId();
    }

    @Transactional(readOnly = true)
    public PostDetailRes getPostByPostId(Long postId) {
        PostDetailRes postDetailRes = postRepository.getPostByStatusAndPostId(VALID, postId)
                .orElseThrow(() -> new CustomException(CustomExceptionStatus.NOT_EXIST_POST));
        return postDetailRes;
    }
}
