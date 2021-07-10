package com.example.soc.login;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.soc.find.FindActivity;
import com.example.soc.func.ApiClient;
import com.example.soc.func.ApiService;
import com.example.soc.func.BackPressHandler;
import com.example.soc.main.LoadingActivity;
import com.example.soc.main.MainActivity;
import com.example.soc.R;
import com.example.soc.signup.SignUpActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

//Activity를 상속받으면 앱바가 없음
public class LoginActivity extends Activity {

    // UI references.
    private EditText idView;
    private EditText passwordView;
    private BackPressHandler backPressHandler = new BackPressHandler(this);
    InputMethodManager imm;
    LinearLayout ll;
    private TokenManager tokenManager;
    @Override
    //뒤로가기 버튼 클릭 시 동작
    public void onBackPressed() {
        backPressHandler.onBackPressed();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Set up the login form.
        idView = (EditText) findViewById(R.id.id);
        passwordView = (EditText) findViewById(R.id.password);
        imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        tokenManager = new TokenManager(getApplicationContext());

        //로그인,회원가입,비밀번호 찾기 클릭 이벤트처리
        Button LoginButton = (Button) findViewById(R.id.login);
        Button SignButton = (Button) findViewById(R.id.sign);
        Button FindButton = (Button) findViewById(R.id.find);


        SignButton.setOnClickListener(new OnClickListener() { //회원가입버튼 눌렀을 떄의 동작

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
                startActivity(intent);
            }
        });

        FindButton.setOnClickListener(new OnClickListener() { //비밀번호찾기버튼 눌렀을 때의 동작
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), FindActivity.class);
                startActivity(intent);
            }
        });
        LoginButton.setOnClickListener(new OnClickListener() { //로그인버튼 눌렀을 때의 동작
            @Override
            public void onClick(View view) {
        LoginData data = new LoginData(passwordView.getText().toString(),idView.getText().toString());
        // LoginData의 data객체를 생성해서 password, studentId 정보를 준비해둔다.
        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        // APiservice의 apiService를 정의해 자동으로 Json에서 Gson으로 변환할 수 있게 함.
        Call<LoginResponse> call = apiService.userLogin(data);
                    call.enqueue(new Callback<LoginResponse>() {
                        @Override
                        public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                            if (response.isSuccessful()) {
                                LoginResponse res = response.body();
                                Log.e(TAG, "Response:" + res.getSuccess());
                                tokenManager.createSession(res.getData()); //Loginresponse의 getData함수를 이용해 토큰을 저장
                                SharedPreferences pref = getSharedPreferences("PREF", MODE_PRIVATE);
                                Call<GetLogin> call1 = apiService.getuser(pref.getString("data", ""));
                                call1.enqueue(new Callback<GetLogin>() {
                                                  @Override
                                                  public void onResponse(Call<GetLogin> call, Response<GetLogin> response) {
                                                      GetLogin res = response.body();
                                                        tokenManager.autoLogin(res.getMessage());
                                                        Log.d(TAG, res.getMessage());
                                                  }
                                                  @Override
                                                  public void onFailure(Call<GetLogin> call, Throwable t) {

                                                  }
                                              });
                                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(i);
                                finish();
                            } else{
                                showDialog();
                            }
                        }

                        @Override
                        public void onFailure(Call<LoginResponse> call, Throwable t) {
                            Log.d(TAG, "Response:");
                        }
                    });
                }
        });
        //화면클릭 시 키보드 숨김
        LinearLayout ll = (LinearLayout) findViewById(R.id.ll);
        ll.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
            });
    }
    public void showDialog() {
        AlertDialog.Builder msgBuilder = new AlertDialog.Builder(LoginActivity.this)
                .setTitle("알림")
                .setMessage("로그인 정보가 일치하지 않습니다.")
                .setPositiveButton("확인",null);

        AlertDialog msgDlg = msgBuilder.create();
        msgDlg.show();
    }
}



