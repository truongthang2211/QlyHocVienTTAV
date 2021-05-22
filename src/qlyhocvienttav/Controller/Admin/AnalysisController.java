 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qlyhocvienttav.Controller.Admin;

import com.jfoenix.controls.JFXComboBox;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
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
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        
        BarChartLoad();
        ObservableList<String> ListCbb = FXCollections.observableArrayList("This month","Last month","Last 3 month");
        FilterCbb.setItems(ListCbb);
        FilterCbb.getSelectionModel().selectedItemProperty().addListener( (Observable, oldValue, newValue) -> 
            CbbChange());
        FilterCbb.getSelectionModel().select(0);
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
                month = month==1?12:month;
                PieChartByMonth(month);
                break;
            case "Last 3 month":
                PieChartLast3Month();
                break;
        }
    }
    void BarChartLoad(){
        data = anal_dal.GetAmountStudentPerMonth();
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
}
