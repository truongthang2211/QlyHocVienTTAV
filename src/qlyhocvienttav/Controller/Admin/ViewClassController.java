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
import qlyhocvienttav.Model.DAL.Class_DAL;
import qlyhocvienttav.Model.DTO.Class;

/**
 * FXML Controller class
 *
 * @author Thang
 */
public class ViewClassController implements Initializable {
    public ObservableList<Class> data;
    Class_DAL class_dal= new Class_DAL();
    
    @FXML
    private Label topcenterlabel;
    @FXML
    private TableView<Class> maintable;
       
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TableColumn classId = new TableColumn("Class ID");
        TableColumn classname = new TableColumn("Ten lop");
        TableColumn numofPeople = new TableColumn("So nguoi");
        TableColumn MaximumPeople = new TableColumn("So nguoi quy dinh");
        TableColumn courseID = new TableColumn("Course ID");
        TableColumn basic_grade = new TableColumn("Diem dau vao");
        
        classId.setCellValueFactory(new PropertyValueFactory<Class,String>("Class_ID"));
        classname.setCellValueFactory(new PropertyValueFactory<>("Classname"));
        numofPeople.setCellValueFactory(new PropertyValueFactory<>("numofpeople"));
        MaximumPeople.setCellValueFactory(new PropertyValueFactory<>("maxnumberofpeople"));
        courseID.setCellValueFactory(new PropertyValueFactory<>("course_id"));
        basic_grade.setCellValueFactory(new PropertyValueFactory<>("basic_grade"));
        
        maintable.getColumns().addAll(classId,classname,numofPeople,MaximumPeople,courseID,basic_grade);


        data = class_dal.GetData();
        maintable.setItems(data);
    }    
    
}
