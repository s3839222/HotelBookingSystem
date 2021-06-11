package main.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import main.model.AccountModel;
import main.model.LoginModel;
import main.model.UserDetailModel;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class EmployeeAccountController implements Initializable {
    public LoginModel loginModel = new LoginModel();
    public AccountModel accountModel = new AccountModel();
    public UserDetailModel usrMOdel = new UserDetailModel();

    @FXML
    private Label isConnected;
    @FXML
    private Label isUsername;
    @FXML
    private Label isName;
    @FXML
    private Label isSurname;
    @FXML
    private Label isPass;
    @FXML
    private Label isSQ;
    @FXML
    private Label isSA;
    @FXML
    private Label isAge;
    @FXML
    private Label isRole;
    @FXML
    private TextField txtFirstname;
    @FXML
    private TextField txtSurname;
    @FXML
    private TextField txtUsername;
    @FXML
    private TextField txtNewPassword;
    @FXML
    private TextField txtAge;
    @FXML
    private TextField txtQuestion;
    @FXML
    private TextField txtAnswer;
    @FXML
    private ComboBox txtNewQuestion;
    @FXML
    private Button updateButton;

    String ID;

    // Check database connection
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        UserHolder holder = UserHolder.getInstance();
        User u = holder.getUser();
        String username = u.getUsername();
        txtUsername.setText(username);
        txtUsername.setEditable(false);




        System.out.println("isUsername:" + username);



        if (accountModel.isDbConnected()){
            isConnected.setText("Connected");
            System.out.println("connected");
        }else{
            isConnected.setText("Not Connected");
            System.out.println("not connected");
        }

    }
    // action to Find employee details
    public void Find(ActionEvent event) throws SQLException {

        ID = usrMOdel.getIDByUser(txtUsername.getText());
        String pass = usrMOdel.getPassword(ID);
        String name = usrMOdel.getFirstName(ID);
        String surname = usrMOdel.getSurname(ID);


        txtNewPassword.setText(pass);
        txtFirstname.setText(name);
        txtSurname.setText(surname);


    }
    // action to update employee details
    public void Manage(ActionEvent event) {
        UserHolder holder = UserHolder.getInstance();
        User u = holder.getUser();
        String uname = u.getUsername();
        txtUsername.setText(uname);
        System.out.println("isUsername:" + uname);
        String firstname =txtFirstname.getText();
        String surname =txtSurname.getText();
        String username =txtUsername.getText();
        String password =txtNewPassword.getText();

        try {
            if(firstname.equals("") || surname.equals("") || username.equals("")|| password.equals("")){
                isConnected.setText("Please fill in all the detail");
            }else {
                if (accountModel.isEmployeeUpdate(firstname, surname, username, password)) {
                    isConnected.setText("Account has been updated");
                } else {
                    isConnected.setText("Please fill in the form to make changes to your account");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
