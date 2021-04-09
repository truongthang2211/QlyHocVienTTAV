/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qlyhocvienttav.Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import qlyhocvienttav.Main;
/**
 * FXML Controller class
 *
 * @author Thang
 */
public class LoginViewController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Button loginbtn;
    @FXML
    private void HamThucHienButton(ActionEvent event) throws Exception {
        Main.ShowForm("View/TestForm1.fxml", false,event);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
