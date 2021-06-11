package main.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import main.model.ReportModel;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ReportController implements Initializable {
    ReportModel reportModel = new ReportModel();

    @FXML
    private Label isConnected;

    @Override
    public void initialize(URL location, ResourceBundle resources){
//
    }
    // action to generate the employee report where the admin is able to select the location of download
    //only consist data of the employees
    public void EmployeeReport(javafx.event.ActionEvent event) throws SQLException, IOException {
        System.out.println("Employee report generated");
        Stage popupwindow = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialFileName("Employee Report");
        fileChooser.setTitle("Save File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Microsoft Excel Comma Separated Values File", "*.csv"));
        File file = fileChooser.showSaveDialog(popupwindow);

        if(file != null) {
            String chosenFile = file.toString();
            reportModel.EmplyoeeReport(chosenFile,"Employee");
        }

    }

    // action to generate the booking report where the admin is able to select the location of download
    //generate booking data for only accepted/pending bookings and not cancelled or lockdown
    public void BookingReport(javafx.event.ActionEvent event) {
        Stage popupwindow = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialFileName("Booking Report");
        fileChooser.setTitle("Save File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Microsoft Excel Comma Separated Values File", "*.csv"));
        File file = fileChooser.showSaveDialog(popupwindow);

        if(file != null) {
            String chosenFile = file.toString();
            reportModel.BookingReport(chosenFile, "accepted", "pending");
        }
    }
}
