package com.soc.backend.account;

import com.soc.backend.account.dto.SignupDto;
import com.soc.backend.config.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Entity
public class Account extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;

    private String status;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String emailToken;

    private boolean isConfirm;

    @Column(nullable = false, unique = true)
    private String nickname;

    @Column(nullable = false)
    private String password;

    @Column(nullable = true, unique = true)
    private String phoneNumber;

    @Column(nullable = false, unique = true)
    private String studentId;

    @Lob
    @Basic(fetch = FetchType.EAGER)
    @Column(nullable = true)
    private String profileImageUrl;

    private String oAuth;

    @Enumerated(EnumType.STRING)
    private RoleType role;

    public void changeConfirm(){
        this.isConfirm = true;
    }

    public void changePassword(String password){
        this.password = password;
    }

    public void changeNickname(String nickname) { this.nickname = nickname; }

    public static Account createAccount(SignupDto dto) {
        return Account.builder()
                .studentId(dto.getStudentId())
                .nickname(dto.getNickname())
                .email(dto.getEmail())
                .role(RoleType.ROLE_USER)
                .isConfirm(false)
                .emailToken(UUID.randomUUID().toString())
                .build();
    }
}
