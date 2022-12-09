package com.danifitrianto.intermediaterecyclerview.setups;

import android.app.Application;
import androidx.room.Room;

import com.danifitrianto.intermediaterecyclerview.setups.rooms.AppDatabase;

public class AppApplication extends Application {

    public static AppDatabase db;

    @Override
    public void onCreate() {
        super.onCreate();
        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "mahasiswa").allowMainThreadQueries().build();
    }
}
