package com.soc.backend.board.controller;

import com.soc.backend.api.SingleResponse;
import com.soc.backend.board.dto.GetPostsPageRes;
import com.soc.backend.board.service.PostService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Api(tags = {"Post API"})
@RequiredArgsConstructor
@Controller
@RequestMapping(value = "/api")
public class PostController {

    private final PostService postService;

    @GetMapping("/posts/boards/{boardId}")
    public SingleResponse<Page<GetPostsPageRes>> getPostsByBoard(@PageableDefault(size = 10, page = 0) Pageable pageable, @PathVariable(name = "boardId") Long boardId) {
        return postService.getPostsByBoard(pageable, boardId);
    }

}
