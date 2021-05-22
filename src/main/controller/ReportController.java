package main.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import main.model.ReportModel;

import java.awt.event.ActionEvent;
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
        if (reportModel.isDbConnected()){
            isConnected.setText("Connected");
        }else{
            isConnected.setText("Not Connected");
        }

    }

    public void EmployeeReport(ActionEvent event) throws SQLException, IOException {
        System.out.println("Employee report generated");
        reportModel.EmplyoeeReport();
        isConnected.setText("Report Generated");
    }

    public void EmployeeReport(javafx.event.ActionEvent event) throws SQLException, IOException {
        System.out.println("Employee report generated");
        reportModel.EmplyoeeReport();
        isConnected.setText("Report Generated");
    }
}
