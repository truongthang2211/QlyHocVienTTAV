 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qlyhocvienttav.Controller.Admin;

import com.jfoenix.controls.JFXComboBox;
import java.net.URL;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import qlyhocvienttav.Controller.LoginViewController;
import qlyhocvienttav.Model.DAL.Analysis_DAL;
import qlyhocvienttav.Model.DTO.Analysis;

/**
 * FXML Controller class
 *
 * @author Thang
 */
public class AnalysisController implements Initializable {

    @FXML
    private Label topcenterlabel;
    @FXML
    private PieChart CoursePieChart;
    @FXML
    private BarChart<?, ?> StudentBarChart;
    Analysis_DAL anal_dal = new Analysis_DAL();
    ObservableList<Analysis> data;
    @FXML
    private JFXComboBox<String> FilterCbb;
    Report AReport = new Report();
    Map<String,Object> map;
    Connection connect = LoginViewController.connection.con;
    @FXML
    private PieChart ClassPieChart;
    @FXML
    private Tab Revenue_Tab;
    @FXML
    private Tab AmountStudent_Tab;
    @FXML
    private Tab Class_Tab;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        
        
        ObservableList<String> ListCbb = FXCollections.observableArrayList("This month","Last month","Last 3 month");
        FilterCbb.setItems(ListCbb);
        FilterCbb.getSelectionModel().selectedItemProperty().addListener( (Observable, oldValue, newValue) -> 
            CbbChange());
        FilterCbb.getSelectionModel().select(0);
        
        
        
        AmountStudent_Tab.setOnSelectionChanged(new EventHandler<Event>() {
            @Override
            public void handle(Event t) {
                if (AmountStudent_Tab.isSelected()) {
                    BarChartLoad();
                }
            }
        });
        Class_Tab.setOnSelectionChanged(new EventHandler<Event>() {
            @Override
            public void handle(Event t) {
                if (Class_Tab.isSelected()) {
                    // refreshTabBData();
                    PieChartClassLoad();
                }
            }
        });
        
    }    

    private void CbbChange() {
        String text = FilterCbb.getSelectionModel().getSelectedItem();
        LocalDate today = LocalDate.now();
        int month = today.getMonthValue();
        switch (text){
            case "This month":
                PieChartByMonth(month);
                
                break;
            case "Last month":
                month = month==1?12:month-1;
                PieChartByMonth(month);
                break;
            case "Last 3 month":
                PieChartLast3Month();
                break;
        }
    }
    void BarChartLoad(){
        data = anal_dal.GetAmountStudentPerMonth();
        StudentBarChart.getData().clear();
        XYChart.Series se = new XYChart.Series<>();
        se.getData().add(new XYChart.Data("Jan",data.get(0).jan));
        se.getData().add(new XYChart.Data("Feb",data.get(0).feb));
        se.getData().add(new XYChart.Data("Mar",data.get(0).march));
        se.getData().add(new XYChart.Data("Apr",data.get(0).april));
        se.getData().add(new XYChart.Data("May",data.get(0).may));
        se.getData().add(new XYChart.Data("Jun",data.get(0).jun));
        se.getData().add(new XYChart.Data("Jul",data.get(0).july));
        se.getData().add(new XYChart.Data("Aug",data.get(0).aug));
        se.getData().add(new XYChart.Data("Sep",data.get(0).sep));
        se.getData().add(new XYChart.Data("Oct",data.get(0).oc));
        se.getData().add(new XYChart.Data("Nov",data.get(0).no));
        se.getData().add(new XYChart.Data("Dec",data.get(0).dec));
        StudentBarChart.getData().addAll(se);
    }
    void PieChartByMonth(int month){
        data=anal_dal.GetRevenueByMonth(month);
        ObservableList<PieChart.Data> pieData = FXCollections.observableArrayList();
        for (Analysis t : data){
            pieData.add(new PieChart.Data(t.getCourseName(),t.getRevenue()));
        }
        CoursePieChart.getData().clear();
        CoursePieChart.getData().addAll(pieData);
        
    }
    void PieChartLast3Month(){
        data=anal_dal.GetRevenueByLast3Month();
        ObservableList<PieChart.Data> pieData = FXCollections.observableArrayList();
        for (Analysis t : data){
            pieData.add(new PieChart.Data(t.getCourseName(),t.getRevenue()));
        }
        CoursePieChart.getData().clear();
        CoursePieChart.getData().addAll(pieData);
    }
    void PieChartClassLoad(){
        data=anal_dal.GetClassData();
        ObservableList<PieChart.Data> pieData = FXCollections.observableArrayList();
        for (Analysis t : data){
            pieData.add(new PieChart.Data(t.getClassName(),t.getSoLuongHS()));
        }
        ClassPieChart.getData().clear();
        ClassPieChart.getData().addAll(pieData);
    }
    @FXML
    private void Revenue_Btn(ActionEvent event) {
        Thread t  = new Thread(()->{
            
            AReport.CreateReport(connect, map, "src\\qlyhocvienttav\\View\\Admin\\KhoaHoc.jrxml");
            AReport.ShowReport();
        });
        t.start();
    }

    @FXML
    private void Student_Btn(ActionEvent event) {
        Thread t  = new Thread(()->{
            AReport.CreateReport(connect, map, "src\\qlyhocvienttav\\View\\Admin\\HocSinh.jrxml");
            AReport.ShowReport();
        });
        t.start();
    }

    @FXML
    private void Class_Btn(ActionEvent event) {
        Thread t  = new Thread(()->{
            AReport.CreateReport(connect, map, "src\\qlyhocvienttav\\View\\Admin\\SLHS.jrxml");
            AReport.ShowReport();
        });
        t.start();
    }
}
