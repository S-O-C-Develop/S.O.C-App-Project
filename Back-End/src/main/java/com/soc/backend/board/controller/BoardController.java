package com.soc.backend.board.controller;

import com.soc.backend.board.dto.PostBoardReq;
import com.soc.backend.board.service.BoardService;
import com.soc.backend.config.response.DataResponse;
import com.soc.backend.config.response.ResponseService;
import com.soc.backend.config.security.CustomUserDetails;
import com.soc.backend.utils.ValidationExceptionProvider;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Api(tags = {"Board API"})
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api")
public class BoardController {

    private final BoardService boardService;
    private final ResponseService responseService;

    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-ACCESS-TOKEN", value = "로그인 성공 후 토큰", required = false, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "게시판 생성 API", notes ="게시판 정보 전송")
    @PostMapping(value = "/boards")
    public DataResponse<Long> createBoard(@ApiParam(hidden = true) @AuthenticationPrincipal CustomUserDetails customUserDetails,
                                          @RequestBody @Valid PostBoardReq postBoardReq, @ApiParam(hidden = true) Errors errors) {
        if (errors.hasErrors()) ValidationExceptionProvider.throwValidError(errors);

        return responseService.getDataResponse(boardService.createBoard(customUserDetails, postBoardReq));
    }
}
