package com.example.soc.board;

import android.annotation.SuppressLint;
import android.media.Image;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.soc.R;

public class Board extends Fragment {
    ViewGroup viewGroup;
    TextView name;
    ImageView menu;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        viewGroup = (ViewGroup) inflater.inflate(R.layout.board, container, false);
        name = (TextView) viewGroup.findViewById(R.id.board_name);
        menu = (ImageView) viewGroup.findViewById(R.id.board_menu);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(getActivity().getApplicationContext(), v);
                getActivity().getMenuInflater().inflate(R.menu.board_menu, popup.getMenu());
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch(item.getItemId()) {
                            case R.id.board_menu1:
                                name.setText("정보");
                                break;
                            case R.id.board_menu2:
                                name.setText("묻고 답하기");
                                break;
                            case R.id.board_menu3:
                                name.setText("공모전");
                                break;
                            case R.id.board_menu4:
                                name.setText("대외활동");
                                break;
                        }
                        return false;
                    }
                });
                    popup.show();
                }
        });
        return viewGroup;
    }
}

