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
public class

Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardId;

    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "accountId")
    private Account account;

    @Column(nullable = false)
    String category;

    @Column(nullable = false, unique = true)
    String korName;

    @Column(nullable  = true, unique = true)
    String engName;

    Integer grade;

    Integer semester;

    String subject;

}
