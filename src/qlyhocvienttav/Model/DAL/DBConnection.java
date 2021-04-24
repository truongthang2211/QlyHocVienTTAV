/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qlyhocvienttav.Model.DAL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Thang Nguyen Anh
 */
public class DBConnection {
    String dbURL= "jdbc:oracle:thin:@//localhost:1521/Qlyhocvien";
    String username = "admin";
    String password = "22112001";
    public Connection con;

    public DBConnection() {
    }
    public void OpenConnection(){
        try {
            this.con = DriverManager.getConnection(dbURL,username,password);
            System.out.println("connect succesfull");
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void CloseConnection(){
        try{
            con.close();;
        } catch (SQLException ex){
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
