package main.controller;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;
// getters and setters
public class RegisteredUsers {

    private final SimpleStringProperty name = new SimpleStringProperty();
    private final SimpleStringProperty table = new SimpleStringProperty();
    private final SimpleStringProperty status = new SimpleStringProperty();
    private final SimpleStringProperty dateOfBook = new SimpleStringProperty();
    private final SimpleStringProperty confirmDate = new SimpleStringProperty();
    private final SimpleStringProperty firstName = new SimpleStringProperty();
    private final SimpleStringProperty surname = new SimpleStringProperty();
    private final SimpleStringProperty passWrd = new SimpleStringProperty();
    private final SimpleStringProperty secretQs = new SimpleStringProperty();
    private final SimpleStringProperty secretAs = new SimpleStringProperty();
    private final SimpleIntegerProperty id = new SimpleIntegerProperty();

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getFirstName() {
        return firstName.get();
    }

    public SimpleStringProperty firstNameProperty() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public String getSurname() {
        return surname.get();
    }

    public SimpleStringProperty surnameProperty() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname.set(surname);
    }

    public String getPassWrd() {
        return passWrd.get();
    }

    public SimpleStringProperty passWrdProperty() {
        return passWrd;
    }

    public void setPassWrd(String passWrd) {
        this.passWrd.set(passWrd);
    }

    public String getSecretQs() {
        return secretQs.get();
    }

    public SimpleStringProperty secretQsProperty() {
        return secretQs;
    }

    public void setSecretQs(String secretQs) {
        this.secretQs.set(secretQs);
    }

    public String getSecretAns() {
        return secretAs.get();
    }

    public SimpleStringProperty secretAnsProperty() {
        return secretAs;
    }

    public void setSecretAns(String secretAns) {
        this.secretAs.set(secretAns);
    }

    public String getConfirmDate() {
        return confirmDate.get();
    }

    public SimpleStringProperty confirmDateProperty() {
        return confirmDate;
    }

    public void setConfirmDate(String confirmDate) {
        this.confirmDate.set(confirmDate);
    }

    public String getDateOfBook() {
        return dateOfBook.get();
    }

    public SimpleStringProperty dateOfBookProperty() {
        return dateOfBook;
    }

    public void setDateOfBook(String dateOfBook) {
        this.dateOfBook.set(dateOfBook);
    }
//    private final SimpleStringProperty status = new SimpleStringProperty();

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getTable() {
        return table.get();
    }

    public SimpleStringProperty tableProperty() {
        return table;
    }

    public void setTable(String table) {
        this.table.set(table);
    }

    public String getStatus() {
        return status.get();
    }

    public SimpleStringProperty statusProperty() {
        return status;
    }

    public void setStatus(String status) {
        this.status.set(status);
    }


}
