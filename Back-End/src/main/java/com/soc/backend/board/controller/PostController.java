package com.soc.backend.board.controller;

import com.soc.backend.board.dto.CreatePostReq;
import com.soc.backend.board.dto.GetPostsPageRes;
import com.soc.backend.board.service.PostService;
import com.soc.backend.config.response.DataResponse;
import com.soc.backend.config.response.ResponseService;
import com.soc.backend.config.security.CustomUserDetails;
import io.swagger.annotations.*;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = {"Post API"})
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api")
public class PostController {

    private final PostService postService;
    private final ResponseService responseService;

    @ApiOperation(value = "게시글 조회 API", notes ="게시판 번호 전송")
    @GetMapping(value = "/posts/boards/{boardId}")
    public DataResponse<Page<GetPostsPageRes>> getPostsByBoard(@PageableDefault(size = 10, page = 0) Pageable pageable, @ApiParam(value ="게시판 번호",required = true) @PathVariable(name = "boardId") Long boardId) {
        return responseService.getDataResponse(postService.getPostsByBoard(pageable, boardId));
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-ACCESS-TOKEN", value = "로그인 성공 후 토큰", required = false, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "게시글 생성 API", notes = "boardId를 포함한 게시글 정보 전송")
    @PostMapping("/posts")
    public DataResponse<Long> createPostByAccount(@AuthenticationPrincipal CustomUserDetails customUserDetails,
                                                  @ApiParam(value = "게시판 생성 객체", required = true) @Valid @RequestBody CreatePostReq req) {
        Long postId = postService.createPostByAccount(customUserDetails, req);
        return responseService.getDataResponse(postId);
    }


}
