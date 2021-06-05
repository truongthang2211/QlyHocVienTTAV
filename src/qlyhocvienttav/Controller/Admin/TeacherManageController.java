/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qlyhocvienttav.Controller.Admin;

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
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;
import qlyhocvienttav.Model.DAL.Account_DAL;
import qlyhocvienttav.Model.DTO.Account;

/**
 * FXML Controller class
 *
 * @author Thang
 */
public class TeacherManageController implements Initializable {

    @FXML
    private Label topcenterlabel;
    @FXML
    private TableView<Account> maintable;
    @FXML
    private JFXTextField txt_id;
    @FXML
    private JFXTextField txt_name;
    @FXML
    private JFXDatePicker datePicker_DOB;
    @FXML
    private JFXTextField txt_findData;
    @FXML
    private JFXComboBox<String> cb_sex;
    @FXML
    private JFXTextField txt_national;
    @FXML
    private JFXTextField txt_address;
    @FXML
    private JFXTextField txt_email;
    @FXML
    private JFXTextField txt_phoneNumber;
    @FXML
    private JFXTextField txt_username;
    @FXML
    private JFXTextField txt_pass;

    public ObservableList<Account> data;
    Account_DAL acc_dal= new Account_DAL();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> sexList = FXCollections.observableArrayList("Male","Female","Other");
        cb_sex.setItems(sexList);
        TableColumn teacherID = new TableColumn("Teacher ID");
        TableColumn fullname = new TableColumn("Full Name");
        TableColumn Sex = new TableColumn("Gender");
        TableColumn DateofBirth = new TableColumn("Date of birth");
        TableColumn national = new TableColumn("National");
        TableColumn address = new TableColumn("Address");
        TableColumn email = new TableColumn("Email");
        TableColumn phonenumber = new TableColumn("Phone");
        TableColumn usernamae = new TableColumn("Username");

        teacherID.setCellValueFactory(new PropertyValueFactory<>("owner"));
        fullname.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        Sex.setCellValueFactory(new PropertyValueFactory<>("sex"));
        DateofBirth.setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));
        national.setCellValueFactory(new PropertyValueFactory<>("nationality"));
        address.setCellValueFactory(new PropertyValueFactory<>("address"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        phonenumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        usernamae.setCellValueFactory(new PropertyValueFactory<>("username"));



        maintable.getColumns().addAll(teacherID,fullname,Sex,DateofBirth,national,address,email,phonenumber,usernamae);


        data = acc_dal.GetTeacherData();
        maintable.setItems(data);
    }    

    @FXML
    private void displaySelected(MouseEvent event) {
        Account acc = maintable.getSelectionModel().getSelectedItem();
        System.out.println(acc.getNationality());
        if (acc == null ){
            System.out.println("Khong thay st");
        }else {
            txt_name.setText(acc.getFullName());
            txt_id.setText(acc.getOwner());
            cb_sex.getSelectionModel().select(acc.getSex());
            datePicker_DOB.setValue(LocalDate.parse(acc.getDateOfBirth(), DateTimeFormatter.ISO_DATE));
            txt_national.setText(acc.getNationality());
            txt_address.setText(acc.getAddress());
            txt_email.setText(acc.getEmail());
            txt_phoneNumber.setText(acc.getPhoneNumber());
            txt_username.setText(acc.getUsername());
        }
    }

    @FXML
    private void AddButton(ActionEvent event) {
         System.out.println("click add button");
        if (txt_name.getText().equals("")||datePicker_DOB.getValue()==null){
            JOptionPane.showMessageDialog(null,"Thieu Thong Tin");
        }
        else{
            String sex = cb_sex.getSelectionModel().getSelectedItem();
            sex=sex==null?"":sex;
            LocalDate lcdate = datePicker_DOB.getValue();
            String date = lcdate==null?"":lcdate.toString();
            Account acc = new Account(txt_username.getText(),txt_pass.getText(),"Teacher","'TC'||to_char(seq_teacher_id.currval)",txt_name.getText(),sex,date,txt_national.getText(),txt_address.getText(),txt_email.getText(),txt_phoneNumber.getText());
            acc_dal.Insert(acc);
            data = acc_dal.GetTeacherData();
        }
    }

    @FXML
    private void EditButton(ActionEvent event) {
         String sex = cb_sex.getSelectionModel().getSelectedItem();
        sex=sex==null?"":sex;
        LocalDate lcdate = datePicker_DOB.getValue();
        String date = lcdate==null?"":lcdate.toString();
        Account acc = new Account(txt_username.getText(),txt_pass.getText(),"Teacher",txt_id.getText(),txt_name.getText(),sex,date,txt_national.getText(),txt_address.getText(),txt_email.getText(),txt_phoneNumber.getText());
        acc_dal.Update(acc);
        data = acc_dal.GetTeacherData();
    }

    @FXML
    private void DeleteButton(ActionEvent event) {
        String sex = cb_sex.getSelectionModel().getSelectedItem();
        sex=sex==null?"":sex;
        LocalDate lcdate = datePicker_DOB.getValue();
        String date = lcdate==null?"":lcdate.toString();
        Account acc = new Account(txt_username.getText(),txt_pass.getText(),"Teacher",txt_id.getText(),txt_name.getText(),sex,date,txt_national.getText(),txt_address.getText(),txt_email.getText(),txt_phoneNumber.getText());
        acc_dal.Delete(acc);
        data = acc_dal.GetTeacherData();
    }
    @FXML
    public void FindData(MouseEvent event) {
        
    }
    @FXML
    private void Search(KeyEvent event) {
         String dataFind = txt_findData.getText();
        if (data == null){
            return;
        }
        
            ObservableList<Account> list_teacherFind = FXCollections.observableArrayList();

            data.forEach((Account t) -> {
                if (t.checkContain(dataFind)){
                    list_teacherFind.add(t);
                }
            });
            maintable.setItems(list_teacherFind);
    }

}
