package com.soc.backend.board.service;

import com.soc.backend.account.Account;
import com.soc.backend.board.dto.CreatePostReq;
import com.soc.backend.board.dto.GetPostsPageRes;
import com.soc.backend.board.entity.Board;
import com.soc.backend.board.entity.Post;
import com.soc.backend.board.repository.BoardRepository;
import com.soc.backend.board.repository.PostRepository;
import com.soc.backend.config.response.exception.CustomException;
import com.soc.backend.config.response.exception.CustomExceptionStatus;
import com.soc.backend.config.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;
    private final BoardRepository boardRepository;

    @Transactional(readOnly = true)
    public Page<GetPostsPageRes> getPostsByBoard(Pageable pageable, Long boardId)  {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new CustomException(CustomExceptionStatus.NOT_EXIST_BOARD));

        return postRepository.findAllByBoard(pageable, board);
    }

    public Long createPostByAccount(CustomUserDetails customUserDetails, CreatePostReq req) {
        Account account = customUserDetails.getAccount();
        Board board = boardRepository.findById(req.getBoardId())
                .orElseThrow(() -> new CustomException(CustomExceptionStatus.NOT_EXIST_BOARD));
        Post save = postRepository.save(Post.createPost(req, board, account));
        return save.getPostId();
    }


    public Long updatePostByAccount(CustomUserDetails customUserDetails, CreatePostReq req, Long postId) {
        Account account = customUserDetails.getAccount();
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new CustomException(CustomExceptionStatus.NOT_EXIST_POST));
        post.updatePost(req);
        return post.getPostId();
    }
}
