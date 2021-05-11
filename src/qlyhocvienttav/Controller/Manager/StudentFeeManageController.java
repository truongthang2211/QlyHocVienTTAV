<<<<<<< HEAD
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qlyhocvienttav.Controller.Manager;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
=======
package qlyhocvienttav.Controller.Manager;

import javafx.collections.ObservableArray;
>>>>>>> db342fce126da7233efcdf26134bdee96c43c706
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
<<<<<<< HEAD
import javafx.scene.control.TableView;
import qlyhocvienttav.Model.DAL.TestSche_DAL;

/**
 * FXML Controller class
 *
 * @author Thang
 */
public class StudentFeeManageController implements Initializable {
    public ObservableList<?> data;
    @FXML
    private Label topcenterlabel;
    @FXML
    private TableView<?> maintable;
    @FXML
    private JFXTextField Searchtxt;
    @FXML
    private JFXTextField FeeIdtxt;
    @FXML
    private JFXTextField StudentIDtxt;
    @FXML
    private JFXTextField FullNametxt;
    @FXML
    private JFXTextField FeeCompletedtxt;
    @FXML
    private JFXDatePicker Timetxt;
    @FXML
    private JFXComboBox<String> StatusCbb;
    
    TestSche_DAL testsche_dal = new TestSche_DAL();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ObservableList<String> StatusList = FXCollections.observableArrayList("Hoàn thành","Chưa hoàn thành");
        StatusCbb.setItems(StatusList);
        
       
    }    

    @FXML
    private void AddBtn(ActionEvent event) {
        
    }

    @FXML
    private void EditBtn(ActionEvent event) {
    }

    @FXML
    private void DeleteBtn(ActionEvent event) {
    }
    
=======
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import qlyhocvienttav.Model.DAL.StudentFee_DAL;
import qlyhocvienttav.Model.DAL.Teacher_DAL;
import qlyhocvienttav.Model.DTO.Student;
import qlyhocvienttav.Model.DTO.StudentFee;
import qlyhocvienttav.Model.DTO.Teacher;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class StudentFeeManageController implements Initializable {
    public ObservableList<StudentFee> data;
    StudentFee_DAL stf_dal= new StudentFee_DAL();

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
    private JFXTextField txt_amountOfFee;

    @FXML
    private JFXDatePicker datePickerOfComplete;
    @FXML
    private JFXTextField txt_status;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TableColumn idFee = new TableColumn("ID Fee");
        TableColumn idStudent = new TableColumn("MSSV");
        TableColumn fullNameStudent = new TableColumn("Ten Sinh Vien");
        TableColumn amountOfFee = new TableColumn("So Tien");
        TableColumn status = new TableColumn("Tinh Trang");
        TableColumn dateOfCompleteFee = new TableColumn("Ngay Nap");


        idFee.setCellValueFactory(new PropertyValueFactory<>("idFee"));
        idStudent.setCellValueFactory(new PropertyValueFactory<>("idStudent"));
        fullNameStudent.setCellValueFactory(new PropertyValueFactory<>("nameStudent"));
        amountOfFee.setCellValueFactory(new PropertyValueFactory<>("amountOfFee"));
        status.setCellValueFactory(new PropertyValueFactory<>("status"));
        dateOfCompleteFee.setCellValueFactory(new PropertyValueFactory<>("dateOfCompleteFee"));

        maintable.getColumns().addAll(idFee,idStudent,fullNameStudent,amountOfFee,status,dateOfCompleteFee);


        data = stf_dal.GetData();
        maintable.setItems(data);

    }
    @FXML
    private void displaySelected(MouseEvent event) {
        StudentFee stf = maintable.getSelectionModel().getSelectedItem();
        if (stf == null ){
            System.out.println("Khong thay st");
        }else {
            txt_FeeID.setText(stf.getIdFee());
            txt_StudentId.setText(stf.getIdStudent());

            System.out.println(stf.getNameStudent());
            txt_FullName.setText(stf.getNameStudent());

            System.out.println(stf.getDateOfCompleteFee());
            if (stf.getDateOfCompleteFee().equals("")){
                datePickerOfComplete.setValue(null);
            }else {
                datePickerOfComplete.setValue(LocalDate.parse(stf.getDateOfCompleteFee(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            }
            txt_status.setText(stf.getStatus());
            txt_amountOfFee.setText(Long.toString(stf.getAmountOfFee()));
        }
    }
    @FXML
    private void DeleteButton(ActionEvent event) throws SQLException {
        StudentFee stf = maintable.getSelectionModel().getSelectedItem();
        stf_dal.Delete(stf);
        data = stf_dal.GetData();
    }

    @FXML
    private void EditButton(ActionEvent event) throws SQLException {
        StudentFee stf = maintable.getSelectionModel().getSelectedItem();
        String date = datePickerOfComplete.getValue().toString();
        StudentFee stf2 = new StudentFee(stf.getIdFee(),txt_StudentId.getText(),txt_FullName.getText(),Long.parseLong(txt_amountOfFee.getText()),txt_status.getText(),date);
        stf_dal.Update(stf2);
        data = stf_dal.GetData();
    }

    @FXML
    private void AddButton(ActionEvent event) throws SQLException {

        LocalDate lcdate = datePickerOfComplete.getValue();
        String date = lcdate==null?"":lcdate.toString();
        StudentFee st = new StudentFee("",txt_StudentId.getText(),txt_FullName.getText(),Long.parseLong(txt_amountOfFee.getText()),date,txt_status.getText());
        stf_dal.Insert(st);
        data = stf_dal.GetData();


    }
>>>>>>> db342fce126da7233efcdf26134bdee96c43c706
}
