package com.soc.backend.board.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.soc.backend.board.entity.Post;
import com.soc.backend.config.enums.Status;
import com.soc.backend.utils.LocalDateTimeChanger;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GetPostsPageRes {

    private Long postId;

    private Status status;

    private String title;

    private String contents;

    private String author;

    private Long reportCount;

    private Boolean hasImages;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String firstImageUrl;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String secondImageUrl;

    private String updatedAt;

    public GetPostsPageRes(Post post) {
        this.postId = post.getPostId();
        this.status = post.getStatus();
        this.title = post.getTitle();
        this.contents = post.getContents();
        this.author = post.getAccount().getNickname();
        reportCount = post.getReportCount();
        if (post.getFirstImageUrl() == null && post.getSecondImageUrl() == null) {
            this.hasImages = false;
        } else this.hasImages = true;
        this.updatedAt = LocalDateTimeChanger.changeTimeByCurrent(post.getUpdatedAt());
    }

}
