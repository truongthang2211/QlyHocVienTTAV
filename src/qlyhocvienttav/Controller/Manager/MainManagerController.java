/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qlyhocvienttav.Controller.Manager;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
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
public class MainManagerController implements Initializable {

    /**
     * Initializes the controller class.
     * 
     */
    
    Account account;
    private Parent root = null;
    private FXMLLoader loader = null;
    @FXML
    private ImageView logo;
    @FXML
    private GridPane logogrid;
    @FXML
    private GridPane rootgrid;
    @FXML
    private GridPane maingrid;
   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Platform.runLater(() -> {
            ChangeScreen("../../View/Manager/Info.fxml");
            InfoController ctro = loader.getController();
            ctro.SetAccount(account);
        });
        
       
        logo.fitWidthProperty().bind(logogrid.widthProperty());
        rootgrid.setPrefHeight(Main.height);
        rootgrid.setPrefWidth(Main.width);

    }    

    @FXML
    private void InfoButton(ActionEvent event) {
        ChangeScreen("../../View/Manager/Info.fxml");
        InfoController ctro = loader.getController();
        ctro.SetAccount(account);
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
    private void Student_management_Button(ActionEvent event) {
        ChangeScreen("../../View/Manager/StudentManage.fxml");

    }

    @FXML
    private void SignoutButton(ActionEvent event) throws IOException {
        LoginViewController.connection.CloseConnection();
        Main.ShowForm("View/LoginView.fxml", false, event);
    }

    @FXML
    private void StudentFeeButton(ActionEvent event) {
        ChangeScreen("../../View/Manager/StudentFeeManage.fxml");
    }

    @FXML
    private void ScheduleButton(ActionEvent event) {
        ChangeScreen("../../View/Manager/ScheduleManage.fxml");
    }

    @FXML
    private void RoomButton(ActionEvent event) {
        ChangeScreen("../../View/Manager/RoomManage.fxml");
    }

    @FXML
    private void ClassButton(ActionEvent event) {
        ChangeScreen("../../View/Manager/ClassManage.fxml");
    }
    @FXML
    private void ChangePasswordButton(ActionEvent event) {
        ChangeScreen("../../View/Manager/ChangePass.fxml");
        ChangePassController ctrol = new ChangePassController();
        ctrol.SetAccount(account);
    }
    private void ChangeScreen(String centerfxml){
        maingrid.getChildren().clear();
        LoadUI(centerfxml);
        maingrid.add(root, 0, 0);
    }

    @FXML
    private void ViewTeacherButton(ActionEvent event) {
        ChangeScreen("../../View/Manager/ViewTeacher.fxml");
    }


    @FXML
    private void TestScheduleButton(ActionEvent event) {
        ChangeScreen("../../View/Manager/TestScheduleManage.fxml");

    }
    public void SetAccount(Account ac){
        this.account = ac;
    }
}

