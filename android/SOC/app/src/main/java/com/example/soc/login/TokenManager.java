package com.example.soc.login;

import android.content.Context;
import android.content.SharedPreferences;

public class TokenManager {
    private SharedPreferences sharedPref;
    private SharedPreferences.Editor editor;
    private int Mode = 0;
    private static final String REFNAME = "JWTTOKEN";
    private static final String TOKEN = "data";
    private Context context;

    public TokenManager(Context context)
    {
        this.context = context;
        sharedPref = context.getSharedPreferences(REFNAME,Mode);
        editor = sharedPref.edit();
    }
public void createSession(String data) {
        editor.putString(TOKEN,data);
        editor.commit();
}
}
