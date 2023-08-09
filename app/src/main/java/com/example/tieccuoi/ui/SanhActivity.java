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
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tieccuoi.DB.DBHelper;
import com.example.tieccuoi.DTO.MonAn;
import com.example.tieccuoi.DTO.Sanh;
import com.example.tieccuoi.R;
import com.example.tieccuoi.adapter.MonAnAdapter;
import com.example.tieccuoi.adapter.SanhAdapter;
import com.example.tieccuoi.addedit.AddEditMonAnActivity;
import com.example.tieccuoi.addedit.AddEditSanhActivity;
import com.example.tieccuoi.fragment.ManageFragment;
import com.example.tieccuoi.uiCT.CTMonAnActivity;
import com.example.tieccuoi.uiCT.CTSanhActivity;

import java.util.ArrayList;

public class SanhActivity extends AppCompatActivity implements SanhAdapter.Listener {

    RecyclerView rvSanh;
    TextView tvThemSanh;
    ImageView ivBack;
    ArrayList<Sanh> sanhs;
    SanhAdapter sanhAdapter;
    DBHelper dbHelper;
    int position;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sanh);

        rvSanh = findViewById(R.id.rvSanh);
        ivBack = findViewById(R.id.ivBackSanh);
        tvThemSanh = findViewById(R.id.tvThemSanh);

        dbHelper = new DBHelper(SanhActivity.this);

        sanhs = dbHelper.getSanh();
        tvThemSanh.setOnClickListener(ThemSanh());
        ivBack.setOnClickListener(Back());

        sanhAdapter = new SanhAdapter(SanhActivity.this, sanhs, SanhActivity.this);
        rvSanh.setAdapter(sanhAdapter);
        rvSanh.setLayoutManager(new GridLayoutManager(SanhActivity.this, 2));
        rvSanh.addItemDecoration(new DividerItemDecoration(SanhActivity.this,LinearLayoutManager.VERTICAL));
    }

    @NonNull
    private View.OnClickListener ThemSanh() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SanhActivity.this, AddEditSanhActivity.class);
                intent.putExtra("flag", 1);
                mLaucher.launch(intent);
            }
        };
    }

    @NonNull
    private View.OnClickListener Back() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SanhActivity.this, ManageFragment.class);
                startActivity(intent);
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
                            Sanh sanh = (Sanh) result.getData().getSerializableExtra("sanh");
                            if(dbHelper.ThemSanh(sanh)>0){
                                Toast.makeText(SanhActivity.this, "Thành Công", Toast.LENGTH_SHORT).show();
                            }
                            sanhs.clear();
                            sanhs.addAll(dbHelper.getSanh());
                            sanhAdapter.notifyDataSetChanged();
                        }else {
                            Sanh sanh = (Sanh) result.getData().getSerializableExtra("sanh");
                            dbHelper.SuaSanh(sanh);
                            sanhs.clear();
                            sanhs.addAll(dbHelper.getSanh());
                            sanhAdapter.notifyDataSetChanged();
                        }
                    }
                }
            }
    );
    @Override
    public void OnItemListener(int pos, Sanh sanh) {
        position = pos;
        Intent intent = new Intent(SanhActivity.this, CTSanhActivity.class);
        intent.putExtra("sanh", sanh);
        startActivity(intent);
    }

    @Override
    public void OnEditListener(int pos, Sanh sanh) {
        position =pos;
        Intent intent = new Intent(SanhActivity.this, AddEditSanhActivity.class);
        intent.putExtra("flag",2);
        intent.putExtra("sanh", sanh);


        mLaucher.launch(intent);
    }

    @Override
    public void OnDeleteListener(Sanh sanh) {
        AlertDialog.Builder builder = new AlertDialog.Builder(SanhActivity.this);
        builder.setTitle("Xóa sảnh tiệc");
        builder.setMessage("Bạn có chắc chắn muốn xóa sảnh này ?");
        builder.setNegativeButton("Không", (dialogInterface, i) -> {
            dialogInterface.cancel();

        });
        builder.setPositiveButton("Có", (dialogInterface, i) -> {
            dbHelper.XoaSanh(sanh);
            sanhs.clear();
            sanhs.addAll(dbHelper.getSanh());
            sanhAdapter.notifyDataSetChanged();
            dialogInterface.dismiss();

        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}