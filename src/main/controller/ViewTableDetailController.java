package main.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import main.model.BookingModel;

import java.net.URL;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class ViewTableDetailController implements Initializable {
    @FXML
    private TableView<RegisteredUsers> userTable;
    @FXML
    private TableColumn<RegisteredUsers, String> userID;
    @FXML
    private TableColumn<RegisteredUsers, String> tableID;
    @FXML
    private TableColumn<RegisteredUsers, String> status;

    private ObservableList<RegisteredUsers> list;

    public BookingModel bookingModel = new BookingModel();

    //intialize the info into a table for admins to view
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        list = FXCollections.observableArrayList();
        userID.setCellValueFactory(new PropertyValueFactory<>("name"));
        tableID.setCellValueFactory(new PropertyValueFactory<>("table"));
        status.setCellValueFactory(new PropertyValueFactory<>("status"));

        try {
            list = bookingModel.getTableDetails();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        userTable.setItems(list);
    }
}
