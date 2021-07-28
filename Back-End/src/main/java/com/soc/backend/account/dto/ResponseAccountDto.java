package com.soc.backend.account.dto;

import com.soc.backend.account.Account;
import com.soc.backend.account.RoleType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseAccountDto {
    private Long id;
    private String studentId;
    private String nickname;
    private String email;
    private boolean isConfirm;
    private List<RoleType> roles;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public ResponseAccountDto(Account account){
        this.id = account.getAccountId();
        this.studentId = account.getStudentId();
        this.nickname = account.getNickname();
        this.email = account.getEmail();
        this.isConfirm = account.isConfirm();
        this.roles = account.getRoles();
        this.createdDate = account.getCreatedDate();
        this.modifiedDate = account.getModifiedDate();
    }

}
