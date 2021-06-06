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
    public Boolean isNormal(String tableStatus) throws SQLException {
        PreparedStatement preparedStatement = null;
        String sql = "DELETE FROM Booking WHERE TableStatus = ?";
        int reasultSet = 0;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, tableStatus);
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

    public boolean isAccept(String tableInfo, String tableStatus) throws SQLException {
        PreparedStatement preparedStatement = null;
        int resultSet= 0;
        String query = "UPDATE booking SET TableStatus =? WHERE TableInfo = ?";
        try {
//
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, tableStatus);
            preparedStatement.setString(2, tableInfo);
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

    public ArrayList<String> getTableInfo() throws SQLException {
        String sql = "SELECT TableInfo, TableStatus, TableColour FROM Booking";
        ResultSet rs = null;
        ArrayList<String> users = new ArrayList<String>();
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

    /*public ArrayList<String> getLockdownInfo() throws SQLException {
        String sql = "SELECT TableInfo, TableStatus, TableColour FROM Booking";
        ResultSet rs = null;
        ArrayList<String> users = new ArrayList<String>();
        try {
            Statement stmt  = connection.createStatement();
            rs   = stmt.executeQuery(sql);
            String status = "";
            while(rs.next()) {
                String lck = "Lockdown";
                status = rs.getString("TableStatus");
                if(lck.equals(status) == true) {
                    users.add(rs.getString("TableInfo"));
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally {
            rs.close();
        }
        return  users;
    }*/

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

    public ArrayList<String> getTableByUsername(String username) throws SQLException {
        String sql = "SELECT Username, TableInfo FROM Booking";
        ResultSet rs = null;
        ArrayList<String> users = new ArrayList<String>();
        try {
            Statement stmt  = connection.createStatement();
            rs   = stmt.executeQuery(sql);
            while(rs.next()) {
                if (username.equals(rs.getString("Username"))) {
                    users.add(rs.getString("TableInfo"));
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally {
            rs.close();
        }
        return users;
    }
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

    public Boolean updateTableStatus(String tableInfo, String status) throws SQLException{
        PreparedStatement preparedStatement = null;
        int resultSet = 0;
        String sql = "UPDATE Booking SET TableStatus = ? "
                + "WHERE TableInfo = ?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, status);
            preparedStatement.setString(2, tableInfo);
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
}
