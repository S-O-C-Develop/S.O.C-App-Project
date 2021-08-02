package com.example.soc.board.Menu.Notice;

import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Notice_setWebviewClient extends WebViewClient {//페이지 이동
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        Log.d("check URL",url);
        view.loadUrl(url);
        return true;
    }
}
