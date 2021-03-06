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
            Object arg_info[]= {sc.getStudent_ID(),sc.getListening(),sc.getWriting(),sc.getReading(), sc.getSpeaking(),sc.getTestSchedule_ID()};
            String sc_sql;
            
            sc_sql = String.format("INSERT INTO Score VALUES ('SC'||to_char(score_seq_id.nextval),'%s','%s','%s','%s','%s','%s')", arg_info);

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
            Score_sql = String.format("DELETE FROM Score WHERE id = '%s'", arg);
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
            Object arg[]= {sc.getListening(),sc.getReading(),sc.getWriting(), sc.getSpeaking(),sc.getScore_ID()};
            String sql;
            sql = String.format("UPDATE Score SET  listening = '%f', reading = '%f', writing = '%f', speaking = '%f' where id = '%s'", arg);
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
    public void LoadData(String MaGV){

        try {
            this.Data.clear();
            String sql = String.format("select st.student_id,pi.fullname,a.kindoftest,a.listening,a.writing,a.reading,a.speaking,b.id,a.id\n" +
"    from student st\n" +
"    left join (select sc.*,tsc.id as tsc_id,tsc.kindoftest ,tsc.testdate\n" +
"                from score sc join test_schedule tsc on sc.test_schedule_id = tsc.id\n" +
"                where to_date(testdate) = to_date(sysdate)) a on st.student_id = a.student_id\n" +
"    join personal_info pi on pi.id = st.student_id \n" +
"    join (select tsc2.course_id,tsc2.id\n" +
"                        from test_schedule tsc2\n" +
"                        where teacher_id = '%s' and to_date(testdate) = to_date(sysdate)) b on st.course_id = b.course_id\n" +
"    where st.course_id in (b.course_id) ",MaGV);
            ResultSet rs = LoginViewController.connection.con.createStatement().executeQuery(sql);
            while (rs.next()){
                
                
                Score sc = new Score(rs.getString(1),rs.getString(2),rs.getString(3),rs.getFloat(4),rs.getFloat(5),rs.getFloat(6),rs.getFloat(7));
                sc.setTestSchedule_ID(rs.getString(8));
                sc.setScore_ID(rs.getString(9));
                Data.add(sc);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,ex.toString(),"Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    public ObservableList<Score> GetData(String MaGV){
        try{
            LoadData( MaGV);
        }catch (Exception ex){
            JOptionPane.showMessageDialog(null,ex.toString(),"Error at GetData() function", JOptionPane.ERROR_MESSAGE);
        }

        return this.Data;
    }
}
