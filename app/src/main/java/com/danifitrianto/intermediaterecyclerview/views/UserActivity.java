package com.danifitrianto.intermediaterecyclerview.views;

import static com.danifitrianto.intermediaterecyclerview.setups.AppApplication.db;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.danifitrianto.intermediaterecyclerview.R;
import com.danifitrianto.intermediaterecyclerview.setups.ItemClickListener;
import com.danifitrianto.intermediaterecyclerview.setups.adapters.RecyclerviewUserAdapter;
import com.danifitrianto.intermediaterecyclerview.setups.rooms.AppDatabase;
import com.danifitrianto.intermediaterecyclerview.setups.rooms.Mahasiswa;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class UserActivity extends AppCompatActivity {
    ItemClickListener rvListener;
    RecyclerView recyclerView;
    RecyclerviewUserAdapter adapter;
    List<Mahasiswa> listMahasiswa = new ArrayList<>();

    boolean validation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        recyclerView = findViewById(R.id.recyclerView);
        fetchDataFromRoom();
        initRecyclerView();
        setAdapter();
    }

    private void fetchDataFromRoom() {
        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "mahasiswa").allowMainThreadQueries().build();
        listMahasiswa = db.userDao().getAll();
    }

    private void setAdapter() {
        recyclerView.setAdapter(adapter);
    }

    private void initRecyclerView() {
        recyclerView.setHasFixedSize(true);
        rvListener = new ItemClickListener<Mahasiswa>() {

            @Override
            public void onClick(Mahasiswa model) {
                Intent i = new Intent(UserActivity.this,ControllerUserActivity.class);
                i.putExtra("nama",model.getNama());
                Log.i("Hello",model.getNama());
                startActivity(i);
            }

            @Override
            public boolean onLongClick(@Nullable Mahasiswa model) {
                return removeMhs(model);
            }

        };

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new RecyclerviewUserAdapter(this,listMahasiswa,rvListener);

    }

    private boolean removeMhs(Mahasiswa model) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Hapus");
        builder
                .setMessage("Anda Yakin Menghapus " + model.getNama() + "?")
                .setCancelable(true)
                .setPositiveButton("Ya", (dialogInterface, i) -> {
                    db.userDao().deleteUsers(model);
                    fetchDataFromRoom();
                    initRecyclerView();
                    setAdapter();
                    validation = true;
                    dialogInterface.dismiss();
                })
                .setNegativeButton("Batal", (dialogInterface, i) -> {
                    validation = false;
                    dialogInterface.dismiss();
                });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

        Log.e("x","nilai adalah" + validation);
        return validation;
    }


}