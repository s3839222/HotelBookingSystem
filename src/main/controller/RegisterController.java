package main.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.SQLConnection;
import main.model.LoginModel;
import main.model.RegisterModel;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class RegisterController implements Initializable{

    public RegisterModel registerModel = new RegisterModel();
    @FXML
    private Label isConnected;
    @FXML
    private TextField txtFirstname;
    @FXML
    private TextField txtSurname;
    @FXML
    private TextField txtUsername;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private TextField txtAge;
    @FXML
    private ComboBox txtQuestion;
    @FXML
    private TextField txtAnswer;
    @FXML
    private PasswordField txtConfirmPassword;
    @FXML
    private ComboBox txtRole;
    @FXML
    private Button cancelButton;



    // Check database connection, initialize the question and roles
    @Override
    public void initialize(URL location, ResourceBundle resources){
        ObservableList<String> questions = FXCollections.observableArrayList(
                "What is your favourite fruit","What is your favourite food",
                "Who is your favourite superhero");
        txtQuestion.setItems(questions);
        txtQuestion.getSelectionModel().selectFirst();

        ObservableList<String> role = FXCollections.observableArrayList(
                "Employee","Admin");
        txtRole.setItems(role);
        txtRole.getSelectionModel().selectFirst();
        if (registerModel.isDbConnected()){
            isConnected.setText("Connected");
        }else{
            isConnected.setText("Not Connected");
        }

    }
    // action to register and check if user input is the same as database.
    public void Register(ActionEvent event){
        String firstname =txtFirstname.getText();
        String surname =txtSurname.getText();
        String username =txtUsername.getText();
        String password =txtPassword.getText();
        String age =txtAge.getText();
        String role =(String) txtRole.getValue();;
        String question =(String) txtQuestion.getValue();
        String answer =txtAnswer.getText();

        if(firstname.equals("") || age.equals("") || username.equals("")|| password.equals("")||
                                            role.equals("")|| question.equals("")|| answer.equals("")){
                isConnected.setText("Please fill in the form");
        }

        if(txtPassword.getText().equals("")){
            isConnected.setText("Please fill in the form");
        }else {
            if (txtPassword.getText().equals(txtConfirmPassword.getText())) {

                try {

                    if (registerModel.isRegister(firstname, surname,  age, username, password, role, question, answer)){

                        isConnected.setText("Registration is successfully");



                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                isConnected.setText("User Registered");

            } else {
                isConnected.setText("Password does not match");
            }
        }
//

    }
    // cancel registration and back to login page
    public void cancelButtonAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../ui/login.fxml"));
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

}
