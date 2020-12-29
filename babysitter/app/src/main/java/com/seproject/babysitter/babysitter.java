package com.seproject.babysitter;

public class babysitter {
    private String UID, age, environment, experience, name;
    private String notice, number, salary, sexual, time;

    public babysitter(){

    }

    public babysitter(String UID, String age, String environment, String experience, String name, String notice, String number, String salary, String sexual, String time) {
        this.UID = UID;
        this.age = age;
        this.environment = environment;
        this.experience = experience;
        this.name = name;
        this.notice = notice;
        this.number = number;
        this.salary = salary;
        this.sexual = sexual;
        this.time = time;
    }

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getSexual() {
        return sexual;
    }

    public void setSexual(String sexual) {
        this.sexual = sexual;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
