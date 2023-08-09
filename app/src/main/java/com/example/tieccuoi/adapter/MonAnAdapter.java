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
import com.example.tieccuoi.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MonAnAdapter extends RecyclerView.Adapter<MonAnAdapter.MonAnVH> implements Filterable {


    Context context;
    ArrayList<MonAn> monAns;
    ArrayList<MonAn> monAnsFilter;
    Listener listener;

    public MonAnAdapter(Listener listener, ArrayList<MonAn> monAns, Context context) {
        this.listener = listener;
        this.monAns = monAns;
        this.monAnsFilter = monAns;
        this.context = context;
    }

    @NonNull
    @Override
    public MonAnVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.monan_row, parent, false);

        return new MonAnVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MonAnVH holder, @SuppressLint("RecyclerView") int position) {
        MonAn monAn = monAnsFilter.get(position);
        //holder.imgPhone.setImageResource(contact.getImage());
        holder.txTenMon.setText(monAn.getTenMonAn());
        holder.txGia.setText(monAn.getGia());


        try{
            InputStream is = context.getAssets().open(monAn.getImage());
            Bitmap bitmap = BitmapFactory.decodeStream(is);
            holder.imgMonAn.setImageBitmap(bitmap);

        } catch (IOException e){
            e.printStackTrace();
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.OnItemListener(position, monAn);
            }
        });
        holder.ivEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.OnEditListener(position, monAn);
            }
        });
        holder.ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.OnDeleteListener(monAn);
            }
        });

    }

    @Override
    public int getItemCount() {
        return monAns.size();
    }

    public Filter getFilter() {
        MonAnFilter monAnFilter = new MonAnFilter();
        return monAnFilter;
    }


    class MonAnVH extends RecyclerView.ViewHolder {
        CircleImageView imgMonAn;
        ImageView ivEdit, ivDelete;
        TextView txTenMon, txGia;

        public MonAnVH(@NonNull View itemView) {
            super(itemView);
            imgMonAn = itemView.findViewById(R.id.imgMonAn);
            txTenMon = itemView.findViewById(R.id.tvTenMon);
            txGia = itemView.findViewById(R.id.tvGia);
            ivEdit = itemView.findViewById(R.id.ivEdit);
            ivDelete = itemView.findViewById(R.id.ivDelete);
        }
    }

    class MonAnFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            String charString = constraint.toString();
            if (charString.isEmpty()) {
                monAnsFilter = monAns;
            } else {
                List<MonAn> filteredList = new ArrayList<>();
                for (MonAn row : monAns) {
                    if (row.getTenMonAn().toLowerCase().contains(charString.toLowerCase())) {
                        filteredList.add(row);

                    }
                }
                monAnsFilter = (ArrayList<MonAn>) filteredList;
            }
            FilterResults filterResults = new FilterResults();
            filterResults.values = monAnsFilter;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults filterResults) {
            monAnsFilter = (ArrayList<MonAn>) filterResults.values;
            notifyDataSetChanged();
        }
    }

    public void addMonAn(MonAn monAn) {
        monAnsFilter.add(monAn);
        notifyDataSetChanged();
    }

    public void editMonAn(MonAn monAn, int pos) {
        monAnsFilter.set(pos, monAn);
        notifyDataSetChanged();
    }

    public void deleteMonAn(int pos) {
        monAnsFilter.remove(pos);
        notifyDataSetChanged();
    }

    public void deleteMonAn(MonAn monAn) {
        monAnsFilter.remove(monAn);
        notifyDataSetChanged();
    }

    public interface Listener {
        void OnItemListener(int pos, MonAn monAn);

        void OnEditListener(int pos, MonAn monAn);

        void OnDeleteListener(MonAn monAn);
    }
}

