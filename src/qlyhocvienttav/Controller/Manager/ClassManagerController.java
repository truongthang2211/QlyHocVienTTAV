package qlyhocvienttav.Controller.Manager;

import com.jfoenix.controls.JFXTextField;
import javafx.beans.value.ObservableValue;
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
import qlyhocvienttav.Model.DAL.Class_DAL;
import qlyhocvienttav.Model.DAL.Student_DAL;
import qlyhocvienttav.Model.DTO.Class;
import qlyhocvienttav.Model.DTO.Student;
import qlyhocvienttav.Model.DTO.StudentFee;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;


public class ClassManagerController implements Initializable {
    Class_DAL class_dal = new Class_DAL();
    public ObservableList<Class> data;
    @FXML
    private Label topcenterlabel;

    @FXML
    private TableView<Class> maintable;

    @FXML
    private JFXTextField txt_classId;

    @FXML
    private JFXTextField txt_nameClass;

    @FXML
    private JFXTextField txt_numberOfPeople;

    @FXML
    private JFXTextField txt_maxNumberOfPeople;

    @FXML
    private JFXTextField txt_courseId;

    @Override
    public void initialize(URL location, ResourceBundle resources) {




        TableColumn classId = new TableColumn("ID Class");
        TableColumn nameClass = new TableColumn("Name Class");
        TableColumn courseId = new TableColumn("Id Course");
        TableColumn numberOfPeople = new TableColumn("numberOfPeople");
        TableColumn maxNumberOfPeople = new TableColumn("maxNumberOfPeople");

        classId.setCellValueFactory(new PropertyValueFactory<>("classId"));
        nameClass.setCellValueFactory(new PropertyValueFactory<>("className"));
        courseId.setCellValueFactory(new PropertyValueFactory<>("courseId"));
        numberOfPeople.setCellValueFactory(new PropertyValueFactory<>("numberOfPeople"));
        maxNumberOfPeople.setCellValueFactory(new PropertyValueFactory<>("maxNumberOfPeople"));

        maintable.getColumns().addAll(classId,nameClass,courseId,numberOfPeople,maxNumberOfPeople);
        data = class_dal.GetData();
        maintable.setItems(data);

    }
    @FXML
    private void displaySelected(MouseEvent event) {
        Class cl = maintable.getSelectionModel().getSelectedItem();
        if (cl == null ){
            System.out.println("Khong thay st");
        }else {
            txt_classId.setText(cl.getClassId());
            txt_nameClass.setText(cl.getClassName());

            txt_courseId.setText(cl.getCourseId());
            txt_numberOfPeople.setText(Integer.toString(cl.getNumberOfPeople()));

            txt_maxNumberOfPeople.setText(Integer.toString(cl.getMaxNumberOfPeople()));

        }
    }
    @FXML
    private void DeleteButton(ActionEvent event) throws SQLException {
        Class cl = maintable.getSelectionModel().getSelectedItem();
        class_dal.Delete(cl);
        data = class_dal.GetData();
    }

    @FXML
    private void EditButton(ActionEvent event) throws SQLException {
        Class cl = GetClassFromGUI();
        class_dal.Update(cl);
        data = class_dal.GetData();
    }

    @FXML
    private void AddButton(ActionEvent event) throws SQLException {
        Class cl= GetClassFromGUI();
        class_dal.Insert(cl);
        data = class_dal.GetData();


    }
    private Class GetClassFromGUI(){
        Class cl = new Class(txt_classId.getText(),txt_nameClass.getText(),Integer.parseInt(txt_numberOfPeople.getText()),Integer.parseInt(txt_maxNumberOfPeople.getText()),txt_courseId.getText());
        return cl;
    }

}
