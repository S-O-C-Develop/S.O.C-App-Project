package com.soc.BackEnd.board.repository;

import com.soc.BackEnd.board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
}
