package com.example.soc.login;

import com.google.gson.annotations.SerializedName;

public class GetLogin {

    @SerializedName("result")
    private Result result;
    @SerializedName("message")
    private String message;
    @SerializedName("code")
    private int code;
    @SerializedName("isSuccess")
    private boolean issuccess;

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
        @SerializedName("confirm")
        private boolean confirm;
        @SerializedName("createdDate")
        private String createddate;
        @SerializedName("roles")
        private String roles;
        @SerializedName("email")
        private String email;
        @SerializedName("nickname")
        private String nickname;
        @SerializedName("studentId")
        private String studentid;
        @SerializedName("id")
        private int id;

        public boolean getConfirm() {
            return confirm;
        }

        public void setConfirm(boolean confirm) {
            this.confirm = confirm;
        }

        public String getCreateddate() {
            return createddate;
        }

        public void setCreateddate(String createddate) {
            this.createddate = createddate;
        }

        public String getRoles() {
            return roles;
        }

        public void setRoles(String roles) {
            this.roles = roles;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getStudentid() {
            return studentid;
        }

        public void setStudentid(String studentid) {
            this.studentid = studentid;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}