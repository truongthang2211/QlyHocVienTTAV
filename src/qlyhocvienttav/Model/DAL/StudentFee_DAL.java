package qlyhocvienttav.Model.DAL;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import qlyhocvienttav.Controller.LoginViewController;
import qlyhocvienttav.Model.DTO.StudentFee;
import qlyhocvienttav.Model.DTO.Teacher;

import javax.swing.*;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StudentFee_DAL {

    public StudentFee_DAL() {
    };
    ObservableList<StudentFee> data = FXCollections.observableArrayList();

    public void Insert(StudentFee stf){
        try {
            Object arg[]= {stf.getStudent_id(),stf.getAmountOfFeeIsComplete(),stf.getStatus(),stf.getDateOfCompleteFee()};

            String studentFee_SQL;
            studentFee_SQL = String.format("INSERT INTO StudentFee VALUES ('STF'||to_char(seq_StudentFee_id.nextval),'%s','%s','%s',TO_DATE('%s','YYYY-MM-DD'))",arg);

            Statement statement = LoginViewController.connection.con.createStatement();

            int rows_stf = statement.executeUpdate(studentFee_SQL);
            if (rows_stf > 0){
                System.out.println("Insert successfull");
            }else {
                System.out.println("Insert fail");
            }
        }catch (SQLException ex){
            JOptionPane.showMessageDialog(null,ex.toString(),"Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    public boolean Delete(StudentFee stf){
        try {
            Object arg[]= {stf.getIdFee()};
            String studentFee_SQL;
            studentFee_SQL = String.format("DELETE FROM StudentFee WHERE studentFeeId  = '%s'", arg);
            Statement statement = LoginViewController.connection.con.createStatement();
            int studentFee_rows = statement.executeUpdate(studentFee_SQL);
            if (studentFee_rows > 0 ){
                System.out.println("Delete successfull");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,ex.toString(),"Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
    public boolean Update(StudentFee stf) {
        try {
            Object arg[]= {stf.getStudent_id(),stf.getAmountOfFeeIsComplete(),stf.getStatus(),stf.getDateOfCompleteFee(),stf.getIdFee(),};
            String sql;
            sql = String.format("UPDATE StudentFee SET student_id  = '%s', amountOfFeeIsComplete  = '%s',statusOfFee = '%s', dateOfCompleteFee  = TO_DATE('%s','YYYY-MM-DD') WHERE studentFeeId  = '%s'", arg);
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
            String sql = "SELECT STF.studentFeeId, STF.student_id,IF.fullName,CR.COURSENAME,amountOfFeeIsComplete ,CR.FEE,dateOfCompleteFee,statusOfFee " +
                    "FROM StudentFee STF "+
                    "JOIN STUDENT ST ON ST.STUDENT_ID = STF.STUDENT_ID " +
                    "JOIN PERSONAL_INFO IF ON ST.student_id  = IF.ID "+
                    "JOIN COURSE CR ON ST.COURSE_ID = CR.COURSE_ID";
            ResultSet rs = LoginViewController.connection.con.createStatement().executeQuery(sql);
            while (rs.next()){
                Date lcdate = rs.getDate(7);
                String date = lcdate==null?"":lcdate.toString();
                StudentFee stf = new StudentFee(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getDouble(5),rs.getDouble(6),date,rs.getString(8));
                data.add(stf);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,ex.toString(),"Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    public ObservableList<StudentFee> GetData(){
        try{
            LoadData();
        }catch (Exception ex){
            JOptionPane.showMessageDialog(null,ex.toString(),"Error at GetData() function", JOptionPane.ERROR_MESSAGE);
        }

        return this.data;
    }
}
