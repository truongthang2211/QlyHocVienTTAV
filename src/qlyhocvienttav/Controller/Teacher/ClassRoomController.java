/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qlyhocvienttav.Controller.Teacher;

import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import qlyhocvienttav.Controller.LoginViewController;
import qlyhocvienttav.Model.DAL.Class_DAL;
import qlyhocvienttav.Model.DAL.Student_DAL;
import qlyhocvienttav.Model.DTO.Account;
import qlyhocvienttav.Model.DTO.Class;
import qlyhocvienttav.Model.DTO.Student;

/**
 * FXML Controller class
 *
 * @author Thang
 */
public class ClassRoomController implements Initializable {
    
    private Account account = LoginViewController.account;
    private ObservableList<Class> class_data;
    private ObservableList<Student> student_data;
    private Class_DAL class_dal = new Class_DAL();
    @FXML
    private Label topcenterlabel;
    @FXML
    private Label topcenterlabel1;
    @FXML
    private TableView<Class> class_table;
    @FXML
    private TableView<Student> student_table;
    @FXML
    private JFXTextField txt_searchclass;
    @FXML
    private JFXTextField txt_searchstd;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        CreateClassTable();
        CreateStudentTable();
    }

    private void CreateClassTable() {
        TableColumn classId = new TableColumn("ID Class");
        TableColumn nameClass = new TableColumn("Name Class");
        TableColumn courseId = new TableColumn("Id Course");
        TableColumn numberOfPeople = new TableColumn("Amount");
        TableColumn maxNumberOfPeople = new TableColumn("Capacity");
        TableColumn BasicGrade = new TableColumn("Basic Grade");

        classId.setCellValueFactory(new PropertyValueFactory<>("classId"));
        nameClass.setCellValueFactory(new PropertyValueFactory<>("className"));
        courseId.setCellValueFactory(new PropertyValueFactory<>("courseId"));
        numberOfPeople.setCellValueFactory(new PropertyValueFactory<>("numberOfPeople"));
        maxNumberOfPeople.setCellValueFactory(new PropertyValueFactory<>("maxNumberOfPeople"));
        BasicGrade.setCellValueFactory(new PropertyValueFactory<>("BasicGrade"));

        class_table.getColumns().addAll(classId, nameClass, courseId, numberOfPeople, maxNumberOfPeople, BasicGrade);
        class_data = class_dal.GetDataByTeacherID(account.getOwner());
        class_table.setItems(class_data);
    }

    @FXML
    private void DisplayStudent(MouseEvent event) {
        Class cl = class_table.getSelectionModel().getSelectedItem();
        ObservableList<Student> Display_student_data = FXCollections.observableArrayList();

        for (Student t : student_data) {
            if (t.getClass_id() != null && t.getClass_id().equals(cl.getClassId())) {
                Display_student_data.add(t);
            }
        }
        student_table.setItems(Display_student_data);
    }

    private void CreateStudentTable() {
        TableColumn st_id = new TableColumn("Student ID");
        TableColumn class_id = new TableColumn("Class ID");
        TableColumn course_id = new TableColumn("Course ID");
        TableColumn fullname = new TableColumn("Full Name");
        TableColumn Sex = new TableColumn("Gender");
        TableColumn DateofBirth = new TableColumn("DoB");
        TableColumn national = new TableColumn("Nationality");
        TableColumn address = new TableColumn("Address");
        TableColumn email = new TableColumn("Email");
        TableColumn phonenumber = new TableColumn("Phone");

        st_id.setCellValueFactory(new PropertyValueFactory<>("student_id"));
        class_id.setCellValueFactory(new PropertyValueFactory<>("class_id"));
        course_id.setCellValueFactory(new PropertyValueFactory<>("course_id"));
        fullname.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        Sex.setCellValueFactory(new PropertyValueFactory<>("sex"));
        DateofBirth.setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));
        national.setCellValueFactory(new PropertyValueFactory<>("nationality"));
        address.setCellValueFactory(new PropertyValueFactory<>("address"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        phonenumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        student_table.getColumns().addAll(st_id, class_id, course_id, fullname, Sex, DateofBirth, national, address, email, phonenumber);

        student_data = new Student_DAL().GetData();


    }

    @FXML
    private void Searching(KeyEvent event) {
         String srch = txt_searchclass.getText();
        if (class_data == null){
            return;
        }
        ObservableList<Class> list_class= FXCollections.observableArrayList();

            class_data.forEach((Class c) -> {
                if (c.checkContain(srch)){
                    list_class.add(c);
                }
            });
            class_table.setItems(list_class);
    }

    @FXML
    private void Searchingstd(KeyEvent event) {
        String srch = txt_searchstd.getText();
        if (student_data== null){
            return;
        }
        ObservableList<Student> list_std= FXCollections.observableArrayList();

            student_data.forEach((Student s) -> {
                if (s.checkContain(srch)){
                    list_std.add(s);
                }
            });
            student_table.setItems(list_std);
    }
    
}
