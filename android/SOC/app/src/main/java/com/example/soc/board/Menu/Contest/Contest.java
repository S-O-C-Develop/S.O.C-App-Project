package com.example.soc.board.Menu.Contest;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.soc.R;
import com.google.android.material.tabs.TabLayout;

public class Contest extends Fragment {
    ViewGroup viewGroup;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewGroup = (ViewGroup) inflater.inflate(R.layout.contest,container,false);
        ViewPager adapter = (ViewPager) viewGroup.findViewById(R.id.contestViewPager);
        adapter.setAdapter(new ContestPagerAdapter((getChildFragmentManager())));
        TabLayout mTab = (TabLayout) viewGroup.findViewById(R.id.contesttabs);
        mTab.setupWithViewPager(adapter);
        return viewGroup;
    };
}
