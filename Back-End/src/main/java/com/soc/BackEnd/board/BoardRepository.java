package com.soc.BackEnd.board;

import com.soc.BackEnd.board.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
}