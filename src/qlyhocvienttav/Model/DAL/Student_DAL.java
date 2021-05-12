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
import qlyhocvienttav.Model.DTO.Student;

/**
 *
 * @author Thang Nguyen Anh
 */
public class Student_DAL{
    public Student_DAL(){
    };
    ObservableList<Student> Data = FXCollections.observableArrayList();

   
    
    public boolean Insert(Student st) {
        try {
            Object arg_st[]= {st.getCourse_id()};
            Object arg_info[]= {st.getFullName(),st.getSex(),st.getDateOfBirth(),st.getNationality(),st.getAddress(),st.getEmail(),st.getPhoneNumber()};
            String st_sql;
            st_sql = String.format("INSERT INTO STUDENT VALUES ('HV'||to_char(seq_student_id.currval),'','%s')", arg_st);
            String info_sql;
            info_sql = String.format("INSERT INTO Personal_Info VALUES ('HV'||to_char(seq_student_id.nextval),'%s','%s',TO_DATE('%s','YYYY-MM-DD'),'%s','%s','%s','%s')", arg_info);
            
            Statement statement = LoginViewController.connection.con.createStatement();
            int rows_info = statement.executeUpdate(info_sql);
            int rows_st = statement.executeUpdate(st_sql);
            if (rows_st > 0 && rows_info >0){
                System.out.println("Insert successfull");
            }else {
                System.out.println("Insert fail");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,ex.toString(),"Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
     public boolean Delete(Student st){
        try {
            Object arg[]= {st.getStudent_id()};
            String info_sql,st_sql;
            info_sql = String.format("DELETE FROM Personal_Info WHERE ID = '%s'", arg);
            st_sql = String.format("DELETE FROM STUDENT WHERE student_id = '%s'", arg);
            Statement statement = LoginViewController.connection.con.createStatement();
            int st_rows = statement.executeUpdate(st_sql);
            int info_rows = statement.executeUpdate(info_sql);
            if (info_rows > 0 && st_rows >0){
                System.out.println("Delete successfull");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,ex.toString(),"Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
    public boolean Update(String ID ,Student st) {
        try {
            Object arg_st[]={st.getClass_id(),st.getCourse_id(),ID};
            Object arg_info[]= {st.getFullName(),st.getSex(),st.getDateOfBirth(),st.getNationality(),st.getAddress(),st.getEmail(),st.getPhoneNumber(),ID};
            String st_sql = String.format("UPDATE STUDENT SET CLASS_ID = '%s', COURSE_ID = '%s' WHERE STUDENT_ID = '%s'",arg_st);
            String info_sql = String.format("UPDATE Personal_Info SET fullName = '%s', sex = '%s', dateOfBirth = TO_DATE('%s','YYYY-MM-DD'), nationality = '%s', address = '%s', email = '%s', phoneNumber = '%s' WHERE ID = '%s'", arg_info);
            Statement statement = LoginViewController.connection.con.createStatement();
            int rows_info = statement.executeUpdate(info_sql);
            int rows_st = statement.executeUpdate(st_sql);
            if (rows_info > 0 || rows_st >0){
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
            String sql = "SELECT ST.*,FULLNAME,SEX,DATEOFBIRth,NATIONALITY,ADDRESS,EMAIL,PHONENUMBER \n" +
                            "FROM STUDENT ST JOIN PERSONAL_INFO IF ON ST.STUDENT_ID = IF.ID";
            ResultSet rs = LoginViewController.connection.con.createStatement().executeQuery(sql);
            while (rs.next()){
                Date lcdate = rs.getDate(6);
                String date = lcdate==null?"":lcdate.toString();
                Data.add(new Student(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),date,rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10)));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,ex.toString(),"Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    public ObservableList<Student> GetData(){
        try{
            LoadData();
        }catch (Exception ex){
            JOptionPane.showMessageDialog(null,ex.toString(),"Error at GetData() function", JOptionPane.ERROR_MESSAGE);
        }
        
        return this.Data;
    }
    
}
