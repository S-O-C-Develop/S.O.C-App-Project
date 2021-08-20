package com.soc.backend.account.dto;

import com.soc.backend.account.Account;
import com.soc.backend.account.RoleType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseAccountRes {
    private Long id;
    private String studentId;
    private String nickname;
    private String email;
    private RoleType roles;
    private LocalDateTime createdDate;

    public ResponseAccountRes(Account account){
        this.id = account.getAccountId();
        this.studentId = account.getStudentId();
        this.nickname = account.getNickname();
        this.email = account.getEmail();
        this.roles = account.getRole();
        this.createdDate = account.getCreatedDate();
    }

}
