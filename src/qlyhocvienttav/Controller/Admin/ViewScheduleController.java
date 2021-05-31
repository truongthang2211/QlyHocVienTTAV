/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qlyhocvienttav.Controller.Admin;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import qlyhocvienttav.Model.DAL.Schedule_DAL;
import qlyhocvienttav.Model.DTO.Schedule;

/**
 * FXML Controller class
 *
 * @author Thang
 */
public class ViewScheduleController implements Initializable {
    private ObservableList<Schedule> data;
    private Schedule_DAL sche_dal = new Schedule_DAL();
    @FXML
    private Label topcenterlabel;
    @FXML
    private TableView<Schedule> maintable;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TableColumn scheduleID = new TableColumn("Schedule ID");
        TableColumn Day = new TableColumn("Thứ");
        TableColumn Shift = new TableColumn("Thời gian");
        TableColumn classID = new TableColumn("Class ID");
        TableColumn roomID = new TableColumn("Room ID");
        TableColumn teacherID = new TableColumn("Teacher ID");
        

        scheduleID.setCellValueFactory(new PropertyValueFactory<>("ScheduleID"));
        Day.setCellValueFactory(new PropertyValueFactory<>("Day"));
        Shift.setCellValueFactory(new PropertyValueFactory<>("Shift"));
        classID.setCellValueFactory(new PropertyValueFactory<>("ClassId"));
        roomID.setCellValueFactory(new PropertyValueFactory<>("RoomId"));
        teacherID.setCellValueFactory(new PropertyValueFactory<>("TeacherId"));
        maintable.getColumns().addAll(scheduleID,Day,Shift,classID,roomID,teacherID);
        data = sche_dal.GetData();
        maintable.setItems(data);
    }    
    
}
