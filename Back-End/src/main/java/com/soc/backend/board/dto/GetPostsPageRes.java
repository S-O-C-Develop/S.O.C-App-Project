package com.soc.backend.board.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GetPostsPageRes {

    private Long postId;

    private String status;

    private String title;

    private String contents;

    private String author;

}
