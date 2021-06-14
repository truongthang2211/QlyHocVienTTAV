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
import javafx.application.Platform;
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
import qlyhocvienttav.Model.DTO.Account;
import qlyhocvienttav.Model.DTO.Score;
import qlyhocvienttav.Model.DTO.Student;

/**
 * FXML Controller class
 *
 * @author Thang
 */
public class ScoreManageController implements Initializable {
     ObservableList<Score> data;
     Account account;
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
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        TableColumn Student_ID = new TableColumn("Student ID");
        TableColumn Student_Name = new TableColumn("Full Name");

        TableColumn Listening = new TableColumn("Listening");
        TableColumn Writing = new TableColumn("Writing");
        TableColumn Reading = new TableColumn("Reading");
        TableColumn Speaking = new TableColumn("Speaking");
        TableColumn TestSchedule_ID= new TableColumn("Test ID");
        
        Student_Name.setCellValueFactory(new PropertyValueFactory<>("Student_Name"));
        Student_ID.setCellValueFactory(new PropertyValueFactory<>("Student_ID"));
        Listening.setCellValueFactory(new PropertyValueFactory<>("Listening"));
        Writing.setCellValueFactory(new PropertyValueFactory<>("Writing"));
        Reading.setCellValueFactory(new PropertyValueFactory<>("Reading"));
        Speaking.setCellValueFactory(new PropertyValueFactory<>("Speaking"));
        TestSchedule_ID.setCellValueFactory(new PropertyValueFactory<>("TestSchedule_ID"));
        
        maintable.getColumns().addAll(Student_ID,Student_Name,Listening,Writing,Reading,Speaking);
        Platform.runLater(()->{
            data = sc_dal.GetData(account.getOwner());
            maintable.setItems(data);
        });
        
    }
    private Score GetScoreFromGUI(){
        Score in4 = new Score(StudentID_Txt.getText(),StudentName_Txt.getText(),"",Float.parseFloat(Listening_Txt.getText()),Float.parseFloat(Writting_Txt.getText()),Float.parseFloat(Reading_Txt.getText()),Float.parseFloat(Speaking_Txt.getText()));
        return in4;
    }
    
    private boolean CheckInputGUI(){
        //String date= DatePicker.getValue() == null?"":DatePicker.getValue().toString();
        if (StudentID_Txt==null||StudentID_Txt.getText().equals("")){
            return false;
        }
        return true;
    }
    @FXML
    private void EditBtn(ActionEvent event){
        if(CheckInputGUI()){
            Score sc = GetScoreFromGUI();
            Score sc2  = (Score) maintable.getSelectionModel().getSelectedItem();
            sc.setScore_ID(sc2.getScore_ID());
            sc.setTestSchedule_ID(sc2.getTestSchedule_ID());
            if (sc.getScore_ID() == null){
                sc_dal.Insert(sc);

            }else {
                sc_dal.Update(sc);
            }
            data = sc_dal.GetData(account.getOwner());
        }
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @FXML
    private void DisPlaySelected(MouseEvent event) {
        Score sc  = (Score) maintable.getSelectionModel().getSelectedItem();
        if (sc == null ){
            System.out.println("Khong thay diem");
        }else {
            StudentID_Txt.setText(sc.getStudent_ID());
            StudentName_Txt.setText(sc.getStudent_Name());
            Listening_Txt.setText(Float.toString(sc.getListening()));
            Writting_Txt.setText(Float.toString(sc.getWriting()));
            Reading_Txt.setText(Float.toString(sc.getReading()));
            Speaking_Txt.setText(Float.toString(sc.getSpeaking()));
            

        }
    }
    
}
