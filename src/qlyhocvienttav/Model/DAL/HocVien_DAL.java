/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qlyhocvienttav.Model.DAL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import qlyhocvienttav.Controller.LoginViewController;
import qlyhocvienttav.Model.DTO.HocVien;

/**
 *
 * @author Thang Nguyen Anh
 */
public class HocVien_DAL {
    public HocVien_DAL(){
    };
    ObservableList<HocVien> Data = FXCollections.observableArrayList();

   
    
    public boolean Insert(HocVien hv) throws SQLException{
        Object arg[]= {hv.getHoTen(),hv.getEmail(),hv.getGhichu()};
        String sql;
        sql = MessageFormat.format("INSERT INTO HOCVIEN (HOTEN, EMAIL, GHICHU) VALUES (\''{0}\'',\''{1}\'',\''{2}\'')", arg);
        Statement statement = LoginViewController.connection.con.createStatement();
        int rows = statement.executeUpdate(sql);
        if (rows > 0){
            System.out.println("Insert successfull");
        }
        return true;
    }
     public boolean Delete(HocVien hv) throws SQLException{
        String arg[]= {hv.getHoTen(),hv.getEmail(),hv.getGhichu()};
        String sql;
        sql = MessageFormat.format("DELETE FROM HOCVIEN WHERE HOTEN "+ Format(arg[0])+" AND EMAIL "+Format(arg[1])+ " AND GHICHU "+ Format(arg[2]), (Object[]) arg);
        Statement statement = LoginViewController.connection.con.createStatement();
        int rows = statement.executeUpdate(sql);
        if (rows > 0){
            System.out.println("Delete successfull");
        }
        return true;
    }
    public ObservableList<HocVien> GetData(){
        try {
            String sql = "SELECT * FROM HOCVIEN";
            ResultSet rs = LoginViewController.connection.con.createStatement().executeQuery(sql);
            while (rs.next()){
                Data.add(new HocVien(rs.getString(1),rs.getString(2),rs.getString(3)));
            }
            return Data;
        } catch (SQLException ex) {
            Logger.getLogger(HocVien_DAL.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            return Data;
        }
    }
    String Format(String s){
        return s == null?"IS NULL":"="+"''"+s+"''";
    }
}
