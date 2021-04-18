/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qlyhocvienttav.Controller;

import com.jfoenix.controls.JFXButton;
import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import qlyhocvienttav.Main;

/**
 * FXML Controller class
 *
 * @author Thang
 */
public class MainManagerController implements Initializable {

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
    @FXML
    private ImageView logo;
    @FXML
    private GridPane logogrid;
    @FXML
    private GridPane rootgrid;
   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        topcenterlabel.setText("Information");
        LoadUI("../View/Manager/CenterInfo.fxml");
        centergrid.add(root, 0, 0);
        
        logo.fitWidthProperty().bind(logogrid.widthProperty());
        rootgrid.setPrefHeight(Main.height);
        rootgrid.setPrefWidth(Main.width);
    }    

    @FXML
    private void InfoButton(ActionEvent event) {
        centergrid.getChildren().clear();
        topcenterlabel.setText("Information");
        LoadUI("../View/Manager/CenterInfo.fxml");
        centergrid.add(root, 0, 0);
        rightgrid.getChildren().clear();
       
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
        topcenterlabel.setText("Student management");
        LoadUI("../View/Manager/CenterStudentManage.fxml");
        centergrid.getChildren().clear();
        centergrid.add(root, 0, 0);
        LoadUI("../View/Manager/RightStudentManage.fxml");
        rightgrid.getChildren().clear();
        rightgrid.add(root, 0, 1);
    }

    @FXML
    private void SignoutButton(ActionEvent event) throws IOException {
        Main.ShowForm("View/LoginView.fxml", false, event);
    }

    @FXML
    private void StudentFeeButton(ActionEvent event) {
        LoadUI("../View/Manager/CenterStudentManage.fxml");
        centergrid.getChildren().clear();
        centergrid.add(root, 0, 0);
        LoadUI("../View/Manager/RightStudentFee.fxml");
        rightgrid.getChildren().clear();
        rightgrid.add(root, 0, 1);
        topcenterlabel.setText("Student's fee management");
    }

    @FXML
    private void ScheduleButton(ActionEvent event) {
        LoadUI("../View/Manager/CenterStudentManage.fxml");
        centergrid.getChildren().clear();
        centergrid.add(root, 0, 0);
        LoadUI("../View/Manager/RightScheduleManage.fxml");
        rightgrid.getChildren().clear();
        rightgrid.add(root, 0, 1);
        topcenterlabel.setText("Schedule management");
    }

    @FXML
    private void RoomButton(ActionEvent event) {
        LoadUI("../View/Manager/CenterStudentManage.fxml");
        centergrid.getChildren().clear();
        centergrid.add(root, 0, 0);
        LoadUI("../View/Manager/RightRoomManage.fxml");
        rightgrid.getChildren().clear();
        rightgrid.add(root, 0, 1);
        topcenterlabel.setText("Room mangement");
    }

    @FXML
    private void ClassButton(ActionEvent event) {
        LoadUI("../View/Manager/CenterStudentManage.fxml");
        centergrid.getChildren().clear();
        centergrid.add(root, 0, 0);
        LoadUI("../View/Manager/RightClassManage.fxml");
        rightgrid.getChildren().clear();
        rightgrid.add(root, 0, 1);
        topcenterlabel.setText("Class management");
    }

    @FXML
    private void ChangePasswordButton(ActionEvent event) {
        centergrid.getChildren().clear();
        topcenterlabel.setText("Change password");
        LoadUI("../View/Manager/CenterChangePass.fxml");
        centergrid.add(root, 0, 0);
        GridPane.setHalignment(root, HPos.LEFT);
        GridPane.setValignment(root, VPos.TOP);
        rightgrid.getChildren().clear();
    }
}
