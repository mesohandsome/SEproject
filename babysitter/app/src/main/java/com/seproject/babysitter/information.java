package com.seproject.babysitter;

public class information {
    private String username;
    private String email;
    private String password;
    private String comment;

    public information(){

    }

    public information(String username, String email, String password, String comment) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.comment = comment;
    }

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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}