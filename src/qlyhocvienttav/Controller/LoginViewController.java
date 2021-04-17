/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qlyhocvienttav.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import qlyhocvienttav.Main;
/**
 * FXML Controller class
 *
 * @author Thang
 */
public class LoginViewController implements Initializable {

    @FXML
    private JFXTextField username;
    @FXML
    private JFXPasswordField password;
    @FXML
    private Label warninglabel;
    @FXML
    private JFXButton loginbutton;
    @FXML
    private void LoginButton(ActionEvent event) throws Exception {
        
        String Role = "";
        if (username.getText().equals("manager") && password.getText().equals("manager")){
            Role = "Manager/MainManager";
        }else if (username.getText().equals("teacher") && password.getText().equals("teacher")){
            Role = "Teacher/MainTeacher";
        }else {
            warninglabel.visibleProperty().set(true);
            return;
        }
        Main.ShowForm("View/"+Role +".fxml", false,event);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }    

}
