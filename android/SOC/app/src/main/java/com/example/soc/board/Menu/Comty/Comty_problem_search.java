package com.example.soc.board.Menu.Comty;

import android.app.Activity;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import com.example.soc.R;

public class Comty_problem_search extends Activity {
    CheckBox chk_gr1,chk_gr2,chk_gr3,chk_gr4;
    CheckBox chk_se1,chk_se2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comty_problem_condition_search);
        Button init = (Button) findViewById(R.id.init);
        Button reg_b = (Button) findViewById(R.id.sign);

        reg_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}