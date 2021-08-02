package com.example.soc.board.Menu.Comty;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.soc.login.LoginActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;

import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.soc.R;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Comty_Problem_write extends AppCompatActivity{
    private static final int PICK_FROM_ALBUM = 201;
    private static final int PICK_FROM_ALBUM2 = 202;
    private ImageView image1;
    private ImageView image2;
    private TextView nickname,content,time;
    private Spinner gradeSpinner, semesterSpinner, subjectSpinner;
    private ArrayAdapter<String> arrayAdapter;
    public static final String EXTRA_ADDRESS = "address";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comty__problem_write);
        gradeSpinner = (Spinner) findViewById(R.id.spinner1);
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, (String[]) getResources().getStringArray(R.array.grade));
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gradeSpinner.setAdapter(arrayAdapter);

        semesterSpinner = (Spinner) findViewById(R.id.spinner2);
        subjectSpinner = (Spinner) findViewById(R.id.spinner3);

        initAddressSpinner();

        tedPermission(); //사진 권한 접근

        image1 = (ImageView) findViewById(R.id.img1);
        image2 = (ImageView) findViewById(R.id.img2);
        Button reg_button = (Button) findViewById(R.id.reg_button);
        ImageButton backbutton = (ImageButton) findViewById(R.id.backbutton);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        reg_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogClick(v);
            }
        });
        backbutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });

        image1.setOnClickListener(new View.OnClickListener() { //이미지뷰 사진넣기기
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setDataAndType(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(intent, PICK_FROM_ALBUM);
            }
        });
        image2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setDataAndType(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(intent, PICK_FROM_ALBUM2);
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
        Uri selectedImageUri;
        RequestOptions option1 = new RequestOptions().centerCrop();
        MultiTransformation option2 = new MultiTransformation(new CenterCrop(), new RoundedCorners(8));
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null && data.getData() != null) {
            switch (requestCode) {
                case PICK_FROM_ALBUM:
                    selectedImageUri = data.getData();
                    Glide.with(getApplicationContext()).load(selectedImageUri).apply(option1).into(image1);
                    break;
                case PICK_FROM_ALBUM2:
                    selectedImageUri = data.getData();
                    Glide.with(getApplicationContext()).load(selectedImageUri).apply(RequestOptions.bitmapTransform(option2)).into(image2);
                    break;
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