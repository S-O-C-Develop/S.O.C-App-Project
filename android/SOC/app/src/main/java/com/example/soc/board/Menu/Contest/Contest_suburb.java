package com.example.soc.board.Menu.Contest;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.soc.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class Contest_suburb extends Fragment {
    private View view;
    RecyclerView recyclerView;
    Contest_suburbAdapter adapter;
    String suburburl="https://www.thinkcontest.com/Contest/CateField.html?c=11";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.contest_suburb, container, false);
        recyclerView = v.findViewById(R.id.recycle_1boon);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new Contest_suburbAdapter();
        recyclerView.setAdapter(adapter);

        getData();
        return v;
    }

    private void getData() {
        Ilboonsoup ilboonsoupTask = new Ilboonsoup();
        ilboonsoupTask.execute();
    }

    private class Ilboonsoup extends AsyncTask<Void, Void, Void> {
        ArrayList<String> listTitle = new ArrayList<>();
        ArrayList<String> listUrl = new ArrayList<>();
        ArrayList<String> clickUrl = new ArrayList<>();

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Document doc = Jsoup.connect(suburburl).get();
                final Elements title = doc.select("ul.contest-banner-list li h4");
                final Elements img = doc.select("ul.contest-banner-list li img");
                final Elements click = doc.select("ul.contest-banner-list li a");
                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        for (Element element : title) {
                            listTitle.add(element.text());
                        }
                        for (Element element : img) {
                            listUrl.add(element.attr("src"));
                        }
                        for (Element element : click) {
                            clickUrl.add(element.attr("href"));

                        }
                        for (int i = 0; i < listTitle.size(); i++) {
                            System.out.println("========================");
                            System.out.println(listTitle);
                            System.out.println(listUrl);
                            System.out.println(clickUrl);
                            System.out.println("========================");
                            Contest_suburb_data data = new Contest_suburb_data();
                            String url = listUrl.get(i);
                            int temp = url.lastIndexOf("=");
                                data.setTitle(listTitle.get(i));
                                data.setImageUrl("https://www.thinkcontest.com" + url.substring(temp + 1, url.length()));
                                data.setClickUrl(clickUrl.get(i));
                                // 제대로 값이 들어감을 확인
                                adapter.addItem(data);
                            }
                            adapter.notifyDataSetChanged();
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }
    }
}


