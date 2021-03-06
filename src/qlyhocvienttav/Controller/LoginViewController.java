/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qlyhocvienttav.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javax.swing.JOptionPane;
import qlyhocvienttav.Controller.Admin.MainAdminController;
import qlyhocvienttav.Controller.Manager.MainManagerController;
import qlyhocvienttav.Controller.Teacher.MainTeacherController;

import qlyhocvienttav.Main;
import qlyhocvienttav.Model.DAL.Account_DAL;
import qlyhocvienttav.Model.DAL.DBConnection;
import qlyhocvienttav.Model.DTO.Account;

/**
 * FXML Controller class
 *
 * @author Thang
 */
public class LoginViewController implements Initializable {
    
    public static Account account = new Account();
    
    @FXML
    private JFXTextField username;
    @FXML
    private JFXPasswordField password;
    @FXML
    private Label warninglabel;
    @FXML
    private JFXButton loginbutton;
    @FXML
    private GridPane logogrid;
    @FXML
    private ImageView logogif;

    @FXML
    private void LoginButton(ActionEvent event) throws IOException {
        loginbutton.disableProperty().set(true);
        Task task;
        task = new Task<Void>() {
            @Override
            public Void call() {
                try {
                    if (connection.OpenConnection()) {
                        ObservableList<Account> data = new Account_DAL().GetData();
                        boolean flag = false;
                        String Role = "";
                        String Type = "";
                        for (Account a : data) {
                            if (username.getText().equals(a.getUsername()) && password.getText().equals(a.getPassword())) {
                                Type = a.getAcctype();
                                Role = Type + "/" + "Main" + Type;
                                flag = true;
                                account = a;
                                break;
                            }
                        }
                        if (flag) {

                            FXMLLoader loader;
                            loader = new FXMLLoader(getClass().getResource("../View/" + Role + ".fxml"));
                            Parent root = loader.load();
                            System.out.println("X??c ?????nh quy???n");
                            if (Type.equals("Manager")) {
                                Main.ShowForm(root, false, event);
                            } else if (Type.equals("Teacher")) {
                                Main.ShowForm(root, false, event);
                            } else if (Type.equals("Admin")) {
                                Main.ShowForm(root, false, event);
                            }
                            System.out.println("X??c ?????nh quy???n th??nh c??ng");
                        } else {
                            warninglabel.visibleProperty().set(true);
                            LoginViewController.connection.CloseConnection();

                        }
                    }
                    

                } catch (IOException ex) {
                    connection.CloseConnection();
                    loginbutton.disableProperty().set(false);
                    JOptionPane.showMessageDialog(null, "L???i Hi???n th??? form: " + ex, "L???i", 0);
                }
                loginbutton.disableProperty().set(false);
                return null;
            }
        };


        Thread thread = new Thread(task);

        thread.start();
        
    }

    public static DBConnection connection = new DBConnection();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        logogif.fitWidthProperty().bind(logogrid.widthProperty());

    }

}
