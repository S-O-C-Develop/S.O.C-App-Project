package com.soc.BackEnd.api;

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
    private String msg;

    public CommonResponse(){}
    public CommonResponse(String msg){
        this.msg = msg;
    };
    
}
