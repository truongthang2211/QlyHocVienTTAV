/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qlyhocvienttav.Model.DAL;

import javax.swing.*;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.swing.JOptionPane;
import qlyhocvienttav.Controller.LoginViewController;
import qlyhocvienttav.Model.DTO.Account;
import qlyhocvienttav.Model.DTO.TestSchedule;

/**
 *
 * @author nvtru
 */
public class Account_DAL {
    public Account_DAL(){};
    ObservableList <Account> Data = FXCollections.observableArrayList();
    public void Insert(Account acc){

        try {
            Object arg_acc[]= {acc.getUsername(),acc.getPassword(),acc.getAcctype(),acc.getOwner(),acc.getCreate_date()};
            String acc_sql;
            acc_sql = String.format("INSERT INTO Account VALUES ('AC'||to_char(seq_username.currval))");
            String accinf_sql;
            accinf_sql = String.format("INSERT INTO Personal_Info VALUES ('TC'||to_char(seq_username.nextval),'%s','%s',TO_DATE('%s','YYYY-MM-DD'),'%s','%s','%s',int)", arg_acc);

            Statement statement = LoginViewController.connection.con.createStatement();
            int rows_info = statement.executeUpdate(accinf_sql);
            int rows_st = statement.executeUpdate(acc_sql);
            if (rows_st > 0 && rows_info >0){
                System.out.println("Insert successfull");
            }else {
                System.out.println("Insert fail");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,ex.toString(),"Error", JOptionPane.ERROR_MESSAGE);
        }

    }
    public boolean Delete(Account acc){
        try {
            Object arg[]= {acc.getUsername()};
            String accinf_sql,acc_sql;
            accinf_sql = String.format("DELETE FROM Personal_Info WHERE ID = '%s'", arg);
            acc_sql = String.format("DELETE FROM ACCOUNT WHERE USERNAME = '%s'", arg);
            Statement statement = LoginViewController.connection.con.createStatement();
            int acc_rows = statement.executeUpdate(acc_sql);
            int rows = statement.executeUpdate(accinf_sql);
            if (rows > 0 && acc_rows >0){
                System.out.println("Delete successfull");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,ex.toString(),"Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }    
    public boolean Update(String username ,Account acc) {
        try {
            Object arg_acc[]= {acc.getUsername(),acc.getPassword(),acc.getAcctype(),acc.getOwner(),acc.getCreate_date()};
            String sql;
            sql = String.format("UPDATE Account SET Password = '%s', acctype = '%s',owner ='%s', create_date = TO_DATE('%s','YYYY-MM-DD') WHERE username = '%s'", arg_acc);
            Statement statement = LoginViewController.connection.con.createStatement();
            int rows = statement.executeUpdate(sql);
            if (rows > 0){
                System.out.println("Update successfull");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,ex.toString(),"Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;

    }
    
    
}
