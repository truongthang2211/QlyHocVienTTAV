package qlyhocvienttav.Model.DAL;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import qlyhocvienttav.Controller.LoginViewController;
import qlyhocvienttav.Model.DTO.Class;

import javax.swing.*;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Class_DAL {
    public Class_DAL(){};
    ObservableList<Class> data = FXCollections.observableArrayList();

    public void Insert(Class cl){
        try {
            Object arg[]= {cl.getClassName(),cl.getNumberOfPeople(),cl.getMaxNumberOfPeople(),cl.getCourseId()};

            String class_SQL;
            class_SQL = String.format("INSERT INTO Class VALUES ('CL'||to_char(seq_Class_id.nextval),'%s','%s','%s','%s')",arg);

            Statement statement = LoginViewController.connection.con.createStatement();

            int rows_class = statement.executeUpdate(class_SQL);
            if (rows_class > 0){
                System.out.println("Insert successfull");
            }else {
                System.out.println("Insert fail");
            }
        }catch (SQLException ex){
            JOptionPane.showMessageDialog(null,ex.toString(),"Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    public boolean Delete(Class cl){
        try {
            Object arg[]= {cl.getClassId()};
            String course_SQL;
            course_SQL = String.format("DELETE FROM Class WHERE class_id  = '%s'", arg);
            Statement statement = LoginViewController.connection.con.createStatement();
            int class_rows = statement.executeUpdate(course_SQL);
            if (class_rows > 0 ){
                System.out.println("Delete successfull");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,ex.toString(),"Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
    public boolean Update(Class cl) {
        try {
            Object arg[]= {cl.getClassName(),cl.getNumberOfPeople(),cl.getMaxNumberOfPeople(),cl.getCourseId(),cl.getClassId()};
            String sql;

            sql = String.format("UPDATE Class SET  className='%s',numberOfPeople='%s',maxNumberOfPeople='%s',course_id='%s' WHERE  class_id = '%s'", arg);
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
            this.data.clear();
            String sql = "SELECT class_id, className, numberOfPeople, MaxNumberOfPeople, course_id \n" +
                    "FROM Class ";
            ResultSet rs = LoginViewController.connection.con.createStatement().executeQuery(sql);
            while (rs.next()){

                Class cl = new Class(rs.getString(1),rs.getString(2), Integer.parseInt(rs.getString(3)),Integer.parseInt(rs.getString(4)),rs.getString(5));
                data.add(cl);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,ex.toString(),"Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    public ObservableList<Class> GetData(){
        try{
            LoadData();
        }catch (Exception ex){
            JOptionPane.showMessageDialog(null,ex.toString(),"Error at GetData() function", JOptionPane.ERROR_MESSAGE);
        }

        return this.data;
    }
}


