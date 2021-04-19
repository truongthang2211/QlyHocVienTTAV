/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qlyhocvienttav.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import qlyhocvienttav.BUS.HocVien_BUS;
import qlyhocvienttav.Model.DAL.DBConnection;
import qlyhocvienttav.Model.DTO.HocVien;

/**
 * FXML Controller class
 *
 * @author Thang
 */
public class ManagerController implements Initializable {

    @FXML
    private JFXTextField fullnameTxt;
    @FXML
    private JFXTextField emailTxt;
    @FXML
    private JFXTextField GhichuTxt;
    
    HocVien_BUS hv_bus = new HocVien_BUS();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
   private void AddButton(ActionEvent event) {
       DBConnection db = new DBConnection();
       db.OpenConnection();
        HocVien hv = new HocVien(fullnameTxt.getText(), emailTxt.getText(), GhichuTxt.getText());
        //hv.Insert(hv);
    }
    
}
