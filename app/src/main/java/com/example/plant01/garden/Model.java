package com.example.plant01.garden;

public class Model {

    String id, profileUri , name, location, date;
    public Model(String s){}


    public Model(String id, String profileUri, String name, String location, String date){
        this.id = id;
        this.name = name;
        this.location = location;
        this.date = date;
        this.profileUri = profileUri;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProfileUri() {
        return profileUri;
    }

    public void setProfileUri(String profileUri) {
        this.profileUri = profileUri;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
