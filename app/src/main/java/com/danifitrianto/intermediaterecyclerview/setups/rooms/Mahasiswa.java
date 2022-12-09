package com.danifitrianto.intermediaterecyclerview.setups.rooms;
import androidx.room.*;

@Entity
public class Mahasiswa {
    @PrimaryKey(autoGenerate = true)
    int id;
    @ColumnInfo(name = "nama")
    String nama;
    @ColumnInfo(name = "nim")
    String nim;
    @ColumnInfo(name = "kejuruan")
    String kejuruan;
    @ColumnInfo(name = "alamat")
    String alamat;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getAlamat() {
        return alamat;
    }

    public String getKejuruan() {
        return kejuruan;
    }

    public void setKejuruan(String kejuruan) {
        this.kejuruan = kejuruan;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }
}
