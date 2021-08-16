package com.soc.backend.board.service;

import com.soc.backend.account.Account;
import com.soc.backend.board.dto.PostBoardReq;
import com.soc.backend.board.entity.Board;
import com.soc.backend.board.repository.BoardRepository;
import com.soc.backend.config.response.exception.CustomException;
import com.soc.backend.config.response.exception.CustomExceptionStatus;
import com.soc.backend.config.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;

    public Long createBoard(CustomUserDetails customUserDetails, PostBoardReq postBoardReq) {
        String targetKorName = postBoardReq.getKorName();
        Optional<Board> optionalBoard = boardRepository.findByKorName(targetKorName);

        Account account = customUserDetails.getAccount();

        if (optionalBoard.isPresent()) throw new CustomException(CustomExceptionStatus.EXIST_BOARD);

        Board board = Board.createBoard(account, postBoardReq);
        Board saveBoard = boardRepository.save(board);

        return saveBoard.getBoardId();

    }
}
