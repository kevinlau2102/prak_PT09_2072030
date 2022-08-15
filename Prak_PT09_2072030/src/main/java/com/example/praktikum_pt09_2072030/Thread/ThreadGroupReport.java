package com.example.praktikum_pt09_2072030.Thread;

import com.example.praktikum_pt09_2072030.Utility.JDBCUtility;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

public class ThreadGroupReport extends Thread{
    @Override
    public void run() {
        JasperPrint jp;
        Connection conn = JDBCUtility.getConnection();
        Map param = new HashMap();
        try {
            jp = JasperFillManager.fillReport("reports/Prak_ReportFilter_PT06_2072030.jasper", param, conn);
            JasperViewer viewer = new JasperViewer(jp, false);
            viewer.setTitle("Menu Report Filtered");
            viewer.setVisible(true);
        } catch (
                JRException e) {
            throw new RuntimeException(e);
        }
    }
}
