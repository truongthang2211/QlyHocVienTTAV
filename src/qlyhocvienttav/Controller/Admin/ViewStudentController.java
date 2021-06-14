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
import qlyhocvienttav.Model.DAL.Student_DAL;
import qlyhocvienttav.Model.DTO.Student;

/**
 * FXML Controller class
 *
 * @author Thang
 */
public class ViewStudentController implements Initializable {
    public ObservableList<Student> data;
    Student_DAL st_dal= new Student_DAL();
    @FXML
    private Label topcenterlabel;
    @FXML
    private TableView<Student> maintable;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        TableColumn st_id = new TableColumn("Student ID");
        TableColumn class_id = new TableColumn("Class ID");
        TableColumn course_id = new TableColumn("Course ID");
        TableColumn stdname = new TableColumn("Full Name");
        TableColumn sex = new TableColumn("Gender");
        TableColumn dob = new TableColumn("DoB");
        TableColumn national = new TableColumn("Nationality");
        TableColumn addr = new TableColumn("Address");
        TableColumn email = new TableColumn("Email");
        TableColumn sdt = new TableColumn("Phone");


        st_id.setCellValueFactory(new PropertyValueFactory<>("student_id"));
        class_id.setCellValueFactory(new PropertyValueFactory<>("class_id"));
        course_id.setCellValueFactory(new PropertyValueFactory<>("course_id"));
        stdname.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        sex.setCellValueFactory(new PropertyValueFactory<>("sex"));
        dob.setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));
        national.setCellValueFactory(new PropertyValueFactory<>("nationality"));
        addr.setCellValueFactory(new PropertyValueFactory<>("address"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        sdt.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        maintable.getColumns().addAll(st_id,class_id,course_id,stdname,sex,dob,national,addr,email,sdt);
        data = st_dal.GetData();
        maintable.setItems(data);
    }    
    
}
