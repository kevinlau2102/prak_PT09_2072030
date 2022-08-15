package com.example.praktikum_pt09_2072030.Dao;

import com.example.praktikum_pt09_2072030.Model.Item;
import com.example.praktikum_pt09_2072030.Utility.JDBCUtility;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class ItemDao implements DaoInterface<Item>{

    @Override
    public List<Item> getData() {
        List<Item> iList;
        SessionFactory sf = JDBCUtility.getSessionFactory();
        Session s = sf.openSession();
        CriteriaBuilder cb = s.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery(Item.class);
        cq.from(Item.class);
        iList = s.createQuery(cq).getResultList();
        s.close();
        return iList;
    }

    @Override
    public int addData(Item data) {
        SessionFactory sf = JDBCUtility.getSessionFactory();
        Session s = sf.openSession();
        Transaction t = s.beginTransaction();
        try {
            s.save(data);
            t.commit();
            return 1;

        } catch (Exception e) {
            t.rollback();
            return 0;

        }
    }

    @Override
    public int deleteData(Item data) {
        SessionFactory sf = JDBCUtility.getSessionFactory();
        Session s = sf.openSession();
        Transaction t = s.beginTransaction();
        try {
            s.delete(data);
            t.commit();
            return 1;

        } catch (Exception e) {
            t.rollback();
            return 0;

        }
    }

    @Override
    public int updateData(Item data) {
        SessionFactory sf = JDBCUtility.getSessionFactory();
        Session s = sf.openSession();
        Transaction t = s.beginTransaction();
        try {
            s.update(data);
            t.commit();
            return 1;

        } catch (Exception e) {
            t.rollback();
            return 0;

        }
    }
}
