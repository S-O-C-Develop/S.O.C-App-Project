package com.soc.BackEnd.board;

import com.soc.BackEnd.account.dto.BoardRequestDto;
import com.soc.BackEnd.config.BaseTimeEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor // 기본생성자를 대신 생성해줍니다.
@Entity // 테이블임을 나타냅니다.
public class Board extends BaseTimeEntity {
    @Id // ID 값, Primary Key로 사용하겠다는 뜻입니다.
    @GeneratedValue(strategy = GenerationType.AUTO) // 자동 증가 명령입니다.
    private Long id;
    @Column(nullable = false) // 컬럼 값이고 반드시 값이 존재해야 함을 나타냅니다.
    private String title;
    @Column(nullable = false)
    private String content;
    @Column
    private Boolean likeCount;


    public Board(BoardRequestDto requestDto){
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        this.likeCount = requestDto.getLikeCount();
    }

    public Board(String title, String content, Boolean likeCount) {
        this.title = title;
        this.content = content;
        this.likeCount = likeCount;
    }

    public void update(BoardRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        this.likeCount = requestDto.getLikeCount();
    }
}