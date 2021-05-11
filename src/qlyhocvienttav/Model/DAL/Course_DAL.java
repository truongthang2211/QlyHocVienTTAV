package qlyhocvienttav.Model.DAL;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import qlyhocvienttav.Controller.LoginViewController;
import qlyhocvienttav.Model.DTO.Course;
import qlyhocvienttav.Model.DTO.StudentFee;

import javax.swing.*;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Course_DAL {
    public Course_DAL(){};
    ObservableList<Course> data = FXCollections.observableArrayList();

    public void Insert(Course course){
        try {
            Object arg[]= {course.getCourseName(),course.getFeeOfCourse(),course.getDateStart(),course.getDateEnd()};

            String studentFee_SQL;
            studentFee_SQL = String.format("INSERT INTO Course VALUES ('C'||to_char(seq_Course_id.nextval),'%s','%s',TO_DATE('%s','YYYY-MM-DD'),TO_DATE('%s','YYYY-MM-DD'))",arg);

            Statement statement = LoginViewController.connection.con.createStatement();

            int rows_course = statement.executeUpdate(studentFee_SQL);
            if (rows_course > 0){
                System.out.println("Insert successfull");
            }else {
                System.out.println("Insert fail");
            }
        }catch (SQLException ex){
            JOptionPane.showMessageDialog(null,ex.toString(),"Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    public boolean Delete(Course course){
        try {
            Object arg[]= {course.getCourse_id()};
            String course_SQL;
            course_SQL = String.format("DELETE FROM Course WHERE course_id  = '%s'", arg);
            Statement statement = LoginViewController.connection.con.createStatement();
            int course_rows = statement.executeUpdate(course_SQL);
            if (course_rows > 0 ){
                System.out.println("Delete successfull");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,ex.toString(),"Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
    public boolean Update(Course course) {
        try {
            Object arg[]= {course.getCourseName(),course.getFeeOfCourse(),course.getDateStart(),course.getDateEnd(),course.getCourse_id()};
            String sql;

            sql = String.format("UPDATE Course SET  courseName  = '%s', fee   = '%s',dayStart  =  TO_DATE('%s','YYYY-MM-DD'), dayEnd   = TO_DATE('%s','YYYY-MM-DD') WHERE course_id  = '%s'", arg);
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
            String sql = "SELECT course_id, courseName, fee, dayStart, dayEnd \n" +
                    "FROM Course ";
            ResultSet rs = LoginViewController.connection.con.createStatement().executeQuery(sql);
            while (rs.next()){
                Date dateStart = rs.getDate(4);
                Date dateEnd = rs.getDate(5);
                String str_dayStart = dateStart==null?"":dateStart.toString();

                String str_dayEnd = dateEnd==null?"":dateEnd.toString();
                Course course = new Course(rs.getString(1),rs.getString(2),Long.parseLong(rs.getString(3)),str_dayStart,str_dayEnd);
                data.add(course);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,ex.toString(),"Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    public ObservableList<Course> GetData(){
        try{
            LoadData();
        }catch (Exception ex){
            JOptionPane.showMessageDialog(null,ex.toString(),"Error at GetData() function", JOptionPane.ERROR_MESSAGE);
        }

        return this.data;
    }
}
