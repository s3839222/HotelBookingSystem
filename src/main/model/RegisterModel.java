package main.model;


import main.SQLConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class RegisterModel {
    Connection connection;

    public RegisterModel(){

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
    // sql to query to insert new usrs to the database
    public boolean isRegister(String firstname, String surname, String age, String username, String password, String role, String question, String answer) throws SQLException {
        PreparedStatement preparedStatement = null;
        boolean resultSet = false;
        String query = "insert into employee (name, surname, age, username, password, role, secret_question, secret_answer) values(?,?,?,?,?,?,?,?)";
        try {

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, firstname);
            preparedStatement.setString(2, surname);
            preparedStatement.setString(3, age);
            preparedStatement.setString(4, username);
            preparedStatement.setString(5, password);
            preparedStatement.setString(6, role);
            preparedStatement.setString(7, question);
            preparedStatement.setString(8, answer);

            resultSet = preparedStatement.execute();
            if (resultSet==true) {
                return true;
            }
            else{
                return false;
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
            e.getCause();
            return false;
        }finally {
            preparedStatement.close();
            //resultSet.close();
        }

        //return false;
    }


}
