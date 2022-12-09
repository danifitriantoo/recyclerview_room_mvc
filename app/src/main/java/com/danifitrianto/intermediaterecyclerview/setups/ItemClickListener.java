package com.danifitrianto.intermediaterecyclerview.setups;

import androidx.annotation.Nullable;

public interface ItemClickListener<Mahasiswa> {
    void onClick(Mahasiswa model);
    boolean onLongClick(@Nullable  Mahasiswa model);
}
