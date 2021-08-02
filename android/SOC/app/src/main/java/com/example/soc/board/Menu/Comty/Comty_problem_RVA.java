package com.example.soc.board.Menu.Comty;

import android.content.Context;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.soc.R;

import java.util.ArrayList;
import java.util.List;

public class Comty_problem_RVA extends RecyclerView.Adapter<Comty_problem_RVA.MyViewHolder> {
    private final ArrayList<Compty_problem_Response> mDataset;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, time, content, nickname, reply;
        public ImageView image1, image2;

        //ViewHolder
        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.board_title);
        }
    }

    public Comty_problem_RVA(ArrayList<Compty_problem_Response> myData){
        this.mDataset = myData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.comty_problem_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Comty_problem_RVA.MyViewHolder holder, int position) {
        holder.title.setText(mDataset.get(position).getTitle());

    }

    @Override
    public int getItemCount() {

        return mDataset.size();
    }
}