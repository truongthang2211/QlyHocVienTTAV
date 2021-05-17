/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qlyhocvienttav.Model.DAL;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import qlyhocvienttav.Controller.LoginViewController;
import qlyhocvienttav.Model.DTO.Score;

import javax.swing.*;
//import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 *
 * @author Khang
 */
public class Score_DAL {
    public  Score_DAL(){};
    ObservableList<Score> Data = FXCollections.observableArrayList();

    public void Insert(Score sc){

        try {
            Object arg_info[]= {sc.getScore_ID(),sc.getStudent_ID(),sc.getTestSchedule_ID(),sc.getListening(),sc.getReading(),sc.getWriting(), sc.getSpeaking()};
            String sc_sql;
            
            sc_sql = String.format("INSERT INTO Score VALUES ('TC'||to_char(seq_score_id.nextval),'%s','%s','%f','%f','%f','%f')", arg_info);

            Statement statement = LoginViewController.connection.con.createStatement();
            //int rows_info = statement.executeUpdate(infoScore_sql);
            int rows_st = statement.executeUpdate(sc_sql);
            if (rows_st > 0){
                System.out.println("Insert successfull");
            }else {
                System.out.println("Insert fail");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,ex.toString(),"Error", JOptionPane.ERROR_MESSAGE);
        }

    }
    public boolean Delete(Score sc){
        try {
            Object arg[]= {sc.getScore_ID()};
            String Score_sql;
            //info_sql = String.format("DELETE FROM Score WHERE ID = '%s'", arg);
            Score_sql = String.format("DELETE FROM Score WHERE Score_ID = '%s'", arg);
            Statement statement = LoginViewController.connection.con.createStatement();
            int Score_rows = statement.executeUpdate(Score_sql);
            //int info_rows = statement.executeUpdate(info_sql);
            if (Score_rows >0){
                System.out.println("Delete successfull");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,ex.toString(),"Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
    public boolean Update(Score sc) {
        try {
            Object arg[]= {sc.getScore_ID(),sc.getStudent_ID(),sc.getTestSchedule_ID(),sc.getListening(),sc.getReading(),sc.getWriting(), sc.getSpeaking()};
            String sql;
            sql = String.format("UPDATE Score SET Score_ID = '%s', Student_ID = '%s', TestSchedule_ID = '%s', Listening = '%f', Reading = '%f', Writing = '%f', Speaking = '%f'", arg);
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
            String sql = "SELECT TC.TestSchedule_ID,TEACHER_ID,COURSE_ID,ROOM_ID,LOATKT,TESTDATE \n" + "FROM Score";
            ResultSet rs = LoginViewController.connection.con.createStatement().executeQuery(sql);
            while (rs.next()){
                
                
                Score sc = new Score(rs.getString(1),rs.getString(2),rs.getString(3),rs.getFloat(5),rs.getFloat(6),rs.getFloat(7),rs.getFloat(8));
                        
                Data.add(sc);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,ex.toString(),"Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    public ObservableList<Score> GetData(){
        try{
            LoadData();
        }catch (Exception ex){
            JOptionPane.showMessageDialog(null,ex.toString(),"Error at GetData() function", JOptionPane.ERROR_MESSAGE);
        }

        return this.Data;
    }
}
