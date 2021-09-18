package com.soc.backend.board.entity;

import com.soc.backend.account.Account;
import com.soc.backend.config.BaseTimeEntity;
import com.soc.backend.config.enums.Status;
import lombok.Getter;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Getter
@Entity
public class AccountRippleRelation extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long relationId;

    @Enumerated(EnumType.STRING)
    private Status status;

    private Boolean isReport;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "accountId")
    private Account account;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "rippleId")
    private Ripple ripple;

}
