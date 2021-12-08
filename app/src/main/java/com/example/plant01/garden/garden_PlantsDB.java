package com.example.plant01.garden;


public class garden_PlantsDB {

    private String profile;
    private String Name;
    private String Location;
    private String Date;

    public garden_PlantsDB(){}

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getName() {

        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        this.Location = location;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        this.Date = date;
    }

    public garden_PlantsDB(String name, String location, String date){
        this.Name = name;
        this.Location = location;
        this.Date = date;
    }
}