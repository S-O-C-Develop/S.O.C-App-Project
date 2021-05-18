package com.soc.BackEnd.board;

import com.soc.BackEnd.account.dto.BoardRequestDto;
import com.soc.BackEnd.board.model.Board;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = {"Board API"})
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api")
public class BoardController {

    private final BoardRepository boardRepository;
    private final BoardService boardService;

    @ApiOperation(value = "게시물 생성 API", notes = "게시물 생성 객체 전송")
    @PostMapping("/boards")
    public Board createBoards(@RequestBody BoardRequestDto requestDto){
        Board board = new Board(requestDto);
        return boardRepository.save(board);
    }

    @ApiOperation(value = "모든 게시물 조회 API")
    @GetMapping("/boards")
    public List<Board> getBoards(){
        return boardRepository.findAll();
    }

    @ApiOperation(value = "게시물 수정 API", notes = "게시물 Dto, 게시물 번호 전송")
    @PutMapping("/boards/{id}")
    public Long updateBorads(@PathVariable Long id, @RequestBody BoardRequestDto requestDto){
        return boardService.update(id, requestDto);
    }

    @ApiOperation(value = "게시물 삭제 API", notes = "게시물 번호 전송")
    @DeleteMapping("/boards/{id}")
    public Long deleteBoard(@PathVariable Long id){
        boardRepository.deleteById(id);
        return id;
    }
}
