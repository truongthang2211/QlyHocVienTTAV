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
import javax.swing.JOptionPane;
/**
 *
 * @author Thang Nguyen Anh
 */
public class DBConnection {
    String dbURL= "jdbc:oracle:thin:@//localhost:1521/Qlyhocvien";
    String username = "admin";
    String password = "123456";
    public Connection con;
    
    public DBConnection() {
    }
    public boolean OpenConnection(){
        try {
            this.con = DriverManager.getConnection(dbURL,username,password);
            System.out.println("Connect succeedfull");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,ex.toString(),"Error at OpenConnection()", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
    public void CloseConnection(){
        try{
            con.close();
            System.out.println("Connect closed");
        } catch (SQLException ex){
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
