/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qlyhocvienttav.Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import qlyhocvienttav.Model.ConNguoi;
/**
 *
 * @author Thang
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label label;
    @FXML
    private void ShowThongTin(ActionEvent event) {
        System.out.println("You clicked me! Kimochiii!!!");
        ConNguoi a = new ConNguoi();
        a.HoTen = "Nguyen Anh Truong Thang";
        label.setText(a.HoTen);

    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
