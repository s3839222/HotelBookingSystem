package main.controller;

public class User {
    private String username;
    private Long date;
    private String table;

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public long getDate() {
        return this.date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getTable(){
        return this.table;
    }

    public void setTable(String table){
        this.table = table;
    }
}

