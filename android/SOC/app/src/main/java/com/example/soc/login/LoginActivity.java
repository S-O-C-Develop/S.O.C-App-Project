package com.example.soc.login;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Message;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.soc.function.BackPressHandler;
import com.example.soc.main.MainActivity;
import com.example.soc.R;
import com.google.gson.Gson;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
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
        SignButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        FindButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), FIndActivity.class);
                startActivity(intent);

            }
        });
        LoginButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
        LoginData data = new LoginData(passwordView.getText().toString(),idView.getText().toString());
        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        Call<LoginResponse> call = apiService.userLogin(data);
                    call.enqueue(new Callback<LoginResponse>() {
                        @Override
                        public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                            if (response.isSuccessful()) {
                                LoginResponse res = response.body();
                                Log.d(TAG, "Response:" + res.getSuccess());
                                tokenManager.createSession(res.getData());
                                SharedPreferences pref = getSharedPreferences("JWTTOKEN", MODE_PRIVATE);
                                Call<GetLogin> call1 = apiService.getuser(pref.getString("data", ""));
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



