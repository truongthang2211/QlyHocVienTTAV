/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qlyhocvienttav.Controller.Teacher;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import qlyhocvienttav.Model.DAL.Schedule_DAL;
import qlyhocvienttav.Model.DTO.Account;
import qlyhocvienttav.Model.DTO.Schedule;

/**
 * FXML Controller class
 *
 * @author Thang
 */
public class ScheduleController implements Initializable {

    @FXML
    private Label topcenterlabel;
    @FXML
    private GridPane Schedule_Grid;
    Account account ;
    private ObservableList<Schedule> data;
    private Schedule_DAL sche_dal = new Schedule_DAL();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        data = sche_dal.GetDataByTeacher(account.getOwner());
    }    
    public void SetAccount (Account ac){
        this.account = ac;
    }
}
