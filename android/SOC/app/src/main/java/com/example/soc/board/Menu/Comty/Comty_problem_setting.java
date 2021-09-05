package com.example.soc.board.Menu.Comty;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.sip.SipSession;
import android.os.Bundle;

import android.preference.PreferenceManager;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;

import androidx.annotation.Nullable;

import com.example.soc.R;

import org.json.JSONArray;
import org.json.JSONException;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

public class Comty_problem_setting extends Activity {

    ArrayAdapter<CharSequence> Adapter;
    ArrayAdapter<CharSequence> Adapter2;
    ArrayAdapter<CharSequence> Adaptergr1_1se;
    ArrayAdapter<CharSequence> Adaptergr1_2se;
    ArrayAdapter<CharSequence> Adaptergr2_1se;
    ArrayAdapter<CharSequence> Adaptergr2_2se;
    ArrayAdapter<CharSequence> Adaptergr3_1se;
    ArrayAdapter<CharSequence> Adaptergr3_2se;
    ArrayAdapter<CharSequence> Adaptergr4_1se;
    ArrayAdapter<CharSequence> Adaptergr4_2se;
    private static final String SETTINGS_PLAYER_JSON = "settings_item_json";
    String arr[][][] = new String[4][2][8];
    ArrayList<String> arrData = new ArrayList<String>();
    ArrayList<String> arrData2 = new ArrayList<String>();
    @Override
   public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comty_problem_setting);
        Button init = (Button) findViewById(R.id.init);
        Button reg_b = (Button) findViewById(R.id.sign);
        Button subjectall = (Button) findViewById(R.id.subjectall);
        SharedPreferences pref = getSharedPreferences("pref", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        ListView gradelist = (ListView) findViewById(R.id.gradelist);
        ListView semesterlist = (ListView) findViewById(R.id.semesterlist);
        ListView subjectlist = (ListView) findViewById(R.id.subjectlist);

        //grade 셋팅
        Adapter = ArrayAdapter.createFromResource(this, R.array.grade, android.R.layout.simple_list_item_1);
        gradelist.setAdapter(Adapter);
        gradelist.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        //semester 셋팅
        Adapter2 = ArrayAdapter.createFromResource(this, R.array.semester, android.R.layout.simple_list_item_1);
        //학년,학기에 따른 과목 설정
        //1학년
        Adaptergr1_1se = ArrayAdapter.createFromResource(this, R.array.gr1_1se, android.R.layout.simple_list_item_multiple_choice);
        Adaptergr1_2se = ArrayAdapter.createFromResource(this, R.array.gr1_2se, android.R.layout.simple_list_item_multiple_choice);

        //2학년

        Adaptergr2_1se = ArrayAdapter.createFromResource(this, R.array.gr2_1se, android.R.layout.simple_list_item_multiple_choice);
        Adaptergr2_2se = ArrayAdapter.createFromResource(this, R.array.gr2_2se, android.R.layout.simple_list_item_multiple_choice);

        //3학년

        Adaptergr3_1se = ArrayAdapter.createFromResource(this, R.array.gr3_1se, android.R.layout.simple_list_item_multiple_choice);
        Adaptergr3_2se = ArrayAdapter.createFromResource(this, R.array.gr3_2se, android.R.layout.simple_list_item_multiple_choice);

        //4학년
        Adaptergr4_1se = ArrayAdapter.createFromResource(this, R.array.gr4_1se, android.R.layout.simple_list_item_multiple_choice);
        Adaptergr4_2se = ArrayAdapter.createFromResource(this, R.array.gr4_2se, android.R.layout.simple_list_item_multiple_choice);


        gradelist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                System.out.println(arrData2);
                System.out.println(Arrays.toString(arr[0][0]));
                System.out.println(Arrays.toString(arr[1][0]));
                System.out.println(Arrays.toString(arr[2][0]));
                System.out.println(Arrays.toString(arr[3][0]));

                switch (position) {
                    case 0:
                        subjectlist.setVisibility(INVISIBLE);
                        semesterlist.setAdapter(Adapter2);
                        semesterlist.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
                        semesterlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                subjectall.setVisibility(VISIBLE);
                                subjectlist.setVisibility(VISIBLE);
                                switch (position) {
                                    case 0:
                                        subjectlist.setAdapter(Adaptergr1_1se);
                                        subjectlist.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
                                        subjectlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                            @Override
                                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                                SparseBooleanArray selected = subjectlist.getCheckedItemPositions();
                                                for (int i = 0; i < subjectlist.getCount(); i++) {
                                                    if (selected.get(i)) {
                                                        arr[0][0][i] = (String) subjectlist.getItemAtPosition(i);
                                                    } else {
                                                        arr[0][0][i] = "";
                                                    }
                                                }
                                            }
                                        });
                                        for (int i = 0; i < subjectlist.getCount(); i++) {
                                            if (!arr[0][0][i].equals("")) {
                                                subjectlist.setItemChecked(i, true);
                                            } else if (arr[0][0][i].equals("")) {
                                                subjectlist.setItemChecked(i, false);
                                            }
                                            Adaptergr1_1se.notifyDataSetChanged();
                                        }
                                        break;
                                    case 1:
                                        subjectlist.setAdapter(Adaptergr1_2se);
                                        subjectlist.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
                                        subjectlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                            @Override
                                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                                SparseBooleanArray selected = subjectlist.getCheckedItemPositions();
                                                for (int i = 0; i < subjectlist.getCount(); i++) {
                                                    if (selected.get(i)) {
                                                        arr[0][1][i] = (String) subjectlist.getItemAtPosition(i);
                                                    } else {
                                                        arr[0][1][i] = "";
                                                    }
                                                }
                                            }
                                        });
                                        for (int i = 0; i < subjectlist.getCount(); i++) {
                                            if (!arr[0][1][i].equals("")) {
                                                subjectlist.setItemChecked(i, true);
                                            } else if (arr[0][1][i].equals("")) {
                                                subjectlist.setItemChecked(i, false);
                                            }
                                            Adaptergr1_2se.notifyDataSetChanged();
                                        }
                                        break;
                                }
                            }
                        });
                        break;
                    case 1:
                        subjectlist.setVisibility(INVISIBLE);
                        semesterlist.setAdapter(Adapter2);
                        semesterlist.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
                        semesterlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                subjectlist.setVisibility(VISIBLE);
                                subjectall.setVisibility(VISIBLE);
                                switch (position) {
                                    case 0:
                                        subjectlist.setAdapter(Adaptergr2_1se);
                                        subjectlist.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
                                        subjectlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                            @Override
                                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                                SparseBooleanArray selected = subjectlist.getCheckedItemPositions();
                                                for (int i = 0; i < subjectlist.getCount(); i++) {
                                                    if (selected.get(i)) {
                                                        arr[1][0][i] = (String) subjectlist.getItemAtPosition(i);
                                                    } else {
                                                        arr[1][0][i] = "";
                                                    }
                                                }

                                            }
                                        });
                                        for (int i = 0; i < subjectlist.getCount(); i++) {
                                            if (!arr[1][0][i].equals("")) {
                                                subjectlist.setItemChecked(i, true);
                                            } else if (arr[1][0][i].equals("")) {
                                                subjectlist.setItemChecked(i, false);
                                            }

                                            Adaptergr2_1se.notifyDataSetChanged();
                                        }
                                        break;
                                    case 1:
                                        subjectlist.setAdapter(Adaptergr2_2se);
                                        subjectlist.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
                                        subjectlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                            @Override
                                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                                SparseBooleanArray selected = subjectlist.getCheckedItemPositions();
                                                for (int i = 0; i < subjectlist.getCount(); i++) {
                                                    if (selected.get(i)) {
                                                        arr[1][1][i] = (String) subjectlist.getItemAtPosition(i);
                                                    } else {
                                                        arr[1][1][i] = "";
                                                    }

                                                }

                                            }
                                        });
                                        for (int i = 0; i < subjectlist.getCount(); i++) {
                                            if (!arr[1][1][i].equals("")) {
                                                subjectlist.setItemChecked(i, true);
                                            } else if (arr[1][1][i].equals("")) {
                                                subjectlist.setItemChecked(i, false);
                                            }

                                            Adaptergr2_2se.notifyDataSetChanged();
                                        }
                                        break;
                                }
                            }
                        });
                        break;
                    case 2:
                        subjectlist.setVisibility(INVISIBLE);
                        semesterlist.setAdapter(Adapter2);
                        semesterlist.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
                        semesterlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                subjectlist.setVisibility(VISIBLE);
                                subjectall.setVisibility(VISIBLE);
                                switch (position) {
                                    case 0:
                                        subjectlist.setAdapter(Adaptergr3_1se);
                                        subjectlist.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
                                        subjectlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                            @Override
                                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                                SparseBooleanArray selected = subjectlist.getCheckedItemPositions();
                                                for (int i = 0; i < subjectlist.getCount(); i++) {
                                                    if (selected.get(i)) {
                                                        arr[2][0][i] = (String) subjectlist.getItemAtPosition(i);
                                                    } else {
                                                        arr[2][0][i] = "";
                                                    }

                                                }

                                            }
                                        });
                                        for (int i = 0; i < subjectlist.getCount(); i++) {
                                            if (!arr[2][0][i].equals("")) {
                                                subjectlist.setItemChecked(i, true);
                                            } else if (arr[2][0][i].equals("")) {
                                                subjectlist.setItemChecked(i, false);
                                            }

                                            Adaptergr3_1se.notifyDataSetChanged();
                                        }
                                        break;
                                    case 1:
                                        subjectlist.setAdapter(Adaptergr3_2se);
                                        subjectlist.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
                                        subjectlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                            @Override
                                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                                SparseBooleanArray selected = subjectlist.getCheckedItemPositions();
                                                for (int i = 0; i < subjectlist.getCount(); i++) {
                                                    if (selected.get(i)) {
                                                        arr[2][1][i] = (String) subjectlist.getItemAtPosition(i);
                                                    } else {
                                                        arr[2][1][i] = "";
                                                    }
                                                }

                                            }
                                        });
                                        for (int i = 0; i < subjectlist.getCount(); i++) {
                                            if (!arr[2][1][i].equals("")) {
                                                subjectlist.setItemChecked(i, true);
                                            } else if (arr[2][1][i].equals("")) {
                                                subjectlist.setItemChecked(i, false);
                                            }

                                            Adaptergr3_2se.notifyDataSetChanged();
                                        }
                                        break;
                                }
                            }
                        });
                        break;
                    case 3:
                        subjectlist.setVisibility(INVISIBLE);
                        semesterlist.setAdapter(Adapter2);
                        semesterlist.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
                        semesterlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                subjectlist.setVisibility(VISIBLE);
                                subjectall.setVisibility(VISIBLE);
                                switch (position) {
                                    case 0:
                                        subjectlist.setAdapter(Adaptergr4_1se);
                                        subjectlist.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
                                        subjectlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                            @Override
                                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                                SparseBooleanArray selected = subjectlist.getCheckedItemPositions();
                                                for (int i = 0; i < subjectlist.getCount(); i++) {
                                                    if (selected.get(i)) {
                                                        arr[3][0][i] = (String) subjectlist.getItemAtPosition(i);
                                                    } else {
                                                        arr[3][0][i] = "";
                                                    }
                                                }
                                            }
                                        });
                                        for (int i = 0; i < subjectlist.getCount(); i++) {
                                            if (!arr[3][0][i].equals("")) {
                                                subjectlist.setItemChecked(i, true);
                                            } else if (arr[3][0][i].equals("")) {
                                                subjectlist.setItemChecked(i, false);
                                            }

                                            Adaptergr4_1se.notifyDataSetChanged();
                                        }
                                        break;
                                    case 1:
                                        subjectlist.setAdapter(Adaptergr4_2se);
                                        subjectlist.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
                                        subjectlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                            @Override
                                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                                SparseBooleanArray selected = subjectlist.getCheckedItemPositions();
                                                for (int i = 0; i < subjectlist.getCount(); i++) {
                                                    if (selected.get(i)) {
                                                        arr[3][1][i] = (String) subjectlist.getItemAtPosition(i);
                                                    } else {
                                                        arr[3][1][i] = "";
                                                    }
                                                }
                                            }
                                        });
                                        for (int i = 0; i < subjectlist.getCount(); i++) {
                                            if (!arr[3][1][i].equals("")) {
                                                subjectlist.setItemChecked(i, true);
                                            } else if (arr[3][1][i].equals("")) {
                                                subjectlist.setItemChecked(i, false);
                                            }

                                            Adaptergr4_2se.notifyDataSetChanged();
                                        }
                                        break;
                                }
                            }
                        });
                        break;
                }
            }
        });

        subjectall.setVisibility(View.GONE);
        subjectall.setOnClickListener(new Button.OnClickListener() { //과목 전체선택
            public void onClick(View v) {
                int count = 0;
                SparseBooleanArray sel = gradelist.getCheckedItemPositions();
                if (sel.get(0)) {
                    SparseBooleanArray sel2 = semesterlist.getCheckedItemPositions();
                    if (sel2.get(0)) {
                        count = subjectlist.getCount();
                        for (int i = 0; i < count; i++) {
                            subjectlist.setItemChecked(i, true);
                        }
                        for (int i = 0; i < subjectlist.getCount(); i++) {
                            arr[0][0][i] = (String) subjectlist.getItemAtPosition(i);
                        }
                    } else if (sel2.get(1)) {
                        count = subjectlist.getCount();
                        for (int i = 0; i < count; i++) {
                            subjectlist.setItemChecked(i, true);
                        }
                        for (int i = 0; i < subjectlist.getCount(); i++) {
                            arr[0][1][i] = (String) subjectlist.getItemAtPosition(i);
                        }
                    }
                } else if (sel.get(1)) {
                    SparseBooleanArray sel2 = semesterlist.getCheckedItemPositions();
                    if (sel2.get(0)) {
                        count = subjectlist.getCount();
                        for (int i = 0; i < count; i++) {
                            subjectlist.setItemChecked(i, true);
                        }
                        for (int i = 0; i < subjectlist.getCount(); i++) {
                            arr[1][0][i] = (String) subjectlist.getItemAtPosition(i);
                        }
                    } else if (sel2.get(1)) {
                        count = subjectlist.getCount();
                        for (int i = 0; i < count; i++) {
                            subjectlist.setItemChecked(i, true);
                        }
                        for (int i = 0; i < subjectlist.getCount(); i++) {
                            arr[1][1][i] = (String) subjectlist.getItemAtPosition(i);
                        }
                    }
                }
                if (sel.get(2)) {
                    SparseBooleanArray sel2 = semesterlist.getCheckedItemPositions();
                    if (sel2.get(0)) {
                        count = subjectlist.getCount();
                        for (int i = 0; i < count; i++) {
                            subjectlist.setItemChecked(i, true);
                        }
                        for (int i = 0; i < subjectlist.getCount(); i++) {
                            arr[2][0][i] = (String) subjectlist.getItemAtPosition(i);
                        }
                    } else if (sel2.get(1)) {
                        count = subjectlist.getCount();
                        for (int i = 0; i < count; i++) {
                            subjectlist.setItemChecked(i, true);
                        }
                        for (int i = 0; i < subjectlist.getCount(); i++) {
                            arr[2][1][i] = (String) subjectlist.getItemAtPosition(i);
                        }
                    }

                } else if (sel.get(3)) {
                    SparseBooleanArray sel2 = semesterlist.getCheckedItemPositions();
                    if (sel2.get(0)) {
                        count = subjectlist.getCount();
                        for (int i = 0; i < count; i++) {
                            subjectlist.setItemChecked(i, true);
                        }
                        for (int i = 0; i < subjectlist.getCount(); i++) {
                            arr[3][0][i] = (String) subjectlist.getItemAtPosition(i);
                        }
                    } else if (sel2.get(1)) {
                        count = subjectlist.getCount();
                        for (int i = 0; i < count; i++) {
                            subjectlist.setItemChecked(i, true);
                        }
                        for (int i = 0; i < subjectlist.getCount(); i++) {
                            arr[3][1][i] = (String) subjectlist.getItemAtPosition(i);
                        }
                    }
                }
            }
        });


        init.setOnClickListener(new View.OnClickListener() { //전체 초기화
            @Override
            public void onClick(View v) {
                subjectlist.clearChoices();
                Adaptergr1_1se.notifyDataSetChanged();
                Adaptergr1_2se.notifyDataSetChanged();
                Adaptergr2_1se.notifyDataSetChanged();
                Adaptergr2_2se.notifyDataSetChanged();
                Adaptergr3_1se.notifyDataSetChanged();
                Adaptergr3_2se.notifyDataSetChanged();
                Adaptergr4_1se.notifyDataSetChanged();
                Adaptergr4_2se.notifyDataSetChanged();
                arr = new String[4][2][8];
            }
        });

        reg_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 2; j++) {
                        for (int k = 0; k < 8; k++) {
                            if (arr[i][j][k] != null) {
                                arrData.add(arr[i][j][k]);
                            } else if (arr[i][j][k] == null) {
                                arrData.add("");
                            }
                        }
                    }
                }

                setStringArrayPref(getApplicationContext(), SETTINGS_PLAYER_JSON, arrData);
                finish();
            }
        });

            arrData2 = getStringArrayPref(getApplicationContext(), SETTINGS_PLAYER_JSON);
            int s = 0;
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 2; j++) {
                    for (int k = 0; k < 8; k++) {
                        if(!arrData2.isEmpty()){
                        arr[i][j][k] = arrData2.get(s);
                        s = s + 1;
                        }
                        else if(arrData2.isEmpty()){
                            arr[i][j][k] = "";
                    }
                }
            }
        }
    }

    private void setStringArrayPref(Context context, String key, ArrayList<String> values) {

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        JSONArray a = new JSONArray();

        for (int i = 0; i < values.size(); i++) {
            a.put(values.get(i));
        }

        if (values != null) {
            editor.putString(key, a.toString());
        } else {
            editor.putString(key, "");
        }
        editor.apply();
    }

    private ArrayList getStringArrayPref(Context context, String key) {

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        String json = prefs.getString(key, null);
        ArrayList urls = new ArrayList();

        if (json != null) {
            try {
                JSONArray a = new JSONArray(json);

                for (int i = 0; i < a.length(); i++) {
                    String url = a.optString(i);
                    urls.add(url);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return urls;
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}