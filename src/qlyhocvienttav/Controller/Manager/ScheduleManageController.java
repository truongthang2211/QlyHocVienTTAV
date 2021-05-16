/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qlyhocvienttav.Controller.Manager;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import qlyhocvienttav.Model.DAL.Schedule_DAL;
import qlyhocvienttav.Model.DTO.Schedule;

/**
 * FXML Controller class
 *
 * @author Thang
 */
public class ScheduleManageController implements Initializable {
    private ObservableList<Schedule> data;
    private Schedule_DAL sche_dal = new Schedule_DAL();
    @FXML
    private Label topcenterlabel;
    @FXML
    private TableView<Schedule> maintable;
    @FXML
    private JFXTextField ScheduleIDTxt;
    @FXML
    private JFXComboBox<String> DayCbb;
    @FXML
    private JFXComboBox<String> ShiftCbb;
    @FXML
    private JFXTextField ClassnameTxt;
    @FXML
    private JFXTextField RoomnameTxt;
    @FXML
    private JFXTextField TeachernameTxt;
    @FXML
    private JFXComboBox<String> ClassIDCbb;
    @FXML
    private JFXComboBox<String> TeacherIDCbb;
    @FXML
    private JFXComboBox<String> RoomIDCbb;

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
        classID.setCellValueFactory(new PropertyValueFactory<>("ClassID"));
        roomID.setCellValueFactory(new PropertyValueFactory<>("RoomID"));
        teacherID.setCellValueFactory(new PropertyValueFactory<>("TeacherID"));
        maintable.getColumns().addAll(scheduleID,Day,Shift,classID,roomID,teacherID);
        data = sche_dal.GetData();
        maintable.setItems(data);
    }    

    @FXML
    private void AddBtn(ActionEvent event) {
        Schedule sche = GetScheduleFromGUI();
        sche_dal.Insert(sche);
        data = sche_dal.GetData();
    }

    @FXML
    private void EditBtn(ActionEvent event) {
        Schedule sche = GetScheduleFromGUI();
        sche_dal.Update(sche);
        data = sche_dal.GetData();
    }

    @FXML
    private void DeleteBtn(ActionEvent event) {
        Schedule sche = GetScheduleFromGUI();
        sche_dal.Delete(sche);
        data = sche_dal.GetData();
    }
    private Schedule GetScheduleFromGUI(){
        Schedule ans = new Schedule(ScheduleIDTxt.getText(),DayCbb.getSelectionModel().getSelectedItem(),ShiftCbb.getSelectionModel().getSelectedItem(),ClassIDCbb.getSelectionModel().getSelectedItem(),RoomIDCbb.getSelectionModel().getSelectedItem(),TeacherIDCbb.getSelectionModel().getSelectedItem());
        return ans;
    }

    @FXML
    private void displaySelected(MouseEvent event) {
        Schedule sche = maintable.getSelectionModel().getSelectedItem();
        ScheduleIDTxt.setText(sche.getScheduleID());
        DayCbb.getSelectionModel().select(sche.getDay());
        ShiftCbb.getSelectionModel().select(sche.getShift());
        ClassIDCbb.getSelectionModel().select(sche.getClassId());
        RoomIDCbb.getSelectionModel().select(sche.getRoomId());
        TeacherIDCbb.getSelectionModel().select(sche.getTeacherId());
        ClassnameTxt.setText(sche.getClassName());
        RoomnameTxt.setText(sche.getRoomName());
        TeachernameTxt.setText(sche.getTeacherName());
    }
}
