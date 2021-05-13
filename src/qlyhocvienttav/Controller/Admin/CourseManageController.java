package qlyhocvienttav.Controller.Admin;

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
import qlyhocvienttav.Model.DAL.Course_DAL;
import qlyhocvienttav.Model.DAL.Student_DAL;
import qlyhocvienttav.Model.DAL.Teacher_DAL;
import qlyhocvienttav.Model.DTO.Course;
import qlyhocvienttav.Model.DTO.Student;
import qlyhocvienttav.Model.DTO.StudentFee;
import qlyhocvienttav.Model.DTO.Teacher;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class CourseManageController implements Initializable {

    @FXML
    private Label topcenterlabel;

    @FXML
    private TableView<Course> maintable;

    @FXML
    private JFXTextField txt_courseName;

    @FXML
    private JFXTextField txt_Fee;

    @FXML
    private JFXDatePicker dp_DayStart;

    @FXML
    private JFXDatePicker dp_dayEnd;
    public ObservableList<Course> data;
    Course_DAL course_dal= new Course_DAL();
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        TableColumn courseID = new TableColumn("ID");
        TableColumn courseName = new TableColumn("Môn Học");
        TableColumn fee = new TableColumn("Phí");
        TableColumn dateStart = new TableColumn("Ngày Bắt Đầu");
        TableColumn dateEnd = new TableColumn("Ngày Kết Thúc");

        courseName.setCellValueFactory(new PropertyValueFactory<Teacher,String>("courseName"));
        courseID.setCellValueFactory(new PropertyValueFactory<Teacher,String>("course_id"));
        fee.setCellValueFactory(new PropertyValueFactory<>("feeOfCourse"));
        dateStart.setCellValueFactory(new PropertyValueFactory<>("dateStart"));
        dateEnd.setCellValueFactory(new PropertyValueFactory<>("dateEnd"));



        maintable.getColumns().addAll(courseID,courseName,fee,dateStart,dateEnd);


        data = course_dal.GetData();
        maintable.setItems(data);
    }
    @FXML
    private void displaySelected(MouseEvent event) {
        Course course = maintable.getSelectionModel().getSelectedItem();
        if (course == null ){
            System.out.println("Khong thay st");
        }else {
            txt_courseName.setText(course.getCourseName());
            txt_Fee.setText(Long.toString(course.getFeeOfCourse()));



            if (course.getDateStart().equals("")){
                dp_DayStart.setValue(null);
            }else {
                dp_DayStart.setValue(LocalDate.parse(course.getDateStart(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            }


            if (course.getDateEnd().equals("")){
                dp_dayEnd.setValue(null);
            }else {
                dp_dayEnd.setValue(LocalDate.parse(course.getDateEnd(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            }

        }
    }
    @FXML
    private void DeleteButton(ActionEvent event) throws SQLException {
        Course course = maintable.getSelectionModel().getSelectedItem();
        course_dal.Delete(course);
        data = course_dal.GetData();
    }

    @FXML
    private void EditButton(ActionEvent event) throws SQLException {
        Course course = maintable.getSelectionModel().getSelectedItem();

        LocalDate dateStart = dp_DayStart.getValue();
        LocalDate dateEnd = dp_dayEnd.getValue();


        String str_dateStart = dateStart==null?"":dateStart.toString();
        String str_dateEnd = dateEnd==null?"":dateEnd.toString();

        Course course1 = new Course(course.getCourse_id(),txt_courseName.getText(),Long.parseLong(txt_Fee.getText()),str_dateStart,str_dateEnd);
        course_dal.Update(course1);
        data = course_dal.GetData();
    }

    @FXML
    private void AddButton(ActionEvent event) throws SQLException {

        LocalDate dateStart = dp_DayStart.getValue();
        LocalDate dateEnd = dp_dayEnd.getValue();


        String str_dateStart = dateStart==null?"":dateStart.toString();
        String str_dateEnd = dateEnd==null?"":dateEnd.toString();

        Course course = new Course("",txt_courseName.getText(),Long.parseLong(txt_Fee.getText()),str_dateStart,str_dateEnd);
        course_dal.Insert(course);
        data = course_dal.GetData();


    }

//    private void OutfocusStudentID(){
//        Student_DAL st_dal = new Student_DAL();
//        ObservableList<Student> studentList = st_dal.GetData();
//        for(Student st : studentList){
//            if (st.getStudent_id().equalsIgnoreCase(txt_StudentId.getText())){
//                txt_FullName.setText(st.getFullName());
//                return;
//            }
//        }
//    }
}
// Thang dep trai
