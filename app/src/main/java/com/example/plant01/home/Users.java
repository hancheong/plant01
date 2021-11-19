package com.example.plant01.home;

public class Users {

    public String firstName;
    public String lastName;
    String userImg;
    public long age;

    public Users() {
    }

    public Users(String firstName, String lastName, long age, String userImg) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.userImg = userImg;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public long getAge() {
        return age;
    }

    public void setAge(long age) {
        this.age = age;
    }

    public String getUserImg() {
        return userImg;
    }

    public void setUserImg(String userImg) {
        this.userImg = userImg;
    }
}
