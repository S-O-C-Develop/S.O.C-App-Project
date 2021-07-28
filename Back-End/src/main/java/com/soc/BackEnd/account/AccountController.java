package com.soc.BackEnd.account;

import com.soc.BackEnd.account.dto.LoginDto;
import com.soc.BackEnd.account.dto.PasswordUpdateDto;
import com.soc.BackEnd.account.dto.ResponseAccountDto;
import com.soc.BackEnd.account.dto.SignupDto;
import com.soc.BackEnd.api.CommonResponse;
import com.soc.BackEnd.api.SingleResponse;
import com.soc.BackEnd.config.security.CustomUserDetails;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    public SingleResponse<ResponseAccountDto> getAccount(@AuthenticationPrincipal CustomUserDetails customUserDetails) {
        String studentId = customUserDetails.getUsername();
        return accountService.getAccount(studentId);
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 토큰", required = false, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "비밀번호 변경 API", notes ="이전 비밀번호, 새로운 비밀번호 전송")
    @PutMapping(value = "/account/password")
    public SingleResponse<String> changePwd(@ApiParam(value = "비밀번호 변경 객체", required = true) @RequestBody @Valid PasswordUpdateDto dto, Errors errors,
                                            @AuthenticationPrincipal CustomUserDetails customUserDetails){
        String studentId = customUserDetails.getUsername();
        return accountService.changePwd(dto, errors, studentId);
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 토큰", required = false, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "닉네임 변경 API", notes ="새로운 닉네임 전송")
    @PutMapping(value = "/account-update")
    public SingleResponse<String> changeNickname(@ApiParam(value = "변경 될 닉네임", required = true) @RequestParam String updateNickname,
                                                 @AuthenticationPrincipal CustomUserDetails customUserDetails){
        String studentId = customUserDetails.getUsername();
        return accountService.changeNickname(updateNickname, studentId);
    }

}