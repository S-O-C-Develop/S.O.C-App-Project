package com.soc.backend.account.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PasswordUpdateDto {

    @NotBlank
    @Length(min = 8, max = 50)
    private String beforePassword;

    @NotBlank
    @Length(min = 8, max = 50)
    private String updatePassword;
}
