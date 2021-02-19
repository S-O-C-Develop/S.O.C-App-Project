package com.soc.BackEnd.api;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResponseService {

    public <T> SingleResponse<T> getSingleResponse(T data){
        SingleResponse<T> res = new SingleResponse<>();
        res.setData(data);
        res.setCode(0);
        res.setMsg("Success");
        res.setSuccess(true);
        return res;
    }

    public <T> ListResponse<T> getListResponse(List<T> list){
        ListResponse<T> res = new ListResponse<>();
        res.setList(list);
        res.setCode(0);
        res.setMsg("Success");
        res.setSuccess(true);
        return res;
    }

    public CommonResponse getSuccessResponse(){
        CommonResponse res = new CommonResponse();
        res.setSuccess(true);
        res.setCode(0);
        res.setMsg("Success");
        return res;
    }

    public CommonResponse getFailResponse(String msg){
        CommonResponse res = new CommonResponse(msg);
        res.setSuccess(false);
        res.setCode(-1);
        res.setMsg(msg);
        return res;
    }

}
