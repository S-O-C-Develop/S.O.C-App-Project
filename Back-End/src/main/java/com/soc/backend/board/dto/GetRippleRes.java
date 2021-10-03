package com.soc.backend.board.dto;

import com.soc.backend.board.entity.Ripple;
import com.soc.backend.config.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GetRippleRes {


    private Long rippleId;

    private Status status;

    private Long accountId;

    private String accountNickName;

    private Long postId;

    private String postTitle;

    private String contents;

    private Long likeCount;

    private Long reportCount;

    private Integer nestedRippleCount;

    public GetRippleRes(Ripple ripple) {
        this.rippleId = ripple.getRippleId();
        this.status = ripple.getStatus();
        this.accountId = ripple.getAccount().getAccountId();
        this.accountNickName = ripple.getAccount().getNickname();
        this.postId = ripple.getPost().getPostId();
        this.postTitle = ripple.getPost().getTitle();
        this.contents = ripple.getContents();
        this.likeCount = ripple.getLikeCount();
        this.reportCount = ripple.getReportCount();
        this.nestedRippleCount = ripple.getRippleList().size();
    }

}
