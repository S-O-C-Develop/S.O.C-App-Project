package com.example.soc.board.Menu.Notice;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.soc.R;

public class Notice_major extends Fragment {
    private WebView mWebView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.notice_major, container, false);
        mWebView = (WebView) v.findViewById(R.id.major_webView);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.loadUrl("https://ice.catholic.ac.kr/front/boardlist.do?cmsDirPkid=1032&cmsLocalPkid=0");//웹뷰 실행
        mWebView.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_BACK) {
                        if
                        (mWebView != null) {
                            if (mWebView.canGoBack()) {
                                mWebView.goBack();
                            } else {
                                getActivity().onBackPressed();
                            }
                        }
                    }
                }
                return true;
            }
        });
        return v;
    }
}

