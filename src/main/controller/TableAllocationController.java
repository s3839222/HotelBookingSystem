package main.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import main.model.BookingModel;
import main.model.UserDetailModel;



import java.awt.*;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class TableAllocationController implements Initializable {
    public BookingModel bookingModel = new BookingModel();
    public UserDetailModel userDetailModel = new UserDetailModel();



    @FXML
    private DatePicker date;

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
        }catch (SQLException e) {
            e.printStackTrace();
        }

    }
//    public void Cancel(javafx.event.ActionEvent event) throws SQLException {
//        UserHolder holder = UserHolder.getInstance();
//        User user = holder.getUser();
//        holder.setUser(user);
//        String tble = user.getTable();
//        if(userDetailModel.getRoleByUser(user.getUsername()).equals("Admin")){
//            try {
//                if (bookingModel.isCancelled(tble)) {
//                    System.out.println("Cancelled");
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//    }

    public void Lockdown(ActionEvent event) throws SQLException {
        UserHolder holder =UserHolder.getInstance();
        User user =holder.getUser();
        if(date.getValue() == null){
                    System.out.println("Please choose a date");
        }else{
            for(int j = 0; j < anchorPane.getChildren().size(); j++) {
                System.out.println(anchorPane.getChildren().get(j).getStyle());
                anchorPane.getChildren().get(j).setStyle("-fx-fill: orange");
            if(anchorPane.getChildren().get(j).getId() == null) {
                System.out.println("null or date");
            }else if(anchorPane.getChildren().get(j).getId().equals("date")){
                System.out.println("null or date");
            }
            else{
                LocalDate local = date.getValue();
                long dateInt = local.toEpochDay();
                String dateString = local.toString();
                String userName = user.getUsername();
                user.setDate(dateInt);
                holder.setUser(user);
                String tble = anchorPane.getChildren().get(j).getId();
                System.out.println(tble);
                try {
                    if (bookingModel.isBooked(userName, tble, "Lockdown", dateString, (int) dateInt)) {
                        System.out.println("Lockdown");
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
             System.out.println(anchorPane.getChildren().get(j).getId());
            }
        }
    }
}
