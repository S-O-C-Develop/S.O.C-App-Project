package com.soc.backend.board.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;


@Setter
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CreatePostReq {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long boardId;

    @NotBlank
    @Pattern(regexp = "^[0-9a-zA-Z가-힣\\s]{1,20}$")
    private String title;

    @NotBlank
    private String contents;

    private String firstImageUrl;

    private String secondImageUrl;

    private Long subjectId;

}
