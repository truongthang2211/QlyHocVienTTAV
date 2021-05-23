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
import qlyhocvienttav.Model.DAL.TestSche_DAL;
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
    private JFXTextField courseIDTxt;
    @FXML
    private JFXTextField roomIDtxt;
    @FXML
    private JFXTextField testscheduleIDTxt;
    @FXML
    private JFXDatePicker DatePicker;
    @FXML
    private JFXComboBox<String> shiftCbb;
    @FXML
    private JFXTextField kindoftestTxt;
    @FXML
    private JFXTextField teacherIDtxt;
    @FXML
    private JFXButton btn_add;

    @FXML
    private JFXButton btn_edit;

    @FXML
    private JFXButton btn_delete;
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
         
        TableColumn id = new TableColumn("Test Schedule ID");
        TableColumn kindofDate = new TableColumn("Date");
        TableColumn Shift = new TableColumn("Ca thi");
        TableColumn room_id = new TableColumn("Phong thi");
        TableColumn course_id = new TableColumn("Ma khoa hoc");
        TableColumn teacher_id = new TableColumn("Ma giao vien");
        TableColumn kindoftets = new TableColumn("Kieu thi");
        
        id.setCellValueFactory(new PropertyValueFactory<TestSchedule,String>("Tets Schedule ID"));
        room_id.setCellValueFactory(new PropertyValueFactory<TestSchedule,String>("Phong thi"));
        course_id.setCellValueFactory(new PropertyValueFactory<TestSchedule,String>("Ma khoa hoc"));
        kindofDate.setCellValueFactory(new PropertyValueFactory<>("Date"));
        teacher_id.setCellValueFactory(new PropertyValueFactory<TestSchedule,String>("Ma giao vien"));
        kindoftets.setCellValueFactory(new PropertyValueFactory<>("Kieu thi"));
        Shift.setCellValueFactory(new PropertyValueFactory<>("Ca thi"));
        
        maintable.getColumns().addAll(id,kindofDate,course_id,Shift,room_id,teacher_id,kindoftets);
        data=testsche_dal.GetData();
        maintable.setItems(data);
    }    
    
    @FXML
    private void displaySelected(MouseEvent event) {
        TestSchedule ts = maintable.getSelectionModel().getSelectedItem();
        if (ts == null ){
            System.out.println("Khong thay ts");
        }else {
            testscheduleIDTxt.setText(ts.getid());
            courseIDTxt.setText(ts.getCourse_ID());
            shiftCbb.getSelectionModel().select(ts.getShift());
            
            teacherIDtxt.setText(ts.getTeacher_ID());
            roomIDtxt.setText(ts.getRoom_ID());
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
        TestSchedule ts = new TestSchedule(testscheduleIDTxt.getText(),shiftCbb.getSelectionModel().getSelectedItem(),courseIDTxt.getText(),DatePicker.getValue().toString(),teacherIDtxt.getText(),roomIDtxt.getText(),kindoftestTxt.getText());
        return ts;
    }
    
    private boolean CheckInputGUI(){
        String date= DatePicker.getValue() == null?"":DatePicker.getValue().toString();
        String [] ListInput = {shiftCbb.getSelectionModel().getSelectedItem(),teacherIDtxt.getText(),date,testscheduleIDTxt.getText(),courseIDTxt.getText(),roomIDtxt.getText(),kindoftestTxt.getText()};
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
