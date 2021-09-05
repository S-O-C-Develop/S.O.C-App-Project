package com.example.soc.board.Menu.Comty;

import com.google.gson.annotations.SerializedName;

public class Compty_condition_Response {
    @SerializedName("result")
    private int result;
    @SerializedName("message")
    private String message;
    @SerializedName("isSuccess")
    private boolean issuccess;
    @SerializedName("code")
    private int code;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean getIssuccess() {
        return issuccess;
    }

    public void setIssuccess(boolean issuccess) {
        this.issuccess = issuccess;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
