package com.example.soc.board.Menu.Contest;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class ContestPagerAdapter extends FragmentPagerAdapter {
Contest_campus campus = new Contest_campus();
Contest_suburb sub = new Contest_suburb();
    public ContestPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
        getItem(0);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch(position) {
            case 0:
                return campus;
            case 1:
                return sub;
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
                return "교내";
            case 1:
                return "교외";
            default:
                return null;
        }
    }
}

