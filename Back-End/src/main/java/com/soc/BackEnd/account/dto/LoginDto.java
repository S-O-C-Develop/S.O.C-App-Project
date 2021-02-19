package com.soc.BackEnd.account.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginDto {
    @NotBlank
    @Length(min=9,max=9)
    @Pattern(regexp = "^[0-9]{3,20}$")
    private String studentId;

    @NotBlank
    @Length(min=8, max= 50)
    private String password;

}
