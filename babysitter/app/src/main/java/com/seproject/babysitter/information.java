package com.seproject.babysitter;

public class information {
    private String username;
    private String email;
    private String password;
    private String friend_request;

    public information(){

    }

    public information(String username, String email, String password, String friend_request) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.friend_request = friend_request;
    }

    public String getFriend_request() { return friend_request; }

    public void setFriend_request(String friend_request) { this.friend_request = friend_request; }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}