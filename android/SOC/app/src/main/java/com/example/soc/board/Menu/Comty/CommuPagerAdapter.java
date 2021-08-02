package com.example.soc.board.Menu.Comty;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

public class CommuPagerAdapter extends FragmentStatePagerAdapter {
Comty_information infor = new Comty_information();
Comty_problem problem = new Comty_problem();
    public CommuPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
        getItem(0);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch(position) {
            case 0:
                return problem;
            case 1:
                return infor;
            default:
                return null;
        }
        }
    private static int PAGE_NUMBER = 2;
    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public int getCount() {
        return PAGE_NUMBER;
    }
    public CharSequence getPageTitle(int position) {
        switch(position) {
            case 0:
                return "문제";
            case 1:
                return "정보";
            default:
                return null;
        }
    }






}

