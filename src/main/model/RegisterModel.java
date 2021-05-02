package main.model;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.SQLConnection;
import org.sqlite.SQLiteConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RegisterModel {
//    @FXML
//    public void registerScene(ActionEvent event) throws IOException {
//        Parent view = FXMLLoader.load(getClass().getResource("register.fxml"));
//        Scene scene = new Scene(view);
//        Stage window = new Stage();
//        window.setScene(scene);
//        window.show();
//    }


//    Connection connection;
//
//    public RegisterModel(){
//
//        connection = SQLConnection.connect();
//        if (connection == null)
//            System.exit(1);
//
//    }
//    public Boolean isDbConnected(){
//        try {
//            return !connection.isClosed();
//        }
//        catch(Exception e){
//            return false;
//        }
//    }

}
