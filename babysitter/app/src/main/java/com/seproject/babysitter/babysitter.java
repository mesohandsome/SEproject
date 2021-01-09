package com.seproject.babysitter;

public class babysitter {
    private String name;
    private String place;
    private String salary;
    private String time;
    private long money;

    public babysitter(){

    }

    public babysitter(String name, String place, String salary, String time, long money) {
        this.name = name;
        this.place = place;
        this.salary = salary;
        this.time = time;
        this.money = money;
    }

    public long getMoney() {
        return money;
    }

    public void setMoney(long money) {
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
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
