/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qlyhocvienttav.Controller.Manager;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import qlyhocvienttav.Model.DAL.TestSche_DAL;

/**
 * FXML Controller class
 *
 * @author Thang
 */
public class StudentFeeManageController implements Initializable {
    public ObservableList<?> data;
    @FXML
    private Label topcenterlabel;
    @FXML
    private TableView<?> maintable;
    @FXML
    private JFXTextField Searchtxt;
    @FXML
    private JFXTextField FeeIdtxt;
    @FXML
    private JFXTextField StudentIDtxt;
    @FXML
    private JFXTextField FullNametxt;
    @FXML
    private JFXTextField FeeCompletedtxt;
    @FXML
    private JFXDatePicker Timetxt;
    @FXML
    private JFXComboBox<String> StatusCbb;
    
    TestSche_DAL testsche_dal = new TestSche_DAL();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ObservableList<String> StatusList = FXCollections.observableArrayList("Hoàn thành","Chưa hoàn thành");
        StatusCbb.setItems(StatusList);
        
       
    }    

    @FXML
    private void AddBtn(ActionEvent event) {
        
    }

    @FXML
    private void EditBtn(ActionEvent event) {
    }

    @FXML
    private void DeleteBtn(ActionEvent event) {
    }
    
}
