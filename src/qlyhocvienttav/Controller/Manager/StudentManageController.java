/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qlyhocvienttav.Controller.Manager;

import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import qlyhocvienttav.Model.DAL.HocVien_DAL;
import qlyhocvienttav.Model.DTO.HocVien;

/**
 * FXML Controller class
 *
 * @author Thang
 */
public class StudentManageController implements Initializable {
    @FXML
    private JFXTextField fullnameTxt;
    @FXML
    private JFXTextField emailTxt;
    @FXML
    private JFXTextField GhichuTxt;
    
    HocVien_DAL hv_dal= new HocVien_DAL();
    @FXML
    private JFXTextField IDTxt;


    /**
     * Initializes the controller class.
     */
    HocVien_DAL hocvien_dal = new HocVien_DAL();
    
    

    public ObservableList data ;
    @FXML
    private TableView<HocVien> maintable;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        TableColumn fullname = new TableColumn("Ho ten");
        TableColumn email = new TableColumn("Email");
        TableColumn ghichu = new TableColumn("Ghi chus");
        
        
        
        fullname.setCellValueFactory(new PropertyValueFactory<>("HoTen"));
        email.setCellValueFactory(new PropertyValueFactory<>("Email"));
        ghichu.setCellValueFactory(new PropertyValueFactory<>("Ghichu"));
        maintable.getColumns().addAll(fullname,email,ghichu);
        data = hocvien_dal.GetData();
        maintable.setItems(data);
    }    

    @FXML
   private void AddButton(ActionEvent event) throws SQLException {
       HocVien hv = new HocVien(fullnameTxt.getText(), emailTxt.getText(), GhichuTxt.getText());
       hv_dal.Insert(hv);
       data.add(hv);

    }
    @FXML
    private void displaySelected(MouseEvent event) {
        HocVien hv = maintable.getSelectionModel().getSelectedItem();
        if (hv == null ){
            System.out.println("Khong thay hv");
        }else {
            fullnameTxt.setText(hv.getHoTen());
        }
    }

    @FXML
    private void DeleteButton(ActionEvent event) throws SQLException {
        HocVien hv = maintable.getSelectionModel().getSelectedItem();
        hocvien_dal.Delete(hv);
        data.remove(hv);
    }
    
}
