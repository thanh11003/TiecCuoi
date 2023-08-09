package com.example.tieccuoi.addedit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.tieccuoi.DTO.MonAn;
import com.example.tieccuoi.DTO.Sanh;
import com.example.tieccuoi.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Random;

public class AddEditSanhActivity extends AppCompatActivity {

    TextInputLayout tiTenSanh, tiGiaSanh, tiThongTinSanh, tiSoLuongBanToiDa, tiDonGiaBan;
    TextInputEditText edTenSanh, edGiaSanh, edThongTinSanh, edSoLuongBanToiDa, edDonGiaBan;
    Sanh sanhEdit;
    int flag;
    Button btnSaveSanh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_sanh);

        tiTenSanh = findViewById(R.id.tiTenSanh);
        tiGiaSanh = findViewById(R.id.tiGiaSanh);
        tiThongTinSanh = findViewById(R.id.tiThongTinSanh);
        tiSoLuongBanToiDa = findViewById(R.id.tiSoLuongBanToiDa);
        tiDonGiaBan = findViewById(R.id.tiDonGiaBan);

        edTenSanh = findViewById(R.id.edSanh);
        edGiaSanh = findViewById(R.id.edGiaSanh);
        edThongTinSanh = findViewById(R.id.edThongTinSanh);
        edSoLuongBanToiDa = findViewById(R.id.edSoLuongBanToiDa);
        edDonGiaBan = findViewById(R.id.edDonGiaBan);

        btnSaveSanh = findViewById(R.id.btnSaveSanh);

        Intent intent = getIntent();
        flag = intent.getIntExtra("flag", 0);
        if (flag == 1) {
        } else {

            sanhEdit = (Sanh) intent.getSerializableExtra("sanh");
            edTenSanh.setText(sanhEdit.getTenSanh());
            edGiaSanh.setText(sanhEdit.getGia());
            edThongTinSanh.setText(sanhEdit.getThongTin());
            edSoLuongBanToiDa.setText(sanhEdit.getSoLuongBanTD());
            edDonGiaBan.setText(sanhEdit.getDonGiaBan());

        }

        btnSaveSanh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.getId() == R.id.btnSaveSanh) {
                    if (edTenSanh.getText().toString().isEmpty()
                            || edGiaSanh.getText().toString().isEmpty()
                            || edThongTinSanh.getText().toString().isEmpty()
                            || edSoLuongBanToiDa.getText().toString().isEmpty()
                            || edDonGiaBan.getText().toString().isEmpty()) {
                        tiTenSanh.setError("Not null");
                        tiGiaSanh.setError("Not null");
                        tiThongTinSanh.setError("Not null");
                        tiSoLuongBanToiDa.setError("Not null");
                        tiDonGiaBan.setError("Not null");
                    } else {
                        if (flag == 1) {
                            Sanh sanh = new Sanh(new Random().nextInt(9999),
                                    edTenSanh.getText().toString(),
                                    edGiaSanh.getText().toString(),
                                    "",
                                    edSoLuongBanToiDa.getText().toString(),
                                    edDonGiaBan.getText().toString(),
                                    edThongTinSanh.getText().toString());


                            Intent intent = new Intent();
                            intent.putExtra("sanh", sanh);
                            intent.putExtra("flag", 1);
                            setResult(RESULT_OK, intent);
                            finish();
                        } else {
                            Sanh sanh= new Sanh(sanhEdit.getId(),
                                    edTenSanh.getText().toString(),
                                    edGiaSanh.getText().toString(),
                                    sanhEdit.getImage(),
                                    edSoLuongBanToiDa.getText().toString(),
                                    edDonGiaBan.getText().toString(),
                                    edThongTinSanh.getText().toString());

                            Intent intent = new Intent();
                            intent.putExtra("sanh", sanh);
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