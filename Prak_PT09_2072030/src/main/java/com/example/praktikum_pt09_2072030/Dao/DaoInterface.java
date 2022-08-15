package com.example.praktikum_pt09_2072030.Dao;

import java.util.List;

public interface DaoInterface<T> {
    List<T> getData();
    int addData(T data);
    int deleteData(T data);
    int updateData(T data);
}
