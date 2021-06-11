package qlyhocvienttav.Controller.Manager;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import qlyhocvienttav.Model.DAL.Room_DAL;
import qlyhocvienttav.Model.DTO.Room;
import qlyhocvienttav.Model.DTO.Schedule;
import com.jfoenix.controls.JFXTextField;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.scene.input.KeyEvent;

public class RoomManageController implements Initializable {
    @FXML
    private Label topcenterlabel;

    @FXML
    private TableView<Room> maintable;
    
    @FXML
    private JFXTextField txt_findData;
    
    @FXML
    private JFXTextField txt_roomId;

    @FXML
    private JFXTextField txt_nameRoom;

    @FXML
    private JFXTextField txt_capacity;
    ObservableList<Room> data;
    Room_DAL room_dal = new Room_DAL();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TableColumn roomId = new TableColumn("Room Id");
        TableColumn roomName = new TableColumn("Room Name");
        TableColumn capacity = new TableColumn("Capacity");

        roomId.setCellValueFactory(new PropertyValueFactory<>("roomId"));
        roomName.setCellValueFactory(new PropertyValueFactory<>("roomName"));
        capacity.setCellValueFactory(new PropertyValueFactory<>("capacity"));

        maintable.getColumns().addAll(roomId,roomName,capacity);
        data = room_dal.GetData();
        maintable.setItems(data);
    }
    @FXML
    private void AddBtn(ActionEvent event) {
        Room room = GetRoomFromGUI();
        room_dal.Insert(room);
        data = room_dal.GetData();
    }

    @FXML
    private void EditBtn(ActionEvent event) {
        Room room = GetRoomFromGUI();
        room_dal.Update(room);
        data = room_dal.GetData();
    }

    @FXML
    private void DeleteBtn(ActionEvent event) {
        Room room = GetRoomFromGUI();
        room_dal.Delete(room);
        data = room_dal.GetData();
    }
    private Room GetRoomFromGUI(){
        Room room = new Room(txt_roomId.getText(),txt_nameRoom.getText(),Integer.parseInt(txt_capacity.getText()));
        return room;
    }

    @FXML
    private void displaySelected(MouseEvent event) {
        Room room  = maintable.getSelectionModel().getSelectedItem();
        txt_roomId.setText(room.getRoomId());
        txt_nameRoom.setText(room.getRoomName());
        txt_capacity.setText(Integer.toString(room.getCapacity()));
    }
     @FXML
    private void Search(KeyEvent event) {
         String dataFind = txt_findData.getText();
        if (data == null){
            return;
        }
        
            ObservableList<Room> list_RoomFind = FXCollections.observableArrayList();

            data.forEach((Room t) -> {
                if (t.checkContain(dataFind)){
                    list_RoomFind.add(t);
                }
            });
            maintable.setItems(list_RoomFind);
    }
}
