package com.soc.backend.board.repository;

import com.soc.backend.board.dto.GetPostsPageRes;
import com.soc.backend.board.entity.Board;
import com.soc.backend.board.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PostRepository extends JpaRepository<Post, Long> {

    Page<GetPostsPageRes> findAllByBoard(Pageable pageable, Board board);
}
