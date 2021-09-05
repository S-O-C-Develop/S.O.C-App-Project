package com.example.soc.board.Menu.Comty;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.soc.R;
import com.example.soc.board.Menu.Contest.Contest_suburbAdapter;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Comty_problem_RVA extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;
    private List<Compty_problem_Board_Response.Content> itemList;


    public Comty_problem_RVA(List<Compty_problem_Board_Response.Content> mitemList) {
        itemList = mitemList;
    }

    @Override
    public int getItemViewType(int position) {
        return itemList.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_ITEM) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.comty_problem_item, parent, false);
            return new ItemViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_loading, parent, false);
            return new LoadingViewHolder(view);
        }
    }
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        if (viewHolder instanceof ItemViewHolder) {
            populateItemRows((ItemViewHolder) viewHolder, position);
        } else if (viewHolder instanceof LoadingViewHolder) {
            showLoadingView((LoadingViewHolder) viewHolder, position);
        }

    }


    @Override
    public int getItemCount() {
        return itemList == null ? 0 : itemList.size();
    }

    private class ItemViewHolder extends RecyclerView.ViewHolder {
        public TextView  content, nickname, reply, title;
        public ImageView image1, image2;
        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.pro_title);
            nickname = (TextView) itemView.findViewById(R.id.pro_nickname);
            content = (TextView) itemView.findViewById(R.id.pro_cotent);
            reply = (TextView) itemView.findViewById(R.id.pro_reply);
            image1 = (ImageView) itemView.findViewById(R.id.pro_image1);
            image2 = (ImageView) itemView.findViewById(R.id.pro_image2);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), Post.class);
                    v.getContext().startActivity(intent);
                    Toast.makeText(v.getContext(), "클릭 되었습니다.", Toast.LENGTH_SHORT).show();
                }
            });
        }//뷰안에 필요한 정보 채움
    }
    private class LoadingViewHolder extends RecyclerView.ViewHolder {

        ProgressBar progressBar;

        public LoadingViewHolder(@NonNull View itemView) {
            super(itemView);
            progressBar = itemView.findViewById(R.id.progressBar);
        }
    }

    private void showLoadingView(LoadingViewHolder viewHolder, int position) {
        //
    }

    private void populateItemRows(ItemViewHolder viewHolder, int position) {
        Compty_problem_Board_Response.Content item = itemList.get(position);
        viewHolder.title.setText(item.getTitle());
        viewHolder.nickname.setText((item.getAuthor()));
        viewHolder.content.setText(item.getContents());
        viewHolder.reply.setText(String.valueOf(item.getReportcount()));
        viewHolder.image1.setVisibility(View.GONE);
        viewHolder.image2.setVisibility(View.GONE);
        }

    }

