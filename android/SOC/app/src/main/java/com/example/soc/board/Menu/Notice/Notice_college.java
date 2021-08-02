package com.example.soc.board.Menu.Notice;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.soc.R;

public class Notice_college extends Fragment {
    ViewGroup viewGroup;
    private WebView mWebView;
    private String myUrl = "http://";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.notice_college, container, false);
        mWebView = (WebView) v.findViewById(R.id.colleage_webView);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.loadUrl("https://www.catholic.ac.kr/front/boardlist.do?cmsDirPkid=2053&cmsLocalPkid=2");//웹뷰 실행
        mWebView.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event)
            { if (event.getAction() == KeyEvent.ACTION_DOWN){
                if (keyCode == KeyEvent.KEYCODE_BACK){ if
                (mWebView!=null){ if (mWebView.canGoBack()){ mWebView.goBack(); }
                else
                    { getActivity().onBackPressed(); }
                }
                }
            }
            return true;
            }
        });
        return v;
    }
}



