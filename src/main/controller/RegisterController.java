package main.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import main.SQLConnection;
import main.model.LoginModel;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class RegisterController implements Initializable{

    public LoginModel loginModel = new LoginModel();
    @FXML
    private Label isConnected;
    @FXML
    private TextField txtFirstname;
    @FXML
    private TextField txtSurname;
    @FXML
    private TextField txtUsername;
    @FXML
    private TextField txtPassword;
    @FXML
    private TextField txtConfirmPassword;

    // Check database connection
    @Override
    public void initialize(URL location, ResourceBundle resources){
        if (loginModel.isDbConnected()){
            isConnected.setText("Connected");
        }else{
            isConnected.setText("Not Connected");
        }

    }

    public void registerButtonOnAction(ActionEvent event){
        if(txtPassword.getText().equals(txtConfirmPassword.getText())){
            registerUser();

        }else{
            isConnected.setText("Password does not match");
        }


    }

    public void registerUser(){

        //SQLConnection connectNow =new SQLConnection();
        Connection connectDB = SQLConnection.connect();

        String firstname =txtFirstname.getText();
        String surname =txtSurname.getText();
        String username =txtUsername.getText();
        String password =txtPassword.getText();


        String insertFields ="insert into employee (name, sure name, username, password) values('";
        String insertValues = firstname +"','"+ surname +"','"+ username +"','"+ password + "')";
        String insertRegister = insertFields + insertValues;

        try{
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(insertRegister);
            isConnected.setText("User has been registered");
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }

}
