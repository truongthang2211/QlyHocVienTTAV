/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qlyhocvienttav.Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Thang
 */
public class MainViewController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Button btn1;
    @FXML
    private void HamThucHienButton(ActionEvent event) throws Exception {
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("../View/TestForm1.fxml"));
        Parent root1 = fxmlloader.load();
        Stage stage = new Stage();
        //stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root1));
        stage.show();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
