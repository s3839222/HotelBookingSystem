package main.controller;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class BookingController implements Initializable {
    public BookingModel bookingModel = new BookingModel();
    public UserDetailModel userDetailModel = new UserDetailModel();
    UserHolder holder = UserHolder.getInstance();
    @FXML
    private AnchorPane anchorPane;
    private List<Rectangle> tables;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        tables = new ArrayList<>();
        try {
            User user =holder.getUser();
            int length =bookingModel.getTableInfo().size();
            System.out.println(bookingModel.getTableInfo());
            String username = user.getUsername();
            System.out.println(userDetailModel.getRoleByUser(username));
            System.out.println(userDetailModel.getRoleByUser(username).equalsIgnoreCase("Admin"));
            if(userDetailModel.getRoleByUser(username).equalsIgnoreCase("Admin")){
                System.out.println("ok");
                DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                Date today = new Date();
                String date_1 = formatter.format(today).toString();
                String date_2 = null;
                for(int j = 0; j < anchorPane.getChildren().size(); j++) {
                    if(anchorPane.getChildren().get(j).getId() == null || anchorPane.getChildren().get(j).getId().equals("freeID")
                            || anchorPane.getChildren().get(j).getId().equals("lockdownID") || anchorPane.getChildren().get(j).getId().equals("bookedID")
                            || anchorPane.getChildren().get(j).getId().equals("redID") || anchorPane.getChildren().get(j).getId().equals("greenID")
                            || anchorPane.getChildren().get(j).getId().equals("orangeID") || anchorPane.getChildren().get(j).getId().equals("pinkID")
                            || anchorPane.getChildren().get(j).getId().equals("pendingID")) {
                        System.out.println("null or not table");
                    }else{
                        String tble = anchorPane.getChildren().get(j).getId();
                        String stats = bookingModel.getTableStatus(tble);
                        if (stats.equalsIgnoreCase("pending")) {
                            anchorPane.getChildren().get(j).setDisable(true);
                            date_2 = bookingModel.getDateOfBooking(/*anchorPane.getChildren().get(j).getId()*/ tble);
                            if (!date_2.equals(date_1)) {
                                bookingModel.updateTableStatus(anchorPane.getChildren().get(j).getId(), "Cancelled");
                            }
                        }
                    }

                }
            }
//            for(int i = 0; i < length; i++){
//                for(int j = 0; j < anchorPane.getChildren().size(); j++) {
//                    if(bookingModel.getTableInfo().get(i).equals(anchorPane.getChildren().get(j).getId())) {
//                        System.out.println(anchorPane.getChildren().get(j).getStyle());
//                        anchorPane.getChildren().get(j).setStyle("-fx-fill: pink");
//                    }
//
//                   // System.out.println(anchorPane.getChildren().get(j).getId());
//                }
//            }

            if(userDetailModel.getRoleByUser(user.getUsername()).equals("Employee")){
                for(int j = 0; j < anchorPane.getChildren().size(); j++) {

                    String tble = anchorPane.getChildren().get(j).getId();
                    String status = bookingModel.getTableStatus(tble);
                    if(status.equalsIgnoreCase("accepted") && status != null){
                        anchorPane.getChildren().get(j).setStyle("-fx-fill: red;");
                        anchorPane.getChildren().get(j).setDisable(true);
                    }else if(status.equalsIgnoreCase("Lockdown") && status != null){
                        anchorPane.getChildren().get(j).setStyle("-fx-fill: orange;");
                        anchorPane.getChildren().get(j).setDisable(true);
                    }else if(status.equalsIgnoreCase("pending") && status != null){
                        anchorPane.getChildren().get(j).setStyle("-fx-fill: pink;");
                        anchorPane.getChildren().get(j).setDisable(true);
                    }
                }
            }
            if(userDetailModel.getRoleByUser(user.getUsername()).equals("Admin")){
                for(int i = 0; i < anchorPane.getChildren().size(); i++) {
                    String tble = anchorPane.getChildren().get(i).getId();
                    String status = bookingModel.getTableStatus(tble);
                    if(status.equalsIgnoreCase("accepted")&& status != null){
                        anchorPane.getChildren().get(i).setStyle("-fx-fill: red;");
                    }else if(status.equalsIgnoreCase("Lockdown") && status != null){
                        anchorPane.getChildren().get(i).setStyle("-fx-fill: orange;");
                    }
                    else if(status.equalsIgnoreCase("pending") && status != null){
                        anchorPane.getChildren().get(i).setStyle("-fx-fill: pink;");
                    }
                }
            }

        }catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void Table() throws IOException {
        System.out.println("In table");
        Stage popupWindow = new Stage();
        popupWindow.initModality(Modality.APPLICATION_MODAL);
        Parent root = FXMLLoader.load(getClass().getResource("../ui/bookingDate.fxml"));
        popupWindow.setScene(new Scene(root));
        popupWindow.showAndWait();
    }
    public void ConfirmBooking() throws IOException {
        System.out.println("In confirmation");
        Stage popupWindow = new Stage();
        popupWindow.initModality(Modality.APPLICATION_MODAL);
        popupWindow.setTitle("Booking Confirmation");
        Parent root = FXMLLoader.load(getClass().getResource("../ui/bookingConfirmation.fxml"));
        popupWindow.setScene(new Scene(root));
        popupWindow.showAndWait();
    }
    public Boolean WhiteList(String username, String table) throws SQLException {
        int length = bookingModel.getTableByUsername(username).size();
        System.out.println("length: " + length);
        for(int i = 0; i < length; i++){
            if(bookingModel.getTableByUsername(username).get(i).
                    equals(table) == true){
                System.out.println("true: " + anchorPane.getChildren().get(i));
                return true;
            }
        }
        return false;
    }

    public void BookTable(javafx.scene.input.MouseEvent mouseEvent) throws SQLException, IOException {
        final Node src = (Node) mouseEvent.getSource();
        User u = holder.getUser();
        String id = src.getId();
        String user = u.getUsername();
        u.setTable(id);
        String tble = u.getTable();
        String status = bookingModel.getTableStatus(tble);
        System.out.println("stat: " + status);


        if(WhiteList(user, id) == true){
            System.out.println("please choose a different table");
        }else {
            if(bookingModel.OneBookPerUser(user)){
                System.out.println("you have already booked: " + user);
                if(userDetailModel.getRoleByUser(u.getUsername()).equals("Admin") && status.equals("accepted")){
                    System.out.println("in accepted window in onebookperuser");
                    ConfirmBooking();
                }else if(userDetailModel.getRoleByUser(u.getUsername()).equals("Admin") && status.equals("pending")) {
                    System.out.println("in pending window in onebookperuser");
                    ConfirmBooking();
                }
            }else {
                if(userDetailModel.getRoleByUser(u.getUsername()).equals("Admin") && status.equals("accepted")){
                    System.out.println("in accepted window");
                    ConfirmBooking();
                }else if(userDetailModel.getRoleByUser(u.getUsername()).equals("Admin") && status.equals("pending")){
                    System.out.println("in pending window");
                    ConfirmBooking();
                }else if(userDetailModel.getRoleByUser(u.getUsername()).equals("Admin") && status == null){
                    System.out.println("in emp");
                    Table();
//                    long day = u.getDate();
                    System.out.println("In book popup: " + u.getUsername());
                    System.out.println("str: " + u.getTable());

                }
                else if(userDetailModel.getRoleByUser(u.getUsername()).equals("Employee")){
                    System.out.println("in emp");
                    Table();
//                    long day = u.getDate();
                    System.out.println("In book popup: " + u.getUsername());
                    System.out.println("str: " + u.getTable());
                }else{
                    System.out.println("stat : " + status);
//                    ConfirmBooking();
                    Table();
                }
            }

        }
    }
}
