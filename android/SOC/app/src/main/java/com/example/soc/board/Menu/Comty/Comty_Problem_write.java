package com.example.soc.board.Menu.Comty;
import android.Manifest;
import android.app.AlertDialog;
import android.content.ClipData;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.example.soc.R;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;
import java.util.ArrayList;


public class Comty_Problem_write extends  AppCompatActivity{
    private static final int PICK_FROM_ALBUM = 100;
    private ImageView image1;
    private ImageView image2;
    private ImageButton image1_del, image2_del;
    private TextView nickname,content,time;
    private Spinner gradeSpinner, semesterSpinner, subjectSpinner;
    private ArrayAdapter<String> arrayAdapter;
    public static final String EXTRA_ADDRESS = "address";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comty__problem_write);

        //스피너
        gradeSpinner = (Spinner) findViewById(R.id.spinner1);
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, (String[]) getResources().getStringArray(R.array.grade)); //스피너를 위한 배열 어댑터 선언
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gradeSpinner.setAdapter(arrayAdapter);
        semesterSpinner = (Spinner) findViewById(R.id.spinner2);
        subjectSpinner = (Spinner) findViewById(R.id.spinner3);
        initAddressSpinner();

        //이미지뷰
        tedPermission(); //사진 권한 접근

       image1 = (ImageView) findViewById(R.id.img1_write);
       image2 = (ImageView) findViewById(R.id.img2_write);
       image1_del = (ImageButton) findViewById(R.id.delete_image1);
       image1_del.setVisibility(View.INVISIBLE);
       image2_del = (ImageButton) findViewById(R.id.delete_image2);
       image2_del.setVisibility(View.INVISIBLE);
        ImageButton imageBtn= (ImageButton) findViewById(R.id.wirte_imgBt);
        ImageButton backbutton = (ImageButton) findViewById(R.id.backbutton_write);
        imageBtn.setOnClickListener(new View.OnClickListener() { //이미지뷰 사진넣기
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent,PICK_FROM_ALBUM);
            }
        });

        //글쓰기 등록버튼
        Button reg_button = (Button) findViewById(R.id.reg_button);
        reg_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogClick(v);
            }
        });

        //뒤로가기 버튼
        backbutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });

        //화면클릭시 키보드 숨김
        LinearLayout ll1 = (LinearLayout) findViewById(R.id.ll_write);
        ll1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        });
    }

    private void initAddressSpinner() {
        gradeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                        setsemesterSpinnerAdapterItem((R.array.semester));
                }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        semesterSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (gradeSpinner.getSelectedItemPosition() == 0 && semesterSpinner.getSelectedItemPosition() > -1) {
                    switch (position) {
                        case 0:
                            setsubjectSpinnerAdapterItem(R.array.gr1_1se);
                            break;
                        case 1:
                            setsubjectSpinnerAdapterItem(R.array.gr1_2se);
                            break;
                    }
                } else if (gradeSpinner.getSelectedItemPosition() == 1 && semesterSpinner.getSelectedItemPosition() > -1) {
                    switch (position) {
                        case 0:
                            setsubjectSpinnerAdapterItem(R.array.gr2_1se);
                            break;
                        case 1:
                            setsubjectSpinnerAdapterItem(R.array.gr2_2se);
                            break;
                    }
                } else if (gradeSpinner.getSelectedItemPosition() == 2 && semesterSpinner.getSelectedItemPosition() > -1) {
                    switch (position) {
                        case 0:
                            setsubjectSpinnerAdapterItem(R.array.gr3_1se);
                            break;
                        case 1:
                            setsubjectSpinnerAdapterItem(R.array.gr3_2se);
                            break;
                    }
                }
                else if(gradeSpinner.getSelectedItemPosition() == 3 && semesterSpinner.getSelectedItemPosition() > -1) {
                    switch (position) {
                        case 0:
                            setsubjectSpinnerAdapterItem(R.array.gr4_1se);
                            break;
                        case 1:
                            setsubjectSpinnerAdapterItem(R.array.gr4_2se);
                            break;
                    }
                }
                else {
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void setsemesterSpinnerAdapterItem(int array_resource) {
        if (arrayAdapter != null) {
            semesterSpinner.setAdapter(null);
            arrayAdapter = null;
        }

        if (gradeSpinner.getSelectedItemPosition() > 1) {
            subjectSpinner.setAdapter(null);
        }

        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, (String[])getResources().getStringArray(array_resource));
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        semesterSpinner.setAdapter(arrayAdapter);
    }

    private void setsubjectSpinnerAdapterItem(int array_resource) {
        if (arrayAdapter != null) {
            subjectSpinner.setAdapter(null);
            arrayAdapter = null;
        }

        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, (String[])getResources().getStringArray(array_resource));
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        subjectSpinner.setAdapter(arrayAdapter);
    }
    private void tedPermission() {

        PermissionListener permissionListener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {
                // 권한 요청 성공

            }

            @Override
            public void onPermissionDenied(ArrayList<String> deniedPermissions) {
                // 권한 요청 실패
            }
        };

        TedPermission.with(this)
                .setPermissionListener(permissionListener)
                .setRationaleMessage(getResources().getString(R.string.permission_2))
                .setDeniedMessage(getResources().getString(R.string.permission_1))
                .setPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA)
                .check();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_FROM_ALBUM && resultCode == RESULT_OK) {
            ClipData clipData = data.getClipData();
            Log.i("clipdata", String.valueOf(clipData.getItemCount()));
             if (clipData.getItemCount() == 1) {
                 Uri urione = clipData.getItemAt(0).getUri();
                 image1.setImageURI(urione);
                 image1_del.setVisibility(View.VISIBLE);
                 image1_del.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View v) {
                        image1.setImageResource(0);
                         image1_del.setVisibility(View.INVISIBLE);
                     }
                 });
             }
             else if (clipData.getItemCount() == 2) {
                 image1_del.setVisibility(View.VISIBLE);
                 image1_del.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View v) {
                         image1.setImageResource(0);
                         image1_del.setVisibility(View.INVISIBLE);
                     }
                 });
                 image2_del.setVisibility(View.VISIBLE);
                 image2_del.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View v) {
                         image2.setImageResource(0);
                         image2_del.setVisibility(View.INVISIBLE);
                     }
                 });
                for (int i = 0; i <clipData.getItemCount(); i++) {
                    Uri uritwo = clipData.getItemAt(i).getUri();
                    switch (i) {
                        case 0:
                            image1.setImageURI(uritwo);
                            break;
                        case 1:
                            image2.setImageURI(uritwo);
                    }
                }
            }
            else if (clipData.getItemCount() > 3) {
                Toast.makeText(this, "사진은 2장까지 선택 가능합니다.", Toast.LENGTH_LONG).show();
            }
            // setting 1st selected image into image switcher
            // show this if no image is selected
            else {
                Toast.makeText(this, "You haven't picked Image", Toast.LENGTH_LONG).show();
            }
        }
        }
    public void DialogClick(View view) {
        AlertDialog.Builder msgBuilder = new AlertDialog.Builder(Comty_Problem_write.this)
                .setTitle("알림")
                .setMessage("게시글이 등록되었습니다.")
                .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
        AlertDialog msgDlg = msgBuilder.create();
        msgDlg.show();
    }
    public void Dialogselect(View view) {
        AlertDialog.Builder msgBuilder = new AlertDialog.Builder(Comty_Problem_write.this)
                .setTitle("알림")
                .setMessage("과목을 설정해주세요.")
                .setPositiveButton("확인", null);
        AlertDialog msgDlg = msgBuilder.create();
        msgDlg.show();
    }
}