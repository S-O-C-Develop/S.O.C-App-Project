package com.example.soc.board.Menu.Comty;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.soc.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class Comty_problem extends Fragment implements View.OnClickListener{
    private ArrayList<Compty_problem_Response> List = new ArrayList<>();
    private RecyclerView recyclerView;
    private Comty_problem_RVA mAdapter;
    private FloatingActionButton fabmain, fabedit, fabsearch;
    private Animation fab_open, fab_close, rotate_backward, rotate_forward;
    private Boolean isFabOpen = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.comty_problem, container, false);
        //플로팅 버튼
        fab_open = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.fab_open);
        fab_close = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.fab_close);
        rotate_forward = AnimationUtils.loadAnimation(getActivity().getApplicationContext(),R.anim.rotate_forward);
        rotate_backward = AnimationUtils.loadAnimation(getActivity().getApplicationContext(),R.anim.rotate_backward);
        fabmain = (FloatingActionButton) v.findViewById(R.id.fabMain);
        fabedit = (FloatingActionButton) v.findViewById(R.id.fabEdit);
        fabsearch = (FloatingActionButton) v.findViewById(R.id.fabsearch);
        fabmain.setOnClickListener(this);
        fabedit.setOnClickListener(this);
        fabsearch.setOnClickListener(this);


        //recyclerview
        recyclerView = (RecyclerView) v.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        mAdapter = new Comty_problem_RVA(List);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prepareData();

    }

    //데이터 준비(최종적으로는 동적으로 추가하거나 삭제할 수 있어야 한다. 이 데이터를 어디에 저장할지 정해야 한다.)
    private void prepareData() {

    }


        public void onClick(View view) {
        int id = view.getId();
        switch(id){
            case R.id.fabMain:
                animFab();;
                break;
            case R.id.fabEdit:
                animFab();
                Log.d("Raj", "FabEdit");
                Intent intent = new Intent(getActivity(), Comty_Problem_write.class);
                startActivity(intent);
                break;
            case R.id.fabsearch:
                animFab();
                Log.d("Raj", "Fabsearch");
                Intent intent1 = new Intent(getActivity(), Comty_problem_setting.class);
                startActivity(intent1);
                break;
        }
        }
        public void animFab() {
            if (isFabOpen) {
                fabmain.startAnimation(rotate_backward);
                fabedit.startAnimation(fab_close);
                fabsearch.startAnimation(fab_close);
                fabedit.setClickable(false);
                fabsearch.setClickable(false);
                isFabOpen = false;
                Log.d("Raj", "close");
            } else {
                fabmain.startAnimation((rotate_forward));
                fabedit.startAnimation(fab_open);
                fabsearch.startAnimation(fab_open);
                fabedit.setClickable(true);
                fabsearch.setClickable(true);
                isFabOpen = true;
                Log.d("Raj", "open");
            }
        }
    }

