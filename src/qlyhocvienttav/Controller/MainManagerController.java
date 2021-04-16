/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qlyhocvienttav.Controller;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import qlyhocvienttav.Main;

/**
 * FXML Controller class
 *
 * @author Thang
 */
public class MainManagerController implements Initializable {

    @FXML
    private GridPane leftgrid;
    @FXML
    private GridPane centergrid;
    @FXML
    private Label topcenterlabel;
    @FXML
    private GridPane rightgrid;

    /**
     * Initializes the controller class.
     * 
     */
    private Parent root = null;
   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        topcenterlabel.setText("Information");
        LoadUI("../View/Manager/CenterInfo.fxml");
        centergrid.add(root, 0, 0);
        LoadUI("../View/Manager/RightStudentManage.fxml");
        rightgrid.add(root, 0, 0);
        
    }    

    @FXML
    private void InfoButton(ActionEvent event) {
        centergrid.getChildren().clear();
        topcenterlabel.setText("Information");
        LoadUI("../View/Manager/CenterInfo.fxml");
        centergrid.add(root, 0, 0);
        LoadUI("../View/Manager/RightStudentManage.fxml");
        rightgrid.getChildren().clear();
        rightgrid.add(root, 0, 0);
    }
    public void LoadUI(String fxml){
        try {
            root = FXMLLoader.load(getClass().getResource(fxml));
            
        } catch (IOException ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void Student_management_Button(ActionEvent event) {
        centergrid.getChildren().clear();
        topcenterlabel.setText("Student management");
        LoadUI("../View/Manager/CenterStudentManage.fxml");
        centergrid.add(root, 0, 0);
        LoadUI("../View/Manager/RightStudentManage.fxml");
        rightgrid.getChildren().clear();
        rightgrid.add(root, 0, 0);
    }

    @FXML
    private void SignoutButton(ActionEvent event) throws IOException {
        Main.ShowForm("View/LoginView.fxml", false, event);
    }
}
