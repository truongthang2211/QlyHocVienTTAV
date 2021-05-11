package qlyhocvienttav.Controller.Admin;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import qlyhocvienttav.Model.DAL.Teacher_DAL;
import qlyhocvienttav.Model.DTO.Student;
import qlyhocvienttav.Model.DTO.Teacher;

import javax.swing.*;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class ManagerAndTeacherManageController implements Initializable {

    @FXML
    private Label topcenterlabel;

    @FXML
    private TableView<Teacher> maintable;

    @FXML
    private JFXTextField txt_id;

    @FXML
    private JFXTextField txt_name;

    @FXML
    private JFXDatePicker datePicker_DOB;

    @FXML
    private JFXComboBox<String> cb_sex;

    @FXML
    private JFXTextField txt_national;

    @FXML
    private JFXTextField txt_address;

    @FXML
    private JFXTextField txt_email;

    @FXML
    private JFXTextField txt_phoneNumber;

    @FXML
    private JFXTextField txt_role;

    @FXML
    private JFXTextField txt_note;

    @FXML
    private JFXButton btn_add;

    @FXML
    private JFXButton btn_edit;

    @FXML
    private JFXButton btn_delete;
    public ObservableList<Teacher> data;
    Teacher_DAL teacher_dal= new Teacher_DAL();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> sexList = FXCollections.observableArrayList("Male","Female","Other");
        cb_sex.setItems(sexList);


        TableColumn teacherId = new TableColumn("Teacher ID");
        TableColumn fullname = new TableColumn("Ho ten");
        TableColumn Sex = new TableColumn("Gioi tinh");
        TableColumn DateofBirth = new TableColumn("Ngay sinh");
        TableColumn national = new TableColumn("Quoc tich");
        TableColumn address = new TableColumn("Dia chi");
        TableColumn email = new TableColumn("Email");
        TableColumn phonenumber = new TableColumn("SDT");

        teacherId.setCellValueFactory(new PropertyValueFactory<Teacher,String>("teacherId"));
        fullname.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        Sex.setCellValueFactory(new PropertyValueFactory<>("sex"));
        DateofBirth.setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));
        national.setCellValueFactory(new PropertyValueFactory<>("nationality"));
        address.setCellValueFactory(new PropertyValueFactory<>("address"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        phonenumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));



        maintable.getColumns().addAll(teacherId,fullname,Sex,DateofBirth,national,address,email,phonenumber);


        data = teacher_dal.GetData();
        maintable.setItems(data);
    }
    @FXML
    private void displaySelected(MouseEvent event) {
        Teacher tc = maintable.getSelectionModel().getSelectedItem();
        System.out.println(tc.getNationality());
        if (tc == null ){
            System.out.println("Khong thay st");
        }else {
            txt_name.setText(tc.getFullName());
            txt_id.setText(tc.getTeacherId());
            cb_sex.getSelectionModel().select(tc.getSex());
            datePicker_DOB.setValue(LocalDate.parse(tc.getDateOfBirth(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            txt_national.setText(tc.getNationality());
            txt_address.setText(tc.getAddress());
            txt_email.setText(tc.getEmail());
            txt_phoneNumber.setText(tc.getPhoneNumber());
        }
    }

    @FXML
    private void AddButton(ActionEvent event) throws SQLException {
        System.out.println("click add button");
        if (txt_name.getText().equals("")||datePicker_DOB.getValue()==null){
            JOptionPane.showMessageDialog(null,"Thieu Thong Tin");
        }
        else{
            String sex = cb_sex.getSelectionModel().getSelectedItem();
            sex=sex==null?"":sex;
            LocalDate lcdate = datePicker_DOB.getValue();
            String date = lcdate==null?"":lcdate.toString();
            Teacher teacher = new Teacher("1",txt_name.getText(),sex,date,txt_national.getText(),txt_address.getText(),txt_email.getText(),txt_phoneNumber.getText());
            teacher_dal.Insert(teacher);
            data = teacher_dal.GetData();
        }



    }

    @FXML
    private void DeleteButton(ActionEvent event) throws SQLException {
        Teacher tc = maintable.getSelectionModel().getSelectedItem();
        teacher_dal.Delete(tc);
        data = teacher_dal.GetData();
    }

    @FXML
    private void EditButton(ActionEvent event) throws SQLException {
        Teacher tc = maintable.getSelectionModel().getSelectedItem();
        String sex = cb_sex.getSelectionModel().getSelectedItem();
        String date = datePicker_DOB.getValue().toString();
        Teacher teacher;
        if (!date.equals("")){
           teacher = new Teacher("",txt_name.getText(),sex,date,txt_national.getText(),txt_address.getText(),txt_email.getText(),txt_phoneNumber.getText());
        }else{
            teacher = new Teacher("",txt_name.getText(),sex,"",txt_national.getText(),txt_address.getText(),txt_email.getText(),txt_phoneNumber.getText());
        }
        teacher_dal.Update(tc.getTeacherId(),teacher);
        data = teacher_dal.GetData();
    }

}
