package com.soc.backend.subject;

import com.soc.backend.config.response.DataResponse;
import com.soc.backend.config.response.ResponseService;
import com.soc.backend.subject.dto.CreateSubjectReq;
import com.soc.backend.subject.dto.GetSubjectRes;
import com.soc.backend.utils.ValidationExceptionProvider;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = {"Subject API"})
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api")
public class SubjectController {

    private final SubjectService subjectService;
    private final ResponseService responseService;

    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-ACCESS-TOKEN", value = "로그인 성공 후 토큰", required = false, dataTypeClass = String.class, paramType = "header")
    })
    @ApiOperation(value = "과목 생성 API", notes ="과목 정보 전송")
    @PostMapping(value = "/subjects")
    public DataResponse<Long> createSubject(@RequestBody @Valid CreateSubjectReq createSubjectReq, Errors errors) {
        if (errors.hasErrors()) ValidationExceptionProvider.throwValidError(errors);
        Long subjectId = subjectService.createSubject(createSubjectReq);
        return responseService.getDataResponse(subjectId);
    }

    @ApiOperation(value = "과목 조회 API", notes ="과목 정보 전송")
    @GetMapping(value = "/subjects/{subjectId}")
    public DataResponse<GetSubjectRes> getSubject(@PathVariable(name = "subjectId") Long subjectId) {
        GetSubjectRes subjectRes = subjectService.getSubject(subjectId);
        return responseService.getDataResponse(subjectRes);
    }

}
