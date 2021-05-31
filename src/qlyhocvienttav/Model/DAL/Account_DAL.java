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
import qlyhocvienttav.Model.DTO.Account;
import qlyhocvienttav.Model.DTO.Manager;
import qlyhocvienttav.Model.DTO.Teacher;


/**
 *
 * @author nvtru
 */
public class Account_DAL {
    public Account_DAL(){};
    ObservableList <Account> Data = FXCollections.observableArrayList();
    public void Insert(Account acc){

        try {
            Object arg_acc[]= {acc.getUsername(),acc.getPassword(),acc.getAcctype(),acc.getOwner()};
            String acc_sql = String.format("INSERT INTO Account VALUES ('%s','%s','%s',sysdate, %s)",arg_acc); // mỗi cái %s tương ứng với các phần tử cả arg_acc
            Statement statement = LoginViewController.connection.con.createStatement();
            if(acc.getAcctype().equals("Teacher")){
               Teacher_DAL teacher_dal = new Teacher_DAL();
               Teacher t = new Teacher("",acc.getFullName(),acc.getSex(),acc.getDateOfBirth(),acc.getNationality(),acc.getAddress(),acc.getEmail(),acc.getPhoneNumber());
               teacher_dal.Insert(t);
            }else if (acc.getAcctype().equals("Manager")){
                Manager_DAL manager_dal = new Manager_DAL();
                Manager m = new Manager("",acc.getFullName(),acc.getSex(),acc.getDateOfBirth(),acc.getNationality(),acc.getAddress(),acc.getEmail(),acc.getPhoneNumber());
                manager_dal.Insert(m);
            }
            int rows = statement.executeUpdate(acc_sql);
            if (rows > 0){
                System.out.println("Insert into account successfull");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,ex.toString(),"Error", JOptionPane.ERROR_MESSAGE);
        }

    }
    public boolean Delete(Account acc){
        try {
            Object arg[]= {acc.getUsername()};
            String acc_sql;
            acc_sql = String.format("DELETE FROM ACCOUNT WHERE OWNER = '%s'", arg);
            Statement statement = LoginViewController.connection.con.createStatement();
            int acc_rows = statement.executeUpdate(acc_sql);
            if ( acc_rows >0){
                System.out.println("Delete successfull");
            }
            if(acc.getAcctype().equals("Teacher")){
               Teacher_DAL teacher_dal = new Teacher_DAL();
               Teacher t = new Teacher(acc.getOwner(),acc.getFullName(),acc.getSex(),acc.getDateOfBirth(),acc.getNationality(),acc.getAddress(),acc.getEmail(),acc.getPhoneNumber());
               teacher_dal.Delete(t);
            }else if (acc.getAcctype().equals("Manager")){
                Manager_DAL manager_dal = new Manager_DAL();
                Manager m = new Manager(acc.getOwner(),acc.getFullName(),acc.getSex(),acc.getDateOfBirth(),acc.getNationality(),acc.getAddress(),acc.getEmail(),acc.getPhoneNumber());
                manager_dal.Delete(m);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,ex.toString(),"Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }    
    public boolean Update(Account acc) {
        try {
            Object arg_acc[]= {acc.getPassword(),acc.getAcctype(),acc.getOwner(),acc.getUsername()};
            String sql;
            sql = String.format("UPDATE Account SET Password = '%s', acctype = '%s', Owner_ID = '%s' WHERE username = '%s'", arg_acc);
            Statement statement = LoginViewController.connection.con.createStatement();
            int rows = statement.executeUpdate(sql);
            if (rows > 0){
                System.out.println("Update successfull");
            }
            if(acc.getAcctype().equals("Teacher")){
               Teacher_DAL teacher_dal = new Teacher_DAL();
               Teacher t = new Teacher(acc.getOwner(),acc.getFullName(),acc.getSex(),acc.getDateOfBirth(),acc.getNationality(),acc.getAddress(),acc.getEmail(),acc.getPhoneNumber());
               teacher_dal.Update(t);
            }else if (acc.getAcctype().equals("Manager")){
                Manager_DAL manager_dal = new Manager_DAL();
                Manager m = new Manager(acc.getOwner(),acc.getFullName(),acc.getSex(),acc.getDateOfBirth(),acc.getNationality(),acc.getAddress(),acc.getEmail(),acc.getPhoneNumber());
                manager_dal.Update(m);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,ex.toString(),"Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;

    }
    public void LoadData(String role){

        try {
            this.Data.clear();
            String sql;
            if (role.equals("All")){
                sql ="select *\n" +
                "from account a join personal_info if on if.id= a.Owner_ID";
            }else {
                sql = String.format("select *\n" +
                "from account a join personal_info if on if.id= a.Owner_ID where kindOfAccount= '%s'",role);
            }
            
            
            ResultSet rs = LoginViewController.connection.con.createStatement().executeQuery(sql);
            while (rs.next()){
                Date lcdate = rs.getDate(9);
                String date = lcdate==null?"":lcdate.toString();
                Account  ac = new Account(rs.getString("username"),rs.getString("password"),rs.getString("kindofaccount"),rs.getString("ID"),rs.getString(4),rs.getString(7),rs.getString(8),date,rs.getString(10),rs.getString(11),rs.getString(12),rs.getString(13));
                        
                Data.add(ac);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,ex.toString(),"Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    public ObservableList<Account> GetManagerData(){
            LoadData("Manager");
        return this.Data;
    }
    public ObservableList<Account> GetTeacherData(){
            LoadData("Teacher");
        return this.Data;
    }
    public ObservableList<Account> GetData(){
            LoadData("All");
        return this.Data;
    }
    
}
