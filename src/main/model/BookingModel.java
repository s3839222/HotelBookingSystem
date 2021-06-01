package main.model;

import main.SQLConnection;

import java.sql.*;
import java.util.ArrayList;

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

    public Boolean isBooked(String username, String tableInfo, String tableStatus, String bookingDate, int bookingDateInt) throws SQLException {
        PreparedStatement preparedStatement = null;
        String sql = "INSERT INTO booking (username, TableInfo, TableStatus, BookingDate, BookingDateInteger) VALUES(?,?,?,?,?)";
        int reasultSet = 0;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, tableInfo);
            preparedStatement.setString(3, tableStatus);
            preparedStatement.setString(4, bookingDate);
            preparedStatement.setInt(5, bookingDateInt);
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
    public Boolean isCancelled(String tableInfo) throws SQLException {
        PreparedStatement preparedStatement = null;
        String sql = "DELETE FROM Booking WHERE TableInfo = ?";
        int reasultSet = 0;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, tableInfo);
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

    public ArrayList<String> getTableInfo() throws SQLException {
        String sql = "select TableInfo, TableStatus, TableColour from Booking";
        ResultSet rs = null;
        ArrayList<String> users = new ArrayList<String>();
        try {
            Statement stmt  = connection.createStatement();
            rs   = stmt.executeQuery(sql);
            String status = "";
            while(rs.next()) {
                String acpt = "accepted";
                status = rs.getString("TableStatus");
                if(acpt.equals(status) == true || ("pending".equals(status) == true)) {
                    users.add(rs.getString("TableInfo"));
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally {
            rs.close();
        }
        return  users;
    }
}
