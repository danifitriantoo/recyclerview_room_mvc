package com.danifitrianto.intermediaterecyclerview.setups.rooms;

import androidx.room.*;

@Database(entities = {Mahasiswa.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract MahasiswaDao userDao();
}
