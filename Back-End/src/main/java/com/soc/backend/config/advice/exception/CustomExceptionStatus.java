package com.soc.backend.config.advice.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CustomExceptionStatus {

    // 2000 Not Found
    ACCOUNT_NOT_FOUND(false, 2000, "사용자를 찾을 수 없습니다."),
    BOARD_NOT_FOUND(false, 2001, "존재하지 않는 게시판입니다."),

    // 3000 중복
    STUDENT_ID_DUPLICATE(false, 3000, "중복되는 학번입니다."),
    EMAIL_DUPLICATION(false, 3001, "중복되는 이메일입니다."),
    NICKNAME_DUPLICATION(false, 3002, "중복되는 닉네임입니다."),
    NICKNAME_DUPLICATION_SELF(false, 3003, "원래의 닉네임과 중복됩니다."),
    BOARD_DUPLICATION(false, 3004, "중복된 게시판 이름입니다."),

    // 4000 형식적 Validation
    NICKNAME_VALIDATION_ERROR(false, 4001, "잘못된 닉네임입니다."),

    // 5000 인증
    PASSWORD_NOT_CORRECT(false, 5000, "비밀번호가 틀렸습니다.");



    private final boolean isSuccess;
    private final int code;
    private final String message;

}
