/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qlyhocvienttav.Controller.Admin;

import java.io.InputStream;
import java.sql.Connection;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Thang
 */
public class Report {

    public JasperReport jreport;
    public JasperViewer jviewer;
    public JasperPrint jprint;

    public Report() {
        
    }

    public void CreateReport(Connection conn, Map<String, Object> map, String by) {
        try {
            jreport = JasperCompileManager.compileReport(by);
            jprint = JasperFillManager.fillReport(jreport,map, conn);
        } catch (JRException ex) {
            System.out.println(ex);
        }
    }
    public void ShowReport(){
        jviewer = new JasperViewer(jprint,false);
        jviewer.setVisible(true);
    }
}
