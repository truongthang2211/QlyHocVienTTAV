package qlyhocvienttav;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


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

import qlyhocvienttav.Model.HocVien;
/**
 * FXML Controller class
 *
 * @author Thang
 */
public class FormChinhController implements Initializable {

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
    ObservableList<HocVien> ListHocVien ;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ListHocVien = FXCollections.observableArrayList();
        c1.setCellValueFactory(new PropertyValueFactory<>("Ho"));
        c2.setCellValueFactory(new PropertyValueFactory<>("Ten"));
        c3.setCellValueFactory(new PropertyValueFactory<>("QuocTich"));
        table.setItems(ListHocVien);
    }    
    public void Add (ActionEvent event){
        ListHocVien.add(new HocVien(ho.getText(),ten.getText(),quoctich.getText()));
        
    }
}
