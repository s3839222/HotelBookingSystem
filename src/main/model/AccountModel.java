package main.model;

import main.SQLConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AccountModel {

    Connection connection;

    public AccountModel(){

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
    // sql query to update the account informaiton that can be done by the admin only
    public boolean isAccountUpdate(String firstname, String surname, String age, String username, String password, String role, String question, String answer, String id) throws SQLException {
        PreparedStatement preparedStatement = null;
        int resultSet= 0;
        String query = "UPDATE employee SET name =?, surname = ?, age =?, username =?, password = ?, role =?, secret_question =?, secret_answer = ? WHERE id = ?";
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
            preparedStatement.setString(9, id);

            resultSet = preparedStatement.executeUpdate();
            if(resultSet == 1){
              return true;
            }
            else{
                return false;
            }
            //return true;
        } catch (Exception e)
        {
            return false;
        }finally {
            //assert preparedStatement != null;
            preparedStatement.close();
            //resultSet.close();
        }

    }
    // sql query to delete the account and it can be done by the admin only
    public boolean isDelete( String id) throws SQLException {
        PreparedStatement preparedStatement = null;
        int resultSet= 0;
        String query = "DELETE FROM Employee WHERE id = ?";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, id);

            resultSet = preparedStatement.executeUpdate();
            if(resultSet == 1){
                return true;
            }
            else{
                return false;
            }
            //return true;
        } catch (Exception e)
        {
            return false;
        }finally {
            //assert preparedStatement != null;
            preparedStatement.close();
            //resultSet.close();
        }

    }
    // sql query to update the account informaiton that can be done by the employee
    public boolean isEmployeeUpdate(String firstname, String surname, String username, String password) throws SQLException {
        PreparedStatement preparedStatement = null;
        int resultSet= 0;
        String query = "UPDATE employee SET name =?, surname = ?, password = ? WHERE username = ?";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, firstname);
            preparedStatement.setString(2, surname);
            preparedStatement.setString(3, password);
            preparedStatement.setString(4, username);

            resultSet = preparedStatement.executeUpdate();
            if(resultSet == 1){
                return true;
            }
            else{
                return false;
            }
            //return true;
        } catch (Exception e)
        {
            return false;
        }finally {
            //assert preparedStatement != null;
            preparedStatement.close();
            //resultSet.close();
        }

    }


}
