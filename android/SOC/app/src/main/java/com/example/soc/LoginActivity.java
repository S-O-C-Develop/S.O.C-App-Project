package com.example.soc;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

//Activity를 상속받으면 앱바가 없음
public class LoginActivity extends Activity {


    // UI references.
    private EditText idView;
    private EditText passwordView;
    private BackPressHandler backPressHandler = new BackPressHandler(this);
    InputMethodManager imm;
    LinearLayout ll;
    @Override
    //뒤로가기 버튼 클릭 시 동작
    public void onBackPressed(){
        backPressHandler.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Set up the login form.
        idView = (EditText) findViewById(R.id.id);
        passwordView = (EditText) findViewById(R.id.password);
        imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);

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
        LoginButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        FindButton.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), FIndActivity.class);
                startActivity(intent);

            }
        });

        //화면클릭 시 키보드 숨김
        LinearLayout ll = (LinearLayout)findViewById(R.id.ll);
        ll.setOnClickListener(new OnClickListener(){
            @Override
      public void onClick(View view) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
        });
    }

}