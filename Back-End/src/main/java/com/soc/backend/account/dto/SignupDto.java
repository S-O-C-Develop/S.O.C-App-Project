package com.soc.backend.account.dto;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SignupDto {
    @NotBlank
    @Length(min=9,max=9)
    @Pattern(regexp = "^[0-9]{3,20}$")
    private String studentId;

    @NotBlank
    @Length(min=3, max = 20)
    @Pattern(regexp = "^[ㄱ-ㅎ가-힣a-zA-Z0-9_-]{3,20}$")
    private String nickname;

    @NotBlank
    @Length(min=8, max= 50)
    private String password;

    @Email
    @NotBlank
    private String email;
}
