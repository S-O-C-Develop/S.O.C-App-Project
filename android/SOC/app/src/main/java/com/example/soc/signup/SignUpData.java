package com.example.soc.signup;
import com.google.gson.annotations.SerializedName;
public class SignUpData {
    String email;
    String nickname;
    String password;
    String studentId;


    public SignUpData(String email, String nickname, String password, String studentId) {
        this.email = email;
        this.nickname = nickname;
        this.password = password;
        this.studentId = studentId;
    }
}


