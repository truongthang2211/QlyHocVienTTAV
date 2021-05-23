/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qlyhocvienttav.Controller.Manager;

import java.net.URL;
import java.util.ResourceBundle;
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
public class InfoController implements Initializable {

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
    }    
    public void SetInfo(Account ac){
        txt_Fullname.setText(ac.getFullName());
        txt_National.setText(ac.getNationality());
        txt_Address.setText(ac.getAddress());
        txt_Email.setText(ac.getEmail());
        txt_Phone.setText(ac.getPhoneNumber());
        txt_Dateofbirth.setText(ac.getDateOfBirth());
        txt_Sex.setText(ac.getSex());
        txt_Role.setText(ac.getAcctype());
        txt_Owner.setText(ac.getOwner());
    }
}
