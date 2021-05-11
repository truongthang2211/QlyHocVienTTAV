/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qlyhocvienttav.Controller.Manager;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import qlyhocvienttav.Model.DAL.Student_DAL;
import qlyhocvienttav.Model.DTO.Student;

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
    
    Student_DAL st_dal= new Student_DAL();


    /**
     * Initializes the controller class.
     */

    public ObservableList<Student> data;
    @FXML
    private TableView<Student> maintable;
    @FXML
    private JFXTextField idTxt;
    @FXML
    private JFXTextField nationalTxt;
    @FXML
    private JFXTextField addressTxt;
    @FXML
    private JFXTextField phonenumberTxt;
    @FXML
    private JFXComboBox<?> courseTxt;
    @FXML
    private JFXTextField classTxt;
    @FXML
    private JFXDatePicker dateofbirthDate;
    @FXML
    private JFXComboBox<String> sexCbb;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ObservableList<String> sexList = FXCollections.observableArrayList("Male","Femaie","Other");
        sexCbb.setItems(sexList);
        sexCbb.getSelectionModel().select(0);


        TableColumn st_id = new TableColumn("Student ID");
        TableColumn class_id = new TableColumn("Class ID");
        TableColumn course_id = new TableColumn("Course ID");
        TableColumn fullname = new TableColumn("Ho ten");
        TableColumn Sex = new TableColumn("Gioi tinh");
        TableColumn DateofBirth = new TableColumn("Ngay sinh");
        TableColumn national = new TableColumn("Quoc tich");
        TableColumn address = new TableColumn("Dia chi");
        TableColumn email = new TableColumn("Email");
        TableColumn phonenumber = new TableColumn("SDT");


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
        maintable.getColumns().addAll(st_id,class_id,course_id,fullname,Sex,DateofBirth,national,address,email,phonenumber);
        data = st_dal.GetData();
        maintable.setItems(data);
    }

    @FXML
   private void AddButton(ActionEvent event) throws SQLException {
       String sex = sexCbb.getSelectionModel().getSelectedItem();
       sex=sex==null?"":sex;
       LocalDate lcdate = dateofbirthDate.getValue();
       String date = lcdate==null?"":lcdate.toString();
       Student st = new Student("",classTxt.getText(),fullnameTxt.getText(),sex,date,nationalTxt.getText(),addressTxt.getText(),emailTxt.getText(),phonenumberTxt.getText());
       st_dal.Insert(st);
       data = st_dal.GetData();
       

    }
    @FXML
    private void displaySelected(MouseEvent event) {
        Student st = maintable.getSelectionModel().getSelectedItem();
        if (st == null ){
            System.out.println("Khong thay st");
        }else {
            fullnameTxt.setText(st.getFullName());
            idTxt.setText(st.getStudent_id());
            sexCbb.getSelectionModel().select(st.getSex());
            nationalTxt.setText(st.getNationality());
            addressTxt.setText(st.getAddress());
            emailTxt.setText(st.getEmail());
            phonenumberTxt.setText(st.getPhoneNumber());
            if (!st.getDateOfBirth().equals("")){
                dateofbirthDate.setValue(LocalDate.parse(st.getDateOfBirth(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            }else {
                dateofbirthDate.setValue(null);
            }
        }
    }

    @FXML
    private void DeleteButton(ActionEvent event) throws SQLException {
        Student st = maintable.getSelectionModel().getSelectedItem();
        st_dal.Delete(st);
        data = st_dal.GetData();
    }

    @FXML
    private void EditButton(ActionEvent event) throws SQLException {
        Student st = maintable.getSelectionModel().getSelectedItem();
        String sex = sexCbb.getSelectionModel().getSelectedItem();
        String date = dateofbirthDate.getValue().toString();
        Student st2 = new Student("",classTxt.getText(),fullnameTxt.getText(),sex,date,nationalTxt.getText(),addressTxt.getText(),emailTxt.getText(),phonenumberTxt.getText());
        st_dal.Update(st.getStudent_id(),st2);
        data = st_dal.GetData();
    }
    
}
