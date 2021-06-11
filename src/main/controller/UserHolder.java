package main.controller;

// User and UserHolder are singletons
public class UserHolder {
    private User user;
    private final static UserHolder INSTANCE = new UserHolder();
    private UserHolder() {}

    public static UserHolder getInstance(){
        return INSTANCE;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public User getUser() {
        System.out.println("in getUser");
        return this.user;
    }
}
