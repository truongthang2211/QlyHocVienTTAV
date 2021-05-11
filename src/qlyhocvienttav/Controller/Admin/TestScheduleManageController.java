/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qlyhocvienttav.Controller.Admin;

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
import qlyhocvienttav.Model.DTO.TestSchedule;

/**
 * FXML Controller class
 *
 * @author Thang
 */
public class TestScheduleManageController implements Initializable {
    ObservableList<TestSchedule> data;
    TestSche_DAL testsche_dal = new TestSche_DAL();
    @FXML
    private Label topcenterlabel;
    @FXML
    private TableView<TestSchedule> maintable;
    @FXML
    private JFXTextField searchTxt;
    @FXML
    private JFXTextField courseIDTxt;
    @FXML
    private JFXTextField classIDTxt;
    @FXML
    private JFXTextField classnameTxt;
    @FXML
    private JFXTextField roomIDtxt;
    @FXML
    private JFXTextField roomnameTxt;
    @FXML
    private JFXTextField supervisorIDTxt;
    @FXML
    private JFXTextField supervisornameTxt;
    @FXML
    private JFXDatePicker DatePicker;
    @FXML
    private JFXComboBox<String> shiftCbb;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ObservableList<String> shiftCbbList = FXCollections.observableArrayList("Male","Femaie","Other");
        shiftCbb.setItems(shiftCbbList);
        
        
        data=testsche_dal.GetData();
        maintable.setItems(data);
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
