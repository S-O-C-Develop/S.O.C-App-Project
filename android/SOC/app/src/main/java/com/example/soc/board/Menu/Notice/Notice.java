package com.example.soc.board.Menu.Notice;

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

public class Notice extends Fragment {
    ViewGroup viewGroup;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewGroup = (ViewGroup) inflater.inflate(R.layout.notice,container,false);
        ViewPager adapter = (ViewPager) viewGroup.findViewById(R.id.noticeViewPager);
        adapter.setAdapter(new NoticePagerAdapter((getChildFragmentManager())));
        TabLayout mTab = (TabLayout) viewGroup.findViewById(R.id.noticetabs);
        mTab.setupWithViewPager(adapter);
        return viewGroup;
    };
}
