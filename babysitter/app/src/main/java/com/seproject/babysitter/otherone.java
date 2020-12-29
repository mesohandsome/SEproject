package com.seproject.babysitter;

public class otherone {
    String name, ID, email, comment;

    public otherone(String name, String ID, String email, String comment) {
        this.name = name;
        this.ID = ID;
        this.email = email;
        this.comment = comment;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
