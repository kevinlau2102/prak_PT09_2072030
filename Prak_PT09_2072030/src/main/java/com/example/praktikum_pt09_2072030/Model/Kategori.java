package com.example.praktikum_pt09_2072030.Model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Kategori {
    @Id
    @Column(name = "idKategori")
    private int idKategori;
    @Basic
    @Column(name = "nama")
    private String nama;

    public int getIdKategori() {
        return idKategori;
    }

    public void setIdKategori(int idKategori) {
        this.idKategori = idKategori;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Kategori kategori = (Kategori) o;
        return idKategori == kategori.idKategori && Objects.equals(nama, kategori.nama);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idKategori, nama);
    }

    @Override
    public String toString() {
        return nama;
    }
}
