/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qlyhocvienttav.Controller.Teacher;

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
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import qlyhocvienttav.Main;

/**
 * FXML Controller class
 *
 * @author Thang
 */
public class MainTeacherController implements Initializable {

    private Label topcenterlabel;

    private Parent root = null;
    @FXML
    private GridPane rootgrid;
    @FXML
    private GridPane logogrid;
    @FXML
    private ImageView logo;
    @FXML
    private GridPane maingrid;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ChangeScreen("../../View/Teacher/Info.fxml");
        
        logo.fitWidthProperty().bind(logogrid.widthProperty());
        rootgrid.setPrefHeight(Main.height);
        rootgrid.setPrefWidth(Main.width);
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
        ChangeScreen("../../View/Teacher/Info.fxml");
    }


    @FXML
    private void ClassButton(ActionEvent event) {
        ChangeScreen("../../View/Teacher/Class.fxml");
    }

    @FXML
    private void ScheduleButton(ActionEvent event) {
        
    }

    @FXML
    private void ChangePasswordButton(ActionEvent event) {
        ChangeScreen("../../View/Teacher/ChangePass.fxml");

    }

    @FXML
    private void SignoutButton(ActionEvent event) throws IOException {
        Main.ShowForm("View/LoginView.fxml", false, event);
    }

    @FXML
    private void ScoreButton(ActionEvent event) {
        ChangeScreen("../../View/Teacher/ScoreManage.fxml");
    }
    private void ChangeScreen(String centerfxml){
        maingrid.getChildren().clear();
        LoadUI(centerfxml);
        maingrid.add(root, 0, 0);
    }
}
