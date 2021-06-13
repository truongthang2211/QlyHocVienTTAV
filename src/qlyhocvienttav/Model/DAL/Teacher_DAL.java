package qlyhocvienttav.Model.DAL;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import qlyhocvienttav.Controller.LoginViewController;
import qlyhocvienttav.Model.DTO.Teacher;

import javax.swing.*;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Teacher_DAL {
    public Teacher_DAL(){};

    ObservableList<Teacher> Data = FXCollections.observableArrayList();

    public void Insert(Teacher teacher){

        try {
            Object arg_info[]= {teacher.getFullName(),teacher.getSex(),teacher.getDateOfBirth(),teacher.getNationality(),teacher.getAddress(),teacher.getEmail(),teacher.getPhoneNumber()};
            String teacher_sql;
            teacher_sql = String.format("INSERT INTO Teacher VALUES ('TC'||to_char(seq_teacher_id.currval))");
            String infoTeacher_sql;
            infoTeacher_sql = String.format("INSERT INTO Personal_Info VALUES ('TC'||to_char(seq_teacher_id.nextval),'%s','%s',TO_DATE('%s','YYYY-MM-DD'),'%s','%s','%s','%s')", arg_info);

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
    public boolean Delete(Teacher teacher){
        try {
            Object arg[]= {teacher.getTeacherId()};
            String info_sql,teacher_sql;
            info_sql = String.format("DELETE FROM Personal_Info WHERE ID = '%s'", arg);
            teacher_sql = String.format("DELETE FROM Teacher WHERE teacher_id = '%s'", arg);
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
    public boolean Update(Teacher teacher) {
        try {
            Object arg[]= {teacher.getFullName(),teacher.getSex(),teacher.getDateOfBirth(),teacher.getNationality(),teacher.getAddress(),teacher.getEmail(),teacher.getPhoneNumber(),teacher.getTeacherId() };
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

//    public ObservableList<Teacher> FindData(String dataFind) {
//        if (Data == null){
//            return null;
//        }
//            ObservableList<Teacher> list_teacherFind = FXCollections.observableArrayList();
//
//            Data.forEach((Teacher t) -> {
//                if (t.checkContain(dataFind)){
//                    list_teacherFind.add(t);
//                }
//            });
//
//
//        return list_teacherFind;
//    }

    //    public ObservableList<Teacher> FindData(String data){
//        ObservableList<Teacher> list_teacher = FXCollections.observableArrayList();
//        try{
//            String sql = "SELECT TC.Teacher_ID,FULLNAME,SEX,DATEOFBIRth,NATIONALITY,ADDRESS,EMAIL,PHONENUMBER \n" +
//                    "FROM Teacher TC JOIN PERSONAL_INFO IF ON TC.TEACHER_ID = IF.ID \n"
//                    +"WHERE TC.Teacher_ID Like %" + data + "% \n"
//                    +"OR FULLNAME Like %" + data + "% \n"
//                    + "OR Sex Like %" + data + "% \n"
//                    +"OR DateOfBird Like %" + data + "% \n"
//                    +"OR Address Like %" + data + "% \n"
//                    +"OR Email Like %" + data + "% \n"
//                    +"OR PhoneNumber Like %" + data + "% \n";
//
//            ResultSet rs = LoginViewController.connection.con.createStatement().executeQuery(sql);
//            while(rs.next()){
//                Date lcdate = rs.getDate(4);
//                String date = lcdate==null?"":lcdate.toString();
//                Teacher t = new Teacher(rs.getString(1),rs.getString(2),rs.getString(3),date,rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8));
//                list_teacher.add(t);
//            }
//
//
//        } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(null,ex.toString(),"Error", JOptionPane.ERROR_MESSAGE);
//        }
//        return list_teacher;
//    }
    public void LoadData(){

        try {
            this.Data.clear();
            String sql = "SELECT TC.Teacher_ID,FULLNAME,SEX,DATEOFBIRth,NATIONALITY,ADDRESS,EMAIL,PHONENUMBER \n" +
                    "FROM Teacher TC JOIN PERSONAL_INFO IF ON TC.TEACHER_ID = IF.ID";
            ResultSet rs = LoginViewController.connection.con.createStatement().executeQuery(sql);
            while (rs.next()){
                Date lcdate = rs.getDate(4);
                String date = lcdate==null?"":lcdate.toString();
                Teacher t = new Teacher(rs.getString(1),rs.getString(2),rs.getString(3),date,rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8));
                Data.add(t);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,ex.toString(),"Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    public ObservableList<Teacher> GetData(){
        try{
            LoadData();
        }catch (Exception ex){
            JOptionPane.showMessageDialog(null,ex.toString(),"Error at GetData() function", JOptionPane.ERROR_MESSAGE);
        }

        return this.Data;
    }

}
