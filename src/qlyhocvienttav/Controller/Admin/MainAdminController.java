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
import javax.swing.JOptionPane;
import qlyhocvienttav.Controller.LoginViewController;
import qlyhocvienttav.Model.DTO.Account;

public class MainAdminController implements Initializable {
    private Parent root = null;
    private FXMLLoader loader = null;
    Account account;

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
        ChangeScreen("../../View/Admin/Info.fxml");
        InfoController ctro = loader.getController();
        ctro.SetInfo(account);
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
    public void ShowForm(ActionEvent event,Parent root,Account ac) throws IOException{
        this.account = ac;
        ChangeScreen("../../View/Admin/Info.fxml");
        InfoController ctro = loader.getController();
        ctro.SetInfo(account);
        Main.ShowForm(root, false, event);
        
    }
}
