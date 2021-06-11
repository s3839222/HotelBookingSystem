package main.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import main.SQLConnection;
import main.controller.RegisteredUsers;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookingModel {

    Connection connection;

    public BookingModel(){

        connection = SQLConnection.connect();
        if (connection == null)
            System.exit(1);

    }

    public Boolean isDbConnected(){
        try {
            return !connection.isClosed();
        }
        catch(Exception e){
            return false;
        }
    }
    // sql query to book a table by the user and add it to the database
    public Boolean isBooked(String username, String tableInfo, String tableStatus, String bookingDate, int bookingDateInt, String dateOfBooking) throws SQLException {
        PreparedStatement preparedStatement = null;
        String sql = "INSERT INTO booking (username, TableInfo, TableStatus, BookingDate, BookingDateInteger, DateOfBooking) VALUES(?,?,?,?,?,?)";
        int reasultSet = 0;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, tableInfo);
            preparedStatement.setString(3, tableStatus);
            preparedStatement.setString(4, bookingDate);
            preparedStatement.setInt(5, bookingDateInt);
            preparedStatement.setString(6, dateOfBooking);
            reasultSet = preparedStatement.executeUpdate();
            if(reasultSet == 1){
                return true;
            }else{
                return false;
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            preparedStatement.close();
        }
        return false;
    }
    // sql query to accept a booking and chenge the status to accept
    public boolean isAccept(String tableInfo, String tableStatus, String dateConfirmed) throws SQLException {
        PreparedStatement preparedStatement = null;
        int resultSet= 0;
        String query = "UPDATE booking SET TableStatus = ?, BookingConfirmationDate = ? WHERE TableInfo = ?";
        try {
//
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, tableStatus);
            preparedStatement.setString(2, dateConfirmed);
            preparedStatement.setString(3, tableInfo);
            preparedStatement.executeUpdate();
            return true;
        } catch (Exception e)
        {
            return false;
        }finally {
            preparedStatement.close();
            //resultSet.close();
        }

    }
    // sql query to get the table info (the table number)
    public ArrayList<String> getTableInfo() throws SQLException {
        String sql = "SELECT TableInfo, TableStatus FROM Booking";
        ResultSet rs = null;
        ArrayList<String> user = new ArrayList<String>();
        try {
            Statement stmt  = connection.createStatement();
            rs   = stmt.executeQuery(sql);
            String status = "";
            while(rs.next()) {
                String acpt = "accepted";
                String pnd = "pending";
                String lck = "Lockdown";
                status = rs.getString("TableStatus");
                if(acpt.equals(status) == true || (pnd.equals(status) == true) || lck.equals(status) == true) {
                    user.add(rs.getString("TableInfo"));
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally {
            rs.close();
        }
        return  user;
    }
    // sql query to get the table status
    public String getTableStatus(String table) throws SQLException {
        String sql = "SELECT TableInfo, TableStatus FROM Booking";
        ResultSet rs = null;
        String a = "";
        try {
            Statement stmt  = connection.createStatement();
            rs = stmt.executeQuery(sql);
            while(rs.next()){
                if(table.equals(rs.getString("TableInfo")) == true) {
                    a = rs.getString("TableStatus");
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally {
            rs.close();
        }
        return a;
    }

    // sql query to get the table number based of the table selected
    public boolean getTableInfo(String table) throws SQLException {
        String sql = "SELECT TableInfo FROM Booking";
        ResultSet rs = null;
        String a = "";
        try {
            Statement stmt  = connection.createStatement();
            rs = stmt.executeQuery(sql);
            while(rs.next()){
                if(table.equals(rs.getString("TableInfo")) == true) {
                    rs.getString("TableInfo");
                    return true;
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally {
            rs.close();
        }
        return false;
    }
    // sql query to get the table number based of the user
    public ArrayList<String> getTableByUsername(String username) throws SQLException {
        String sql = "SELECT Username, TableInfo FROM Booking";
        ResultSet rs = null;
        ArrayList<String> user = new ArrayList<String>();
        try {
            Statement stmt  = connection.createStatement();
            rs   = stmt.executeQuery(sql);
            while(rs.next()) {
                if (username.equals(rs.getString("Username"))) {
                    user.add(rs.getString("TableInfo"));
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally {
            rs.close();
        }
        return user;
    }
    // sql query to check if user has already book
    public Boolean OneBookPerUser(String user) throws SQLException {
        String sql = "select Username, TableStatus from Booking";
        ResultSet rs = null;
        try {
            Statement stmt  = connection.createStatement();
            rs   = stmt.executeQuery(sql);
            String stat = "";
            while(rs.next()) {
                if(user.equals(rs.getString("Username")) &&
                        (rs.getString("TableStatus").equals("accepted") ||
                                rs.getString("TableStatus").equals("pending"))){
                    return true;
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally {
            rs.close();
        }
        return false;
    }
    // sql query to get the booking date
    public String getDateOfBooking(String table) throws SQLException {
        String sql = "select TableInfo, DateOfBooking from Booking";
        ResultSet rs = null;
        try {
            Statement stmt  = connection.createStatement();
            rs    = stmt.executeQuery(sql);
            while(rs.next()){
                if(table.equals(rs.getString("TableInfo"))) {
                    return rs.getString("DateOfBooking");
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally {
            rs.close();
        }
        return null;
    }
    // sql query to get the user of the booking ased of the table number
    public String getUserOfBooking(String table) throws SQLException {
        String sql = "select TableInfo, Username from Booking";
        ResultSet rs = null;
        try {
            Statement stmt  = connection.createStatement();
            rs    = stmt.executeQuery(sql);
            while(rs.next()){
                if(table.equals(rs.getString("TableInfo"))) {
                    return rs.getString("Username");
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally {
            rs.close();
        }
        return null;
    }
    // sql to update the table status
    public Boolean updateTableStat(String tableInfo, String status, String date) throws SQLException{
        PreparedStatement preparedStatement = null;
        int resultSet = 0;
        String sql = "UPDATE Booking SET TableStatus = ? , BookingConfirmationDate = ?  "
                + "WHERE TableInfo = ?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, status);
            preparedStatement.setString(3, tableInfo);
            preparedStatement.setString(2, date);
            resultSet = preparedStatement.executeUpdate();
            if(resultSet == 1){
                return true;
            }else{
                return false;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }finally {
            preparedStatement.close();
        }
        return false;
    }
    // sql to update the table status based
    public Boolean updateTableStatus(String status, String dateConfirmed, String username, String stat2) throws SQLException{
        PreparedStatement preparedStatement = null;
        int resultSet = 0;
//        String sql = "UPDATE Booking SET TableStatus = ? , BookingConfirmationDate = ?  "
//                + "WHERE TableInfo = ? OR Username =?";
        String sql = "UPDATE Booking SET TableStatus = ? , BookingConfirmationDate = ?  "
                + "WHERE Username =? OR TableStatus=?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, status);
            preparedStatement.setString(2, dateConfirmed);
            preparedStatement.setString(4, stat2);
            preparedStatement.setString(3, username);
            resultSet = preparedStatement.executeUpdate();
            if(resultSet >= 1){
                return true;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }finally {
            preparedStatement.close();
        }
        return false;
    }
    // sql to get the confirmation date of the booking
    public String getConfirmationDate(String table) throws SQLException {
        String sql = "select TableInfo, DateOfBooking from Booking";
        ResultSet rs = null;
        String a = "";
        try {
            Statement stmt  = connection.createStatement();
            rs    = stmt.executeQuery(sql);
            while(rs.next()){
                if(table.equals(rs.getString("TableInfo"))) {
//                    System.out.println(rs.getString("Status"));
                    a= rs.getString("DateOfBooking");
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally {
            rs.close();
        }
        return a;
    }
    // sql to get the status of a booking based of the user
    public ArrayList<String> getStat(String user) throws SQLException {
        String sql = "select Username, TableStatus from Booking";
        ResultSet rs = null;
        ArrayList<String> a = new ArrayList<>();
        try {
            Statement stmt  = connection.createStatement();
            rs    = stmt.executeQuery(sql);
            while(rs.next()){
                if(user.equals(rs.getString("Username"))) {
                    a.add(rs.getString("TableStatus"));
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally {
            rs.close();
        }
        return a;
    }
    // sql to get the user that has done the covid lockdowns
    public String getUserOfLockdown(String table, String status) throws SQLException {
        String sql = "select Username, TableInfo, TableStatus from Booking";
        ResultSet rs = null;
        try {
            Statement stmt  = connection.createStatement();
            rs    = stmt.executeQuery(sql);
            while(rs.next()){
                if(table.equals(rs.getString("TableInfo")) && table.equals(rs.getString("TableStatus"))) {
                    return rs.getString("Username");
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally {
            rs.close();
        }
        return null;
    }
    // sql to get the details of the tables such as their status and their number
    public ObservableList<RegisteredUsers> getTableDetails() throws SQLException {
        String sql = "select Username, TableInfo, TableStatus from Booking";
        ResultSet rs = null;
        ObservableList<RegisteredUsers> userList = FXCollections.observableArrayList();;
        try {
            Statement stmt  = connection.createStatement();
            rs    = stmt.executeQuery(sql);
            while(rs.next()){
                    RegisteredUsers users = new RegisteredUsers();
                    users.setName(rs.getString("Username"));
                    users.setTable(rs.getString("TableInfo"));
                    users.setStatus(rs.getString("TableStatus"));
                    userList.add(users);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally {
            rs.close();
        }
        return userList;
    }
    // sql to get the table numbers based of the users
    public ArrayList<String> getUserTables(String username) throws SQLException {
        String sql = "select Username, TableInfo from Booking";
        ResultSet rs = null;
        ArrayList<String> user = new ArrayList<String>();
        try {
            Statement stmt  = connection.createStatement();
            rs   = stmt.executeQuery(sql);
            while(rs.next()) {
                if (username.equals(rs.getString("Username"))) {
                    user.add(rs.getString("TableInfo"));
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally {
            rs.close();
        }
        return user;
    }
}
