package com.example.tieccuoi.uiCT;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tieccuoi.DTO.MonAn;
import com.example.tieccuoi.R;
import com.example.tieccuoi.ui.MonAnActivity;

public class CTMonAnActivity extends AppCompatActivity {

    TextView tvLoai, tvTenMonAn, tvGia, tvThongTin;
    ImageView ivMonAn,ivBack;
    MonAn monAn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ctmon_an);

        tvLoai.findViewById(R.id.tvLoai);
        tvTenMonAn.findViewById(R.id.tvTenMonAn);
        tvGia.findViewById(R.id.tvGiaCT);
        tvThongTin.findViewById(R.id.tvThongTin);
        ivMonAn.findViewById(R.id.ivMonAn);
        ivBack.findViewById(R.id.ivBackMonAn);

        Intent intent = getIntent();

        monAn = (MonAn) intent.getSerializableExtra("monan");
        tvLoai.setText(monAn.getLoai());


        ivBack.setOnClickListener(Back());
    }

    @NonNull
    private View.OnClickListener Back() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(CTMonAnActivity.this, MonAnActivity.class);
                startActivity(i);
            }
        };
    }
}