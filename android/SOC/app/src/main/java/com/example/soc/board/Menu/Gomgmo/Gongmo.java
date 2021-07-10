package com.example.soc.board.Menu.Gomgmo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.soc.R;
import com.example.soc.board.Menu.Commu.CommuPagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class Gongmo extends Fragment {
    ViewGroup viewGroup;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewGroup = (ViewGroup) inflater.inflate(R.layout.board_gongmo,container,false);
        ViewPager adapter = (ViewPager) viewGroup.findViewById(R.id.gongmoViewPager);
        adapter.setAdapter(new GongmoPagerAdapter((getChildFragmentManager())));
        TabLayout mTab = (TabLayout) viewGroup.findViewById(R.id.gongmotabs);
        mTab.setupWithViewPager(adapter);
        return viewGroup;
    };
}
