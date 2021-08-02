package com.example.soc.board.Menu.Comty;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.soc.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Comty_information extends Fragment implements View.OnClickListener {
    private FloatingActionButton fabmain, fabedit;
    private Animation fab_open, fab_close, rotate_backward, rotate_forward;
    private Boolean isFabOpen = false;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.comty_information, container, false);
        fab_open = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.fab_open);
        fab_close = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.fab_close);
        rotate_forward = AnimationUtils.loadAnimation(getActivity().getApplicationContext(),R.anim.rotate_forward);
        rotate_backward = AnimationUtils.loadAnimation(getActivity().getApplicationContext(),R.anim.rotate_backward);
        fabmain = (FloatingActionButton) v.findViewById(R.id.fabMain);
        fabedit = (FloatingActionButton) v.findViewById(R.id.fabEdit);
        fabmain.setOnClickListener(this);
        fabedit.setOnClickListener(this);
        return v;
    };
    public void onClick(View view) {
        int id = view.getId();
        switch(id){
            case R.id.fabMain:
                animFab();;
                break;
            case R.id.fabEdit:
                animFab();
                Log.d("Raj", "FabEdit");
                break;
            case R.id.fabsearch:
                animFab();
                Log.d("Raj", "Fabsearch");
                break;
        }
    }
    public void animFab() {
        if (isFabOpen) {
            fabmain.startAnimation(rotate_backward);
            fabedit.startAnimation(fab_close);
            fabedit.setClickable(false);
            isFabOpen = false;
            Log.d("Raj", "close");
        } else {
            fabmain.startAnimation((rotate_forward));
            fabedit.startAnimation(fab_open);
            fabedit.setClickable(true);
            isFabOpen = true;
            Log.d("Raj", "open");
        }
    }
}

