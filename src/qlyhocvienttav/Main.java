/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qlyhocvienttav;

import java.awt.Dimension;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javax.swing.JOptionPane;
import qlyhocvienttav.Controller.LoginViewController;
/**
 *
 * @author Thang
 */
public class Main extends Application {
    public static double height=1920,width=1080;
    @Override
    public void start(Stage stage){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("View//LoginView.fxml"));
            
            Scene scene = new Scene(root);
            stage.setScene(scene);
            //stage.setResizable(false);
            stage.show();
            
            Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
            width = screenSize.getWidth()*0.9375;
            height = screenSize.getHeight()*0.9;
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Lỗi khởi động phần mềm: " +ex, "Lỗi", 0);
        }
    }
    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        launch(args);
    }
    public static void ShowForm(String fxml , boolean newwindow, ActionEvent event) throws IOException{
        
        Task<Parent> task = new Task<Parent>() {
            @Override
            protected Parent call() throws Exception {
                return FXMLLoader.load(Main.class.getResource(fxml));
            }
        };

        task.setOnSucceeded(event2 -> {
            Parent root = task.getValue();
            Stage stage = new Stage();
            stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                public void handle(WindowEvent we) {
                    System.out.println("Stage is closing");
                    LoginViewController.connection.CloseConnection();
                }
            }); 
            stage.setScene(new Scene(root));
            stage.show();
            if (!newwindow){
                ((Node)(event.getSource())).getScene().getWindow().hide();
                
            }
        });
        Thread thread = new Thread(task);
        thread.start();
    }
    public static void ShowForm(Parent root , boolean newwindow, ActionEvent event) throws IOException{
        
        Task<Parent> task = new Task<Parent>() {
            @Override
            protected Parent call() throws Exception {
                return root;
            }
        };

        task.setOnSucceeded(event2 -> {
            Stage stage = new Stage();
            stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                public void handle(WindowEvent we) {
                    System.out.println("Stage is closing");
                    LoginViewController.connection.CloseConnection();
                }
            }); 
            stage.setScene(new Scene(root));
            stage.show();
            if (!newwindow){
                ((Node)(event.getSource())).getScene().getWindow().hide();
                
            }
        });
        Thread thread = new Thread(task);
        thread.start();
    }
}

