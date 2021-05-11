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
            Object arg[]= {stf.getIdStudent(),stf.getAmountOfFee(),stf.getStatus(),stf.getDateOfCompleteFee()};

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
            Object arg[]= {stf.getIdStudent(),stf.getAmountOfFee(),stf.getStatus(),stf.getDateOfCompleteFee(),stf.getIdFee(),};
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
            String sql = "SELECT STF.studentFeeId, student_id,IF.fullName,amountOfFeeIsComplete ,statusOfFee,dateOfCompleteFee \n" +
                    "FROM StudentFee STF JOIN PERSONAL_INFO IF ON STF.student_id  = IF.ID";
            ResultSet rs = LoginViewController.connection.con.createStatement().executeQuery(sql);
            while (rs.next()){
                Date lcdate = rs.getDate(6);
                String date = lcdate==null?"":lcdate.toString();
                StudentFee stf = new StudentFee(rs.getString(1),rs.getString(2),rs.getString(3),rs.getLong(4),rs.getString(5),date);
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
