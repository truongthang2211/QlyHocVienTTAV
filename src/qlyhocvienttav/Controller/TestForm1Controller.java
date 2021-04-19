/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qlyhocvienttav.Controller;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import qlyhocvienttav.Main;

import qlyhocvienttav.Model.DTO.HocVien;

/**
 * FXML Controller class
 *
 * @author Thang
 */
public class TestForm1Controller implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField ho;
    @FXML
    private TextField ten;
    @FXML
    private TextField quoctich;
    @FXML
    private TableColumn<HocVien, String> c1;
    @FXML
    private TableColumn<HocVien, String> c2;
    @FXML
    private TableColumn<HocVien, String> c3;
    @FXML
    private TableView<HocVien> table;
    ObservableList<HocVien> ListHocVien = FXCollections.observableArrayList();
    @FXML
    private JFXButton logoutbtn;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        c1.setCellValueFactory(new PropertyValueFactory<>("Ho"));
        c2.setCellValueFactory(new PropertyValueFactory<>("Ten"));
        c3.setCellValueFactory(new PropertyValueFactory<>("QuocTich"));
        table.setItems(ListHocVien);
    }    
    @FXML
    public void Add (ActionEvent event){
        ListHocVien.add(new HocVien(ho.getText(),ten.getText(),quoctich.getText()));
        
    }

    @FXML
    private void logoutbtnAction(ActionEvent event) throws IOException {
        Main.ShowForm("View/LoginView.fxml", false, event);
    }
    
}
