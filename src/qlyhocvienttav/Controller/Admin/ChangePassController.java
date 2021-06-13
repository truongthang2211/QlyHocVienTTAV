/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qlyhocvienttav.Controller.Admin;

import com.jfoenix.controls.JFXPasswordField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javax.swing.JOptionPane;
import qlyhocvienttav.Model.DAL.Account_DAL;
import qlyhocvienttav.Model.DTO.Account;

/**
 * FXML Controller class
 *
 * @author Thang
 */
public class ChangePassController implements Initializable {
    Account account;

    @FXML
    private Label topcenterlabel;
    @FXML
    private JFXPasswordField Current_Txt;
    @FXML
    private JFXPasswordField New_Txt;
    @FXML
    private JFXPasswordField Confirm_Txt;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void SaveButton(ActionEvent event) {
        String info = "Your current password wrong!";
        if (account.getPassword().equals(Current_Txt.getText())){
            if (New_Txt.getText().equals(Confirm_Txt.getText())){
                account.setPassword(New_Txt.getText());
                if (new Account_DAL().Update(account)){
                    info = "Your password has been changed!";
                }else {
                    info = "There was somthing wrong!";
                }
                JOptionPane.showMessageDialog(null,info,"Successfull", JOptionPane.INFORMATION_MESSAGE);
            }else {
                info = "Your confirm password not equal your new password!";
                JOptionPane.showMessageDialog(null,info,"Error!", JOptionPane.INFORMATION_MESSAGE);
            }
            
        }else {
            JOptionPane.showMessageDialog(null,info,"Error", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    public void SetAccount (Account ac){
        this.account = ac;
    }
    
}
