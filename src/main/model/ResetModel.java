package main.model;

import main.SQLConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ResetModel {

    Connection connection;

    public ResetModel(){

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
    // sql query to update the password of an employee
    public boolean isReset(String newPassword, String username, String question, String answer) throws SQLException {
        PreparedStatement preparedStatement = null;
        int resultSet= 0;
        String query = "UPDATE employee SET password =? WHERE username = ? AND secret_question =? AND secret_answer =?";
        try {
//
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, newPassword);
            preparedStatement.setString(2, username);
            preparedStatement.setString(3, question);
            preparedStatement.setString(4, answer);

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



}
