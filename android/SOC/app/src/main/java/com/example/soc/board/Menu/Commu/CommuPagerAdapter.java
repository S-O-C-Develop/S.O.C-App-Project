package com.example.soc.board.Menu.Commu;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class CommuPagerAdapter extends FragmentPagerAdapter {
Infor infor = new Infor();
QnA qna = new QnA();
    public CommuPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
        getItem(0);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch(position) {
            case 0:
                return infor;
            case 1:
                return qna;
            default:
                return null;
        }
        }
    private static int PAGE_NUMBER = 2;
    @Override

    public int getCount() {
        return PAGE_NUMBER;
    }
    public CharSequence getPageTitle(int position) {
        switch( position) {
            case 0:
                return "문제";
            case 1:
                return "정보";
            default:
                return null;
        }
    }
}

