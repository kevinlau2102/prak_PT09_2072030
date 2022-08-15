package com.example.praktikum_pt09_2072030.Dao;

import com.example.praktikum_pt09_2072030.Model.Kategori;
import com.example.praktikum_pt09_2072030.Utility.JDBCUtility;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class KategoriDao implements DaoInterface<Kategori>{

    @Override
    public List<Kategori> getData() {
        List<Kategori> kList;
        SessionFactory sf = JDBCUtility.getSessionFactory();
        Session s = sf.openSession();
        CriteriaBuilder cb = s.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery(Kategori.class);
        cq.from(Kategori.class);
        kList = s.createQuery(cq).getResultList();
        s.close();
        return kList;
    }

    @Override
    public int addData(Kategori data) {
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
    public int deleteData(Kategori data) {
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
    public int updateData(Kategori data) {
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
