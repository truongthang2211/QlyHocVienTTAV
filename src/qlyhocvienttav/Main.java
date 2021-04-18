/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qlyhocvienttav;

import java.awt.Dimension;
import java.io.IOException;
import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
/**
 *
 * @author Thang
 */
public class Main extends Application {
    public static double height=1920,width=1080;
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("./View/LoginView.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        //stage.setResizable(false);
        stage.show();
        
        Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        width = screenSize.getWidth()*0.9375;
        height = screenSize.getHeight()*0.9;
    }
    /**
     * @param args the command line arguments
     */
    
    public static void ShowForm(String fxml , boolean newwindow, ActionEvent event) throws IOException{
        FXMLLoader fxmlloader = new FXMLLoader(Main.class.getResource(fxml));
        Parent root1 = fxmlloader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.show();
        if (!newwindow){
            ((Node)(event.getSource())).getScene().getWindow().hide();
        }
    }
    public static void main(String[] args) {
        launch(args);
    }
}

