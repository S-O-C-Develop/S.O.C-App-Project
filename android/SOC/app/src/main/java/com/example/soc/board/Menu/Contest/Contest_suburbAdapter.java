package com.example.soc.board.Menu.Contest;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.soc.R;

import java.util.ArrayList;

public class Contest_suburbAdapter extends RecyclerView.Adapter<Contest_suburbAdapter.ItemViewHolder> {
    private ArrayList<Contest_suburb_data> listData = new ArrayList<>();
    @Override
    public Contest_suburbAdapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.contest_suburbitem, viewGroup, false);
        return new ItemViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull Contest_suburbAdapter.ItemViewHolder itemViewHolder, int i) {
        itemViewHolder.onBind(listData.get(i));
        String defaulturl="https://www.thinkcontest.com";
        final String url=defaulturl+listData.get(i).getClickUrl().toString();
        itemViewHolder.subimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Log.e("Url 이동 포인트",url);
                final Context context=view.getContext();
                Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                context.startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {
        return listData.size();
    }
    public void addItem(Contest_suburb_data data) {
        listData.add(data);
    }
    class ItemViewHolder extends RecyclerView.ViewHolder{
        private TextView subtitle;
        private ImageView subimage;
        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            subtitle = itemView.findViewById(R.id.suburb_title);
            subimage = itemView.findViewById(R.id.suburb_image);
        }
        void onBind(Contest_suburb_data data){
            subtitle.setText(data.getTitle());
            Glide.with(itemView.getContext()).load(data.getImageUrl()).into(subimage);

        }
    }
}
