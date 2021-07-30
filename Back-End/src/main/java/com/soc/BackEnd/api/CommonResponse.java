package com.soc.backend.api;

import com.soc.backend.config.advice.exception.CustomDynamicExceptionState;
import com.soc.backend.config.advice.exception.CustomExceptionStatus;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommonResponse {

    @ApiModelProperty(value = "응답 성공 여부 : 0 -> 성공, -1 -> 실패")
    private boolean isSuccess;

    @ApiModelProperty(value = "응답 코드 번호")
    private int code;

    @ApiModelProperty(value = "응답 메세지")
    private String message;

    public CommonResponse(){}
    public CommonResponse(String msg){
        this.message = msg;
    };
    public CommonResponse(CustomExceptionStatus customExceptionStatus){
        this.isSuccess = customExceptionStatus.isSuccess();
        this.code = customExceptionStatus.getCode();
        this.message = customExceptionStatus.getMessage();
    }
    public CommonResponse(CustomDynamicExceptionState customDynamicExceptionState){
        this.isSuccess = customDynamicExceptionState.isSuccess();
        this.code = customDynamicExceptionState.getCode();
        this.message = customDynamicExceptionState.getMessage();
    }
    
}
