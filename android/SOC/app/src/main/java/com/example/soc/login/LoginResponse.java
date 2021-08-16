package com.example.soc.login;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {


    @SerializedName("result")
    private Result result;
    @SerializedName("message")
    private String message;
    @SerializedName("code")
    private int code;
    @SerializedName("isSuccess")
    private boolean issuccess;
    public LoginResponse() {}

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public boolean getIssuccess() {
        return issuccess;
    }

    public void setIssuccess(boolean issuccess) {
        this.issuccess = issuccess;
    }

    public static class Result {
        @SerializedName("jwt")
        private String jwt;
        @SerializedName("accountId")
        private int accountid;

        public String getJwt() {
            return jwt;
        }

        public void setJwt(String jwt) {
            this.jwt = jwt;
        }

        public int getAccountid() {
            return accountid;
        }

        public void setAccountid(int accountid) {
            this.accountid = accountid;
        }
    }
}
