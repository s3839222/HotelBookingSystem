package main.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import main.SQLConnection;
import main.model.LoginModel;
import main.model.ResetModel;
import main.model.UserDetailModel;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ResetController implements Initializable{

    public ResetModel resetModel = new ResetModel();
    public UserDetailModel usrMOdel = new UserDetailModel();
    @FXML
    private Label isConnected;
    @FXML
    private Label SQ;
    @FXML
    private Label SA;
    @FXML
    private Label NP;
    @FXML
    private TextField txtUsername;
    @FXML
    private TextField secretQs;
    @FXML
    private PasswordField txtNewPassword;
    @FXML
    private TextField txtAnswer;
    @FXML
    private Button resetBttn;
    @FXML
    private Button loginBttn;


    String userName;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        txtAnswer.setVisible(false);
        txtNewPassword.setVisible(false);
        secretQs.setVisible(false);
        resetBttn.setVisible(false);
        loginBttn.setVisible(false);
        SQ.setVisible(false);
        SA.setVisible(false);
        NP.setVisible(false);

        if (resetModel.isDbConnected()){
            isConnected.setText("Connected");
        }else{
            isConnected.setText("Not Connected");
        }
    }
    // action to get detail of the user for them to rest their pass
    public void Submit(ActionEvent event) throws SQLException {

        userName = txtUsername.getText();
        String qs = usrMOdel.getSecretQs(userName);
        System.out.println(qs);
        secretQs.setText(qs);
        secretQs.setVisible(true);
        secretQs.setEditable(false);
        txtAnswer.setVisible(true);
        txtNewPassword.setVisible(true);
        resetBttn.setVisible(true);
        loginBttn.setVisible(true);
        SQ.setVisible(true);
        SA.setVisible(true);
        NP.setVisible(true);

    }
    // action to rese the users password
    public void Reset(ActionEvent event) {
        String username =txtUsername.getText();
        String answer =txtAnswer.getText();
        String question = secretQs.getText();
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
    }
    // navigate back to the login page
    public void Login(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../ui/login.fxml"));
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
