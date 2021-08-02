package com.soc.backend.board.entity;

import com.soc.backend.account.Account;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "boardId")
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "accountId")
    private Account account;

    private String author;

    @Column(nullable = false)
    private String title;

    @Lob
    @Basic(fetch = FetchType.EAGER)
    @Column(nullable = false)
    private String contents;

    @Lob
    @Basic(fetch = FetchType.EAGER)
    private String firstImageUrl;

    @Lob
    @Basic(fetch = FetchType.EAGER)
    private String secondImageUrl;

    private Long reportCount = 0L;

}
