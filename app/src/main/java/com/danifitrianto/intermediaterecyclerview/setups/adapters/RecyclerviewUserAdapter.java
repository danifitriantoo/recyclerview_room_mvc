package com.danifitrianto.intermediaterecyclerview.setups.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.danifitrianto.intermediaterecyclerview.R;
import com.danifitrianto.intermediaterecyclerview.setups.rooms.Mahasiswa;
import com.danifitrianto.intermediaterecyclerview.setups.ItemClickListener;

import java.util.List;

public class RecyclerviewUserAdapter extends RecyclerView.Adapter<RecyclerviewUserAdapter.MyViewHolder> {
    private Context context;
    private List<Mahasiswa> models;
    private ItemClickListener listener;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvNama,tvNim,tvKejuruan,tvAlamat;
        public CardView cvMhs;

        public MyViewHolder(@Nullable View itemView) {
            super(itemView);
            this.tvNama = itemView.findViewById(R.id.tvNama);
            this.tvNim = itemView.findViewById(R.id.tvNim);
            this.tvKejuruan = itemView.findViewById(R.id.tvKejuruan);
            this.tvAlamat = itemView.findViewById(R.id.tvAlamat);
            this.cvMhs = itemView.findViewById(R.id.cvMahasiswa);
        }
    }

    public RecyclerviewUserAdapter(Context context, List<Mahasiswa> myList, ItemClickListener listener) {
        this.context = context;
        this.models = myList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecyclerviewUserAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_mahasiswa, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerviewUserAdapter.MyViewHolder holder, int position) {
        final Mahasiswa album = models.get(position);
        holder.tvNama.setText(album.getNama());
        holder.tvNim.setText(album.getNim());
        holder.tvKejuruan.setText(album.getKejuruan());
        holder.tvAlamat.setText(album.getAlamat());

        holder.cvMhs.setOnClickListener(view -> listener.onClick(album));

        holder.cvMhs.setOnLongClickListener(view -> {
            if(listener.onLongClick(album) == true) {
                models.remove(holder.getAdapterPosition());
                notifyItemRemoved(holder.getAdapterPosition());
            }
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

}
