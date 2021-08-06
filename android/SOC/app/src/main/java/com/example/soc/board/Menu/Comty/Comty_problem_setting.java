package com.example.soc.board.Menu.Comty;

import android.app.Activity;
import android.content.Context;
import android.net.sip.SipSession;
import android.os.Bundle;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;

import com.example.soc.R;

public class Comty_problem_setting extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comty_problem_setting);
        Button init = (Button) findViewById(R.id.init);
        Button reg_b = (Button) findViewById(R.id.sign);

        //grade 셋팅
        ArrayAdapter<CharSequence> Adapter;
        Adapter = ArrayAdapter.createFromResource(this, R.array.grade, android.R.layout.simple_list_item_1);
        ListView gradelist = (ListView) findViewById(R.id.gradelist);
        ListView semesterlist = (ListView) findViewById(R.id.semesterlist);
        ListView subjectlist = (ListView) findViewById(R.id.subjectlist);
        ArrayAdapter<CharSequence> Adapter2;
        Adapter2 = ArrayAdapter.createFromResource(this, R.array.semester, android.R.layout.simple_list_item_1);
        gradelist.setAdapter(Adapter);


        //학년,학기에 따른 과목 설정
        //1학년
        ArrayAdapter<CharSequence> Adaptergr1_1se;
        Adaptergr1_1se = ArrayAdapter.createFromResource(this, R.array.gr1_1se, android.R.layout.simple_list_item_1);
        ArrayAdapter<CharSequence> Adaptergr1_2se;
        Adaptergr1_2se = ArrayAdapter.createFromResource(this, R.array.gr1_2se, android.R.layout.simple_list_item_1);

       //2학년
        ArrayAdapter<CharSequence> Adaptergr2_1se;
        Adaptergr2_1se = ArrayAdapter.createFromResource(this, R.array.gr2_1se, android.R.layout.simple_list_item_1);
        ArrayAdapter<CharSequence> Adaptergr2_2se;
        Adaptergr2_2se = ArrayAdapter.createFromResource(this, R.array.gr2_2se, android.R.layout.simple_list_item_1);

        //3학년
        ArrayAdapter<CharSequence> Adaptergr3_1se;
        Adaptergr3_1se = ArrayAdapter.createFromResource(this, R.array.gr3_1se, android.R.layout.simple_list_item_1);
        ArrayAdapter<CharSequence> Adaptergr3_2se;
        Adaptergr3_2se = ArrayAdapter.createFromResource(this, R.array.gr3_2se, android.R.layout.simple_list_item_1);

        //4학년
        ArrayAdapter<CharSequence> Adaptergr4_1se;
        Adaptergr4_1se = ArrayAdapter.createFromResource(this, R.array.gr4_1se, android.R.layout.simple_list_item_1);
        ArrayAdapter<CharSequence> Adaptergr4_2se;
        Adaptergr4_2se = ArrayAdapter.createFromResource(this, R.array.gr4_2se, android.R.layout.simple_list_item_1);

       gradelist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               switch (position) {
                   case 0:
                       System.out.println(position);
                   semesterlist.setAdapter(Adapter2);
                   semesterlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                       @Override
                       public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                           switch (position) {
                               case 0:
                                   subjectlist.setAdapter(Adaptergr1_1se);
                                   break;
                               case 1:
                                   subjectlist.setAdapter(Adaptergr1_2se);
                                   break;
                           }
                       }
                   });
                   break;
                   case 1:
                       semesterlist.setAdapter(Adapter2);
                       semesterlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                           @Override
                           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                               switch (position) {
                                   case 0:
                                       subjectlist.setAdapter(Adaptergr2_1se);
                                       break;
                                   case 1:
                                       subjectlist.setAdapter(Adaptergr2_2se);
                                       break;
                               }
                           }
                       });
                       break;
                   case 2:
                       semesterlist.setAdapter(Adapter2);
                       semesterlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                           @Override
                           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                               switch (position) {
                                   case 0:
                                       subjectlist.setAdapter(Adaptergr3_1se);
                                       break;
                                   case 1:
                                       subjectlist.setAdapter(Adaptergr3_2se);
                                       break;
                               }
                           }
                       });
                       break;
                   case 3:
                       semesterlist.setAdapter(Adapter2);
                       semesterlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                           @Override
                           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                               switch (position) {
                                   case 0:
                                       subjectlist.setAdapter(Adaptergr4_1se);
                                       break;
                                   case 1:
                                       subjectlist.setAdapter(Adaptergr4_2se);
                                       break;
                               }
                           }
                       });
                       break;
               }
           }
       });

            //ListView의 아이템 중 하나가 클릭될 때 호출되는 메소드
            //첫번째 파라미터 : 클릭된 아이템을 보여주고 있는 AdapterView 객체(여기서는 ListView객체)
            //두번째 파라미터 : 클릭된 아이템 뷰
            //세번째 파라미터 : 클릭된 아이템의 위치(ListView이 첫번째 아이템(가장위쪽)부터 차례대로 0,1,2,3.....)
            //네번재 파리미터 : 클릭된 아이템의 아이디(특별한 설정이 없다면 세번째 파라이터인 position과 같은 값)
        //semester 셋팅


        reg_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}