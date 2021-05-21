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
import qlyhocvienttav.Model.DTO.Teacher;
import qlyhocvienttav.Model.DTO.TestSchedule;


/**
 *
 * @author nvtru
 */
public class Account_DAL {
    public Account_DAL(){};
    ObservableList <Account> Data = FXCollections.observableArrayList();
    public void Insert(Account acc,Teacher tc){

        try {
            Object arg_acc[]= {acc.getUsername(),acc.getPassword(),acc.getAcctype(),acc.getOwner(),acc.getCreate_date()};
            String acc_sql;
            acc_sql = String.format("INSERT INTO Account VALUES ('AC'||to_char(seq_username.currval))");
            String accinf_sql;
            accinf_sql = String.format("INSERT INTO Personal_Info VALUES ('TC'||to_char(seq_username.nextval),'%s','%s',TO_DATE('%s','YYYY-MM-DD'),'%s','%s','%s',int)", arg_acc);
            Statement statement = LoginViewController.connection.con.createStatement();
            String ins_tb;
            Object ins_tbl[] = {acc.getOwner()};//,tc.getFullName(),tc.getSex(),tc.getDateOfBirth(),tc.getNationality(),tc.getAddress(),tc.getEmail(),tc.getPhoneNumber()};
            if(acc.getAcctype().equals("Teacher")){
                ins_tb= String.format("INSERT INTO TEACHER VALUES('%s')",ins_tbl);
            }

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
            acc_sql = String.format("DELETE FROM ACCOUNT WHERE OWNER = '%s'", arg);
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
            sql = String.format("UPDATE Account SET Password = '%s', acctype = '%s', create_date = TO_DATE('%s','YYYY-MM-DD') WHERE username = '%s'", arg_acc);
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
    public void LoadData(){

        try {
            this.Data.clear();
            String sql = "SELECT USERNAME,PASSWORD, ACCTYPE, CREATE_DATE \n" + "FROM ACCOUNT";
            ResultSet rs = LoginViewController.connection.con.createStatement().executeQuery(sql);
            while (rs.next()){
                Date acdate = rs.getDate(4);
                String date = acdate==null?"":acdate.toString();
                
                Account  ac = new Account(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(5),date);
                        
                Data.add(ac);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,ex.toString(),"Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    public ObservableList<Account> GetData(){
        try{
            LoadData();
        }catch (Exception ex){
            JOptionPane.showMessageDialog(null,ex.toString(),"Error at GetData() function", JOptionPane.ERROR_MESSAGE);
        }

        return this.Data;
    }
    
    
}
