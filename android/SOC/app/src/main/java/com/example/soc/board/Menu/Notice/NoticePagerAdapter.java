package com.example.soc.board.Menu.Notice;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class NoticePagerAdapter extends FragmentPagerAdapter {
Notice_college college = new Notice_college();
Notice_major major = new Notice_major();
Notice_scholarship scholar = new Notice_scholarship();
Notice_soc soc = new Notice_soc();
    public NoticePagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
        getItem(0);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch(position) {
            case 0:
                return college;
            case 1:
                return scholar;
            case 2:
                return major;
            case 3:
                return soc;
            default:
                return null;
        }
        }
    private static int PAGE_NUMBER = 4;
    @Override

    public int getCount() {
        return PAGE_NUMBER;
    }
    public CharSequence getPageTitle(int position) {
        switch( position) {
            case 0:
                return "학사";
            case 1:
                return "장학";
            case 2:
                return "학과";
            case 3:
                return "SOC";
            default:
                return null;
        }
    }
}

