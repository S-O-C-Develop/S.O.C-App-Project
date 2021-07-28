package com.soc.backend.api;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ListResponse<T> extends CommonResponse {
    @ApiModelProperty(value = "응답 Data 리스트")
    private List<T> list;
}
