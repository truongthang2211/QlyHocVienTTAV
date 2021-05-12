/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qlyhocvienttav.Model.DAL;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import qlyhocvienttav.Controller.LoginViewController;
import qlyhocvienttav.Model.DTO.TestSchedule;

import javax.swing.*;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 *
 * @author Khang
 */
public class TestSche_DAL {

    public TestSche_DAL(){};
    ObservableList<TestSchedule> Data = FXCollections.observableArrayList();

    public void Insert(TestSchedule ts){

        try {
            Object arg_info[]= {ts.getCourse_ID(),ts.getTestSche_ID(),ts.getTeacher_ID(),ts.getRoom_ID(),ts.getLoai_KT(),ts.getTestDate(), ts.getShift()};
            String ts_sql;
            ts_sql = String.format("INSERT INTO TestSchedule VALUES ('TC'||to_char(seq_testsche_id.currval))");
            String infoTestSche_sql;
            infoTestSche_sql = String.format("INSERT INTO Personal_Info VALUES ('TC'||to_char(seq_testsche_id.nextval),'%s','%s','%s','%s','%s',TO_DATE('%s','YYYY-MM-DD'),int)", arg_info);

            Statement statement = LoginViewController.connection.con.createStatement();
            int rows_info = statement.executeUpdate(infoTestSche_sql);
            int rows_st = statement.executeUpdate(ts_sql);
            if (rows_st > 0 && rows_info >0){
                System.out.println("Insert successfull");
            }else {
                System.out.println("Insert fail");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,ex.toString(),"Error", JOptionPane.ERROR_MESSAGE);
        }

    }
    public boolean Delete(TestSchedule ts){
        try {
            Object arg[]= {ts.getTestSche_ID()};
            String info_sql,testsche_sql;
            info_sql = String.format("DELETE FROM Personal_Info WHERE ID = '%s'", arg);
            testsche_sql = String.format("DELETE FROM TestSchedule WHERE TestSchedule_id = '%s'", arg);
            Statement statement = LoginViewController.connection.con.createStatement();
            int testschedule_rows = statement.executeUpdate(testsche_sql);
            int info_rows = statement.executeUpdate(info_sql);
            if (info_rows > 0 && testschedule_rows >0){
                System.out.println("Delete successfull");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,ex.toString(),"Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
    public boolean Update(String ID ,TestSchedule ts) {
        try {
            Object arg[]= {ts.getTestSche_ID(),ts.getCourse_ID(),ts.getTeacher_ID(),ts.getRoom_ID(),ts.getLoai_KT(),ts.getTestDate(),ts.getShift(),ID};
            String sql;
            sql = String.format("UPDATE Personal_Info SET TestSchedule_ID = '%s', Teacher_ID = '%s', TestDate = TO_DATE('%s','YYYY-MM-DD'), Course_ID = '%s', Room_ID = '%s', Loai_KT = '%s', Shift = int, WHERE ID = '%s'", arg);
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
            String sql = "SELECT TC.TestSchedule_ID,TEACHER_ID,COURSE_ID,ROOM_ID,LOATKT,TESTDATE \n" + "FROM TestSche TC JOIN PERSONAL_INFO IF ON TC.TESTSCHEDULE_ID = IF.ID";
            ResultSet rs = LoginViewController.connection.con.createStatement().executeQuery(sql);
            while (rs.next()){
                Date lcdate = rs.getDate(4);
                String date = lcdate==null?"":lcdate.toString();
                TestSchedule t = new TestSchedule(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(5),rs.getString(6),date,rs.getInt(1));
                        
                Data.add(t);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,ex.toString(),"Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    public ObservableList<TestSchedule> GetData(){
        try{
            LoadData();
        }catch (Exception ex){
            JOptionPane.showMessageDialog(null,ex.toString(),"Error at GetData() function", JOptionPane.ERROR_MESSAGE);
        }

        return this.Data;
    }
}
