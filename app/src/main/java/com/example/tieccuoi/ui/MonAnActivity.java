package com.example.tieccuoi.ui;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tieccuoi.DB.DBHelper;
import com.example.tieccuoi.DTO.MonAn;
import com.example.tieccuoi.MainActivity;
import com.example.tieccuoi.R;
import com.example.tieccuoi.adapter.MonAnAdapter;
import com.example.tieccuoi.addedit.AddEditMonAnActivity;
import com.example.tieccuoi.fragment.ManageFragment;
import com.example.tieccuoi.uiCT.CTMonAnActivity;

import java.util.ArrayList;

public class MonAnActivity extends AppCompatActivity implements MonAnAdapter.Listener{

    RecyclerView rvMonAn;
    ImageView ivBack;
    TextView tvThemMonAn;
    MonAnAdapter monAnAdapter;
    ArrayList<MonAn> monAns;
    DBHelper dbHelper;
    int position;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mon_an);

        rvMonAn = findViewById(R.id.rvMonAn);
        ivBack = findViewById(R.id.ivBack);
        tvThemMonAn = findViewById(R.id.tvThemMonAn);

        dbHelper = new DBHelper(MonAnActivity.this);

        monAns = dbHelper.getMonAns();
        tvThemMonAn.setOnClickListener(ThemMonAn());
        ivBack.setOnClickListener(Back());


        monAnAdapter = new MonAnAdapter(MonAnActivity.this, monAns, MonAnActivity.this);
        rvMonAn.setAdapter(monAnAdapter);
        rvMonAn.setLayoutManager(new LinearLayoutManager(MonAnActivity.this, LinearLayoutManager.VERTICAL, false));
        rvMonAn.addItemDecoration(new DividerItemDecoration(MonAnActivity.this, LinearLayoutManager.VERTICAL));

    }

    @NonNull
    private View.OnClickListener Back() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MonAnActivity.this, MainActivity.class);
                startActivity(i);
            }
        };
    }

    @NonNull
    private View.OnClickListener ThemMonAn() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MonAnActivity.this, AddEditMonAnActivity.class);
                intent.putExtra("flag",1);
                mLaucher.launch(intent);
            }
        };
    }

    ActivityResultLauncher<Intent> mLaucher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK) {
                        if (result.getData().getIntExtra("flag", 0) == 1) {
                            MonAn monAn = (MonAn) result.getData().getSerializableExtra("monan");
                            if(dbHelper.ThemMonAn(monAn)>0){
                                Toast.makeText(MonAnActivity.this, "Thành Công", Toast.LENGTH_SHORT).show();
                            }
                            monAns.clear();
                            monAns.addAll(dbHelper.getMonAns());
                            monAnAdapter.notifyDataSetChanged();
                        }else {
                            MonAn monAn = (MonAn) result.getData().getSerializableExtra("monan");
                            dbHelper.SuaMonAn(monAn);
                            monAns.clear();
                            monAns.addAll(dbHelper.getMonAns());
                            monAnAdapter.notifyDataSetChanged();
                        }
                    }
                }
            }
    );

    @Override
    public void OnItemListener(int pos, MonAn monAn) {
        position = pos;
        Intent intent = new Intent(MonAnActivity.this, CTMonAnActivity.class);
        intent.putExtra("monan", monAn);
        startActivity(intent);
    }

    @Override
    public void OnEditListener(int pos, MonAn monAn) {
        position =pos;
        Intent intent = new Intent(MonAnActivity.this, AddEditMonAnActivity.class);
        intent.putExtra("flag",2);
        intent.putExtra("monan", monAn);


        mLaucher.launch(intent);
    }

    @Override
    public void OnDeleteListener(MonAn monAn) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MonAnActivity.this);
        builder.setTitle("Xóa món ăn");
        builder.setMessage("Bạn có chắc chắn muốn xóa món ăn này ?");
        builder.setNegativeButton("No", (dialogInterface, i) -> {
            dialogInterface.cancel();

        });
        builder.setPositiveButton("Yes", (dialogInterface, i) -> {
            dbHelper.XoaMonAn(monAn);
            monAns.clear();
            monAns.addAll(dbHelper.getMonAns());
            monAnAdapter.notifyDataSetChanged();
            dialogInterface.dismiss();

        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}