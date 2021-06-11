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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;

public class BookingDateController implements Initializable {
    public BookingModel bookingModel = new BookingModel();
    public UserDetailModel userDetailModel = new UserDetailModel();
    public UserHolder holder = UserHolder.getInstance();


    public User user = holder.getUser();

    @FXML
    private DatePicker date;
    @FXML
    private Button book;

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

    }

    // action ot book a table
    public void Book(javafx.event.ActionEvent event) {
        if(date.getValue() == null){
            System.out.println("Please choose a date");
        }else {
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date today = new Date();
            String dateToday = formatter.format(today).toString();
            LocalDate local = date.getValue();
            long dateInt = local.toEpochDay();
            String dateString = local.toString();

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
//            Stage stage = (Stage) book.getScene().getWindow();
//            stage.close();
        }
    }
    // action to reject a table bookinh
    public void Reject(javafx.event.ActionEvent event) throws SQLException {


        holder.setUser(user);
        String tble = user.getTable();

        Date today = new Date();
        String confirmedDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(today);

        try {
//
            bookingModel.updateTableStat(user.getTable(), "cancelled", confirmedDate);

        } catch (SQLException e) {
            e.printStackTrace();
        }

//        Stage stage = (Stage) book.getScene().getWindow();
//        stage.close();
    }
    // action to reject a table booking
    public void Accept(javafx.event.ActionEvent event) throws SQLException, IOException {


        holder.setUser(user);
        String tble = user.getTable();

        Date today = new Date();
        String confirmedDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(today);

        if (date.getValue() == null) {
            System.out.println("Please choose a date");
        } else{
            try {
//
                bookingModel.updateTableStat(user.getTable(), "accepted", confirmedDate);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
//        Stage stage = (Stage) book.getScene().getWindow();
//        stage.close();
    }
    // action to cancel a table booking
    public void Cancel(javafx.event.ActionEvent event){

        Date confirmDateDate2 = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String cancelledDate = dateFormat.format(confirmDateDate2);

        try {
            String stats = bookingModel.getTableStatus(user.getTable());
            String confirmDate = bookingModel.getConfirmationDate(user.getTable());

            if(confirmDate != null && !confirmDate.isEmpty() && stats.equals("accepted")) {
                Date confirmDateDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(confirmDate);
                long a = confirmDateDate.getTime() / 1000 / 60 / 60;
                long b = confirmDateDate2.getTime() / 1000 / 60 / 60;
                if ((b - a) > 48) {
                    System.out.println("a " + a);
                    System.out.println("b " + b);
                }else{
                    System.out.println("ok table update ");
                    bookingModel.updateTableStat(user.getTable(), "cancelled", cancelledDate);
                }

            }else{
                System.out.println("ok else ");
            }

        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }
    }

}
