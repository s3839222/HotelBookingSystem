package main.model;

import main.SQLConnection;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
    public void EmplyoeeReport() throws SQLException, IOException {
        try {
            PrintWriter pw = new PrintWriter(new File("C:\\Users\\kuhaf\\Downloads\\EmplyoeeReport.csv"));

            StringBuilder sb = new StringBuilder();

            connection = SQLConnection.connect();

            ResultSet rs = null;

            String query = "select * from Employee";
            PreparedStatement ps = connection.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                sb.append(new Integer(rs.getInt("id")).toString());
                sb.append(",");
                sb.append(rs.getString("name"));
                sb.append(",");
                sb.append(rs.getString("surname"));
                sb.append(",");
                sb.append(new Integer(rs.getInt("age")).toString());
                sb.append(",");
                sb.append(rs.getString("username"));
                sb.append(",");
                sb.append(rs.getString("password"));
                sb.append(",");
                sb.append(rs.getString("role"));
                sb.append(",");
                sb.append(rs.getString("secret_question"));
                sb.append(",");
                sb.append(rs.getString("secret_question"));
                sb.append("\r\n");
            }

            pw.write(sb.toString());
            pw.close();
            System.out.println("finished");

        } catch (Exception e) {

        }
    }
}
