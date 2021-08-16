package com.soc.backend.board.repository;

import com.soc.backend.board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long> {

    Optional<Board> findByKorName(String korName);
}
