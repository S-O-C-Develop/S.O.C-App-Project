package com.soc.backend.board.entity;

import com.soc.backend.account.Account;
import com.soc.backend.board.dto.CreatePostReq;
import com.soc.backend.config.BaseTimeEntity;
import com.soc.backend.config.enums.Status;
import com.soc.backend.subject.Subject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static com.soc.backend.config.enums.Status.*;
import static javax.persistence.FetchType.LAZY;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Entity
public class Post extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "boardId")
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "accountId")
    private Account account;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String contents;

    private String firstImageUrl;

    private String secondImageUrl;

    private Long reportCount;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "subjectId")
    private Subject subject;

    public static Post createPost(CreatePostReq req, Board board, Account account) {
        return Post.builder()
                .board(board)
                .account(account)
                .status(VALID)
                .title(req.getTitle())
                .contents(req.getContents())
                .firstImageUrl(req.getFirstImageUrl())
                .secondImageUrl(req.getSecondImageUrl())
                .reportCount(0L)
                .build();
    }

    public static Post createPost(CreatePostReq req, Board board, Account account, Subject subject) {
        return Post.builder()
                .board(board)
                .account(account)
                .status(VALID)
                .title(req.getTitle())
                .contents(req.getContents())
                .firstImageUrl(req.getFirstImageUrl())
                .secondImageUrl(req.getSecondImageUrl())
                .reportCount(0L)
                .subject(subject)
                .build();
    }

    public void updatePost(CreatePostReq req) {
        this.title = req.getTitle();
        this.contents = req.getContents();
        this.firstImageUrl = req.getFirstImageUrl();
        this.secondImageUrl = req.getSecondImageUrl();
    }

}
