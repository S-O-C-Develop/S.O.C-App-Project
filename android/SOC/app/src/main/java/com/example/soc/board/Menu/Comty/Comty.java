package com.example.soc.board.Menu.Comty;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.example.soc.R;
import com.google.android.material.tabs.TabLayout;

public class Comty extends Fragment {
    ViewGroup viewGroup;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewGroup = (ViewGroup) inflater.inflate(R.layout.comty, container, false);
        ViewPager adapter = (ViewPager) viewGroup.findViewById(R.id.commuViewPager);
        adapter.setAdapter(new CommuPagerAdapter((getChildFragmentManager())));
        TabLayout mTab = (TabLayout) viewGroup.findViewById(R.id.boardtabs);
        mTab.setupWithViewPager(adapter);
        return viewGroup;
    }
}


