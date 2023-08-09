package com.example.tieccuoi.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tieccuoi.DB.DBHelper;
import com.example.tieccuoi.DTO.NhanVien;
import com.example.tieccuoi.MainActivity;
import com.example.tieccuoi.R;
import com.example.tieccuoi.fragment.HomeFragment;

public class LoginActivity extends AppCompatActivity {
    EditText txEmail, txMatkhau;
    TextView forget;
    Button btLogin, btLogin1;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        forget = (TextView) findViewById(R.id.forget);
        btLogin = findViewById(R.id.btLogin);
        txEmail = (EditText) findViewById(R.id.txEmail);
        txMatkhau = (EditText) findViewById(R.id.txMatkhau);
        btLogin1 = findViewById(R.id.btLogin1);


        NhanvaoLogin();
        NhanVaoResign();
        QuenMatKhau();


    }

    private void QuenMatKhau() {
        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Intent i = new Intent(LoginActivity.this, ForgetActivity.class);

            }
        });
    }

    private View.OnClickListener NhanVaoResign() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent i = new Intent(LoginActivity.this, ResignActivity.class);
//                startActivity(i);
            }
        };
    }


    private void NhanvaoLogin() {
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                String user = txEmail.getText().toString();
//                String password = txMatkhau.getText().toString();
//                NhanVien nv = dbHelper.selectbID(user);
//
//
//                if (nv == null) {
//                    Toast.makeText(getApplicationContext(), "Tài khoản không tồn tại", Toast.LENGTH_SHORT).show();
//                } else if (!nv.getMatKhau().equals(password)) {
//                    Toast.makeText(getApplicationContext(), "Mật khẩu không chính xác", Toast.LENGTH_SHORT).show();
//                } else {
//                    Intent i = new Intent(LoginActivity.this, HomeFragment.class);
//                    startActivity(i);
//                    Toast.makeText(getApplicationContext(), "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
//                }
                Intent i = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(i);

            }
        });
    }

}