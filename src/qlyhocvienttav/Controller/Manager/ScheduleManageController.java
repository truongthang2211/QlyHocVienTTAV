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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import qlyhocvienttav.Model.DAL.Class_DAL;
import qlyhocvienttav.Model.DAL.Room_DAL;
import qlyhocvienttav.Model.DAL.Schedule_DAL;
import qlyhocvienttav.Model.DAL.Teacher_DAL;
import qlyhocvienttav.Model.DTO.Schedule;
import qlyhocvienttav.Model.DTO.Class;
import qlyhocvienttav.Model.DTO.Room;
import qlyhocvienttav.Model.DTO.Teacher;

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
    private JFXTextField txt_findData;
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
        AddDatatoCbb();
        
        TableColumn scheduleID = new TableColumn("Schedule ID");
        TableColumn Day = new TableColumn("Day");
        TableColumn Shift = new TableColumn("Shift");
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
    private void AddDatatoCbb(){
        ObservableList<Class> Classdata = new Class_DAL().GetData();
        Classdata.forEach(t -> {
            ClassIDCbb.getItems().add(t.getClassId());
        });
        ObservableList<Room> Roomdata = new Room_DAL().GetData();
        Roomdata.forEach(t -> {
            RoomIDCbb.getItems().add(t.getRoomId());
        });
        ObservableList<Teacher> Teacherdata = new Teacher_DAL().GetData();
        Teacherdata.forEach(t -> {
            TeacherIDCbb.getItems().add(t.getTeacherId());
        });
        ClassIDCbb.getSelectionModel().selectedItemProperty().addListener( (Observable, oldValue, newValue) -> 
            Classdata.forEach(t -> {
                if (newValue.equals(t.getClassId())){
                    ClassnameTxt.setText(t.getClassName());
                }
            })
        );
        RoomIDCbb.getSelectionModel().selectedItemProperty().addListener( (Observable, oldValue, newValue) -> 
            Roomdata.forEach(t -> {
                if (newValue.equals(t.getRoomId())){
                    RoomnameTxt.setText(t.getRoomName());
                }
            })
        );
        TeacherIDCbb.getSelectionModel().selectedItemProperty().addListener( (Observable, oldValue, newValue) -> 
            Teacherdata.forEach(t -> {
                if (newValue.equals(t.getTeacherId())){
                    TeachernameTxt.setText(t.getFullName());
                }
            })
        );
        ObservableList<String> DayList = FXCollections.observableArrayList("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday","Sunday");
        ObservableList<String> ShiftList = FXCollections.observableArrayList("9:00 - 10:30", "13:00 - 14:30", "15:00 - 16:30", "17:30 - 19:00", "19:15 - 20:45");
        DayCbb.setItems(DayList);
        ShiftCbb.setItems(ShiftList);
    }
     @FXML
    private void Search(KeyEvent event) {
         String dataFind = txt_findData.getText();
        if (data == null){
            return;
        }
        
            ObservableList<Schedule> list_ScheduleFind = FXCollections.observableArrayList();

            data.forEach((Schedule t) -> {
                if (t.checkContain(dataFind)){
                    list_ScheduleFind.add(t);
                }
            });
            maintable.setItems(list_ScheduleFind);
    }
}
