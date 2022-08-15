module com.example.praktikum_pt08_2072030 {
    requires javafx.controls;
    requires javafx.fxml;
    requires jasperreports;
    requires org.hibernate.orm.core;
    requires java.persistence;
    requires java.sql;
    requires java.naming;

    exports com.example.praktikum_pt09_2072030;
    exports com.example.praktikum_pt09_2072030.Model;
    opens com.example.praktikum_pt09_2072030.Model;
    opens com.example.praktikum_pt09_2072030.Controller;
    opens com.example.praktikum_pt09_2072030.Utility;
    opens com.example.praktikum_pt09_2072030.Dao;
}