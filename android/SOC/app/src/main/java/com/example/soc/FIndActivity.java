package com.example.soc;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class FIndActivity extends Activity
{
EditText firstText, secondText;
    // BackPressHandler 객체 선언, 할당
    public void onBackPressed() {
        finish();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_revpass);
        firstText = (EditText) findViewById(R.id.rev);
        secondText = (EditText) findViewById(R.id.revchk);
        TextView check = (TextView) findViewById(R.id.revnc);
        TextView restrict = (TextView) findViewById(R.id.restrict);

        Button next = (Button)findViewById(R.id.revnext);
        next.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                secondText.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    }
                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        if (firstText.getText().toString().length() < 8) {
                            restrict.setText("비밀번호는 8자 이상 입력해주세요.");
                        } else if (firstText.getText().toString().length() >= 8) {
                            restrict.setText("");
                            if(TextUtils.isEmpty(firstText.getText()) && TextUtils.isEmpty(secondText.getText())) {
                                check.setText("");
                            }
                            else if(firstText.getText().toString().equals(secondText.getText().toString())) {
                                check.setText("비밀번호가 일치합니다.");
                                String strColor = "#00FF08";
                                check.setTextColor(Color.parseColor(strColor));
                            }
                            else if (firstText.getText().toString().equals(secondText.getText().toString()) == false) {
                                check.setText("비밀번호가 일치하지 않습니다.");
                                String strColor = "#FF0000";
                                check.setTextColor(Color.parseColor(strColor));
                            }
                        }
                    }
                    @Override
                    public void afterTextChanged(Editable editable) {
                    }
                });
                if (firstText.getText().toString().length() < 8) {
                    restrict.setText("비밀번호는 8자 이상 입력해주세요.");
                    firstText.requestFocus();
                    firstText.setText("");
                    secondText.setText("");
                }
                  else if(firstText.getText().toString().length() >= 8) {
                    restrict.setText("");
                    if (TextUtils.isEmpty(firstText.getText()) && TextUtils.isEmpty(secondText.getText())) {
                        check.setText("");
                    } else if (secondText.getText().toString().equals(firstText.getText().toString())) {
                        check.setText("비밀번호가 일치합니다.");
                        String strColor = "#00FF08";
                        check.setTextColor(Color.parseColor(strColor));
                        AlertDialog.Builder dlg = new AlertDialog.Builder(FIndActivity.this);
                        dlg.setTitle("비밀번호 변경 완료");
                        dlg.setMessage("비밀번호가 변경되었습니다.");
                        dlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        });
                        dlg.show();
                    } else if (secondText.getText().toString().equals(firstText.getText().toString()) == false) {
                        check.setText("비밀번호가 일치하지 않습니다.");
                        String strColor = "#FF0000";
                        check.setTextColor(Color.parseColor(strColor));
                        secondText.requestFocus();
                        secondText.setText("");
                    }

                }

            }
        });
    }
}