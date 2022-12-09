package com.danifitrianto.intermediaterecyclerview.views;

import static android.accounts.AccountManager.KEY_INTENT;
import static com.danifitrianto.intermediaterecyclerview.setups.AppApplication.db;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.danifitrianto.intermediaterecyclerview.R;
import com.danifitrianto.intermediaterecyclerview.setups.rooms.AppDatabase;
import com.danifitrianto.intermediaterecyclerview.setups.rooms.Mahasiswa;

public class ControllerUserActivity extends AppCompatActivity {

    private Button btnAction;
    private EditText etAlamat, etKejuruan, etNama, etNim;
    private boolean accessType = false;
    Mahasiswa mahasiswa = new Mahasiswa();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        btnAction = findViewById(R.id.btnInsert);
        etAlamat = findViewById(R.id.etAlamat);
        etKejuruan = findViewById(R.id.etKejuruan);
        etNim = findViewById(R.id.etNim);
        etNama = findViewById(R.id.etNama);

        try {
            checkData(getIntent().getStringExtra("nama"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        btnAction.setOnClickListener(view -> {
            if(!accessType) {
                insertData();
            } else {
                updateData();
            }
        });
    }

    private void updateData() {
        if(!etAlamat.getText().toString().isEmpty() && !etKejuruan.toString().isEmpty() &&
                !etNama.getText().toString().isEmpty() && !etNim.getText().toString().isEmpty())
        {
            setValue(true);
            db.userDao().updateAll(mahasiswa);

            Intent i = new Intent(ControllerUserActivity.this,UserActivity.class);
            i.putExtra(KEY_INTENT,etNama.getText().toString());
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
        } else {
            Toast.makeText(getApplicationContext(), "Mohon masukan dengan benar!",
                    Toast.LENGTH_LONG).show();
        }
    }

    private void insertData() {
        if(!etAlamat.getText().toString().isEmpty() && !etKejuruan.toString().isEmpty() &&
                !etNama.getText().toString().isEmpty() && !etNim.getText().toString().isEmpty())
        {
            setValue(true);
            db.userDao().insertAll(mahasiswa);

            Intent i = new Intent(ControllerUserActivity.this,UserActivity.class);
            startActivity(i);
        } else {
            Toast.makeText(getApplicationContext(), "Mohon masukan dengan benar!",
                    Toast.LENGTH_LONG).show();
        }
    }

    void setValue(Boolean value) {
        if(value.equals(true)) {
            mahasiswa.setAlamat(etAlamat.getText().toString());
            mahasiswa.setNama(etNama.getText().toString());
            mahasiswa.setKejuruan(etKejuruan.getText().toString());
            mahasiswa.setNim(etNim.getText().toString());
        } else {
            etNama.setText(mahasiswa.getNama());
            etAlamat.setText(mahasiswa.getAlamat());
            etKejuruan.setText(mahasiswa.getKejuruan());
            etNim.setText(mahasiswa.getNim());
        }

    }

    private void checkData(String keyValue) {
        if(!keyValue.isEmpty()) {
            accessType = true;
            try {
                db = Room.databaseBuilder(getApplicationContext(),
                        AppDatabase.class, "mahasiswa").allowMainThreadQueries().build();
                mahasiswa = db.userDao().findByName(keyValue);
                setValue(false);

                if(!accessType) {
                    btnAction.setText("Insert");
                } else {
                    btnAction.setText("Update");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}