/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qlyhocvienttav.Controller.Manager;



/**
 * FXML Controller class
 *
 * @author Thang
 */

import com.jfoenix.controls.JFXComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import qlyhocvienttav.Model.DAL.StudentFee_DAL;
import qlyhocvienttav.Model.DTO.StudentFee;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javax.swing.JOptionPane;
import qlyhocvienttav.Model.DAL.Student_DAL;
import qlyhocvienttav.Model.DTO.Student;

public class StudentFeeManageController implements Initializable {
    public ObservableList<StudentFee> data;
    StudentFee_DAL stf_dal= new StudentFee_DAL();
    DecimalFormat myFormat = new DecimalFormat("###,##0.00");
    @FXML
    private Label topcenterlabel;

    @FXML
    private TableView<StudentFee> maintable;
    @FXML
    private JFXTextField txt_FeeID;

    @FXML
    private JFXTextField txt_StudentId;

    @FXML
    private JFXTextField txt_FullName;

    @FXML
    private JFXDatePicker datePickerOfComplete;
    @FXML
    private JFXTextField txt_Course;
    @FXML
    private JFXComboBox<String> Cbb_Status;
    @FXML
    private JFXTextField Txt_Search;
    @FXML
    private JFXTextField Txt_amountOfFee;
    @FXML
    private JFXTextField Txt_FeePay;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        Txt_FeePay.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (!newValue.matches("[\\d\\.]*") ||   !Txt_amountOfFee.getText().matches("[\\d\\.].+") ) {
                Txt_FeePay.setText(newValue.replaceAll("[^\\d\\.]", ""));
            }else {
                int index = Double.parseDouble(Txt_FeePay.getText()) >= Double.parseDouble(Txt_amountOfFee.getText())?0:1;
                Cbb_Status.getSelectionModel().select(index);
            }
        });
        
        
        
        
        TableColumn idFee = new TableColumn("ID Fee");
        TableColumn idStudent = new TableColumn("Student ID");
        TableColumn fullNameStudent = new TableColumn("Full Name");
        TableColumn courseName = new TableColumn("Course Name");
        TableColumn FeePay = new TableColumn("Fee Paid");
        TableColumn amountOfFee = new TableColumn("Amount ");
        TableColumn status = new TableColumn("Status");
        TableColumn dateOfCompleteFee = new TableColumn("Paid day");

        idFee.setCellValueFactory(new PropertyValueFactory<>("idFee"));
        idStudent.setCellValueFactory(new PropertyValueFactory<>("student_id"));
        fullNameStudent.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        courseName.setCellValueFactory(new PropertyValueFactory<>("course_name"));
        FeePay.setCellValueFactory(new PropertyValueFactory<>("amountOfFeeIsComplete"));
        amountOfFee.setCellValueFactory(new PropertyValueFactory<>("amountOfFee"));
        status.setCellValueFactory(new PropertyValueFactory<>("status"));
        dateOfCompleteFee.setCellValueFactory(new PropertyValueFactory<>("dateOfCompleteFee"));
        maintable.getColumns().addAll(idFee,idStudent,fullNameStudent,courseName,FeePay,amountOfFee,dateOfCompleteFee,status);
        data = stf_dal.GetData();
        maintable.setItems(data);
        txt_StudentId.focusedProperty().addListener((obs, oldVal, newVal) -> 
                {if (oldVal){
                    OutfocusStudentID();
                }});
        datePickerOfComplete.setValue(LocalDate.now());
        Cbb_Status.setItems(FXCollections.observableArrayList("Finished","Not Finished"));
        Cbb_Status.getSelectionModel().select(1);
    }
    @FXML
    private void displaySelected(MouseEvent event) {
        StudentFee stf = maintable.getSelectionModel().getSelectedItem();
        if (stf == null ){
            System.out.println("Khong thay st");
        }else {
            txt_FeeID.setText(stf.getIdFee());
            txt_StudentId.setText(stf.getStudent_id());

            txt_FullName.setText(stf.getFullName());

            System.out.println(stf.getDateOfCompleteFee());
            if (stf.getDateOfCompleteFee().equals("")){
                datePickerOfComplete.setValue(null);
            }else {
                datePickerOfComplete.setValue(LocalDate.parse(stf.getDateOfCompleteFee(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            }
            Cbb_Status.getSelectionModel().select(stf.getStatus());
            Txt_amountOfFee.setText(Double.toString(stf.getAmountOfFee()));
            Txt_FeePay.setText(Double.toString(stf.getAmountOfFeeIsComplete()));
        }
    }
    @FXML
    private void DeleteButton(ActionEvent event) throws SQLException {
        if(CheckInputGUI()){
        StudentFee stf = maintable.getSelectionModel().getSelectedItem();
        stf_dal.Delete(stf);
        data = stf_dal.GetData();
        }
    }

    @FXML
    private void EditButton(ActionEvent event) throws SQLException {
        if(CheckInputGUI()){
        StudentFee stf2 = GetStudentFeeFromGUI();
        stf_dal.Update(stf2);
        data = stf_dal.GetData();
        
        }
    }

    @FXML
    private void AddButton(ActionEvent event) throws SQLException {
        if(CheckInputGUI()){
        StudentFee st= GetStudentFeeFromGUI();
        stf_dal.Insert(st);
        data = stf_dal.GetData();
        }

    }
    private StudentFee GetStudentFeeFromGUI(){
        StudentFee st = new StudentFee(txt_FeeID.getText(),txt_StudentId.getText(),Double.parseDouble(Txt_FeePay.getText()),datePickerOfComplete.getValue().toString(),Cbb_Status.getSelectionModel().getSelectedItem());
        return st;
    }
    private void OutfocusStudentID(){
        txt_StudentId.setText(txt_StudentId.getText().toUpperCase());
        Student_DAL st_dal = new Student_DAL();
        ObservableList<Student> studentList = st_dal.GetData();
        for(Student st : studentList){
            if (st.getStudent_id().equalsIgnoreCase(txt_StudentId.getText())){
                txt_FullName.setText(st.getFullName());
                txt_Course.setText(st.getCourse_name());
                Txt_amountOfFee.setText(String.valueOf(st.getAmountOfFee()));
                return;
            }
        }
    }

    @FXML
    private void StatusAction(ActionEvent event) {
        if (Cbb_Status.getSelectionModel().getSelectedIndex() == 0){
           Txt_FeePay.setText(Txt_amountOfFee.getText());
        }
    }
    
    @FXML
    private void Search(KeyEvent event) {
         String dataFind = Txt_Search.getText();
        if (data == null){
            return;
        }
        
            ObservableList<StudentFee> list_StudentFeeFind = FXCollections.observableArrayList();

            data.forEach((StudentFee t) -> {
                if (t.checkContain(dataFind)){
                    list_StudentFeeFind.add(t);
                }
            });
            maintable.setItems(list_StudentFeeFind);
    }

    @FXML
    private void ActionKeyTypeFeePay(KeyEvent event) {
      
    }
    
    private boolean CheckInputGUI(){
        String date= datePickerOfComplete.getValue() == null?"":datePickerOfComplete.getValue().toString();
        String [] ListInput = {txt_StudentId.getText(),Txt_FeePay.getText(),date,Cbb_Status.getSelectionModel().getSelectedItem()};
        String [] Property = {"Student ID","Date Complete","Fee Pay","Status"};
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
