/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qlyhocvienttav.Controller.Admin;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import qlyhocvienttav.Model.DAL.Room_DAL;
import qlyhocvienttav.Model.DTO.Room;

/**
 * FXML Controller class
 *
 * @author Thang
 */
public class ViewRoomController implements Initializable {
    public ObservableList<Room> data;
    Room_DAL room_dal= new Room_DAL();
    
    @FXML
    private Label topcenterlabel;
    @FXML
    private TableView<Room> maintable;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TableColumn roomid = new TableColumn("Room ID");
        TableColumn roomname = new TableColumn("Ten phong");
        TableColumn capacity = new TableColumn("Suc chua");
        
        
        roomid.setCellValueFactory(new PropertyValueFactory<Class,String>("Room_ID"));
        roomname.setCellValueFactory(new PropertyValueFactory<>("roomname"));
        capacity.setCellValueFactory(new PropertyValueFactory<>("capacity"));
        
         maintable.getColumns().addAll(roomid,roomname,capacity);


        data = room_dal.GetData();
        maintable.setItems(data);
       
    }    
    
}
