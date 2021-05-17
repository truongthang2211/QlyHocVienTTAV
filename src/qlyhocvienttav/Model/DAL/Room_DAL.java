package qlyhocvienttav.Model.DAL;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import qlyhocvienttav.Controller.LoginViewController;
import qlyhocvienttav.Model.DTO.Room;

import javax.swing.*;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Room_DAL {
    public Room_DAL(){};
    ObservableList<Room> data = FXCollections.observableArrayList();

    public void Insert(Room room){
        try {
            Object arg[]= {room.getRoomName(),room.getCapacity()};

            String room_SQL;
            room_SQL = String.format("INSERT INTO Room VALUES ('R'||to_char(seq_Room_id.nextval),'%s','%s')",arg);

            Statement statement = LoginViewController.connection.con.createStatement();

            int rows_room = statement.executeUpdate(room_SQL);
            if (rows_room > 0){
                System.out.println("Insert successfull");
            }else {
                System.out.println("Insert fail");
            }
        }catch (SQLException ex){
            JOptionPane.showMessageDialog(null,ex.toString(),"Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    public boolean Delete(Room room){
        try {
            Object arg[]= {room.getRoomId()};
            String room_SQL;
            room_SQL = String.format("DELETE FROM Room WHERE room_id  = '%s'", arg);
            Statement statement = LoginViewController.connection.con.createStatement();
            int room_rows = statement.executeUpdate(room_SQL);
            if (room_rows > 0 ){
                System.out.println("Delete successfull");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,ex.toString(),"Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
    public boolean Update(Room room) {
        try {
            Object arg[]= {room.getRoomName(),room.getCapacity(),room.getRoomId()};
            String sql;

            sql = String.format("UPDATE Room SET  room_name='%s',capacity='%s' WHERE  room_id = '%s'", arg);
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
            String sql = "SELECT room_id, room_name, capacity \n" +
                    "FROM Room ";
            ResultSet rs = LoginViewController.connection.con.createStatement().executeQuery(sql);
            while (rs.next()){

                Room room = new Room(rs.getString(1),rs.getString(2),Integer.parseInt(rs.getString(3)));
                data.add(room);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,ex.toString(),"Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    public ObservableList<Room> GetData(){
        try{
            LoadData();
        }catch (Exception ex){
            JOptionPane.showMessageDialog(null,ex.toString(),"Error at GetData() function", JOptionPane.ERROR_MESSAGE);
        }

        return this.data;
    }
}


