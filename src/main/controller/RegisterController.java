package main.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import main.SQLConnection;
import main.model.LoginModel;
//import main.model.RegisterModel;

import java.io.IOException;
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



    // Check database connection
    @Override
    public void initialize(URL location, ResourceBundle resources){
        if (loginModel.isDbConnected()){
            isConnected.setText("Connected");
        }else{
            isConnected.setText("Not Connected");
        }

    }

    public void registerButtonOnAction(ActionEvent event) throws IOException {
        if(txtPassword.getText().equals(txtConfirmPassword.getText())){

            registerUser();
            isConnected.setText("User Registered");

        }else{
            isConnected.setText("Password does not match");
        }


    }

    public void registerUser(){

        //SQLConnection connectNow =new SQLConnection();
        Connection connection = SQLConnection.getConnection().connect();

        String firstname =txtFirstname.getText();
        String surname =txtSurname.getText();
        String username =txtUsername.getText();
        String password =txtPassword.getText();
        String age =txtAge.getText();
        String role =txtRole.getText();
        String question =txtQuestion.getText();
        String answer =txtAnswer.getText();


//        String insertFields ="insert into employee (name, sure name, username, password) values('";
//        String insertValues = firstname +"','"+ surname +"','"+ username +"','"+ password + "')";
//        String insertRegister = insertFields + insertValues;
        PreparedStatement preparedStatement = null;
        //ResultSet resultSet=null;
        try{
            String sql ="insert into employee (name, surname, age, username, password, role, secret_question, secret_answer) values(?,?,?,?,?,?,?,?)";
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1, firstname);
            preparedStatement.setString(2, surname);
            preparedStatement.setString(3, age);
            preparedStatement.setString(4, username);
            preparedStatement.setString(5, password);
            preparedStatement.setString(6, role);
            preparedStatement.setString(7, question);
            preparedStatement.setString(8, answer);


            preparedStatement.execute();

//            Statement statement = connection.createStatement();
//            int status = statement.executeUpdate("insert into employee (name, surname, username, password) values ('"+firstname+"','"+surname+"', '"+username+"', '"+password+"')");
//            //statement.executeUpdate(insertRegister);
//            if(status > 0){
//                isConnected.setText("User has been registered");
//            }

        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }


}
