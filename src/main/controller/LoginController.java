package main.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import main.model.LoginModel;
import main.model.UserDetailModel;


import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    public LoginModel loginModel = new LoginModel();
    public UserDetailModel userDetailModel = new UserDetailModel();

    @FXML
    private Label isConnected;
    @FXML
    private TextField txtUsername;
    @FXML
    private TextField txtPassword;
    @FXML
    private Button cancelButton;
    @FXML
    private Button registerButton;

    // Check database connection
    @Override
    public void initialize(URL location, ResourceBundle resources){
        if (loginModel.isDbConnected()){
            isConnected.setText("Connected");
        }else{
            isConnected.setText("Not Connected");
        }

    }
    // action to navigate to register page
    public  void registerButtonAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../ui/register.fxml"));
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }


    // action to reset password page
    public  void resetButtonAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../ui/reset.fxml"));

        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    /* login Action method
       check if user input is the same as database.
     */
    public void Login(ActionEvent event){
        User u = new User();
        String name = txtUsername.getText();
        u.setUsername(name);
        try {
            if (loginModel.isLogin(txtUsername.getText(),txtPassword.getText())){
                if(userDetailModel.getRoleByUser(txtUsername.getText()).equals("Admin")){
                    UserHolder holder = UserHolder.getInstance();
                    holder.setUser(u);
                    isConnected.setText("Logged in successfully");
                    Parent root = FXMLLoader.load(getClass().getResource("../ui/adminNavigation.fxml"));
                    Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    stage.setScene(new Scene(root));
                    stage.show();
                }else{
                    UserHolder holder = UserHolder.getInstance();
                    holder.setUser(u);
                    isConnected.setText("Logged in successfully");
                    Parent root = FXMLLoader.load(getClass().getResource("../ui/empNav.fxml"));
                    Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    stage.setScene(new Scene(root));
                    stage.show();
                }



            }else{
                isConnected.setText("username or password is incorrect");
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
    // action to quit the application
    public void cancelButtonAction(ActionEvent event){
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }






    //11.2.3 big sur



}
