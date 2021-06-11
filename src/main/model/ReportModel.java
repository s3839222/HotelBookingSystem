package main.model;

import main.SQLConnection;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;

public class ReportModel {
    Connection connection;

    public ReportModel(){

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
    // sql query to get the details of all the employees that are registered
    public void EmplyoeeReport(String file, String role) throws SQLException, IOException {
            String filename = file;
            String sql = "select id, name, surname, age, username, password, " +
                    "role, secret_question, secret_answer from Employee";
            ResultSet rs = null;
            try {
                Statement stmt = connection.createStatement();
                rs = stmt.executeQuery(sql);
                //new FileWriter(filename)
                BufferedWriter writer = Files.newBufferedWriter(Paths.get(filename));
                writer.write("id, name, surname, age, username, password, role," +
                        " secret_question, secret_answer");

                while (rs.next()) {
                    if(role.equals(rs.getString("role"))) {
                        String line = String.format("\"%d\",%s,%s,%s,%s,%s,%s,%s,%s",
                                rs.getInt("id"), rs.getString("name"),
                                rs.getString("surname"), rs.getString("age"),
                                rs.getString("username"), rs.getString("password"),
                                rs.getString("role"), rs.getString("secret_question"),
                                rs.getString("secret_answer"));
                        writer.newLine();
                        writer.write(line);
                    }
                }
                rs.close();
                writer.close();
            } catch (SQLException | IOException e) {
                System.out.println(e.getMessage());
            }
    }
    // sql query to get the details of all the bookings that are accepted or pending
    public void BookingReport(String file, String status1, String status2){
        String filename = file;
        String sql = "select id, username, TableInfo, TableStatus, BookingDate," +
                " DateOfBooking, BookingConfirmationDate from Booking";
        ResultSet rs = null;
        try {
            Statement stmt = connection.createStatement();
            rs = stmt.executeQuery(sql);
            //new FileWriter(filename)
            BufferedWriter writer = Files.newBufferedWriter(Paths.get(filename));
            writer.write("id, username, TableInfo, TableStatus, BookingDate," +
                    " DateOfBooking, BookingConfirmationDate");
            while (rs.next()) {
                if(status1.equals(rs.getString("TableStatus"))
                        || status2.equals(rs.getString("TableStatus"))) {
                    String cDate = rs.getString("BookingConfirmationDate");
                    if (cDate == null) {
                        cDate = "";
                    }
                    String line = String.format("\"%d\",%s,%s,%s,%s,%s,%s",
                            rs.getInt("id"), rs.getString("username"),
                            rs.getString("TableInfo"), rs.getString("TableStatus"),
                            rs.getString("BookingDate"), rs.getString("DateOfBooking"), cDate);
                    writer.newLine();
                    writer.write(line);
                }
            }
            rs.close();
            writer.close();
        } catch (SQLException | IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
