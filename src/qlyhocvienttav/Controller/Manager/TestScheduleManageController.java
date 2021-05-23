/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qlyhocvienttav.Controller.Manager;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;
import qlyhocvienttav.Model.DAL.Course_DAL;
import qlyhocvienttav.Model.DAL.Room_DAL;
import qlyhocvienttav.Model.DAL.Teacher_DAL;
import qlyhocvienttav.Model.DAL.TestSche_DAL;
import qlyhocvienttav.Model.DTO.Course;
import qlyhocvienttav.Model.DTO.Room;
import qlyhocvienttav.Model.DTO.Teacher;
import qlyhocvienttav.Model.DTO.TestSchedule;

/**
 * FXML Controller class
 *
 * @author Thang
 */
public class TestScheduleManageController implements Initializable {
    ObservableList<TestSchedule> data;
    TestSche_DAL testsche_dal = new TestSche_DAL();
    @FXML
    private Label topcenterlabel;
    @FXML
    private TableView<TestSchedule> maintable;
    @FXML
    private JFXTextField searchTxt;
    @FXML
    private JFXComboBox<String> CourseCbb;
    
    @FXML
    private JFXTextField testscheduleIDTxt;
    @FXML
    private JFXDatePicker DatePicker;
    @FXML
    private JFXComboBox<String> shiftCbb;
    @FXML
    private JFXTextField kindoftestTxt;
    
    @FXML
    private JFXComboBox<String> Room_Cbb;

    @FXML
    private JFXComboBox<String> Teacher_Cbb;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ObservableList<String> shiftCbbList = FXCollections.observableArrayList("1","2","3");
        shiftCbb.setItems(shiftCbbList);
        
        
         
        TableColumn Test_Sche_ID = new TableColumn("ID"); // Cai tren day la hien thi
        TableColumn kindofDate = new TableColumn("Date");// del hieu sao cai id no ko hien ki 
        TableColumn Shift = new TableColumn("Ca thi");
        TableColumn room_id = new TableColumn("Phong thi");
        TableColumn course_id = new TableColumn("Ma khoa hoc");
        TableColumn teacher_id = new TableColumn("Ma giao vien");//sua ben dal ko can
        TableColumn kindoftets = new TableColumn("Kieu thi");
        
        Test_Sche_ID.setCellValueFactory(new PropertyValueFactory<>("TestSche_ID"));
        room_id.setCellValueFactory(new PropertyValueFactory<>("Room_ID"));
        course_id.setCellValueFactory(new PropertyValueFactory<>("Course_ID"));
        kindofDate.setCellValueFactory(new PropertyValueFactory<>("TestDate"));
        teacher_id.setCellValueFactory(new PropertyValueFactory<>("Teacher_ID"));
        kindoftets.setCellValueFactory(new PropertyValueFactory<>("Loai_KT"));
        Shift.setCellValueFactory(new PropertyValueFactory<>("Shift"));
        
        maintable.getColumns().addAll(Test_Sche_ID,kindofDate,course_id,Shift,room_id,teacher_id,kindoftets);
        data=testsche_dal.GetData();
        maintable.setItems(data);
        GetCourseToCbb();
        GetTeacherToCbb();
        GetRoomToCbb();
    }    
    
    @FXML
    private void displaySelected(MouseEvent event) {
        TestSchedule ts = maintable.getSelectionModel().getSelectedItem();
        if (ts == null ){
            System.out.println("Khong thay ts");
        }else {
            testscheduleIDTxt.setText(ts.getid());
            CourseCbb.getSelectionModel().select(ts.getCourse_ID());
            shiftCbb.getSelectionModel().select(ts.getShift());
            
            Teacher_Cbb.getSelectionModel().select(ts.getTeacher_ID());
            Room_Cbb.getSelectionModel().select(ts.getRoom_ID());
            kindoftestTxt.setText(ts.getLoai_KT());
            
            DatePicker.setValue(LocalDate.parse(ts.getTestDate(), DateTimeFormatter.ISO_DATE));
        }
    }
    
    @FXML
    private void AddBtn(ActionEvent event) {
        if (CheckInputGUI()){
        TestSchedule ts = GetTestScheduleFromGUI();
        testsche_dal.Insert(ts);
        data = testsche_dal.GetData();
       }
    }

    @FXML
    private void EditBtn(ActionEvent event) {
        if (CheckInputGUI()){
            TestSchedule ts = maintable.getSelectionModel().getSelectedItem();
            TestSchedule ts2 = GetTestScheduleFromGUI();
            testsche_dal.Update(ts2);
            data = testsche_dal.GetData();
        }
    }

    @FXML
    private void DeleteBtn(ActionEvent event) {
        TestSchedule ts = maintable.getSelectionModel().getSelectedItem();
        testsche_dal.Delete(ts);
        data = testsche_dal.GetData();
    }
    
    
    
    private TestSchedule GetTestScheduleFromGUI(){
        String date= DatePicker.getValue() == null?"":DatePicker.getValue().toString();
        TestSchedule ts = new TestSchedule(testscheduleIDTxt.getText(),Teacher_Cbb.getSelectionModel().getSelectedItem(),CourseCbb.getSelectionModel().getSelectedItem(),Room_Cbb.getSelectionModel().getSelectedItem(),kindoftestTxt.getText(),date,shiftCbb.getSelectionModel().getSelectedItem());
        return ts;
    }
    
    private void GetCourseToCbb(){
        CourseCbb.getItems().clear();
        Course_DAL course_dal = new Course_DAL();
        ObservableList<Course> CourseList = course_dal.GetData();
        CourseList.forEach(cr -> {
            CourseCbb.getItems().add(cr.getCourse_id());
        });
        CourseCbb.getSelectionModel().select(0);
    }
    
    private void GetTeacherToCbb(){
        Teacher_Cbb.getItems().clear();
        Teacher_DAL teacher_dal = new Teacher_DAL();
        ObservableList<Teacher> CourseList = teacher_dal.GetData();
        CourseList.forEach(cr -> {
            Teacher_Cbb.getItems().add(cr.getTeacherId());
        });
        Teacher_Cbb.getSelectionModel().select(0);
    }
    
    private void GetRoomToCbb(){
        Room_Cbb.getItems().clear();
        Room_DAL room_dal = new Room_DAL();
        ObservableList<Room> CourseList = room_dal.GetData();
        CourseList.forEach(cr -> {
            Room_Cbb.getItems().add(cr.getRoomId());
        });
        Room_Cbb.getSelectionModel().select(0);
    }
    
    private boolean CheckInputGUI(){
        String date= DatePicker.getValue() == null?"":DatePicker.getValue().toString();
        String [] ListInput = {testscheduleIDTxt.getText(), shiftCbb.getSelectionModel().getSelectedItem(),Teacher_Cbb.getSelectionModel().getSelectedItem(),date,CourseCbb.getSelectionModel().getSelectedItem(),Room_Cbb.getSelectionModel().getSelectedItem(),kindoftestTxt.getText()};
        String [] Property = {"Test Schedule ID", "Date","Shift","Course ID","Teacher ID","Room ID","Kind of test"};
        for (int i = 0 ; i< ListInput.length; i++){
            if (ListInput[i] == null || ListInput[i].equals("")){
                String ErrorStr = Property[i] + " can not be empty";
                JOptionPane.showMessageDialog(null,ErrorStr,"Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
        return true;
    }
}
