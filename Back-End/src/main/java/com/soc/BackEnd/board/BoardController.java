package com.soc.BackEnd.board;

import com.soc.BackEnd.account.dto.BoardRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class BoardController {

    private final BoardRepository boardRepository;
    private final BoardService boardService;

    @PostMapping("api/boards")
    public Board createBoards(@RequestBody BoardRequestDto requestDto){
        Board board = new Board(requestDto);
        return boardRepository.save(board);
    }

    @GetMapping("api/boards")
    public List<Board> getBoards(){
        return boardRepository.findAll();
    }

    @PutMapping("api/boards/{id}")
    public Long updateBorads(@PathVariable Long id, @RequestBody BoardRequestDto requestDto){
        return boardService.update(id, requestDto);
    }

    @DeleteMapping("api/boards/{id}")
    public Long deleteBoard(@PathVariable Long id){
        boardRepository.deleteById(id);
        return id;
    }
}
