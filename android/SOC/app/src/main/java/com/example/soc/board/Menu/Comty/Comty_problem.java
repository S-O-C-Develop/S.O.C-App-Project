package com.example.soc.board.Menu.Comty;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.security.identity.ResultData;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.soc.R;
import com.example.soc.func.ApiClient;
import com.example.soc.func.ApiService;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

public class Comty_problem extends Fragment implements View.OnClickListener {
    private RecyclerView recyclerView;
    private Comty_problem_RVA mAdapter;
    private ImageView image1, image2;
    private FloatingActionButton fabmain, fabedit, fabsearch;
    private Animation fab_open, fab_close, rotate_backward, rotate_forward;
    ProgressBar progressBar_tp, progressBar_bt;
    private Boolean isFabOpen = false;
    private int page = 1;
    private Boolean isNext = true;
    private int total = 0;
    ApiService apiService1 = ApiClient.getClient().create(ApiService.class);
    static java.util.ArrayList<Compty_problem_Board_Response.Content> list2 = new ArrayList<>();
    java.util.ArrayList<Compty_problem_Board_Response.Content> list = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.comty_problem, container, false);

        //플로팅 버튼
        fab_open = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.fab_open);
        fab_close = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.fab_close);
        rotate_forward = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.rotate_forward);
        rotate_backward = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.rotate_backward);
        fabmain = (FloatingActionButton) v.findViewById(R.id.fabMain);
        fabedit = (FloatingActionButton) v.findViewById(R.id.fabEdit);
        fabsearch = (FloatingActionButton) v.findViewById(R.id.fabsearch);
        fabmain.setOnClickListener(this);
        fabedit.setOnClickListener(this);
        fabsearch.setOnClickListener(this);
        image1 = (ImageView) v.findViewById(R.id.pro_image1);
        image2 = (ImageView) v.findViewById(R.id.pro_image2);
        recyclerView = (RecyclerView) v.findViewById(R.id.recyclerView);
        progressBar_bt = v.findViewById(R.id.progress_bar);
        progressBar_tp = v.findViewById(R.id.progress_bartop);
        progressBar_bt.setVisibility(View.GONE);
        progressBar_tp.setVisibility(View.GONE);
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        loadPosts();
        initScrollListener();
        return v;

    }
    private void loadPosts() {
        Call<Compty_problem_Board_Response> call2 = apiService1.getBoard(19, 1, "postId", false);
        call2.enqueue(new Callback<Compty_problem_Board_Response>() {
            @Override
            public void onResponse(Call<Compty_problem_Board_Response> call, Response<Compty_problem_Board_Response> response) {
                Compty_problem_Board_Response res = response.body();
                if(response.isSuccessful())
                    total = res.getResult().getSize();
                isNext = res.getResult().getLast();
                list = (ArrayList<Compty_problem_Board_Response.Content>) res.getResult().getContent();
                Log.d(TAG, String.valueOf(list));
                mAdapter = new Comty_problem_RVA(list);
                recyclerView.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();
                Log.d(TAG, "1");
            }
            @Override
            public void onFailure(Call<Compty_problem_Board_Response> call, Throwable t) {
                Log.d(TAG, "123");
            }
        });
    }

    private void dataMore() {
        Log.d(TAG, "dataMore: ");
        progressBar_bt.setVisibility(View.VISIBLE);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Call<Compty_problem_Board_Response> call2 = apiService1.getBoard(19, getPage(), "postId", false);
                call2.enqueue(new Callback<Compty_problem_Board_Response>() {
                    @Override
                    public void onResponse(Call<Compty_problem_Board_Response> call, Response<Compty_problem_Board_Response> response) {
                        Compty_problem_Board_Response res = response.body();
                        if (response.isSuccessful())
                            total = res.getResult().getSize();
                        isNext = res.getResult().getLast();
                        list2 = (ArrayList<Compty_problem_Board_Response.Content>) res.getResult().getContent();
                        list.addAll(list2);
                        recyclerView.scrollToPosition(list.size() + 1);
                        mAdapter.notifyDataSetChanged();
                        Log.d(TAG, "1");

                    }

                    @Override
                    public void onFailure(Call<Compty_problem_Board_Response> call, Throwable t) {
                        Log.d(TAG, "123");
                    }
                });
                progressBar_bt.setVisibility(View.GONE);
            }
        }, 1000);

    }
    private void initScrollListener() {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                Log.d(TAG, "onScrollStateChanged: ");
            }
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();

                if (hasnextPage() == false) {
                    if (recyclerView.canScrollVertically(-1)) {
                        dataMore();
                        setHasNextPage(false);
                    }
                }
            }
        });
    }
    private int getPage() {
        page++;
        return page;
    }
    private Boolean hasnextPage() {
        return isNext;
    }
    private void setHasNextPage(boolean b) {
        isNext = b;
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

