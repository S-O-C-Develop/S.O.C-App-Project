package com.soc.BackEnd.board.repository;

import com.soc.BackEnd.board.entity.Board;
import com.soc.BackEnd.board.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PostRepository extends JpaRepository<Post, Long> {

    Page<Post> findAllByBoard(Pageable pageable, Board board);
}
