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

public class CovidAllocationController implements Initializable {
    public BookingModel bookingModel = new BookingModel();
    public UserDetailModel userDetailModel = new UserDetailModel();
    public UserHolder holder =UserHolder.getInstance();



    @FXML
    private DatePicker date;

    @FXML
    private AnchorPane anchorPane;

//    @FXML
//    private Label isConneccted;

    private List<Rectangle> tables;
    // initialize the table colours
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        date.setValue(LocalDate.now());
        date.setDayCellFactory(picker -> new DateCell(){
            public void updateItem(LocalDate date, boolean empty){
                super.updateItem(date, empty);
                LocalDate d = LocalDate.now();
                setDisable(date.compareTo(d) < 0);
            }
        });

        tables = new ArrayList<>();
        try {

            User user =holder.getUser();
            int length =bookingModel.getTableInfo().size();
            System.out.println(bookingModel.getTableInfo());

            for(int i = 0; i < anchorPane.getChildren().size(); i++) {
                if(anchorPane.getChildren().get(i).getId() == null || anchorPane.getChildren().get(i).getId().equals("date")
                        || anchorPane.getChildren().get(i).getId().equals("freeID") || anchorPane.getChildren().get(i).getId().equals("lockdownID")
                        || anchorPane.getChildren().get(i).getId().equals("bookedID") || anchorPane.getChildren().get(i).getId().equals("redID")
                        || anchorPane.getChildren().get(i).getId().equals("greenID")|| anchorPane.getChildren().get(i).getId().equals("orangeID")
                        || anchorPane.getChildren().get(i).getId().equals("pinkID")|| anchorPane.getChildren().get(i).getId().equals("pendingID")){
//                    System.out.println("null or not table");
                }else {
                    String tble = anchorPane.getChildren().get(i).getId();
                    String status = bookingModel.getTableStatus(tble);
                    if (status.equalsIgnoreCase("accepted") && status != null) {
                        anchorPane.getChildren().get(i).setStyle("-fx-fill: red;");
                    }else if (status.equalsIgnoreCase("Lockdown") && status != null) {
                        anchorPane.getChildren().get(i).setStyle("-fx-fill: orange;");
                    }else if (status.equalsIgnoreCase("Social Distance") && status != null) {
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
    // action to lock down all the tables
    public void Lockdown(ActionEvent event) throws SQLException {
        UserHolder holder = UserHolder.getInstance();
        User user = holder.getUser();
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date today = new Date();
        String dateToday = formatter.format(today).toString();
        LocalDate local = date.getValue();
        long dateInt = local.toEpochDay();
        String dateString = local.toString();
        String userName = user.getUsername();
        user.setDate(dateInt);
        holder.setUser(user);
        if(date.getValue() == null){
                    System.out.println("Please choose a date");
        }else{
            for(int j = 0; j < anchorPane.getChildren().size(); j++) {
                System.out.println(anchorPane.getChildren().get(j).getStyle());
                anchorPane.getChildren().get(j).setStyle("-fx-fill: orange");
                if(anchorPane.getChildren().get(j).getId() == null || anchorPane.getChildren().get(j).getId().equals("date")
                        || anchorPane.getChildren().get(j).getId().equals("freeID") || anchorPane.getChildren().get(j).getId().equals("lockdownID")
                        || anchorPane.getChildren().get(j).getId().equals("bookedID") || anchorPane.getChildren().get(j).getId().equals("redID")
                        || anchorPane.getChildren().get(j).getId().equals("greenID")|| anchorPane.getChildren().get(j).getId().equals("orangeID")
                        || anchorPane.getChildren().get(j).getId().equals("pinkID")|| anchorPane.getChildren().get(j).getId().equals("pendingID")) {
//                    System.out.println("null or date");
                }else {

                    String tble = anchorPane.getChildren().get(j).getId();
                    System.out.println(tble);
                    if (bookingModel.getTableStatus(tble).equals("Lockdown")) {
//                        isConneccted.setText("Already in Lockdown");
                        System.out.println("already in Lockdown");
                    }else {
                        try {
                            if (bookingModel.getTableInfo(tble) && (bookingModel.getTableStatus(tble).equals("accepted")
                                    || bookingModel.getTableStatus(tble).equals("pending") || bookingModel.getTableStatus(tble).equals("Lockdown"))) {
                                bookingModel.updateTableStat(tble, "Lockdown", dateToday);
                            } else if (bookingModel.getTableStatus(tble).equals("Social Distance")) {
                                bookingModel.updateTableStat(tble, "cancelled", dateToday);
                                bookingModel.isBooked(userName, tble, "Lockdown", dateString, (int) dateInt, dateToday);
                            }else if (bookingModel.getTableStatus(tble).equals("cancelled")) {
                                bookingModel.updateTableStat(tble, "cancelled", dateToday);
                                bookingModel.isBooked(userName, tble, "Lockdown", dateString, (int) dateInt, dateToday);
                            } else {
                                bookingModel.isBooked(userName, tble, "Lockdown", dateString, (int) dateInt, dateToday);
                                System.out.println("Now in Lockdown");
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
    // action for social distancing where employees can not sit net toeach other
    public void SocialDistance(ActionEvent event) throws SQLException {

        UserHolder holder =UserHolder.getInstance();
        User user =holder.getUser();
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date today = new Date();
        String dateToday = formatter.format(today).toString();
        LocalDate local = date.getValue();
        long dateInt = local.toEpochDay();
        String dateString = local.toString();
        String userName = user.getUsername();
        user.setDate(dateInt);
        holder.setUser(user);
        for(int j = 0; j < anchorPane.getChildren().size(); j++) {
            String tble = anchorPane.getChildren().get(j).getId();
            if(anchorPane.getChildren().get(j).getId() == null || anchorPane.getChildren().get(j).getId().equals("date")
                        || anchorPane.getChildren().get(j).getId().equals("freeID") || anchorPane.getChildren().get(j).getId().equals("lockdownID")
                        || anchorPane.getChildren().get(j).getId().equals("bookedID") || anchorPane.getChildren().get(j).getId().equals("redID")
                        || anchorPane.getChildren().get(j).getId().equals("greenID")|| anchorPane.getChildren().get(j).getId().equals("orangeID")
                        || anchorPane.getChildren().get(j).getId().equals("pinkID")|| anchorPane.getChildren().get(j).getId().equals("pendingID")) {
//                    System.out.println("null or date");
            }else if(bookingModel.getTableStatus(tble).equals("pending") || bookingModel.getTableStatus(tble).equals("accepted") || bookingModel.getTableStatus(tble).equals("cancelled")){
                System.out.println("pending or accepted");
            }else if(bookingModel.getTableStatus(tble).equals("Social Distance")){
                System.out.println("Already in social distanceing");
            }else{
                bookingModel.updateTableStat(tble, "cancelled", dateToday);
            }
        }

        if(date.getValue() == null){
            System.out.println("Please choose a date");
        }else{
//            bookingModel.isNormal("Lockdown");

            for(int j = 0; j < anchorPane.getChildren().size(); j+=2) {
                System.out.println(anchorPane.getChildren().get(j).getStyle());
//                anchorPane.getChildren().get(j).setStyle("-fx-fill: orange");
                String tble = anchorPane.getChildren().get(j).getId();

                if(anchorPane.getChildren().get(j).getId() == null || anchorPane.getChildren().get(j).getId().equals("date")
                        || anchorPane.getChildren().get(j).getId().equals("freeID") || anchorPane.getChildren().get(j).getId().equals("lockdownID")
                        || anchorPane.getChildren().get(j).getId().equals("bookedID") || anchorPane.getChildren().get(j).getId().equals("redID")
                        || anchorPane.getChildren().get(j).getId().equals("greenID")|| anchorPane.getChildren().get(j).getId().equals("orangeID")
                        || anchorPane.getChildren().get(j).getId().equals("pinkID")|| anchorPane.getChildren().get(j).getId().equals("pendingID")) {
//                    System.out.println("null or date");
                }else if(bookingModel.getTableStatus(tble).equals("accepted")) {
                    System.out.println("Already Social distance");
                }else{
//                        String tble = anchorPane.getChildren().get(j).getId();
                    System.out.println(tble);
                    if(bookingModel.getTableInfo(tble) && (bookingModel.getTableStatus(tble).equals("accepted"))
                            || bookingModel.getTableStatus(tble).equals("pending") || bookingModel.getTableStatus(tble).equals("Lockdown")){
                        bookingModel.updateTableStat(tble, "Social Distance", dateToday);
                    }else if(bookingModel.getTableStatus(tble).equals("Social Distance")){
//                        isConneccted.setText("Already in Social Distance");
                        System.out.println("Already in social distanceing");
                    }else{
                        bookingModel.isBooked(userName, tble, "Social Distance", dateString, (int) dateInt, dateToday);
                        System.out.println("Now in Lockdown");
                    }
                }
            }
        }
    }
    // action to set covid condition to normal
    public void Normal(ActionEvent event) throws SQLException {
        UserHolder holder =UserHolder.getInstance();
        User user =holder.getUser();
//        bookingModel.isNormal("Lockdown");
        System.out.println("Back to Normal Conditions");
        for(int j = 0; j < anchorPane.getChildren().size(); j++) {
            System.out.println(anchorPane.getChildren().get(j).getStyle());
//            anchorPane.getChildren().get(j).setStyle("-fx-fill: orange");
            if(anchorPane.getChildren().get(j).getId() == null || anchorPane.getChildren().get(j).getId().equals("date")
                    || anchorPane.getChildren().get(j).getId().equals("freeID") || anchorPane.getChildren().get(j).getId().equals("lockdownID")
                    || anchorPane.getChildren().get(j).getId().equals("bookedID") || anchorPane.getChildren().get(j).getId().equals("redID")
                    || anchorPane.getChildren().get(j).getId().equals("greenID")|| anchorPane.getChildren().get(j).getId().equals("orangeID")
                    || anchorPane.getChildren().get(j).getId().equals("pinkID")|| anchorPane.getChildren().get(j).getId().equals("pendingID")) {
                System.out.println("null or date");
            }else{
                DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
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
                    if(bookingModel.getTableStatus(tble).equals("Lockdown") || bookingModel.getTableStatus(tble).equals("Social Distance")){
                        bookingModel.updateTableStat(tble, "cancelled", dateToday);
                    }

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            System.out.println(anchorPane.getChildren().get(j).getId());
        }
    }
}
