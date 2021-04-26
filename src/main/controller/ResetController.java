package main.controller;

import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import main.SQLConnection;
import main.model.LoginModel;
//import main.model.RegisterModel;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class ResetController implements Initializable {

    public LoginModel loginModel = new LoginModel();
    @FXML
    private Label isConnected;
    @FXML
    private TextField txtUsername;
    @FXML
    private PasswordField txtNewPassword;
    @FXML
    private TextField txtAnswer;

    // Check database connection
    @Override
    public void initialize(URL location, ResourceBundle resources){

        if (loginModel.isDbConnected()){
            isConnected.setText("Connected");
        }else{
            isConnected.setText("Not Connected");
        }

    }

    public void Reset(ActionEvent event){
        Connection connection = SQLConnection.getConnection().connect();
//
        String username =txtUsername.getText();
        String answer =txtAnswer.getText();
        String newPassword =txtNewPassword.getText();
        PreparedStatement preparedStatement = null;
        //ResultSet resultSet=null;
        String query = "UPDATE employee SET password =? WHERE username = ? AND secret_answer =?";

        try {
//            Statement statement = connection.createStatement();
//            if (loginModel.isLogin(txtUsername.getText(),txtAnswer.getText())){
//                int status = statement.executeUpdate("insert into employee (name, surname, username, password) " +
//                        "values ('"+firstname+"','"+surname+"', '"+username+"', '"+password+"')");
//                isConnected.setText("Reset is successfully");
//
//
//            }else{
//                isConnected.setText("username or security answer is incorrect");
//            }
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, newPassword);
            preparedStatement.setString(2, username);
            preparedStatement.setString(3, answer);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
