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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class MainAdminController implements Initializable {

    private Parent root = null;

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
    void InfoButton(ActionEvent event) {
        ChangeScreen("../../View/Admin/info.fxml");

    }

    @FXML
    void RoomButton(ActionEvent event) {
        ChangeScreen("../../View/Admin/ViewRoom.fxml");

    }

    @FXML
    void SignoutButton(ActionEvent event) {
        try {
            Main.ShowForm("View/LoginView.fxml", false, event);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null,ex.toString(),"Error at SignoutButton", JOptionPane.ERROR_MESSAGE);
        }
    }

    @FXML
    void StudentAnalysisButton(ActionEvent event) {

    }

    @FXML
    void StudentFeeButton(ActionEvent event) {
        ChangeScreen("../../View/Admin/ViewStudentFee.fxml");

    }

    @FXML
    void StudentandManagerManageButton(ActionEvent event) {
        ChangeScreen("../../View/Admin/ManagerandTeacherManage.fxml");
    }

    @FXML
    void TestScheduleButton(ActionEvent event) {
        ChangeScreen("../../View/Admin/TestScheduleManage.fxml");

    }

    @FXML
    void ViewScheduleButton(ActionEvent event) {

    }

    @FXML
    void ViewStudentButton(ActionEvent event) {
        ChangeScreen("../../View/Admin/ViewStudent.fxml");

    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ChangeScreen("../../View/Admin/Info.fxml");
        logo.fitWidthProperty().bind(logogrid.widthProperty());
        rootgrid.setPrefHeight(Main.height);
        rootgrid.setPrefWidth(Main.width);
    }
    public void LoadUI(String fxml){
        try {
            root = FXMLLoader.load(getClass().getResource(fxml));
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null,ex.toString(),"Error at LoadUI() function", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void ChangeScreen(String centerfxml){
        maingrid.getChildren().clear();
        LoadUI(centerfxml);
        maingrid.add(root, 0, 0);
    }

}
