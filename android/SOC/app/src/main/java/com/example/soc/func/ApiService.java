package com.example.soc.func;

import com.example.soc.board.Menu.Comty.Compty_condition_Response;
import com.example.soc.board.Menu.Comty.Compty_problem_Board_Response;
import com.example.soc.board.Menu.Comty.Compty_problem_write_Data;
import com.example.soc.board.Menu.Comty.Compty_problem_write_Response;
import com.example.soc.board.Menu.Comty.Compty_problem_write_condition_Data;
import com.example.soc.login.GetLogin;
import com.example.soc.login.LoginData;
import com.example.soc.login.LoginResponse;
import com.example.soc.signup.SignUpData;
import com.example.soc.signup.SignUpResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface ApiService {
    @POST("sign-in")
    Call<LoginResponse> userLogin(@Body LoginData logindata);
    @GET("get-account")
    Call<GetLogin> getuser(@Header("X-ACCESS-TOKEN") String auth);
    @POST("sign-up")
    Call<SignUpResponse> userSignup(@Body SignUpData signupdata);
    @POST("posts")
    Call<Compty_problem_write_Response> writeinfo(@Header("X-ACCESS-TOKEN") String auth
            , @Body Compty_problem_write_Data problem_Data);
    @POST("boards")
    Call<Compty_condition_Response> Boardinfo(@Header("X-ACCESS-TOKEN") String auth
            , @Body Compty_problem_write_condition_Data Condition_Data);
    @GET("posts/boards/{boardId}")
    Call<Compty_problem_Board_Response> getBoard(@Path("boardId") int id
            ,@Query("page") int page, @Query("sortBy") String postId , @Query("isAsc") Boolean isAsc);

}
