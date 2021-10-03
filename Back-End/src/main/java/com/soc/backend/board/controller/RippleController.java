package com.soc.backend.board.controller;

import com.soc.backend.board.dto.CreateParentRippleReq;
import com.soc.backend.board.dto.GetRippleRes;
import com.soc.backend.board.service.RippleService;
import com.soc.backend.config.response.DataResponse;
import com.soc.backend.config.response.ResponseService;
import com.soc.backend.config.security.CustomUserDetails;
import com.soc.backend.utils.ValidationExceptionProvider;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(tags = {"Ripple API"})
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api")
public class RippleController {

    private final RippleService rippleService;
    private final ResponseService responseService;

    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-ACCESS-TOKEN", value = "로그인 성공 후 토큰", required = false, dataTypeClass = String.class, paramType = "header")
    })
    @ApiOperation(value = "부모 댓글 생성 API", notes = "게시글 id와 댓글 내용 필요")
    @PostMapping("/ripples/parent-ripples")
    public DataResponse<Long> createParentRipple(@AuthenticationPrincipal CustomUserDetails customUserDetails,
                                                 @RequestBody @Valid CreateParentRippleReq createParentRippleReq, Errors errors) {
        if (errors.hasErrors()) ValidationExceptionProvider.throwValidError(errors);
        Long rippleId = rippleService.createParentRipple(customUserDetails, createParentRippleReq);
        return responseService.getDataResponse(rippleId);
    }

    @ApiOperation(value = "부모 댓글 조회 API", notes = "게시글 id가 필요")
    @GetMapping("/ripples/{postId}")
    public DataResponse<List<GetRippleRes>> getParentRipplesByPost(@PathVariable(value = "postId")Long postId) {
        List<GetRippleRes> list = rippleService.getParentRipplesByPost(postId);
        return responseService.getDataResponse(list);
    }

}
