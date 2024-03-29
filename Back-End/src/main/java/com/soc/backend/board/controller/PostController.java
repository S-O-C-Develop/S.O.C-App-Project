package com.soc.backend.board.controller;

import com.soc.backend.board.dto.CreatePostReq;
import com.soc.backend.board.dto.GetPostsPageRes;
import com.soc.backend.board.dto.PostDetailRes;
import com.soc.backend.board.service.PostService;
import com.soc.backend.config.response.DataResponse;
import com.soc.backend.config.response.ResponseService;
import com.soc.backend.config.security.CustomUserDetails;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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

    @ApiOperation(value = "게시글 조회 API",  notes = "page, size, sortBy, isAsc RequestParam 설정")
    @GetMapping(value = "/posts/boards/{boardId}")
    public DataResponse<Page<GetPostsPageRes>> getPostsByBoard(@ApiParam(value ="게시판 번호",required = true) @PathVariable(name = "boardId") Long boardId,
                                                               @RequestParam(name = "page", required = false) Integer page,
                                                               @RequestParam(name = "size", required = false) Integer size,
                                                               @RequestParam(name = "sortBy", required = false) String sortBy,
                                                               @RequestParam(name = "isAsc", required = false) Boolean isAsc
                                                               ) {
        if (page == null) page = 1;
        page = page - 1;
        if (size == null) size = 10;
        if (isAsc == null) isAsc = true;
        if (sortBy == null) sortBy = "updatedAt";
        return responseService.getDataResponse(postService.getPostsByBoard(page, size, sortBy, isAsc,boardId));
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-ACCESS-TOKEN", value = "로그인 성공 후 토큰", required = false, dataTypeClass = String.class, paramType = "header")
    })
    @ApiOperation(value = "게시글 생성 API", notes = "boardId를 포함한 게시글 정보 전송")
    @PostMapping("/posts")
    public DataResponse<Long> createPostByAccount(@AuthenticationPrincipal CustomUserDetails customUserDetails,
                                                  @ApiParam(value = "게시판 생성 객체", required = true) @Valid @RequestBody CreatePostReq req) {
        Long postId = postService.createPostByAccount(customUserDetails, req);
        return responseService.getDataResponse(postId);
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-ACCESS-TOKEN", value = "로그인 성공 후 토큰", required = false, dataTypeClass = String.class, paramType = "header")
    })
    @ApiOperation(value = "게시글 수정 API", notes = "게시글 작성자가 게시글 수정")
    @PatchMapping("/posts/{postId}")
    public DataResponse<Long> updatePostByAccount(@AuthenticationPrincipal CustomUserDetails customUserDetails,
                                                  @ApiParam(value = "게시판 수정 객체", required = true) @Valid @RequestBody CreatePostReq req, @PathVariable(name = "postId") Long postId) {
        Long updatedPostId = postService.updatePostByAccount(customUserDetails, req, postId);
        return responseService.getDataResponse(updatedPostId);
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-ACCESS-TOKEN", value = "로그인 성공 후 토큰", required = false, dataTypeClass = String.class, paramType = "header")
    })
    @ApiOperation(value = "한 게시글 조회 API", notes = "하나의 게시글 Detail 조회 API")
    @GetMapping("/posts/{postId}")
    public DataResponse<PostDetailRes> getPostByPostId(@PathVariable(name = "postId") Long postId) {
        PostDetailRes postDetailRes = postService.getPostByPostId(postId);
        return responseService.getDataResponse(postDetailRes);
    }


}
