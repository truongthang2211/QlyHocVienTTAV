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
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import qlyhocvienttav.Controller.LoginViewController;
import qlyhocvienttav.Main;
import qlyhocvienttav.Model.DTO.Account;

/**
 * FXML Controller class
 *
 * @author Thang
 */
public class MainTeacherController implements Initializable {
    private FXMLLoader loader = null;
    Account account;
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
        
        logo.fitWidthProperty().bind(logogrid.widthProperty());
        rootgrid.setPrefHeight(Main.height);
        rootgrid.setPrefWidth(Main.width);
    }    
    public void LoadUI(String fxml){
        try {
            loader= new FXMLLoader(getClass().getResource(fxml));
            root = loader.load();
        } catch (IOException ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void InfoButton(ActionEvent event) {
        ChangeScreen("../../View/Teacher/Info.fxml");
        InfoController ctro = loader.getController();
        ctro.SetInfo(account);
    }


    @FXML
    private void ClassButton(ActionEvent event) {
        ChangeScreen("../../View/Teacher/Class.fxml");
    }

    @FXML
    private void ScheduleButton(ActionEvent event) {
        ChangeScreen("../../View/Teacher/Schedule.fxml");
        ScheduleController ctrol = loader.getController();
        ctrol.SetAccount(account);
        ctrol.Start();
    }

    @FXML
    private void ChangePasswordButton(ActionEvent event) {
        ChangeScreen("../../View/Teacher/ChangePass.fxml");

    }

    @FXML
    private void SignoutButton(ActionEvent event) throws IOException {
        LoginViewController.connection.CloseConnection();
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
    public void ShowForm(ActionEvent event,Parent root,Account ac) throws IOException{
        this.account = ac;
        ChangeScreen("../../View/Teacher/Info.fxml");
        InfoController ctro = loader.getController();
        ctro.SetInfo(account);
        Main.ShowForm(root, false, event);
        
    }
}
