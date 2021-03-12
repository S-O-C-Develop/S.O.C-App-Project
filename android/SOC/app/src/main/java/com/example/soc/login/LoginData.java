package com.example.soc.login;

import com.google.gson.annotations.SerializedName;
    public class LoginData {
        @SerializedName("password")
        String password;
        @SerializedName("studentId")
        String studentId;

        public LoginData(String password, String studentId) {
            this.password = password;
            this.studentId = studentId;
        }
    }

