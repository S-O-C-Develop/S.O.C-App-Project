package com.soc.backend.board.repository;

import com.soc.backend.board.dto.GetPostsPageRes;
import com.soc.backend.board.dto.PostDetailRes;
import com.soc.backend.board.entity.Board;
import com.soc.backend.board.entity.Post;
import com.soc.backend.config.enums.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


public interface PostRepository extends JpaRepository<Post, Long> {

    Page<GetPostsPageRes> findAllByBoard(Pageable pageable, Board board);

    @Query("SELECT p FROM Post p " +
            "LEFT JOIN FETCH  p.subject JOIN FETCH p.account JOIN FETCH p.board " +
            "WHERE p.status = :status AND p.postId = :postId")
    Optional<PostDetailRes> getPostByStatusAndPostId(@Param("status") Status status, @Param("postId") Long postId);
}
