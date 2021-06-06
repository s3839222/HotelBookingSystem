package main.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import main.model.BookingModel;
import main.model.UserDetailModel;



import java.awt.*;
import java.net.URL;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class TableAllocationController implements Initializable {
    public BookingModel bookingModel = new BookingModel();
    public UserDetailModel userDetailModel = new UserDetailModel();
    public UserHolder holder =UserHolder.getInstance();



    @FXML
    private DatePicker date;

    @FXML
    private AnchorPane anchorPane;

    private List<Rectangle> tables;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        date.setValue(LocalDate.now());
        date.setDayCellFactory(picker -> new DateCell(){
            public void updateItem(LocalDate date, boolean empty){
                super.updateItem(date, empty); // to change body of generated method
                LocalDate d = LocalDate.now();
                setDisable(date.compareTo(d) < 0); //disable all the past dates.
            }
        });

        tables = new ArrayList<>();
        try {

            User user =holder.getUser();
            int length =bookingModel.getTableInfo().size();
//            int lckLength = bookingModel.getLockdownInfo().size();
            System.out.println(bookingModel.getTableInfo());
//
//            for(int i = 0; i < length; i++){
//                for(int j = 0; j < anchorPane.getChildren().size(); j++) {
//                    if(bookingModel.getTableInfo().get(i).equals(anchorPane.getChildren().get(j).getId())) {
//                        System.out.println(anchorPane.getChildren().get(j).getStyle());
//                        anchorPane.getChildren().get(j).setStyle("-fx-fill: red");
//
//                    }
//                    // System.out.println(anchorPane.getChildren().get(j).getId());
//                }
//            }

//            for(int i = 0; i < lckLength; i++){
//                for(int j = 0; j < anchorPane.getChildren().size(); j++) {
//                    if(bookingModel.getLockdownInfo().get(i).equals(anchorPane.getChildren().get(j).getId())) {
//                        System.out.println(anchorPane.getChildren().get(j).getStyle());
//                        anchorPane.getChildren().get(j).setStyle("-fx-fill: orange");
//                    }
//                    // System.out.println(anchorPane.getChildren().get(j).getId());
//                }
//            }
//
            for(int i = 0; i < anchorPane.getChildren().size(); i++) {
                if(anchorPane.getChildren().get(i).getId() == null || anchorPane.getChildren().get(i).getId().equals("date")
                        || anchorPane.getChildren().get(i).getId().equals("freeID") || anchorPane.getChildren().get(i).getId().equals("lockdownID")
                        || anchorPane.getChildren().get(i).getId().equals("bookID") || anchorPane.getChildren().get(i).getId().equals("redID")
                        || anchorPane.getChildren().get(i).getId().equals("greenID")|| anchorPane.getChildren().get(i).getId().equals("orangeID")
                        || anchorPane.getChildren().get(i).getId().equals("pinkID")|| anchorPane.getChildren().get(i).getId().equals("pendingID")) {
                    System.out.println("null or not table");
                }else {
                    String tble = anchorPane.getChildren().get(i).getId();
                    String status = bookingModel.getTableStatus(tble);
                    if (status.equalsIgnoreCase("accepted") && status != null) {
                        anchorPane.getChildren().get(i).setStyle("-fx-fill: red;");
                    } else if (status.equalsIgnoreCase("Lockdown") && status != null) {
                        anchorPane.getChildren().get(i).setStyle("-fx-fill: orange;");
                    }else if (status.equalsIgnoreCase("pending") && status != null) {
                        anchorPane.getChildren().get(i).setStyle("-fx-fill: pink;");
                    }
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
        UserHolder holder = UserHolder.getInstance();
        User user = holder.getUser();
        if(date.getValue() == null){
                    System.out.println("Please choose a date");
        }else{
            bookingModel.isNormal("Lockdown");
            for(int j = 0; j < anchorPane.getChildren().size(); j++) {
                System.out.println(anchorPane.getChildren().get(j).getStyle());
                anchorPane.getChildren().get(j).setStyle("-fx-fill: orange");
                if(anchorPane.getChildren().get(j).getId() == null || anchorPane.getChildren().get(j).getId().equals("date")
                        || anchorPane.getChildren().get(j).getId().equals("freeID") || anchorPane.getChildren().get(j).getId().equals("lockdownID")
                        || anchorPane.getChildren().get(j).getId().equals("bookID") || anchorPane.getChildren().get(j).getId().equals("redID")
                        || anchorPane.getChildren().get(j).getId().equals("greenID")|| anchorPane.getChildren().get(j).getId().equals("orangeID")
                        || anchorPane.getChildren().get(j).getId().equals("pinkID")|| anchorPane.getChildren().get(j).getId().equals("pendingID")) {
                    System.out.println("null or date");
                }else{
                    DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    Date today = new Date();
                    String dateToday = formatter.format(today).toString();
                    LocalDate local = date.getValue();
                    long dateInt = local.toEpochDay();
                    String dateString = local.toString();
                    String userName = user.getUsername();
                    user.setDate(dateInt);
                    holder.setUser(user);
                    String tble = anchorPane.getChildren().get(j).getId();
                    System.out.println(tble);
                    try {
                        if (bookingModel.isBooked(userName, tble, "Lockdown", dateString, (int) dateInt, dateToday)) {
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

    public void SocialDistance(ActionEvent event) throws SQLException {

        UserHolder holder =UserHolder.getInstance();
        User user =holder.getUser();
        if(date.getValue() == null){
            System.out.println("Please choose a date");
        }else{
            bookingModel.isNormal("Lockdown");
            for(int j = 0; j < anchorPane.getChildren().size(); j+=2) {
                System.out.println(anchorPane.getChildren().get(j).getStyle());
                anchorPane.getChildren().get(j).setStyle("-fx-fill: orange");
                if(anchorPane.getChildren().get(j).getId() == null || anchorPane.getChildren().get(j).getId().equals("date")
                        || anchorPane.getChildren().get(j).getId().equals("freeID") || anchorPane.getChildren().get(j).getId().equals("lockdownID")
                        || anchorPane.getChildren().get(j).getId().equals("bookID") || anchorPane.getChildren().get(j).getId().equals("redID")
                        || anchorPane.getChildren().get(j).getId().equals("greenID")|| anchorPane.getChildren().get(j).getId().equals("orangeID")
                        || anchorPane.getChildren().get(j).getId().equals("pinkID")|| anchorPane.getChildren().get(j).getId().equals("pendingID")) {
                    System.out.println("null or date");
                }else{
                    DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    Date today = new Date();
                    String dateToday = formatter.format(today).toString();
                    LocalDate local = date.getValue();
                    long dateInt = local.toEpochDay();
                    String dateString = local.toString();
                    String userName = user.getUsername();
                    user.setDate(dateInt);
                    holder.setUser(user);
                    String tble = anchorPane.getChildren().get(j).getId();
                    System.out.println(tble);
                    try {
                        if (bookingModel.isBooked(userName, tble, "Lockdown", dateString, (int) dateInt, dateToday)) {
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

    public void Normal(ActionEvent event) throws SQLException {
        bookingModel.isNormal("Lockdown");
        System.out.println("Back to Normal Conditions");
    }


}
