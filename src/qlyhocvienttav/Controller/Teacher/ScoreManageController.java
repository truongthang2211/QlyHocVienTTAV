/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qlyhocvienttav.Controller.Teacher;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;
import qlyhocvienttav.Model.DAL.Score_DAL;
import qlyhocvienttav.Model.DTO.Score;
import qlyhocvienttav.Model.DTO.Student;

/**
 * FXML Controller class
 *
 * @author Thang
 */
public class ScoreManageController implements Initializable {
     ObservableList<Score> data;
    private Score_DAL sc_dal = new Score_DAL();

    @FXML
    private Label topcenterlabel;
    @FXML
    private TableView<Score> maintable;
    
    @FXML
    private JFXTextField StudentID_Txt;
    @FXML
    private JFXTextField StudentName_Txt;
    @FXML
    private JFXTextField Listening_Txt;
    @FXML
    private JFXTextField Writting_Txt;
    @FXML
    private JFXTextField Reading_Txt;
    @FXML
    private JFXTextField Speaking_Txt;
    @FXML
    private JFXButton EditBtn;
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        TableColumn Score_ID = new TableColumn("Mã bảng điểm");
        TableColumn Student_ID = new TableColumn("Mã học viên");
        TableColumn Listening = new TableColumn("Nghe");
        TableColumn Writing = new TableColumn("Viết");
        TableColumn Reading = new TableColumn("Đọc ");
        TableColumn Speaking = new TableColumn("Nói ");
        TableColumn TestSchedule_ID= new TableColumn("Mã kỳ thi ");
        
        Score_ID.setCellValueFactory(new PropertyValueFactory<>("Score_ID"));
        Student_ID.setCellValueFactory(new PropertyValueFactory<>("Student_ID"));
        Listening.setCellValueFactory(new PropertyValueFactory<>("Listening"));
        Writing.setCellValueFactory(new PropertyValueFactory<>("Writing"));
        Reading.setCellValueFactory(new PropertyValueFactory<>("Reading"));
        Speaking.setCellValueFactory(new PropertyValueFactory<>("Speaking"));
        TestSchedule_ID.setCellValueFactory(new PropertyValueFactory<>("TestSchedule_ID"));
        
        maintable.getColumns().addAll(Score_ID,Student_ID,Listening,Writing,Reading,Speaking);
        
        data = sc_dal.GetData();
        maintable.setItems(data);
    }
    private Score GetScoreFromGUI(){
        Score in4 = new Score("",StudentID_Txt.getText(),"",Float.parseFloat(Listening_Txt.getText()),Float.parseFloat(Writting_Txt.getText()),Float.parseFloat(Reading_Txt.getText()),Float.parseFloat(Speaking_Txt.getText()));
        return in4;
    }
    
    private boolean CheckInputGUI(){
        //String date= DatePicker.getValue() == null?"":DatePicker.getValue().toString();
        String [] ListInput = {"",StudentID_Txt.getText(),"",String.valueOf(Listening_Txt.getText()),String.valueOf(Writting_Txt.getText()),String.valueOf(Reading_Txt.getText()),String.valueOf(Speaking_Txt.getText())};
        String [] Property = {"Test Schedule ID", "Date","Shift","Course ID","Teacher ID","Room ID","Kind of test"};
        for (int i = 0 ; i< ListInput.length; i++){
            if (ListInput[i] == null || ListInput[i].equals("")){
                String ErrorStr = Property[i] + " can not be empty";
                JOptionPane.showMessageDialog(null,ErrorStr,"Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
        return true;
    }
    private void EditBtn(ActionEvent event){
        if(CheckInputGUI()){
            Score sc = GetScoreFromGUI();
            sc_dal.Update(sc);
            data = sc_dal.GetData();
        }
    }
    @FXML
    private void displaySelected(MouseEvent event) {
        Score sc  = (Score) maintable.getSelectionModel().getSelectedItem();
        if (sc == null ){
            System.out.println("Khong thay diem");
        }else {
            StudentID_Txt.setText(sc.getStudent_ID());
            Listening_Txt.setText(Float.toString(sc.getListening()));
            Writting_Txt.setText(Float.toString(sc.getWriting()));
            Reading_Txt.setText(Float.toString(sc.getReading()));
            Speaking_Txt.setText(Float.toString(sc.getSpeaking()));
            

        }
    }

}
