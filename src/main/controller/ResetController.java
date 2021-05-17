package main.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import main.SQLConnection;
import main.model.LoginModel;
import main.model.ResetModel;
//import main.model.RegisterModel;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class ResetController implements Initializable {

    public ResetModel resetModel = new ResetModel();
    @FXML
    private Label isConnected;
    @FXML
    private TextField txtUsername;
    @FXML
    private ComboBox txtQuestion;
    @FXML
    private PasswordField txtNewPassword;
    @FXML
    private TextField txtAnswer;

    // Check database connection
    @Override
    public void initialize(URL location, ResourceBundle resources){
        ObservableList<String> questions = FXCollections.observableArrayList(
                "What is your favourite fruit","What is your favourite food",
                "Who is your favourite superhero");
        txtQuestion.setItems(questions);
        txtQuestion.getSelectionModel().selectFirst();

        if (resetModel.isDbConnected()){
            isConnected.setText("Connected");
        }else{
            isConnected.setText("Not Connected");
        }

    }

    public void Reset(ActionEvent event){
        Connection connection = SQLConnection.getConnection().connect();

        String username =txtUsername.getText();
        String answer =txtAnswer.getText();
        String question =(String) txtQuestion.getValue();
        String newPassword =txtNewPassword.getText();

        try {
            if(username.equals("") || newPassword.equals("")|| answer.equals("")){
                isConnected.setText("Please fill in the form");
            }else {
                if (resetModel.isReset(newPassword, username, question, answer)) {

                    isConnected.setText("Password reset");


                } else {
                    isConnected.setText("username or answer is incorrect");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

//
//        String username =txtUsername.getText();
//        String answer =txtAnswer.getText();
//        String newPassword =txtNewPassword.getText();
//        PreparedStatement preparedStatement = null;
//        //ResultSet resultSet=null;
//        String query = "UPDATE employee SET password =? WHERE username = ? AND secret_answer =?";
//
//        try {
////
//            preparedStatement = connection.prepareStatement(query);
//            preparedStatement.setString(1, newPassword);
//            preparedStatement.setString(2, username);
//            preparedStatement.setString(3, answer);
//
//            preparedStatement.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }

    public void cancelButtonAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../ui/login.fxml"));
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
