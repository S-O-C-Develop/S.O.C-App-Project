package com.soc.backend.account;

import com.soc.backend.account.dto.LoginDto;
import com.soc.backend.account.dto.PasswordUpdateDto;
import com.soc.backend.account.dto.ResponseAccountDto;
import com.soc.backend.account.dto.SignupDto;
import com.soc.backend.api.CommonResponse;
import com.soc.backend.api.SingleResponse;
import com.soc.backend.config.advice.exception.CustomDynamicExceptionState;
import com.soc.backend.config.advice.exception.CustomDynamicException;
import com.soc.backend.config.security.CustomUserDetails;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.soc.backend.utils.CustomParsing.getErrorString;


@Api(tags = {"Account API"})
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api")
public class AccountController {

    private final AccountService accountService;

    @ApiOperation(value = "회원 가입 API", notes = "학번, 닉네임,비밀번호, 이메일 전송")
    @PostMapping(value = "/sign-up")
    public CommonResponse signUp(@ApiParam(value = "회원가입 요청 객체", required = true) @RequestBody @Valid SignupDto dto, Errors errors){
        if (errors.hasErrors())
            throw new CustomDynamicException((new CustomDynamicExceptionState(4000, getErrorString(errors))));
        return accountService.signUp(dto);
    }

    @ApiOperation(value = "로그인 API",notes = "학번, 비밀번호 전송")
    @PostMapping(value =  "/sign-in")
    public SingleResponse<String> signIn(@ApiParam(value = "로그인 요청 객체",required = true) @RequestBody @Valid LoginDto dto, Errors errors){
        if (errors.hasErrors())
            throw new CustomDynamicException((new CustomDynamicExceptionState(4000, getErrorString(errors))));
        return accountService.signIn(dto);
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
        if (errors.hasErrors())
            throw new CustomDynamicException((new CustomDynamicExceptionState(4000, getErrorString(errors))));
        String studentId = customUserDetails.getUsername();
        return accountService.changePwd(dto, studentId);
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