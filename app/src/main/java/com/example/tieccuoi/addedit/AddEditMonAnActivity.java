package com.example.tieccuoi.addedit;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tieccuoi.DB.DBHelper;
import com.example.tieccuoi.DTO.LoaiMonAn;
import com.example.tieccuoi.DTO.MonAn;
import com.example.tieccuoi.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Random;

public class AddEditMonAnActivity extends AppCompatActivity {

    TextInputLayout tiTenMonAn, tiGia, tiThongTin;
    TextInputEditText edTenMonAn, edGia, edThongTin;
    Spinner spLoai;
    MonAn  monAnEdit;
    DBHelper dbHelper;
    ArrayList<LoaiMonAn> loaiMonAns;
    ArrayList<String> loai;
    int flag;
    Button btnSaveMN;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_mon_an);

        tiTenMonAn = findViewById(R.id.tiTenSanh);
        tiGia = findViewById(R.id.tiGia);
        tiThongTin = findViewById(R.id.tiThongTin);

        edTenMonAn = findViewById(R.id.edTenMonAn);
        edGia = findViewById(R.id.edGia);
        edThongTin = findViewById(R.id.edThongTin);

        spLoai = findViewById(R.id.spLoai);
        btnSaveMN = findViewById(R.id.btnSaveMN);

        //loaiMonAns = dbHelper.getLoai();

//        for (LoaiMonAn lma: loaiMonAns){
//            loai.add(lma.getTenLoaiMonAn());
//        }
//
//        ArrayAdapter Loai = new ArrayAdapter<>(this, R.layout.activity_add_edit_mon_an, loai);
//        spLoai.setAdapter(Loai);
//
//        spLoai.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
//                String loaima = loai.get(position);
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });
        Intent intent = getIntent();
        flag = intent.getIntExtra("flag", 0);
        if (flag == 1) {
        } else {

            monAnEdit = (MonAn) intent.getSerializableExtra("monan");
            edTenMonAn.setText(monAnEdit.getTenMonAn());
            edGia.setText(monAnEdit.getGia());
            edThongTin.setText(monAnEdit.getThongTin());



        }
        btnSaveMN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.getId() == R.id.btnSaveMN) {
                    if (edTenMonAn.getText().toString().isEmpty()
                            || edGia.getText().toString().isEmpty()
                            || edThongTin.getText().toString().isEmpty()) {
                        tiTenMonAn.setError("Not null");
                        tiGia.setError("Not null");
                        tiThongTin.setError("Not null");
                    } else {
                        if (flag == 1) {
                            MonAn monAn = new MonAn(new Random().nextInt(9999),
                                    edTenMonAn.getText().toString(),
                                    edGia.getText().toString(),
                                    "",
                                    edThongTin.getText().toString(),
                                    spLoai.toString());


                            Intent intent = new Intent();
                            intent.putExtra("monan", monAn);
                            intent.putExtra("flag", 1);
                            setResult(RESULT_OK, intent);
                            finish();
                        } else {
                            MonAn monAn = new MonAn(monAnEdit.getMaMonAn(),
                                    edTenMonAn.getText().toString(),
                                    edGia.getText().toString(),
                                    monAnEdit.getImage(),
                                    edThongTin.getText().toString(),
                                    spLoai.toString());

                            Intent intent = new Intent();
                            intent.putExtra("monan", monAn);
                            intent.putExtra("flag", 2);
                            setResult(RESULT_OK, intent);
                            finish();
                        }
                    }
                }
            }
        });
    }

}