package com.example.praktikum_pt09_2072030.Controller;

import com.example.praktikum_pt09_2072030.Dao.ItemDao;
import com.example.praktikum_pt09_2072030.MainApplication;
import com.example.praktikum_pt09_2072030.Model.Item;
import com.example.praktikum_pt09_2072030.Model.Kategori;
import com.example.praktikum_pt09_2072030.Thread.ThreadGroupReport;
import com.example.praktikum_pt09_2072030.Thread.ThreadSimpleReport;
import com.example.praktikum_pt09_2072030.Utility.JDBCUtility;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ItemController {
    @FXML
    private TableView<Item> table;
    @FXML
    private TableColumn<Integer, Item> idCol;
    @FXML
    private TableColumn<String, Item> namaCol;
    @FXML
    private TableColumn<Double, Item> priceCol;
    @FXML
    private TableColumn<Kategori, Item> kategoriCol;
    @FXML
    private TextField idItem;
    @FXML
    private TextField namaItem;
    @FXML
    private TextField priceItem;
    @FXML
    private TextArea descItem;
    @FXML
    private ComboBox<Kategori> kategoriItem;
    @FXML
    private Button addButton;
    @FXML
    private MenuItem showKategori;
    @FXML
    private MenuItem close;
    @FXML
    private MenuItem showSimpleReport;
    @FXML
    private MenuItem showGroupReport;
    @FXML
    private Label id;
    private ObservableList<Item> iList;
    private ItemDao itemDao;
    private KategoriController kController;
    private FXMLLoader fxmlLoader;
    private Stage stage;

    public void initialize() throws IOException {
        fxmlLoader = new FXMLLoader(MainApplication.class.getResource("kategori-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage = new Stage();
        stage.setTitle("Category Management");
        stage.setScene(scene);
        kController = fxmlLoader.getController();
        kategoriItem.setItems(kController.kList);
        iList = FXCollections.observableArrayList();
        showKategori.setAccelerator(KeyCombination.keyCombination("Alt+F2"));
        close.setAccelerator(KeyCombination.keyCombination("Alt+X"));
        showSimpleReport.setAccelerator(KeyCombination.keyCombination("Alt+S"));
        showGroupReport.setAccelerator(KeyCombination.keyCombination("Alt+G"));
        showData();
    }

    public void showData() {
        itemDao = new ItemDao();
        iList.clear();
        iList = FXCollections.observableArrayList(itemDao.getData());
        table.setItems(iList);
        idCol.setCellValueFactory(new PropertyValueFactory<>("idItem"));
        namaCol.setCellValueFactory(new PropertyValueFactory<>("nama"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        kategoriCol.setCellValueFactory(new PropertyValueFactory<>("kategoriByKategoriIdKategori"));
    }

    public void showKategori() {
        stage.showAndWait();
        kategoriItem.setItems(kController.kList);
    }

    public void close() {
        id.getScene().getWindow().hide();
    }

    public void reset() {
        idItem.clear();
        namaItem.clear();
        priceItem.clear();
        descItem.clear();
        kategoriItem.setValue(null);
        idItem.setDisable(false);
        addButton.setDisable(false);
    }

    public void addItem() {
        if (idItem.getText().isEmpty() || namaItem.getText().isEmpty() || priceItem.getText().isEmpty() || descItem.getText().isEmpty() || kategoriItem.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Please fill in all the field", ButtonType.OK);
            alert.showAndWait();
        } else {
            Item i = new Item();
            i.setIdItem(Integer.parseInt(idItem.getText()));
            i.setNama(namaItem.getText());
            i.setPrice(Double.parseDouble(priceItem.getText()));
            i.setDescription(descItem.getText());
            i.setKategoriByKategoriIdKategori(kategoriItem.getValue());
            itemDao.addData(i);
            showData();
            reset();
        }
    }

    public void getSelectedItem() {
        idItem.setText(String.valueOf(table.getSelectionModel().getSelectedItem().getIdItem()));
        namaItem.setText(table.getSelectionModel().getSelectedItem().getNama());
        priceItem.setText(String.valueOf(table.getSelectionModel().getSelectedItem().getPrice()));
        descItem.setText(table.getSelectionModel().getSelectedItem().getDescription());
        kategoriItem.setValue(table.getSelectionModel().getSelectedItem().getKategoriByKategoriIdKategori());
        idItem.setDisable(true);
        addButton.setDisable(true);
    }

    public void updateItem() {
        if (idItem.getText().isEmpty() || namaItem.getText().isEmpty() || priceItem.getText().isEmpty() || descItem.getText().isEmpty() || kategoriItem.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Please fill in all the field", ButtonType.OK);
            alert.showAndWait();
        } else {
            Item i = new Item();
            i.setIdItem(Integer.parseInt(idItem.getText()));
            i.setNama(namaItem.getText());
            i.setPrice(Double.parseDouble(priceItem.getText()));
            i.setDescription(descItem.getText());
            i.setKategoriByKategoriIdKategori(kategoriItem.getValue());
            itemDao.updateData(i);
            showData();
            reset();

        }
    }

    public void deleteItem() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.OK, ButtonType.CANCEL);
        alert.showAndWait();
        if (alert.getResult() == ButtonType.OK) {
            itemDao.deleteData(table.getSelectionModel().getSelectedItem());
            showData();
            reset();

        }
    }

    public void lihatGroupReport() {
        ThreadGroupReport threadGroup = new ThreadGroupReport();
        ExecutorService exService = Executors.newCachedThreadPool();
        exService.execute(threadGroup);
        exService.shutdown();
    }

    public void lihatSimpleReport() {
        ThreadSimpleReport threadSimple = new ThreadSimpleReport();
        ExecutorService exService = Executors.newCachedThreadPool();
        exService.execute(threadSimple);
        exService.shutdown();
    }
}