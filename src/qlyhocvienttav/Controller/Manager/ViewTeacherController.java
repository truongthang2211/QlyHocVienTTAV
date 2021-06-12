package qlyhocvienttav.Controller.Manager;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import qlyhocvienttav.Model.DAL.Teacher_DAL;
import qlyhocvienttav.Model.DTO.Teacher;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.scene.input.KeyEvent;

public class ViewTeacherController implements Initializable {

    public ObservableList<Teacher> data;
    Teacher_DAL teacher_dal= new Teacher_DAL();
    @FXML
    private JFXTextField Txt_Search;
    @FXML
    private Label topcenterlabel;

    @FXML
    private TableView<Teacher> maintable;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
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
    private void Search(KeyEvent event) {
         String dataFind = Txt_Search.getText();
        if (data == null){
            return;
        }
        
            ObservableList<Teacher> list_TeacherFind = FXCollections.observableArrayList();

            data.forEach((Teacher t) -> {
                if (t.checkContain(dataFind)){
                    list_TeacherFind.add(t);
                }
            });
            maintable.setItems(list_TeacherFind);
    }
}
