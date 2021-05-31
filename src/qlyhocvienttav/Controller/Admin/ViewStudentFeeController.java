/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qlyhocvienttav.Controller.Admin;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import qlyhocvienttav.Model.DAL.StudentFee_DAL;
import qlyhocvienttav.Model.DTO.StudentFee;

/**
 * FXML Controller class
 *
 * @author Thang
 */
public class ViewStudentFeeController implements Initializable {
    public ObservableList<StudentFee> data;
    StudentFee_DAL stdfee_dal = new StudentFee_DAL();
    @FXML
    private Label topcenterlabel;
    @FXML
    private TableView<StudentFee> maintable;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        TableColumn feeid = new TableColumn("ID Fee");
        TableColumn stdid = new TableColumn("MSSV");
        TableColumn stdname = new TableColumn("Ten Sinh Vien");
        TableColumn coursename = new TableColumn("Ten khoa hoc");
        TableColumn feepaid= new TableColumn("So Tien Da Dong");
        TableColumn amount = new TableColumn("So Tien");
        TableColumn status = new TableColumn("Tinh Trang");
        TableColumn datecompl = new TableColumn("Ngay Nap");

        feeid.setCellValueFactory(new PropertyValueFactory<>("idFee"));
        stdid.setCellValueFactory(new PropertyValueFactory<>("student_id"));
        stdname.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        coursename.setCellValueFactory(new PropertyValueFactory<>("course_name"));
        feepaid.setCellValueFactory(new PropertyValueFactory<>("amountOfFeeIsComplete"));
        amount.setCellValueFactory(new PropertyValueFactory<>("amountOfFee"));
        status.setCellValueFactory(new PropertyValueFactory<>("status"));
        datecompl.setCellValueFactory(new PropertyValueFactory<>("dateOfCompleteFee"));
        maintable.getColumns().addAll(feeid,stdid,stdname,coursename,feepaid,amount,status,datecompl);
        data = stdfee_dal.GetData();
        maintable.setItems(data);
    }    
    
}
