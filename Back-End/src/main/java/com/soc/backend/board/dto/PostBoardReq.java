package com.soc.backend.board.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PostBoardReq {

    @NotBlank
    @Pattern(regexp = "^[0-9a-zA-Z가-힣\\s]{1,20}$")
    String category;

    @NotBlank
    @Pattern(regexp = "^[0-9a-zA-Z가-힣\\s]{1,20}$")
    String korName;

    @Pattern(regexp = "^[\\sa-zA-Z0-9_-]{1,20}$")
    String engName;

    @Min(value = 1)
    @Max(value = 4)
    Integer grade;

    @Min(value = 1)
    @Max(value = 2)
    Integer semester;

    @Pattern(regexp = "^[\\sㄱ-ㅎ가-힣a-zA-Z0-9_-]{3,20}$")
    String subject;
}

