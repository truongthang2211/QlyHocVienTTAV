/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qlyhocvienttav.Controller.Teacher;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import qlyhocvienttav.Model.DTO.Account;

/**
 * FXML Controller class
 *
 * @author Thang
 */
public class ProfileController implements Initializable {
    Account account;
    @FXML
    private Label topcenterlabel;
    @FXML
    private GridPane centergrid;
    @FXML
    private Label txt_Fullname;
    @FXML
    private Label txt_National;
    @FXML
    private Label txt_Address;
    @FXML
    private Label txt_Email;
    @FXML
    private Label txt_Phone;
    @FXML
    private Label txt_Dateofbirth;
    @FXML
    private Label txt_Sex;
    @FXML
    private Label txt_Role;
    @FXML
    private Label txt_Owner;
    @FXML
    private Label txt_Createdate;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Platform.runLater(()->{
            SetInfo();
        });
    }    
    public void SetInfo(){
        txt_Fullname.setText(this.account.getFullName());
        txt_National.setText(this.account.getNationality());
        txt_Address.setText(this.account.getAddress());
        txt_Email.setText(this.account.getEmail());
        txt_Phone.setText(this.account.getPhoneNumber());
        txt_Dateofbirth.setText(this.account.getDateOfBirth());
        txt_Sex.setText(this.account.getSex());
        txt_Role.setText(this.account.getAcctype());
        txt_Owner.setText(this.account.getOwner());
    }

    public void setAccount(Account ac) {
        this.account=ac;
    }
    
}
