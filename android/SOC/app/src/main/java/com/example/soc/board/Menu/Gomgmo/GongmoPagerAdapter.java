package com.example.soc.board.Menu.Gomgmo;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.soc.board.Menu.Commu.Infor;
import com.example.soc.board.Menu.Commu.QnA;

public class GongmoPagerAdapter extends FragmentPagerAdapter {
In in = new In();
Out out = new Out();
    public GongmoPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
        getItem(0);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch(position) {
            case 0:
                return in;
            case 1:
                return out;
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

