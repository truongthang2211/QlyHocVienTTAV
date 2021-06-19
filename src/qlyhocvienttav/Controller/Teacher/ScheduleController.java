/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qlyhocvienttav.Controller.Teacher;

import java.awt.Color;
import java.awt.Font;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
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
    }    
    public void SetAccount (Account ac){
        this.account = ac;
    }
    public void Start(){
        data = sche_dal.GetDataByTeacher(account.getOwner());
        String [] DayList = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday","Sunday"};
        String [] ShiftList = {"9:00 - 10:30", "13:00 - 14:30", "15:00 - 16:30", "17:30 - 19:00", "19:15 - 20:45"};
        
        for (Schedule sche : data){
            int rows =0, columns=0 ;
            for (int i = 0 ; i < DayList.length; ++i){
                if (sche.getDay().equals(DayList[i])){
                    columns = i;
                    break;
                }
            }
            for (int i = 0 ; i < DayList.length; ++i){
                if (sche.getShift().equals(ShiftList[i])){
                    rows = i;
                    break;
                }
            }
            Schedule_Grid.add(CreateAnchor(sche.getClassName(),sche.getRoomName()),columns+1,rows+1);

        }
    }
    VBox CreateAnchor(String ClassName, String Room){
        Label Class = new Label(ClassName);
        Label RoomLb = new Label(Room);
        Class.setStyle("-fx-font-size: 18; -fx-font-weight: Bold");        
        RoomLb.setStyle("-fx-font-size: 18; -fx-font-weight: Bold"); 

        VBox vbox = new VBox(10,Class, RoomLb);
        vbox.setAlignment(Pos.CENTER);
        return vbox;
    }
}
