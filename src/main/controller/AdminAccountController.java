package main.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import main.SQLConnection;
import main.model.AccountModel;
import main.model.LoginModel;
import main.model.ResetModel;
import main.model.UserDetailModel;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AdminAccountController implements Initializable {
    //public ResetModel resetModel = new ResetModel();
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
    private TextField txtID;
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
    private TextField txtRole;
    @FXML
    private ComboBox txtNewQuestion;
    @FXML
    private ComboBox txtNewRole;
    @FXML
    private Button updateButton;
    @FXML
    private Button isDeleteAcc;

    String ID;

    // Check database connection
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        txtAnswer.setVisible(false);
        txtNewPassword.setVisible(false);
        txtQuestion.setVisible(false);
        txtRole.setVisible(false);
        txtAnswer.setVisible(false);
        txtUsername.setVisible(false);
        txtAge.setVisible(false);
        txtFirstname.setVisible(false);
        txtSurname.setVisible(false);
        updateButton.setVisible(false);
        isDeleteAcc.setVisible(false);
        txtNewQuestion.setVisible(false);
        txtNewRole.setVisible(false);

        isName.setVisible(false);
        isSurname.setVisible(false);
        isPass.setVisible(false);
        isSQ.setVisible(false);
        isSA.setVisible(false);
        isAge.setVisible(false);
        isRole.setVisible(false);
        isUsername.setVisible(false);

        ObservableList<String> questions = FXCollections.observableArrayList(
                "What is your favourite fruit","What is your favourite food",
                "Who is your favourite superhero");
        txtNewQuestion.setItems(questions);
        txtNewQuestion.getSelectionModel().selectFirst();

        ObservableList<String> role = FXCollections.observableArrayList(
                "Employee","Admin");
        txtNewRole.setItems(role);
        txtNewRole.getSelectionModel().selectFirst();

        if (accountModel.isDbConnected()){
            isConnected.setText("Connected");
            System.out.println("connected");
        }else{
            isConnected.setText("Not Connected");
            System.out.println("not connected");
        }

    }
    //Action to find details of a specific registered user
    public void Find(ActionEvent event) throws SQLException {
        ID = txtID.getText();
        String qs = usrMOdel.getSecretQsByID(ID);
        String ans = usrMOdel.getSecretAns(ID);
        String role = usrMOdel.getRole(ID);
        String pass = usrMOdel.getPassword(ID);
        String username = usrMOdel.getUsername(ID);
        String age = usrMOdel.getAge(ID);
        String name = usrMOdel.getFirstName(ID);
        String surname = usrMOdel.getSurname(ID);
        if(ID.equals("")){
            isConnected.setText("Please enter an ID");
        }else {
            System.out.println(qs);
            txtQuestion.setText(qs);
            txtAnswer.setText(ans);
            txtNewPassword.setText(pass);
            txtQuestion.setText(qs);
            txtRole.setText(role);
            txtUsername.setText(username);
            txtAge.setText(age);
            txtFirstname.setText(name);
            txtSurname.setText(surname);
            txtAnswer.setVisible(true);
            txtNewPassword.setVisible(true);
            txtQuestion.setVisible(true);
            txtRole.setVisible(true);
            txtUsername.setVisible(true);
            txtAge.setVisible(true);
            txtFirstname.setVisible(true);
            txtSurname.setVisible(true);
            txtNewQuestion.setVisible(true);
            txtNewRole.setVisible(true);
            updateButton.setVisible(true);
            isDeleteAcc.setVisible(true);
            isName.setVisible(true);
            isSurname.setVisible(true);
            isPass.setVisible(true);
            isSQ.setVisible(true);
            isSA.setVisible(true);
            isAge.setVisible(true);
            isRole.setVisible(true);
            isUsername.setVisible(true);
            txtRole.setEditable(false);
            txtQuestion.setEditable(false);
        }
    }
    // action to manage an account by updating any information that are changed
    public void Manage(ActionEvent event) {
        String firstname =txtFirstname.getText();
        String surname =txtSurname.getText();
        String username =txtUsername.getText();
        String password =txtNewPassword.getText();
        String age =txtAge.getText();
        String role =(String) txtNewRole.getValue();;
        String question =(String) txtNewQuestion.getValue();
        String answer =txtAnswer.getText();
        String id =txtID.getText();
        try {
            if(firstname.equals("") || surname.equals("") || age.equals("") || username.equals("")|| password.equals("")||
                    role.equals("")|| question.equals("")|| answer.equals("")){
                isConnected.setText("Please fill in all the detail");
            }else {
                if (accountModel.isAccountUpdate(firstname, surname,  age, username, password, role, question, answer, id)) {

                    isConnected.setText("Account has been updated");


                } else {
                    isConnected.setText("Please fill in the form to make changes to your account");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    // action to delete an account from the database
    public void Delete(ActionEvent event){
        String id =txtID.getText();
        try {
            if (accountModel.isDelete(id)) {

                isConnected.setText("Account has been delete");


            } else {
                isConnected.setText("Please select ID to delete");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
