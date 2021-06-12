package qlyhocvienttav.Controller.Manager;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
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
import qlyhocvienttav.Model.DTO.Class;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.scene.input.KeyEvent;
import javax.swing.JOptionPane;
import qlyhocvienttav.Model.DAL.Course_DAL;
import qlyhocvienttav.Model.DTO.Account;
import qlyhocvienttav.Model.DTO.Course;


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
    private JFXTextField txt_findData;
    @FXML
    private JFXTextField txt_nameClass;

    @FXML
    private JFXTextField txt_numberOfPeople;

    @FXML
    private JFXTextField txt_maxNumberOfPeople;

    @FXML
    private JFXTextField txt_BasicGrade;
    @FXML
    private JFXComboBox<String> Cbb_CourseID;

    @Override
    public void initialize(URL location, ResourceBundle resources) {



        GetCourseToCbb();
        TableColumn classId = new TableColumn("ID Class");
        TableColumn nameClass = new TableColumn("Name Class");
        TableColumn courseId = new TableColumn("Id Course");
        TableColumn numberOfPeople = new TableColumn("numberOfPeople");
        TableColumn maxNumberOfPeople = new TableColumn("maxNumberOfPeople");
        TableColumn BasicGrade = new TableColumn("BasicGrade");

        classId.setCellValueFactory(new PropertyValueFactory<>("classId"));
        nameClass.setCellValueFactory(new PropertyValueFactory<>("className"));
        courseId.setCellValueFactory(new PropertyValueFactory<>("courseId"));
        numberOfPeople.setCellValueFactory(new PropertyValueFactory<>("numberOfPeople"));
        maxNumberOfPeople.setCellValueFactory(new PropertyValueFactory<>("maxNumberOfPeople"));
        BasicGrade.setCellValueFactory(new PropertyValueFactory<>("BasicGrade"));


        maintable.getColumns().addAll(classId,nameClass,courseId,numberOfPeople,maxNumberOfPeople,BasicGrade);
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

            Cbb_CourseID.getSelectionModel().select(cl.getCourseId());
            txt_numberOfPeople.setText(Integer.toString(cl.getNumberOfPeople()));
            txt_BasicGrade.setText(String.valueOf(cl.getBasicGrade()));
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
        if (CheckInputGUI()){
            Class cl = GetClassFromGUI();
            class_dal.Update(cl);
            data = class_dal.GetData();
        }
    }

    @FXML
    private void AddButton(ActionEvent event) throws SQLException {
        if (CheckInputGUI()){
            Class cl= GetClassFromGUI();
            class_dal.Insert(cl);
            data = class_dal.GetData();
        }
    }
    private Class GetClassFromGUI(){
        Class cl = new Class(txt_classId.getText(),txt_nameClass.getText(),0,Integer.parseInt(txt_maxNumberOfPeople.getText()),Cbb_CourseID.getSelectionModel().getSelectedItem(),Double.parseDouble(txt_BasicGrade.getText()));
        return cl;
    }
    private boolean CheckInputGUI(){
        String [] ListInput = {txt_nameClass.getText(),txt_maxNumberOfPeople.getText(),txt_BasicGrade.getText(),Cbb_CourseID.getSelectionModel().getSelectedItem()};
        String [] Property = {"Class name" , "Max Number of CLass" , "Basic grade" , "Course ID"};
        for (int i = 0 ; i< ListInput.length; i++){
            if (ListInput[i] == null || ListInput[i].equals("")){
                String ErrorStr = Property[i] + " can not be empty";
                JOptionPane.showMessageDialog(null,ErrorStr,"Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
        return true;
    }
    private void GetCourseToCbb(){
        Cbb_CourseID.getItems().clear();
        Course_DAL course_dal = new Course_DAL();
        ObservableList<Course> CourseList = course_dal.GetData();
        CourseList.forEach(cr -> {
            Cbb_CourseID.getItems().add(cr.getCourse_id());
        });
        Cbb_CourseID.getSelectionModel().select(0);
    }
    
    @FXML
    private void Search(KeyEvent event) {
         String dataFind = txt_findData.getText();
        if (data == null){
            return;
        }
        
            ObservableList<Class> list_ClassFind = FXCollections.observableArrayList();

            data.forEach((Class t) -> {
                if (t.checkContain(dataFind)){
                    list_ClassFind.add(t);
                }
            });
            maintable.setItems(list_ClassFind);
    }
}
