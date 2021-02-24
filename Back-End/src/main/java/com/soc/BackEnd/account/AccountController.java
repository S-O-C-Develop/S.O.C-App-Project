package com.soc.BackEnd.account;

import com.soc.BackEnd.account.dto.LoginDto;
import com.soc.BackEnd.account.dto.ResponseAccountDto;
import com.soc.BackEnd.account.dto.SignupDto;
import com.soc.BackEnd.api.CommonResponse;
import com.soc.BackEnd.api.SingleResponse;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = {"Account API"})
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api")
public class AccountController {

    private final AccountService accountService;

    @ApiOperation(value = "회원 가입 API", notes = "학번, 닉네임,비밀번호, 이메일 전송")
    @PostMapping(value = "/sign-up")
    public CommonResponse signUp(@ApiParam(value = "회원가입 요청 객체", required = true) @RequestBody @Valid SignupDto dto, Errors errors){
        return accountService.signUp(dto, errors);
    }

    @ApiOperation(value = "로그인 API",notes = "학번, 비밀번호 전송")
    @PostMapping(value =  "/sign-in")
    public SingleResponse<String> signIn(@ApiParam(value = "로그인 요청 객체",required = true) @RequestBody @Valid LoginDto dto, Errors errors){
        return accountService.signIn(dto, errors);
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 토큰", required = false, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "로그인한 회원 조회", notes = "학번(studentId)으로 회원을 조회한다")
    @GetMapping(value = "/get-account")
    public SingleResponse<ResponseAccountDto> getAccount() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String studentId = authentication.getName();
        return accountService.getAccount(studentId);
    }

}