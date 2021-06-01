package main.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;
import main.model.BookingModel;
import main.model.UserDetailModel;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class BookingDateController implements Initializable {
    public BookingModel bookingModel = new BookingModel();
    public UserDetailModel userDetailModel = new UserDetailModel();

    @FXML
    private DatePicker date;
    @FXML
    private Button book;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void Book(javafx.event.ActionEvent event) {
        if(date.getValue() == null){
            System.out.println("Please choose a date");
        }else {
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
                if (bookingModel.isBooked(userName, tble, "accepted", dateString, (int) dateInt)) {
                    System.out.println("Booked");
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            Stage stage = (Stage) book.getScene().getWindow();
            stage.close();
        }
    }

}
