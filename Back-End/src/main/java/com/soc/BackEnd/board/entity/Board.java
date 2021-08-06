package com.soc.backend.board.entity;

import com.soc.backend.account.Account;
import com.soc.backend.board.dto.PostBoardReq;
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

    public static Board createBoard(Account account, PostBoardReq postBoardReq) {
        Board board = Board.builder()
                .account(account)
                .status("Active")
                .category(postBoardReq.getCategory())
                .korName(postBoardReq.getKorName())
                .engName(postBoardReq.getEngName())
                .grade(postBoardReq.getGrade())
                .semester(postBoardReq.getSemester())
                .subject(postBoardReq.getSubject())
                .build();
        return board;
    }

}
