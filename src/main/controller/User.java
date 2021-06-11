package main.controller;

// User and UserHolder are singletons
public class User {
    private String username;
    private Long date;
    private String table;
    private String status;
    private String whiteList;


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


    public String getStatus(){
        return this.status;
    }
    public void setStatus(String status){
        this.status = status;
    }

    public String getTable(){
        return this.table;
    }

    public void setTable(String table){
        this.table = table;
    }

    public String getWhiteList(){return this.whiteList;}
    public String setWhiteList(){return this.whiteList = whiteList;}
}

