package com.example.praktikum_pt09_2072030.Model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Item {
    @Id
    @Column(name = "idItem")
    private int idItem;
    @Basic
    @Column(name = "nama")
    private String nama;
    @Basic
    @Column(name = "price")
    private double price;
    @Basic
    @Column(name = "description")
    private String description;
    @ManyToOne
    @JoinColumn(name = "kategori_idKategori", referencedColumnName = "idKategori", nullable = false)
    private Kategori kategoriByKategoriIdKategori;

    public int getIdItem() {
        return idItem;
    }

    public void setIdItem(int idItem) {
        this.idItem = idItem;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return idItem == item.idItem && Double.compare(item.price, price) == 0 && Objects.equals(nama, item.nama) && Objects.equals(description, item.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idItem, nama, price, description);
    }

    public Kategori getKategoriByKategoriIdKategori() {
        return kategoriByKategoriIdKategori;
    }

    public void setKategoriByKategoriIdKategori(Kategori kategoriByKategoriIdKategori) {
        this.kategoriByKategoriIdKategori = kategoriByKategoriIdKategori;
    }
}
