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
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;
import qlyhocvienttav.Model.DAL.Course_DAL;
import qlyhocvienttav.Model.DAL.Student_DAL;
import qlyhocvienttav.Model.DTO.Course;
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
    private JFXTextField Txt_Search;
    @FXML
    private JFXTextField classTxt;
    @FXML
    private JFXDatePicker dateofbirthDate;
    @FXML
    private JFXComboBox<String> sexCbb;
    @FXML
    private JFXComboBox<String> courseCbb;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ObservableList<String> sexList = FXCollections.observableArrayList("Male","Female","Other");
        sexCbb.setItems(sexList);
        sexCbb.getSelectionModel().select(0);
        GetCourseToCbb();
        
        
        
        
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
        maintable.getColumns().addAll(st_id,class_id,course_id,fullname,Sex,DateofBirth,national,address,email,phonenumber);
        data = st_dal.GetData();
        maintable.setItems(data);
    }

    @FXML
    private void AddButton(ActionEvent event) throws SQLException {
       if (CheckInputGUI()){
        Student st = GetStudentFromGUI();
        st_dal.Insert(st);
        data = st_dal.GetData();
       }
       
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
            courseCbb.getSelectionModel().select(st.getCourse_id());
            nationalTxt.setText(st.getNationality());
            addressTxt.setText(st.getAddress());
            emailTxt.setText(st.getEmail());
            phonenumberTxt.setText(st.getPhoneNumber());
            dateofbirthDate.setValue(LocalDate.parse(st.getDateOfBirth(), DateTimeFormatter.ISO_DATE));
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
        if (CheckInputGUI()){
            Student st = maintable.getSelectionModel().getSelectedItem();
            Student st2 = GetStudentFromGUI();
            st_dal.Update(st.getStudent_id(),st2);
            data = st_dal.GetData();
        }
        
    }
    private void GetCourseToCbb(){
        courseCbb.getItems().clear();
        Course_DAL course_dal = new Course_DAL();
        ObservableList<Course> CourseList = course_dal.GetData();
        CourseList.forEach(cr -> {
            courseCbb.getItems().add(cr.getCourse_id());
        });
        courseCbb.getSelectionModel().select(0);
    }
    private Student GetStudentFromGUI(){
        Student st = new Student("",classTxt.getText(),courseCbb.getSelectionModel().getSelectedItem(),fullnameTxt.getText(),sexCbb.getSelectionModel().getSelectedItem(),dateofbirthDate.getValue().toString(),nationalTxt.getText(),addressTxt.getText(),emailTxt.getText(),phonenumberTxt.getText());
        return st;
    }
    private boolean CheckInputGUI(){
        String date= dateofbirthDate.getValue() == null?"":dateofbirthDate.getValue().toString();
        String [] ListInput = {courseCbb.getSelectionModel().getSelectedItem(),fullnameTxt.getText(),sexCbb.getSelectionModel().getSelectedItem(),date,nationalTxt.getText(),addressTxt.getText(),emailTxt.getText(),phonenumberTxt.getText()};
        String [] Property = {"Course", "Full name","Sex","Date of birth","National","Address","Email","Phone numer"};
        for (int i = 0 ; i< ListInput.length; i++){
            if (ListInput[i] == null || ListInput[i].equals("")){
                String ErrorStr = Property[i] + " can not be empty";
                JOptionPane.showMessageDialog(null,ErrorStr,"Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
        return true;
    }
    
    @FXML
    private void Search(KeyEvent event) {
         String dataFind = Txt_Search.getText();
        if (data == null){
            return;
        }
        
            ObservableList<Student> list_StudentFind = FXCollections.observableArrayList();

            data.forEach((Student t) -> {
                if (t.checkContain(dataFind)){
                    list_StudentFind.add(t);
                }
            });
            maintable.setItems(list_StudentFind);
    }
    
}