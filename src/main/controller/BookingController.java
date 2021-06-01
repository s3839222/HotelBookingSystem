package main.controller;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import main.model.BookingModel;
import main.model.UserDetailModel;

import javax.jws.soap.SOAPBinding;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class BookingController implements Initializable {
    public BookingModel bookingModel = new BookingModel();
    public UserDetailModel userDetailModel = new UserDetailModel();

    @FXML
    private AnchorPane anchorPane;


    private List<Rectangle> tables;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        tables = new ArrayList<>();
        try {
            UserHolder holder =UserHolder.getInstance();
            User user =holder.getUser();
            int length =bookingModel.getTableInfo().size();
            System.out.println(bookingModel.getTableInfo());

            for(int i = 0; i < length; i++){
                for(int j = 0; j < anchorPane.getChildren().size(); j++) {
                    if(bookingModel.getTableInfo().get(i).equals(anchorPane.getChildren().get(j).getId())) {
                        System.out.println(anchorPane.getChildren().get(j).getStyle());
                        anchorPane.getChildren().get(j).setStyle("-fx-fill: red");

                    }
                   // System.out.println(anchorPane.getChildren().get(j).getId());
                }
            }
            if(userDetailModel.getRoleByUser(user.getUsername()).equals("Employee")){
                for(int j = 0; j < anchorPane.getChildren().size(); j++) {
                    if(anchorPane.getChildren().get(j).getStyle().
                            equalsIgnoreCase("-fx-fill: red") == true) {
                        anchorPane.getChildren().get(j).setDisable(true);
                        anchorPane.getChildren().get(j).setStyle("-fx-fill: red;"
                                + "-fx-opacity: 1;");
                    }
                }
            }

        }catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public void Table(javafx.scene.input.MouseEvent mouseEvent) throws IOException {
        UserHolder holder = UserHolder.getInstance();
        User user = holder.getUser();
        String tble = mouseEvent.getPickResult().getIntersectedNode().getId();
        user.setTable(tble);
        Stage popupWindow = new Stage();
        popupWindow.initModality(Modality.APPLICATION_MODAL);
        Parent root = FXMLLoader.load(getClass().getResource("../ui/bookingDate.fxml"));
        popupWindow.setScene(new Scene(root));
        popupWindow.showAndWait();
        //long date = user.getDate();
    }
}
