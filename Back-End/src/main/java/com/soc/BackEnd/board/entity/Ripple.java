package com.soc.backend.board.entity;

import com.soc.backend.account.Account;
import com.soc.backend.board.dto.CreateParentRippleReq;
import com.soc.backend.config.BaseTimeEntity;
import com.soc.backend.config.enums.Status;
import com.sun.org.apache.xpath.internal.operations.Bool;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static com.soc.backend.config.enums.Status.*;
import static javax.persistence.CascadeType.*;
import static javax.persistence.FetchType.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Entity
public class Ripple extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rippleId;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "accountId")
    private Account account;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "postId")
    private Post post;

    @Column(nullable = false)
    private String contents;

    @Column(nullable = false)
    private Long likeCount;

    @Column(nullable = false)
    private Long reportCount;

    @Column(nullable = false)
    private Boolean isNested;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "parentRipple")
    private Ripple parentRipple;

    @OneToMany(mappedBy = "parentRipple", cascade = ALL)
    private List<Ripple> rippleList = new ArrayList<>();

    public void changeStatus(Status status) {
        this.status = status;
    }

    public Ripple(Account account, Post post, CreateParentRippleReq req) {
        this.status = VALID;
        this.account = account;
        this.post = post;
        this.contents = req.getContents();
        this.likeCount = 0L;
        this.reportCount = 0L;
        this.isNested = false;
    }
}
