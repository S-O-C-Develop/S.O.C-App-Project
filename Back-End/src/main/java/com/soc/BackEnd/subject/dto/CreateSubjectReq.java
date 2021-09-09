package com.soc.backend.subject.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CreateSubjectReq {

    private Integer grade;

    private Integer semester;

    @NotBlank
    @Length(min=3, max = 20)
    @Pattern(regexp = "^[가-힣a-zA-Z0-9\\s_-]*$")
    private String name;
}
