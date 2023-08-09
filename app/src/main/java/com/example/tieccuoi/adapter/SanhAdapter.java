package com.example.tieccuoi.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tieccuoi.DTO.MonAn;
import com.example.tieccuoi.DTO.Sanh;
import com.example.tieccuoi.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class SanhAdapter extends RecyclerView.Adapter<SanhAdapter.SanhVH> implements Filterable {

    Context context;
    ArrayList<Sanh> sanhs;
    ArrayList<Sanh> sanhsFilter;
    Listener listener;


    public SanhAdapter(Listener listener, ArrayList<Sanh> sanhs, Context context) {
        this.listener = listener;
        this.sanhs = sanhs;
        this.sanhsFilter = sanhs;
        this.context = context;
    }

    @NonNull
    @Override
    public SanhVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sanh_grid, parent, false);

        return new SanhVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SanhVH holder, @SuppressLint("RecyclerView") int position) {
        Sanh sanh = sanhsFilter.get(position);
        //holder.imgSanh.setImageResource(sanh.getImage());
        holder.txTenSanh.setText(sanh.getTenSanh());
        holder.txGiaSanh.setText(sanh.getGia());


        try{
            InputStream is = context.getAssets().open(sanh.getImage());
            Bitmap bitmap = BitmapFactory.decodeStream(is);
            holder.imgSanh.setImageBitmap(bitmap);

        } catch (IOException e){
            e.printStackTrace();
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.OnItemListener(position, sanh);
            }
        });
        holder.ivEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.OnEditListener(position, sanh);
            }
        });
        holder.ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.OnDeleteListener(sanh);
            }
        });

    }

    @Override
    public int getItemCount() {
        return sanhs.size();
    }

    public Filter getFilter() {
        SanhFilter sanhFilter = new SanhAdapter.SanhFilter();
        return sanhFilter;
    }


    class SanhVH extends RecyclerView.ViewHolder {
        ImageView imgSanh;
        ImageView ivEdit, ivDelete;
        TextView txTenSanh, txGiaSanh;

        public SanhVH(@NonNull View itemView) {
            super(itemView);
            imgSanh = itemView.findViewById(R.id.imgSanh);
            txTenSanh = itemView.findViewById(R.id.tvTenSanh);
            txGiaSanh = itemView.findViewById(R.id.tvGiaSanh);
            ivEdit = itemView.findViewById(R.id.ivEditSanh);
            ivDelete = itemView.findViewById(R.id.ivDeleteSanh);
        }
    }

    class SanhFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            String charString = constraint.toString();
            if (charString.isEmpty()) {
                sanhsFilter = sanhs;
            } else {
                List<Sanh> filteredList = new ArrayList<>();
                for (Sanh row : sanhs) {
                    if (row.getTenSanh().toLowerCase().contains(charString.toLowerCase())) {
                        filteredList.add(row);

                    }
                }
                sanhsFilter = (ArrayList<Sanh>) filteredList;
            }
            FilterResults filterResults = new FilterResults();
            filterResults.values = sanhsFilter;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults filterResults) {
            sanhsFilter = (ArrayList<Sanh>) filterResults.values;
            notifyDataSetChanged();
        }
    }

    public void addMonAn(Sanh sanh) {
        sanhsFilter.add(sanh);
        notifyDataSetChanged();
    }

    public void editMonAn(Sanh sanh, int pos) {
        sanhsFilter.set(pos, sanh);
        notifyDataSetChanged();
    }

    public void deleteMonAn(int pos) {
        sanhsFilter.remove(pos);
        notifyDataSetChanged();
    }

    public void deleteMonAn(Sanh sanh) {
        sanhsFilter.remove(sanh);
        notifyDataSetChanged();
    }

    public interface Listener {
        void OnItemListener(int pos, Sanh sanh);

        void OnEditListener(int pos, Sanh sanh);

        void OnDeleteListener(Sanh sanh);
    }
}
