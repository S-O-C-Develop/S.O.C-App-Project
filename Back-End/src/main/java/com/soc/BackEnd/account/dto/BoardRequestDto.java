package com.soc.BackEnd.account.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@RequiredArgsConstructor
public class BoardRequestDto {
    private final String title;
    private final String content;
    private final Boolean likeCount;
}
