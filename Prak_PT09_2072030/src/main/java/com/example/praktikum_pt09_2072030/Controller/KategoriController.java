package com.example.praktikum_pt09_2072030.Controller;

import com.example.praktikum_pt09_2072030.Dao.KategoriDao;
import com.example.praktikum_pt09_2072030.Model.Kategori;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class KategoriController {
    @FXML
    private TableView<Kategori> table;
    @FXML
    private TableColumn<Integer, Kategori> idCol;
    @FXML
    private TableColumn<String, Kategori> namaCol;
    @FXML
    private TextField idKategori;
    @FXML
    private TextField namaKategori;
    public ObservableList<Kategori> kList;
    private KategoriDao kategoriDao;


    public void initialize() {
        kategoriDao = new KategoriDao();
        showData();
    }
    public void showData() {
        kList = FXCollections.observableArrayList(kategoriDao.getData());
        table.setItems(kList);
        idCol.setCellValueFactory(new PropertyValueFactory<>("idKategori"));
        namaCol.setCellValueFactory(new PropertyValueFactory<>("nama"));
    }
    public void addData() {
        if (idKategori.getText().isEmpty() || namaKategori.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION,"Please fill in all the field",ButtonType.OK);
            alert.show();
        } else {
            Kategori k = new Kategori();
            k.setIdKategori(Integer.parseInt(idKategori.getText()));
            k.setNama(namaKategori.getText());
            kategoriDao.addData(k);
            showData();
            reset();
        }
    }
    public void reset() {
        idKategori.clear();
        namaKategori.clear();
    }
}
