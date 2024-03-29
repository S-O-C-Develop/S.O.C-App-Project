package com.soc.backend.account;

import com.soc.backend.account.dto.*;
import com.soc.backend.account.sms.PhoneNumberDto;
import com.soc.backend.account.sms.SmsAuthService;
import com.soc.backend.account.sms.TokenDto;
import com.soc.backend.config.response.CommonResponse;
import com.soc.backend.config.response.DataResponse;
import com.soc.backend.config.response.ResponseService;
import com.soc.backend.config.security.CustomUserDetails;
import com.soc.backend.utils.ValidationExceptionProvider;
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
    private final ResponseService responseService;
    private final SmsAuthService smsAuthService;

    @ApiOperation(value = "회원 가입 API", notes = "학번, 닉네임,비밀번호, 이메일 전송")
    @PostMapping(value = "/sign-up")
    public DataResponse<Long> signUp(@ApiParam(value = "회원가입 요청 객체", required = true) @RequestBody @Valid SignupReq dto, Errors errors){
        if (errors.hasErrors()) ValidationExceptionProvider.throwValidError(errors);
        return responseService.getDataResponse(accountService.signUp(dto));
    }

    @ApiOperation(value = "로그인 API",notes = "학번, 비밀번호 전송")
    @PostMapping(value =  "/sign-in")
    public DataResponse<SignInRes> signIn(@ApiParam(value = "로그인 요청 객체",required = true) @RequestBody @Valid LoginReq dto, Errors errors){
        if (errors.hasErrors()) ValidationExceptionProvider.throwValidError(errors);
        return responseService.getDataResponse(accountService.signIn(dto));
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-ACCESS-TOKEN", value = "로그인 성공 후 토큰", required = false, dataTypeClass = String.class, paramType = "header")
    })
    @ApiOperation(value = "로그인한 회원 조회", notes = "JWT토큰으로 조회한다.")
    @GetMapping(value = "/get-account")
    public DataResponse<ResponseAccountRes> getAccount(@AuthenticationPrincipal CustomUserDetails customUserDetails) {
        return responseService.getDataResponse(accountService.getAccount(customUserDetails));
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-ACCESS-TOKEN", value = "로그인 성공 후 토큰", required = false, dataTypeClass = String.class, paramType = "header")
    })
    @ApiOperation(value = "비밀번호 변경 API", notes ="이전 비밀번호, 새로운 비밀번호 전송")
    @PutMapping(value = "/account/password")
    public CommonResponse changePwd(@ApiParam(value = "비밀번호 변경 객체", required = true) @RequestBody @Valid PasswordUpdateReq dto, Errors errors,
                                    @AuthenticationPrincipal CustomUserDetails customUserDetails){
        if (errors.hasErrors()) ValidationExceptionProvider.throwValidError(errors);
        return responseService.getSuccessResponse();
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-ACCESS-TOKEN", value = "로그인 성공 후 토큰", required = false, dataTypeClass = String.class, paramType = "header")
    })
    @ApiOperation(value = "닉네임 변경 API", notes ="새로운 닉네임 전송")
    @PutMapping(value = "/account-update")
    public CommonResponse changeNickname(@ApiParam(value = "변경 될 닉네임", required = true) @RequestParam String updateNickname,
                                               @AuthenticationPrincipal CustomUserDetails customUserDetails){
        accountService.changeNickname(updateNickname, customUserDetails);
        return responseService.getSuccessResponse();
    }

    @PatchMapping(value = "/accounts/sms-token")
    public DataResponse<Integer> updateAccountSmsToken(@AuthenticationPrincipal CustomUserDetails customUserDetails,
                                                       @RequestBody PhoneNumberDto phoneNumberDto) {
        Integer token = smsAuthService.updateAccountSmsToken(customUserDetails, phoneNumberDto.getPhoneNumber());
        return responseService.getDataResponse(token);
    }

    @PatchMapping(value = "/accounts/sms-certification")
    public CommonResponse updateAccountSmsCertification(@AuthenticationPrincipal CustomUserDetails customUserDetails,
                                                        @RequestBody TokenDto tokenDto) {
        smsAuthService.updateAccountSmsCertification(customUserDetails, tokenDto.getSmsToken());
        return responseService.getSuccessResponse();
    }


}