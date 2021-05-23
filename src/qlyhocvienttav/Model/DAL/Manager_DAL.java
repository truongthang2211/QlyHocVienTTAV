/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qlyhocvienttav.Model.DAL;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.swing.JOptionPane;
import qlyhocvienttav.Controller.LoginViewController;
import qlyhocvienttav.Model.DTO.Manager;

/**
 *
 * @author Thang
 */
public class Manager_DAL {
    ObservableList<Manager> Data = FXCollections.observableArrayList();
    public void Insert(Manager manager){

        try {
            Object arg_info[]= {manager.getFullName(),manager.getSex(),manager.getDateOfBirth(),manager.getNationality(),manager.getAddress(),manager.getEmail(),manager.getPhoneNumber()};
            String teacher_sql;
            teacher_sql = String.format("INSERT INTO Manager VALUES ('MNG'||to_char(seq_manager_id.currval))");
            String infoTeacher_sql;
            infoTeacher_sql = String.format("INSERT INTO Personal_Info VALUES ('MNG'||to_char(seq_manager_id.nextval),'%s','%s',TO_DATE('%s','YYYY-MM-DD'),'%s','%s','%s','%s')", arg_info);

            Statement statement = LoginViewController.connection.con.createStatement();
            int rows_info = statement.executeUpdate(infoTeacher_sql);
            int rows_st = statement.executeUpdate(teacher_sql);
            if (rows_st > 0 && rows_info >0){
                System.out.println("Insert successfull");
            }else {
                System.out.println("Insert fail");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,ex.toString(),"Error", JOptionPane.ERROR_MESSAGE);
        }

    }
    public boolean Delete(Manager manager){
        try {
            Object arg[]= {manager.getManager_ID()};
            String info_sql,teacher_sql;
            info_sql = String.format("DELETE FROM Personal_Info WHERE ID = '%s'", arg);
            teacher_sql = String.format("DELETE FROM Manager WHERE manager_id = '%s'", arg);
            Statement statement = LoginViewController.connection.con.createStatement();
            int teacher_rows = statement.executeUpdate(teacher_sql);
            int info_rows = statement.executeUpdate(info_sql);
            if (info_rows > 0 && teacher_rows >0){
                System.out.println("Delete successfull");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,ex.toString(),"Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
    public boolean Update(Manager manager) {
        try {
            Object arg[]= {manager.getFullName(),manager.getSex(),manager.getDateOfBirth(),manager.getNationality(),manager.getAddress(),manager.getEmail(),manager.getPhoneNumber(),manager.getManager_ID()};
            String sql;
            sql = String.format("UPDATE Personal_Info SET fullName = '%s', sex = '%s', dateOfBirth = TO_DATE('%s','YYYY-MM-DD'), nationality = '%s', address = '%s', email = '%s', phoneNumber = '%s' WHERE ID = '%s'", arg);
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
            String sql = "SELECT MNG.Manager_ID,FULLNAME,SEX,DATEOFBIRth,NATIONALITY,ADDRESS,EMAIL,PHONENUMBER \n" +
                    "FROM Manager MNG JOIN PERSONAL_INFO IF ON MNG.Manager_ID = IF.ID";
            ResultSet rs = LoginViewController.connection.con.createStatement().executeQuery(sql);
            while (rs.next()){
                Date lcdate = rs.getDate(4);
                String date = lcdate==null?"":lcdate.toString();
                Manager t = new Manager(rs.getString(1),rs.getString(2),rs.getString(3),date,rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8));
                Data.add(t);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,ex.toString(),"Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    public ObservableList<Manager> GetData(){
        try{
            LoadData();
        }catch (Exception ex){
            JOptionPane.showMessageDialog(null,ex.toString(),"Error at GetData() function", JOptionPane.ERROR_MESSAGE);
        }

        return this.Data;
    }
}
