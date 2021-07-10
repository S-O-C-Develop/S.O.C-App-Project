package com.example.soc.find;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.soc.R;
import com.example.soc.func.BackPressHandler;

//Activity를 상속받으면 앱바가 없음
    public class FindActivity extends Activity {

        // UI references.
        private BackPressHandler backPressHandler = new BackPressHandler(this);
        InputMethodManager imm;
        LinearLayout ll;
        Button findSend, checkNext;
        TextView sendcheck, tfcheck;
        EditText schoolnum, email, checnum;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_find);
            imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            findSend = (Button) findViewById(R.id.findsend);
            checkNext = (Button) findViewById(R.id.checknext);
            sendcheck = (TextView) findViewById(R.id.sendcheck);
            tfcheck = (TextView) findViewById(R.id.tfcheck);

            findSend.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    sendcheck.setText("인증번호가 발송되었습니다.");
                }
            });

            checkNext.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                        Intent intent = new Intent(getApplicationContext(), InFindActivity.class);
                        startActivity(intent);
                    }
            });
            //화면클릭 시 키보드 숨김
            LinearLayout ll = (LinearLayout) findViewById(R.id.ll);
            ll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }
            });
        }

        public void showDialog() {
            AlertDialog.Builder msgBuilder = new AlertDialog.Builder(com.example.soc.find.FindActivity.this)
                    .setTitle("알림")
                    .setMessage("인증번호가 일치하지 않습니다.")
                    .setPositiveButton("확인",null);

            AlertDialog msgDlg = msgBuilder.create();
            msgDlg.show();
        }
    }

