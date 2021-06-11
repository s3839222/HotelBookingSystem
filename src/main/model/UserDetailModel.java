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
    // sql query to get the details of an user and see if they are in the database
    public boolean isUser(String username) throws SQLException{
        String sql = "select username, secretQs from Employee";
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
    // sql query to get the seceret question of an user
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
    // sql query to get the seceret question of an user  based of their ID
    public String getSecretQsByID(String id) throws SQLException {
        String sql = "select id, secret_question from Employee";
        ResultSet rs = null;
        try {
            Statement stmt  = connection.createStatement();
            rs    = stmt.executeQuery(sql);
            while(rs.next()){
                if(id.equals(rs.getString("id"))){
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
    // sql query to get the role of an user based of their id
    public String getRole(String id) throws SQLException {
        String sql = "select id, role from Employee";
        ResultSet rs = null;
        try {
            Statement stmt  = connection.createStatement();
            rs    = stmt.executeQuery(sql);
            while(rs.next()){
                if(id.equals(rs.getString("id"))){
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
    // sql query to get the firstname of an user
    public String getFirstName(String id) throws SQLException {
        String sql = "select id, name from Employee";
        ResultSet rs = null;
        try {
            Statement stmt  = connection.createStatement();
            rs    = stmt.executeQuery(sql);
            while(rs.next()){
                if(id.equals(rs.getString("id"))){
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
    // sql query to get the surname of an user
    public String getSurname(String id) throws SQLException {
        String sql = "select id, surname from Employee";
        ResultSet rs = null;
        try {
            Statement stmt  = connection.createStatement();
            rs    = stmt.executeQuery(sql);
            while(rs.next()){
                if(id.equals(rs.getString("id"))){
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
    // sql query to get the password of an user
    public String getPassword(String id) throws SQLException {
        String sql = "select id, password from Employee";
        ResultSet rs = null;
        try {
            Statement stmt  = connection.createStatement();
            rs   = stmt.executeQuery(sql);
            while(rs.next()) {
                if (id.equals(rs.getString("id"))) {
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
    // sql query to get the username of an user
    public String getUsername(String id) throws SQLException {
        String sql = "select id, username from Employee";
        ResultSet rs = null;
        try {
            Statement stmt  = connection.createStatement();
            rs    = stmt.executeQuery(sql);
            while(rs.next()){
                if(id.equals(rs.getString("id"))){
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
    // sql query to get the secret answer of an user
    public String getSecretAns(String id) throws SQLException {
        String sql = "select id, secret_answer from Employee";
        ResultSet rs = null;
        try {
            Statement stmt  = connection.createStatement();
            rs    = stmt.executeQuery(sql);
            while(rs.next()){
                if(id.equals(rs.getString("id"))){
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
    // sql query to get the age of an user
    public String getAge(String id) throws SQLException {
        String sql = "select id, age from Employee";
        ResultSet rs = null;
        try {
            Statement stmt  = connection.createStatement();
            rs    = stmt.executeQuery(sql);
            while(rs.next()){
                if(id.equals(rs.getString("id"))){
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
    // sql query to get the role of an user based of username
    public String getRoleByUser(String username) throws SQLException {
        String sql = "SELECT username, role from Employee";
        ResultSet rs = null;
        try {
            Statement stmt  = connection.createStatement();
            rs    = stmt.executeQuery(sql);
            while(rs.next()){
                if(username.equals(rs.getString("username"))){
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
    // sql query to get the id of an user based of username
    public String getIDByUser(String username) throws SQLException {
        String sql = "select username, id from Employee";
        ResultSet rs = null;
        try {
            Statement stmt  = connection.createStatement();
            rs    = stmt.executeQuery(sql);
            while(rs.next()){
                if(username.equals(rs.getString("username"))){
                    return rs.getString("id"); //id is integer again
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally {
            rs.close();
        }
        return null;
    }
    // sql query to get the username of an user
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
