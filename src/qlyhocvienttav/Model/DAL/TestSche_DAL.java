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
            Object arg_info[]= {ts.getCourse_ID(),ts.getid(),ts.getTeacher_ID(),ts.getRoom_ID(),ts.getLoai_KT(),ts.getTestDate(), ts.getShift()};
            String ts_sql;
            
            ts_sql = String.format("INSERT INTO Test_Schedule VALUES ('TC'||to_char(seq_id.nextval),'%s','%s','%s',TO_DATE('%s','YYYY-MM-DD'),'%d')", arg_info);

            Statement statement = LoginViewController.connection.con.createStatement();
            //int rows_info = statement.executeUpdate(infoTestSche_sql);
            int rows_st = statement.executeUpdate(ts_sql);
            if (rows_st > 0){
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
            Object arg[]= {ts.getid()};
            String testsche_sql;
            //info_sql = String.format("DELETE FROM Test_Schedule WHERE ID = '%s'", arg);
            testsche_sql = String.format("DELETE FROM Test_Schedule WHERE id = '%s'", arg);
            Statement statement = LoginViewController.connection.con.createStatement();
            int testschedule_rows = statement.executeUpdate(testsche_sql);
            //int info_rows = statement.executeUpdate(info_sql);
            if (testschedule_rows >0){
                System.out.println("Delete successfull");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,ex.toString(),"Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
    public boolean Update(TestSchedule ts) {
        try {
            Object arg[]= {ts.getid(),ts.getCourse_ID(),ts.getTeacher_ID(),ts.getRoom_ID(),ts.getLoai_KT(),ts.getTestDate(),ts.getShift()};
            String sql;
            sql = String.format("UPDATE Test_Schedule SET id = '%s', teacher_id = '%s', testDate = TO_DATE('%s','YYYY-MM-DD'), course_id = '%s', room_id = '%s', kindofTest = '%s', Shift = '%d'", arg);
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
            String sql = "SELECT id,teacher_id,course_id,room_id,kindofTest,testDate,Shift \n" + "FROM Test_Schedule";
            ResultSet rs = LoginViewController.connection.con.createStatement().executeQuery(sql);
            while (rs.next()){
                Date lcdate = rs.getDate(4);
                String date = lcdate==null?"":lcdate.toString();
                
                TestSchedule ts = new TestSchedule(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(5),rs.getString(6),date,rs.getInt(7));
                        
                Data.add(ts);
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
