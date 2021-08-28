package com.soc.backend.board.entity;

import com.soc.backend.account.Account;
import com.soc.backend.config.BaseTimeEntity;
import com.soc.backend.config.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Entity
public class Ripple extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rippleId;

    private Status status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "accountId")
    private Account account;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "postId")
    private Post post;

    @Column(nullable = false)
    private String contents;

    @Column(nullable = false)
    private Long likeCount;

    @Column(nullable = false)
    private Boolean isNested;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parentRippleId")
    private Ripple ripple;

    @OneToMany(mappedBy = "ripple")
    private List<Ripple> rippleList = new ArrayList<>();
}
