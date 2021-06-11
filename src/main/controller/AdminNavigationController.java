package main.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import main.model.LoginModel;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminNavigationController implements Initializable {

    LoginModel loginModel = new LoginModel();

    @FXML
    private StackPane contentArea;
    @FXML
    private Label isUsername;

    @Override
    public void initialize(URL location, ResourceBundle resources){
        UserHolder holder = UserHolder.getInstance();
        User u = holder.getUser();
        String username = u.getUsername();
        isUsername.setText(username);
        System.out.println("isUsername:" + username);

        if (loginModel.isDbConnected()){
            //isConnected.setText("Connected");
            System.out.println("connected");
        }else{
            //isConnected.setText("Not Connected");
            System.out.println("not connected");
        }

    }

    // navigate to the account management
    public void account(javafx.event.ActionEvent event) throws IOException{
        UserHolder holder = UserHolder.getInstance();
        User u = holder.getUser();
        String username = u.getUsername();
        isUsername.setText(username);
        System.out.println("isUsername:" + username);

        Parent root = FXMLLoader.load(getClass().getResource("../ui/adminAccount.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(root);
    }
    // navigate to booking management
    public void booking(javafx.event.ActionEvent event) throws IOException{

        Parent root = FXMLLoader.load(getClass().getResource("../ui/booking.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(root);
    }
    // navigate to report management
    public void report(javafx.event.ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("../ui/report.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(root);
    }
    // navigate to covid condition managment
    public void tableAllocation(javafx.event.ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("../ui/tableAllocation.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(root);
    }
    // navigate to see the table details
    public void tableDetails(javafx.event.ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("../ui/viewTables.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(root);
    }
    // action to signout
    public void signout(javafx.event.ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("../ui/login.fxml"));
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
    // navigate to home
    public void home(javafx.event.ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("../ui/adminNavigation.fxml"));
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

}
