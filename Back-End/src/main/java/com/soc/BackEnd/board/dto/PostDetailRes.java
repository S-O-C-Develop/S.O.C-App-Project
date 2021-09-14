package com.soc.backend.board.dto;

import com.soc.backend.board.entity.Post;
import com.soc.backend.config.enums.Status;
import com.soc.backend.utils.LocalDateTimeChanger;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PostDetailRes {

    private Long postId;

    private Status status;

    private Long boardId;

    private Long accountId;

    private String accountNickname;

    private String title;

    private String contents;

    private String firstImageUrl;

    private String secondImageUrl;

    private Long reportCount;

    private Long subjectId;

    private String subjectName;

    private Boolean hasImages;

    private String updatedAt;

    public PostDetailRes(Post post) {
        this.postId = post.getPostId();
        this.status = post.getStatus();
        this.boardId = post.getBoard().getBoardId();
        this.accountId = post.getAccount().getAccountId();
        this.accountNickname = post.getAccount().getNickname();
        this.title = post.getTitle();
        this.contents = post.getContents();
        this.firstImageUrl = post.getFirstImageUrl();
        this.secondImageUrl = post.getSecondImageUrl();
        this.reportCount = post.getReportCount();
        if (post.getSubject() != null) {
            this.subjectId = post.getSubject().getSubjectId();
            this.subjectName = post.getSubject().getName();
        }
        if (firstImageUrl == null && secondImageUrl == null) {
            this.hasImages = false;
        } else this.hasImages = true;
        this.updatedAt = LocalDateTimeChanger.changeTimeByCurrent(post.getUpdatedAt());
    }

}

