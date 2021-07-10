package com.example.soc.board.Menu.Notice;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.soc.board.Menu.Commu.Infor;
import com.example.soc.board.Menu.Commu.QnA;

public class NoticePagerAdapter extends FragmentPagerAdapter {
HakSa hakSa = new HakSa();
Department depart = new Department();
JangHak janghak = new JangHak();
SOC soc = new SOC();
    public NoticePagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
        getItem(0);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch(position) {
            case 0:
                return hakSa;
            case 1:
                return janghak;
            case 2:
                return depart;
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

