package com.soc.backend.board.controller;

import com.soc.backend.board.dto.CreateParentRippleReq;
import com.soc.backend.board.service.RippleService;
import com.soc.backend.config.response.DataResponse;
import com.soc.backend.config.response.ResponseService;
import com.soc.backend.config.security.CustomUserDetails;
import com.soc.backend.utils.ValidationExceptionProvider;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = {"Ripple API"})
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api")
public class RippleController {

    private final RippleService rippleService;
    private final ResponseService responseService;

    @PostMapping("/ripples/parent-ripples")
    public DataResponse<Long> createParentRipple(@AuthenticationPrincipal CustomUserDetails customUserDetails,
                                                 @RequestBody @Valid CreateParentRippleReq createParentRippleReq, Errors errors) {
        if (errors.hasErrors()) ValidationExceptionProvider.throwValidError(errors);
        Long rippleId = rippleService.createParentRipple(customUserDetails, createParentRippleReq);
        return responseService.getDataResponse(rippleId);
    }

}
