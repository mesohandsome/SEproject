package com.seproject.babysitter;

public class baby {
    private String address, character, diet, gender;
    private String loction, phone, pickup, ps;
    private String salary, time;
    private String old;

    public baby(){

    }

    public baby(String address, String character, String diet, String gender, String loction, String old, String phone, String pickup, String ps, String salary, String time) {
        this.address = address;
        this.character = character;
        this.diet = diet;
        this.gender = gender;
        this.loction = loction;
        this.old = old;
        this.phone = phone;
        this.pickup = pickup;
        this.ps = ps;
        this.salary = salary;
        this.time = time;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public String getDiet() {
        return diet;
    }

    public void setDiet(String diet) {
        this.diet = diet;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getLoction() {
        return loction;
    }

    public void setLoction(String loction) {
        this.loction = loction;
    }

    public String getOld() {
        return old;
    }

    public void setOld(String old) {
        this.old = old;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPickup() {
        return pickup;
    }

    public void setPickup(String pickup) {
        this.pickup = pickup;
    }

    public String getPs() {
        return ps;
    }

    public void setPs(String ps) {
        this.ps = ps;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
