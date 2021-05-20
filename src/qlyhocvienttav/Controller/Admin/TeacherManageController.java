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
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Thang
 */
public class TeacherManageController implements Initializable {

    @FXML
    private Label topcenterlabel;
    @FXML
    private TableView<?> maintable;
    @FXML
    private JFXTextField txt_id;
    @FXML
    private JFXTextField txt_name;
    @FXML
    private JFXDatePicker datePicker_DOB;
    @FXML
    private JFXComboBox<?> cb_sex;
    @FXML
    private JFXTextField txt_national;
    @FXML
    private JFXTextField txt_address;
    @FXML
    private JFXTextField txt_email;
    @FXML
    private JFXTextField txt_phoneNumber;
    @FXML
    private JFXComboBox<?> Cbb_Role;
    @FXML
    private JFXTextField txt_username;
    @FXML
    private JFXTextField txt_pass;
    @FXML
    private JFXButton btn_add;
    @FXML
    private JFXButton btn_edit;
    @FXML
    private JFXButton btn_delete;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void displaySelected(MouseEvent event) {
    }

    @FXML
    private void AddButton(ActionEvent event) {
    }

    @FXML
    private void EditButton(ActionEvent event) {
    }

    @FXML
    private void DeleteButton(ActionEvent event) {
    }
    
}
