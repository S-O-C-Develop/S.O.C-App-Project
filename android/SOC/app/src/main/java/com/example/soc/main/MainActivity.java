package com.example.soc.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;
import android.view.MenuItem;
import com.example.soc.R;
import com.example.soc.function.BackPressHandler;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private BackPressHandler backPressHandler = new BackPressHandler(this);
    private FragmentManager fragmentManager = getSupportFragmentManager();
    private Home menu1Fragment = new Home();
    private Board menu2Fragment = new Board();
    private Community menu3Fragment = new Community();
    private BottomNavigationView bottomNavigationView;
    private FragmentTransaction ft;
    @Override
    public void onBackPressed(){
        backPressHandler.onBackPressed();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);  // 첫화면에 띄우는 layout
        getSupportFragmentManager().beginTransaction().replace(R.id.layout_main_frame,menu1Fragment).commitAllowingStateLoss();
        bottomNavigationView = findViewById(R.id.bottom_navigation_view);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.navigation_menu1: {
                        setFrag(0);
                        break;
                    }
                    case R.id.navigation_menu2: {
                        setFrag(1);

                        break;
                    }
                    case R.id.navigation_menu3: {
                        setFrag(2);
                        break;
                    }
                }
                return true;
            }
        });

    }
    private void setFrag(int n) {
        fragmentManager = getSupportFragmentManager();
        ft=fragmentManager.beginTransaction();
        switch (n) {
            case 0:
                ft.replace(R.id.layout_main_frame,menu1Fragment);
                ft.commit();
                break;
            case 1:
                ft.replace(R.id.layout_main_frame,menu2Fragment);
                ft.commit();
                break;
            case 2:
                ft.replace(R.id.layout_main_frame,menu3Fragment);
                ft.commit();
                break;
        }
    }

}