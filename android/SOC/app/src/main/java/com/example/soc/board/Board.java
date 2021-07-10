package com.example.soc.board;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.soc.R;
import com.example.soc.board.Menu.Commu.Commu;
import com.example.soc.board.Menu.Gomgmo.Gongmo;
import com.example.soc.board.Menu.Notice.Notice;

public class Board extends Fragment {
    ViewGroup viewGroup;
    TextView name;
    ImageView menu;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);

        viewGroup = (ViewGroup) inflater.inflate(R.layout.board_main, container, false);
        getFragmentManager().beginTransaction().add(R.id.board, new Commu()).commit();
        name = (TextView) viewGroup.findViewById(R.id.board_name);
        menu = (ImageView) viewGroup.findViewById(R.id.board_menu);
        name.setText("SOC - 커뮤니티");
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
                                name.setText("SOC - 커뮤니티");
                                getFragmentManager().beginTransaction().replace(R.id.board, new Commu()).commit();
                                break;
                            case R.id.board_menu2:
                                name.setText("SOC - 공지사항");
                                getFragmentManager().beginTransaction().replace(R.id.board, new Notice()).commit();
                                break;
                            case R.id.board_menu3:
                                name.setText("SOC - 공모전");
                                getFragmentManager().beginTransaction().replace(R.id.board, new Gongmo()).commit();
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

