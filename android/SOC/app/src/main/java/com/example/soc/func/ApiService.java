package com.example.soc.func;

import com.example.soc.login.GetLogin;
import com.example.soc.login.LoginData;
import com.example.soc.login.LoginResponse;
import com.example.soc.signup.SignUpData;
import com.example.soc.signup.SignUpResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiService {
    @POST("sign-in")
    Call<LoginResponse> userLogin(@Body LoginData logindata);
    @GET("get-account")
    Call<GetLogin> getuser(@Header("X-ACCESS-TOKEN") String auth);
    @POST("sign-up")
    Call<SignUpResponse> userSignup(@Body SignUpData signupdata);
}
