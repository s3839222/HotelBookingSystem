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


import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    Connection connection;
    public LoginModel loginModel = new LoginModel();
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

    public  void registerButtonAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../ui/register.fxml"));
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
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

        try {
            if (loginModel.isLogin(txtUsername.getText(),txtPassword.getText())){

                isConnected.setText("Logged in successfully");
//                Statement stmt = connection.createStatement();
//                ResultSet rs  =stmt.executeQuery("select from employee where role like '%Employee%'");
//                while (rs.next()){
//                    String name = rs.getString("name");
//                    String role = rs.getString("role");
//                    System.out.println(name + "," + role);
//                }

                Parent root = FXMLLoader.load(getClass().getResource("../ui/menu.fxml"));
                Node node = (Node) event.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.show();


            }else{
                isConnected.setText("username or password is incorrect");
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    public void cancelButtonAction(ActionEvent event){
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }






    //11.2.3 big sur



}
