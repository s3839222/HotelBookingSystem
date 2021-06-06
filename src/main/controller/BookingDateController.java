package main.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;
import main.model.BookingModel;
import main.model.UserDetailModel;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;

public class BookingDateController implements Initializable {
    public BookingModel bookingModel = new BookingModel();
    public UserDetailModel userDetailModel = new UserDetailModel();

    @FXML
    private Button cancel;
    @FXML
    private Button accept;

    @FXML
    private DatePicker date;
    @FXML
    private Button book;

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

    }

    public void Book(javafx.event.ActionEvent event) {
        if(date.getValue() == null){
            System.out.println("Please choose a date");
        }else {
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date today = new Date();
            String dateToday = formatter.format(today).toString();
            LocalDate local = date.getValue();
            long dateInt = local.toEpochDay();
            String dateString = local.toString();
            UserHolder holder = UserHolder.getInstance();
            User user = holder.getUser();
            String userName = user.getUsername();
            user.setDate(dateInt);
            holder.setUser(user);
            String tble = user.getTable();
            System.out.println(tble);
            try {
                if(userDetailModel.getRoleByUser(user.getUsername()).equals("Admin")) {
                    if (bookingModel.isBooked(userName, tble, "accepted", dateString, (int) dateInt, dateToday)) {
                        System.out.println("Booked");
                    }
                }else{
                    if (bookingModel.isBooked(userName, tble, "pending", dateString, (int) dateInt, dateToday)) {
                        System.out.println("Booked");
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            Stage stage = (Stage) book.getScene().getWindow();
            stage.close();
        }
    }
    public void Cancel(javafx.event.ActionEvent event) throws SQLException {

        UserHolder holder = UserHolder.getInstance();
        User user = holder.getUser();
//        String userDate = user.getDate();
        holder.setUser(user);
        String tble = user.getTable();

        try {
            if (bookingModel.isCancelled(tble)) {
                System.out.println("Cancelled");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        Stage stage = (Stage) book.getScene().getWindow();
//        stage.close();
    }
    public void Accept(javafx.event.ActionEvent event) throws SQLException, IOException {

        UserHolder holder = UserHolder.getInstance();
        User user = holder.getUser();

//        String uname = user.getUsername();
//        System.out.println(uname);
//        String userDate = user.getDate();
        holder.setUser(user);
        String tble = user.getTable();

        try {
            if (bookingModel.isAccept(tble, "accepted")) {
                System.out.println("Accepted");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        Stage stage = (Stage) book.getScene().getWindow();
//        stage.close();
    }

}
