/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qlyhocvienttav.Controller.Teacher;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author Thang
 */
public class ScoreManageController implements Initializable {

    @FXML
    private Label topcenterlabel;
    @FXML
    private TableView<?> maintable;
    @FXML
    private JFXTextField StudentID_Txt;
    @FXML
    private JFXTextField StudentName_Txt;
    @FXML
    private JFXTextField Listening_Txt;
    @FXML
    private JFXTextField Writting_Txt;
    @FXML
    private JFXTextField Reading_Txt;
    @FXML
    private JFXTextField Speaking_Txt;
    @FXML
    private JFXButton EditBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
