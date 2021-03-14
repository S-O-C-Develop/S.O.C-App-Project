package com.example.soc.login;
import androidx.versionedparcelable.NonParcelField;

import java.util.HashMap;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Query;
public interface ApiService {
    @POST("sign-in")
    Call<LoginResponse> userLogin(@Body LoginData data);
    @GET("get-account")
    Call<GetLogin> getuser(@Header("X-AUTH-TOKEN") String auth);
}
