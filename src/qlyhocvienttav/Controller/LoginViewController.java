/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qlyhocvienttav.Controller; 

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import qlyhocvienttav.Main;
import qlyhocvienttav.Model.DAL.DBConnection;
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
    private GridPane logogrid;
    @FXML
    private ImageView logogif;
    @FXML
    private void LoginButton(ActionEvent event) throws IOException  {
        loginbutton.disableProperty().set(true);
        Task task;
        task = new Task<Void>() { 
            @Override
            public Void call() {
                try {
                    String Role = "";
                    if (username.getText().equals("manager") && password.getText().equals("manager")){
                        Role = "Manager/MainManager";
                    }else if (username.getText().equals("teacher") && password.getText().equals("teacher")){
                        Role = "Teacher/MainTeacher";
                    }else if (username.getText().equals("admin") && password.getText().equals("admin")){
                        Role = "Admin/MainAdmin";
                    }else {
                        warninglabel.visibleProperty().set(true);
//                        return null;
                    }
                    if ( connection.OpenConnection()){
                        Main.ShowForm("View/"+Role +".fxml", false,event);
                    }
                   
                } catch (IOException ex) {
                    Logger.getLogger(LoginViewController.class.getName()).log(Level.SEVERE, null, ex);
                }
                loginbutton.disableProperty().set(false);
                return null;
            }
        };
        Thread thread = new Thread(task);
        thread.start();
    }
    
    public static DBConnection connection = new DBConnection();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        logogif.fitWidthProperty().bind(logogrid.widthProperty());
        

    }    
    

}
