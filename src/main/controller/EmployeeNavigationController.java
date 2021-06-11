package main.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import main.model.LoginModel;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;

public class EmployeeNavigationController implements Initializable {

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
    }

    // navigate to account management
    public void account(javafx.event.ActionEvent event) throws IOException{
        UserHolder holder = UserHolder.getInstance();
        User u = holder.getUser();
        String username = u.getUsername();
        //isUsername.setText(username);
        System.out.println("isUsername:" + username);

        Parent root = FXMLLoader.load(getClass().getResource("../ui/empAccount.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(root);
    }
    // navigate to booking management
    public void booking(javafx.event.ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("../ui/booking.fxml"));
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
    //navigate to home screen
    public void home(javafx.event.ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("../ui/empNav.fxml"));
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }


}
