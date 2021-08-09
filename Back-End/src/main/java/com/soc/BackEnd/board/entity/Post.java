package com.soc.backend.board.entity;

import com.soc.backend.account.Account;
import com.soc.backend.board.dto.CreatePostReq;
import com.soc.backend.config.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static com.soc.backend.config.enums.Status.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Entity
public class Post {

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

}
