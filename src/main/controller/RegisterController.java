package main.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import main.SQLConnection;
import main.model.LoginModel;
import main.model.RegisterModel;

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
    private TextField txtQuestion;
    @FXML
    private TextField txtAnswer;
    @FXML
    private PasswordField txtConfirmPassword;
    @FXML
    private TextField txtRole;
    @FXML
    private Button cancelButton;


    // Check database connection
    @Override
    public void initialize(URL location, ResourceBundle resources){
        if (registerModel.isDbConnected()){
            isConnected.setText("Connected");
        }else{
            isConnected.setText("Not Connected");
        }

    }

//    public void registerButtonOnAction(ActionEvent event) throws IOException {
//        if(txtPassword.getText().equals("")){
//            isConnected.setText("Please fill in the form");
//        }else {
//            if (txtPassword.getText().equals(txtConfirmPassword.getText())) {
//
//                registerUser();
//                isConnected.setText("User Registered");
//
//            } else {
//                isConnected.setText("Password does not match");
//            }
//        }
//
//
//
//    }
    //name, surname, age, username, password, role, secret_question, secret_answer
    public void Register(ActionEvent event){
        String firstname =txtFirstname.getText();
        String surname =txtSurname.getText();
        String username =txtUsername.getText();
        String password =txtPassword.getText();
        String age =txtAge.getText();
        String role =txtRole.getText();
        String question =txtQuestion.getText();
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

//    public void registerUser(){
//
//        //SQLConnection connectNow =new SQLConnection();
//        Connection connection = SQLConnection.getConnection().connect();
//
//        String firstname =txtFirstname.getText();
//        String surname =txtSurname.getText();
//        String username =txtUsername.getText();
//        String password =txtPassword.getText();
//        String age =txtAge.getText();
//        String role =txtRole.getText();
//        String question =txtQuestion.getText();
//        String answer =txtAnswer.getText();
//
//
//        PreparedStatement preparedStatement = null;
//        //ResultSet resultSet=null;
//        try{
//            String sql ="insert into employee (name, surname, age, username, password, role, secret_question, secret_answer) values(?,?,?,?,?,?,?,?)";
//            preparedStatement=connection.prepareStatement(sql);
//            if(firstname.equals("") || age.equals("") || username.equals("")|| password.equals("")||
//                                            role.equals("")|| question.equals("")|| answer.equals("")){
//                isConnected.setText("Please fill in the form");
//            }else {
//                preparedStatement.setString(1, firstname);
//                preparedStatement.setString(2, surname);
//                preparedStatement.setString(3, age);
//                preparedStatement.setString(4, username);
//                preparedStatement.setString(5, password);
//                preparedStatement.setString(6, role);
//                preparedStatement.setString(7, question);
//                preparedStatement.setString(8, answer);
//
//
//                preparedStatement.execute();
//            }
//
//        }catch (Exception e){
//            e.printStackTrace();
//            e.getCause();
//        }
//    }
    public void cancelButtonAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../ui/login.fxml"));
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

}
