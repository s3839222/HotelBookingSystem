package main.model;

import javafx.scene.control.ChoiceBox;
import main.SQLConnection;
import org.sqlite.SQLiteConnection;

import java.sql.*;

public class UserDetailModel {
    Connection connection;
    public UserDetailModel(){

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

    public boolean isUser(String username) throws SQLException{
        String sql = "select username, secretQs from Employee";
        System.out.println("in getSecretQs");
        ResultSet rs = null;
        try{
            Statement stmt  = connection.createStatement();
            rs    = stmt.executeQuery(sql);
            if(username.equals(rs.getString("username"))){
                return true;
            }else{
                return false;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally {
            rs.close();
        }
        return false;
    }

    public String getSecretQs(String username) throws SQLException {
        String sql = "select username, secret_question from Employee";
        ResultSet rs = null;
        try {
            Statement stmt  = connection.createStatement();
            rs    = stmt.executeQuery(sql);
            while(rs.next()){
                if(username.equals(rs.getString("username"))){
                    return rs.getString("secret_question");
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally {
            rs.close();
        }
        return null;
    }
    public String getSecretQsByID(String username) throws SQLException {
        String sql = "select id, secret_question from Employee";
        ResultSet rs = null;
        try {
            Statement stmt  = connection.createStatement();
            rs    = stmt.executeQuery(sql);
            while(rs.next()){
                if(username.equals(rs.getString("id"))){
                    return rs.getString("secret_question");
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally {
            rs.close();
        }
        return null;
    }

    public String getRole(String username) throws SQLException {
        String sql = "select id, role from Employee";
        ResultSet rs = null;
        try {
            Statement stmt  = connection.createStatement();
            rs    = stmt.executeQuery(sql);
            while(rs.next()){
                if(username.equals(rs.getString("id"))){
                    return rs.getString("role");
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally {
            rs.close();
        }
        return null;
    }
    public String getFirstName(String username) throws SQLException {
        String sql = "select id, name from Employee";
        ResultSet rs = null;
        try {
            Statement stmt  = connection.createStatement();
            rs    = stmt.executeQuery(sql);
            while(rs.next()){
                if(username.equals(rs.getString("id"))){
                    return rs.getString("name");
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally {
            rs.close();
        }
        return null;
    }

    public String getSurname(String username) throws SQLException {
        String sql = "select id, surname from Employee";
        ResultSet rs = null;
        try {
            Statement stmt  = connection.createStatement();
            rs    = stmt.executeQuery(sql);
            while(rs.next()){
                if(username.equals(rs.getString("id"))){
                    return rs.getString("surname");
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally {
            rs.close();
        }
        return null;
    }

    public String getPassword(String username) throws SQLException {
        String sql = "select id, password from Employee";
        ResultSet rs = null;
        try {
            Statement stmt  = connection.createStatement();
            rs   = stmt.executeQuery(sql);
            while(rs.next()) {
                if (username.equals(rs.getString("id"))) {
                    return rs.getString("password");
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally {
            rs.close();
        }
        return null;
    }

    public String getUsername(String username) throws SQLException {
        String sql = "select id, username from Employee";
        ResultSet rs = null;
        try {
            Statement stmt  = connection.createStatement();
            rs    = stmt.executeQuery(sql);
            while(rs.next()){
                if(username.equals(rs.getString("id"))){
                    return rs.getString("username");
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally {
            rs.close();
        }
        return null;
    }

    public String getSecretAns(String username) throws SQLException {
        String sql = "select id, secret_answer from Employee";
        ResultSet rs = null;
        try {
            Statement stmt  = connection.createStatement();
            rs    = stmt.executeQuery(sql);
            while(rs.next()){
                if(username.equals(rs.getString("id"))){
                    return rs.getString("secret_answer");
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally {
            rs.close();
        }
        return null;
    }

    public String getAge(String username) throws SQLException {
        String sql = "select id, age from Employee";
        ResultSet rs = null;
        try {
            Statement stmt  = connection.createStatement();
            rs    = stmt.executeQuery(sql);
            while(rs.next()){
                if(username.equals(rs.getString("id"))){
                    return rs.getString("age");
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally {
            rs.close();
        }
        return null;
    }

    public boolean checkName(String username) throws SQLException{
        String sql = "select username, password from Employee";
        ResultSet rs = null;
        try {
            Statement stmt  = connection.createStatement();
            rs    = stmt.executeQuery(sql);
            while(rs.next()) {
                if (username.equals(rs.getString("username"))) {
                    return true;
                } else {
                    return false;
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally {
            rs.close();
        }
        return false;
    }
}
