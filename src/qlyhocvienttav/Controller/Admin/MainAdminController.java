package qlyhocvienttav.Controller.Admin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import qlyhocvienttav.Main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javax.swing.JOptionPane;
import qlyhocvienttav.Controller.LoginViewController;
import qlyhocvienttav.Model.DTO.Account;

public class MainAdminController implements Initializable {
    private Parent root = null;
    private FXMLLoader loader = null;
    @FXML
    private GridPane rootgrid;

    @FXML
    private GridPane logogrid;

    @FXML
    private ImageView logo;

    @FXML
    private GridPane maingrid;


    @FXML
    void ChangePasswordButton(ActionEvent event) {
        ChangeScreen("../../View/Admin/ChangePass.fxml");
    }

    @FXML
    void ClassButton(ActionEvent event) {
        ChangeScreen("../../View/Admin/ViewClass.fxml");

    }

    @FXML
    void CourseButton(ActionEvent event) {
        ChangeScreen("../../View/Admin/CourseManage.fxml");

    }

    @FXML
    void ProfileButton(ActionEvent event) {
        ChangeScreen("../../View/Admin/Profile.fxml");
    }

    @FXML
    void RoomButton(ActionEvent event) {
        ChangeScreen("../../View/Admin/ViewRoom.fxml");

    }

    @FXML
    void SignoutButton(ActionEvent event) throws IOException {
        LoginViewController.connection.CloseConnection();
        Main.ShowForm("View/LoginView.fxml", false, event);
    }

    @FXML
    void StudentAnalysisButton(ActionEvent event) {
        ChangeScreen("../../View/Admin/Analysis.fxml");
    }

    @FXML
    void StudentFeeButton(ActionEvent event) {
        ChangeScreen("../../View/Admin/ViewStudentFee.fxml");

    }

    @FXML
    void ViewScheduleButton(ActionEvent event) {
        ChangeScreen("../../View/Admin/ViewSchedule.fxml");
    }

    @FXML
    void ViewStudentButton(ActionEvent event) {
        ChangeScreen("../../View/Admin/ViewStudent.fxml");

    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ChangeScreen("../../View/Admin/Profile.fxml");
        logo.fitWidthProperty().bind(logogrid.widthProperty());
        rootgrid.setPrefHeight(Main.height);
        rootgrid.setPrefWidth(Main.width);
    }
    public void LoadUI(String fxml){
        try {
            loader= new FXMLLoader(getClass().getResource(fxml));
            root = loader.load();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null,ex.toString(),"Error at LoadUI() function", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void ChangeScreen(String centerfxml){
        maingrid.getChildren().clear();
        LoadUI(centerfxml);
        maingrid.add(root, 0, 0);
    }

    @FXML
    private void ManagerManageButton(ActionEvent event) {
        ChangeScreen("../../View/Admin/ManagerManage.fxml");
    }

    @FXML
    private void TeacherManageButton(ActionEvent event) {
        ChangeScreen("../../View/Admin/TeacherManage.fxml");
    }
}
