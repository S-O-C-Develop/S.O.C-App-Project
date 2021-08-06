package com.soc.backend.api;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResponseService {

    public <T> DataResponse<T> getSingleResponse(T data){
        DataResponse<T> res = new DataResponse<>();
        res.setData(data);
        res.setCode(0);
        res.setMessage("Success");
        res.setSuccess(true);
        return res;
    }

    public CommonResponse getSuccessResponse(){
        CommonResponse res = new CommonResponse();
        res.setSuccess(true);
        res.setCode(0);
        res.setMessage("Success");
        return res;
    }

    public CommonResponse getFailResponse(String msg){
        CommonResponse res = new CommonResponse(msg);
        res.setSuccess(false);
        res.setCode(-1);
        res.setMessage(msg);
        return res;
    }

}
