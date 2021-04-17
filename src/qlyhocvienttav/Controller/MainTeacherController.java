/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qlyhocvienttav.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import qlyhocvienttav.Main;

/**
 * FXML Controller class
 *
 * @author Thang
 */
public class MainTeacherController implements Initializable {

    @FXML
    private GridPane leftgrid;
    @FXML
    private Label topcenterlabel;
    @FXML
    private GridPane centergrid;
    @FXML
    private GridPane rightgrid;

    private Parent root = null;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        centergrid.getChildren().clear();
        rightgrid.getChildren().clear();
        LoadUI("../View/Teacher/CenterInfo.fxml");
        centergrid.add(root, 0, 0);
        topcenterlabel.setText("Information");
    }    
    public void LoadUI(String fxml){
        try {
            root = FXMLLoader.load(getClass().getResource(fxml));
            
        } catch (IOException ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void InfoButton(ActionEvent event) {
        centergrid.getChildren().clear();
        rightgrid.getChildren().clear();
        LoadUI("../View/Teacher/CenterInfo.fxml");
        centergrid.add(root, 0, 0);
        topcenterlabel.setText("Information");
    }


    @FXML
    private void ClassButton(ActionEvent event) {
        centergrid.getChildren().clear();
        rightgrid.getChildren().clear();
        LoadUI("../View/Teacher/CenterClass.fxml");
        centergrid.add(root, 0, 0);
        topcenterlabel.setText("Class");
    }

    @FXML
    private void ScheduleButton(ActionEvent event) {
        
        topcenterlabel.setText("Schedule");
    }

    @FXML
    private void ChangePasswordButton(ActionEvent event) {
        centergrid.getChildren().clear();
        rightgrid.getChildren().clear();
        LoadUI("../View/Teacher/CenterChangePass.fxml");
        centergrid.add(root, 0, 0);
        GridPane.setHalignment(root, HPos.LEFT);
        GridPane.setValignment(root, VPos.TOP);
        topcenterlabel.setText("Change password");
    }

    @FXML
    private void SignoutButton(ActionEvent event) throws IOException {
        Main.ShowForm("View/LoginView.fxml", false, event);
    }

    @FXML
    private void ScoreButton(ActionEvent event) {
        centergrid.getChildren().clear();
        rightgrid.getChildren().clear();
        LoadUI("../View/Teacher/RightScoreManage.fxml");
        rightgrid.add(root, 0, 1);
        LoadUI("../View/Teacher/CenterStudentManage.fxml");
        centergrid.add(root,0,0);
        topcenterlabel.setText("Score management");
    }
    
}
