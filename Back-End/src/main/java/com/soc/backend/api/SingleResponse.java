package com.soc.backend.api;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SingleResponse<T> extends CommonResponse {
    @ApiModelProperty(value = "응답 Data")
    private T data;
}
