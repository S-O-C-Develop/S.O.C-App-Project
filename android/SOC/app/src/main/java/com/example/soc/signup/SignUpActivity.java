package com.example.soc.signup;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.soc.R;
import com.example.soc.find.FindActivity;
import com.example.soc.function.ApiClient;
import com.example.soc.function.ApiService;
import com.example.soc.login.LoginActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

public class SignUpActivity extends Activity {
    Button btnPrevious, btnNext, btnDoubleCheck;
    EditText schoolNum, Upassword, passwordConf, Uemail, id;
    TextView discord, passwordLen, scNumLen, idLen;
    LinearLayout ll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        btnPrevious = (Button) findViewById(R.id.btn_previous);
        btnDoubleCheck = (Button) findViewById(R.id.btn_doublecheck);
        btnNext = (Button) findViewById(R.id.btn_next);
        schoolNum = (EditText) findViewById(R.id.et_schoolnumber);
        Upassword = (EditText) findViewById(R.id.et_password);
        passwordConf = (EditText) findViewById(R.id.et_passwordconfirm);
        Uemail = (EditText) findViewById(R.id.et_email);
        id = (EditText) findViewById(R.id.et_id);
        discord = (TextView) findViewById(R.id.tv_discord);
        passwordLen = (TextView) findViewById(R.id.tv_passwordlength);
        scNumLen = (TextView) findViewById(R.id.tv_numberLen);
        idLen = (TextView) findViewById(R.id.tv_idLen);
        ll = (LinearLayout) findViewById(R.id.ll);
        ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        });

        //학번이 올바른지 확인
        schoolNum.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (schoolNum.getText().toString().length() < 9) {
                    scNumLen.setText("학번이 아닙니다.");
                } else {
                    scNumLen.setText(" ");
                }
                if (TextUtils.isEmpty(schoolNum.getText())) {
                    scNumLen.setText("");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        //비밀번호와 비밀번호 확인란이 다를 시 일치하지 않음 표시
        passwordConf.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (Upassword.getText().toString().equals(passwordConf.getText().toString())) {
                    discord.setText(" ");
                } else {
                    discord.setText("비밀번호가 일치하지 않습니다.");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        //비밀번호 확인과 비밀번호가 다를 시 일치하지 않음 표시, 3자 이상 입력하시오 표시
        Upassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (passwordConf.getText().toString().equals(Upassword.getText().toString())) {
                    discord.setText(" ");
                } else {
                    discord.setText("비밀번호가 일치하지 않습니다.");
                }
                if (TextUtils.isEmpty(Upassword.getText()) || TextUtils.isEmpty(passwordConf.getText())) {
                    discord.setText("");
                }
                if (Upassword.getText().toString().length() < 8) {
                    passwordLen.setText("8자 이상 입력해주세요.");
                } else {
                    passwordLen.setText(" ");
                }
                if (TextUtils.isEmpty(Upassword.getText())) {
                    passwordLen.setText(" ");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        id.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (id.getText().toString().length() < 3) {
                    idLen.setText("3자 이상 입력해주세요.");
                } else {
                    idLen.setText(" ");
                }
                if (TextUtils.isEmpty(id.getText())) {
                    idLen.setText("");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nickname = id.getText().toString();
                String password = Upassword.getText().toString();
                String email = Uemail.getText().toString() + "@catholic.ac.kr";
                String studentId = schoolNum.getText().toString();
                SignUpData signdata = new SignUpData(email,nickname,password, studentId);
                ApiService apiService1 = ApiClient.getClient().create(ApiService.class);
                // APiservice의 apiService를 정의해 자동으로 Json에서 Gson으로 변환할 수 있게 함.
                Call<SignUpResponse> call = apiService1.userSignup(signdata);
                call.enqueue(new Callback<SignUpResponse>() {
                    @Override
                    public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {
                        if (response.isSuccessful()) {
                            SignUpResponse res = response.body();
                            if(res.getSuccess() == "true"){
                                Loginintent();
                            }
                        } else{
                            showDialog();
                        }
                    }
                    @Override
                    public void onFailure(Call<SignUpResponse> call, Throwable t) {

                    }
                });
                // LoginData의 data객체를 생성해서 password, studentId 정보를 준비해둔다.
            }
        });
    }
    public void showDialog() {
        AlertDialog.Builder msgBuilder = new AlertDialog.Builder(SignUpActivity.this)
                .setTitle("알림")
                .setMessage("회원가입에 실패하였습니다.")
                .setPositiveButton("확인",null);
        AlertDialog msgDlg = msgBuilder.create();
        msgDlg.show();
    }
    public void Loginintent() {
        AlertDialog.Builder Builder = new AlertDialog.Builder(this)
                .setTitle("알림")
                .setMessage("회원가입이 완료되었습니다.")
                .setPositiveButton("확인", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which){
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
                Builder.setCancelable(false);
                AlertDialog dialog = Builder.create();
                dialog.show();
        }
    }

