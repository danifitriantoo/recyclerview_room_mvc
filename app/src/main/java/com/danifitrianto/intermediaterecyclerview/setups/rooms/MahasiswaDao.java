package com.danifitrianto.intermediaterecyclerview.setups.rooms;

import androidx.room.*;

import java.util.List;

@Dao
public interface MahasiswaDao {

    @Query("SELECT * FROM mahasiswa")
    List<Mahasiswa> getAll();

    @Query("SELECT * FROM mahasiswa WHERE nama LIKE :nama ")
    Mahasiswa findByName(String nama);

    @Insert
    void insertAll(Mahasiswa mahasiswa);

    @Delete
    public void deleteUsers(Mahasiswa users);

    @Update
    public void updateAll(Mahasiswa mahasiswa);

    @Delete
    public void deteleAll(Mahasiswa user1, Mahasiswa user2);

}
