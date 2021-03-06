package qlyhocvienttav.Controller.Admin;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
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

import javax.swing.*;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import qlyhocvienttav.Model.DAL.Account_DAL;
import qlyhocvienttav.Model.DTO.Account;

public class ManagerManageController implements Initializable {

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
    private JFXComboBox<String> cb_sex;

    @FXML
    private JFXTextField txt_national;

    @FXML
    private JFXTextField txt_address;

    @FXML
    private JFXTextField txt_email;

    @FXML
    private JFXTextField txt_phoneNumber;


    public ObservableList<Account> data;
    Account_DAL acc_dal= new Account_DAL();
    @FXML
    private JFXTextField txt_username;
    @FXML
    private JFXTextField txt_pass;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> sexList = FXCollections.observableArrayList("Male","Female","Other");
        cb_sex.setItems(sexList);
        TableColumn managerId = new TableColumn("Manager ID");
        TableColumn fullname = new TableColumn("Full Name");
        TableColumn Sex = new TableColumn("Gender");
        TableColumn DateofBirth = new TableColumn("Date of birth");
        TableColumn national = new TableColumn("Nationality");
        TableColumn address = new TableColumn("Address");
        TableColumn email = new TableColumn("Email");
        TableColumn phonenumber = new TableColumn("Phone");
        TableColumn usernamae = new TableColumn("Username");

        managerId.setCellValueFactory(new PropertyValueFactory<>("owner"));
        fullname.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        Sex.setCellValueFactory(new PropertyValueFactory<>("sex"));
        DateofBirth.setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));
        national.setCellValueFactory(new PropertyValueFactory<>("nationality"));
        address.setCellValueFactory(new PropertyValueFactory<>("address"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        phonenumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        usernamae.setCellValueFactory(new PropertyValueFactory<>("username"));



        maintable.getColumns().addAll(managerId,fullname,Sex,DateofBirth,national,address,email,phonenumber,usernamae);


        data = acc_dal.GetManagerData();
        maintable.setItems(data);
    }
    private boolean CheckInputGUI(){
        String sex = cb_sex.getSelectionModel().getSelectedItem();
        sex=sex==null?"":sex;
        LocalDate lcdate = datePicker_DOB.getValue();
        String date = lcdate==null?"":lcdate.toString();
        String [] ListInput = {txt_name.getText(),date,sex,txt_national.getText(),txt_address.getText(),txt_email.getText(),txt_phoneNumber.getText(),txt_username.getText(),txt_pass.getText()};
        String [] Property = {"Name","Date of birth","Sex","Nationality","Address","Email","Phone number","Username","Password"};
        for (int i = 0 ; i< ListInput.length; i++){
            if (ListInput[i] == null || ListInput[i].equals("")){
                String ErrorStr = Property[i] + " can not be empty";
                JOptionPane.showMessageDialog(null,ErrorStr,"Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
        return true;
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
    private void AddButton(ActionEvent event) throws SQLException {
            if(CheckInputGUI()){
            String sex = cb_sex.getSelectionModel().getSelectedItem();
            sex=sex==null?"":sex;
            LocalDate lcdate = datePicker_DOB.getValue();
            String date = lcdate==null?"":lcdate.toString();
            Account acc = new Account(txt_username.getText(),txt_pass.getText(),"Manager","'MNG'||to_char(seq_manager_id.currval)",txt_name.getText(),sex,date,txt_national.getText(),txt_address.getText(),txt_email.getText(),txt_phoneNumber.getText());
            acc_dal.Insert(acc);
            data = acc_dal.GetManagerData();
            }
        }



    

    @FXML
    private void DeleteButton(ActionEvent event) throws SQLException {
        if(CheckInputGUI()){
        Account acc = maintable.getSelectionModel().getSelectedItem();
        acc_dal.Delete(acc);
        data = acc_dal.GetManagerData();
        }
    }

    @FXML
    private void EditButton(ActionEvent event) throws SQLException {
        if(CheckInputGUI()){
        String sex = cb_sex.getSelectionModel().getSelectedItem();
        sex=sex==null?"":sex;
        LocalDate lcdate = datePicker_DOB.getValue();
        String date = lcdate==null?"":lcdate.toString();
        Account acc = new Account(txt_username.getText(),txt_pass.getText(),"Manager",txt_id.getText(),txt_name.getText(),sex,date,txt_national.getText(),txt_address.getText(),txt_email.getText(),txt_phoneNumber.getText());
        acc_dal.Update(acc);
        data = acc_dal.GetManagerData();
        }
    }

}
