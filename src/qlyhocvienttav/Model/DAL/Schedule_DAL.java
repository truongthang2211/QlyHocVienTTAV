package qlyhocvienttav.Model.DAL;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import qlyhocvienttav.Controller.LoginViewController;
import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import qlyhocvienttav.Model.DTO.Schedule;

public class Schedule_DAL {

    public Schedule_DAL() {
    };
    ObservableList<Schedule> data = FXCollections.observableArrayList();

    public void Insert(Schedule sche){
        try {
            Object arg[]= {sche.getDay(), sche.getShift(), sche.getClassId(), sche.getRoomId(),sche.getTeacherId()};

            String Schedule_SQL;
            Schedule_SQL = String.format("INSERT INTO Schedule VALUES ('STF'||to_char(seq_Schedule_id.nextval),'%s','%s','%s','%s','%s')",arg);
            Statement statement = LoginViewController.connection.con.createStatement();
            int rows_sche = statement.executeUpdate(Schedule_SQL);
            if (rows_sche > 0){
                System.out.println("Insert successfull");
            }else {
                System.out.println("Insert fail");
            }
        }catch (SQLException ex){
            JOptionPane.showMessageDialog(null,ex.toString(),"Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    public boolean Delete(Schedule sche){
        try {
            Object arg[]= {sche.getScheduleID()};
            String studentFee_SQL;
            studentFee_SQL = String.format("DELETE FROM Schedule WHERE schedule_id  = '%s'", arg);
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
    public boolean Update(Schedule sche) {
        try {
            Object arg[]= {sche.getDay(), sche.getShift(), sche.getClassId(), sche.getRoomId(),sche.getTeacherId(),sche.getScheduleID()};
            String sql;
            sql = String.format("UPDATE Schedule SET dayOfSchedule = '%s', timeOfSchedule ='%s',class_id ='%s', room_id ='%s',teacher_id='%s' WHERE schedule_id  = '%s'", arg);
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
    public void LoadData(String tcid){

        try {
            this.data.clear();
            String sql = "select schedule_id, dayOfSchedule, timeOfSchedule, sche.class_id , cl.className, sche.room_id, room.room_name, sche.teacher_id , ps.fullName " +
                    "from schedule sche join class cl on sche.class_id = cl.class_id "+
                    "join room on sche.room_id = room.room_id join personal_info ps on sche.teacher_id = ps.id ";
            if (!tcid.equals("Default")){
                sql += ("where sche.teacher_id = '" + tcid + "'");
            }
            ResultSet rs = LoginViewController.connection.con.createStatement().executeQuery(sql);
            while (rs.next()){
                Schedule sche = new Schedule(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9));
                data.add(sche);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,ex.toString(),"Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    public ObservableList<Schedule> GetData(){
        LoadData("Default");
        return this.data;
    }
    public ObservableList<Schedule> GetDataByTeacher(String TeacherID){
        LoadData(TeacherID);
        return this.data;
    }
}
