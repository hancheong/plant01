package com.example.plant01.postpage;

public class Post {

    private int profileIcon;
    private int postImage;
    private String username;
    private String date;

    public Post(int profileIcon, int postImage, String username, String date) {
        this.profileIcon = profileIcon;
        this.postImage = postImage;
        this.username = username;
        this.date = date;
    }

    public int getProfileIcon() {
        return profileIcon;
    }

    public void setProfileIcon(int profileIcon) {
        this.profileIcon = profileIcon;
    }

    public int getPostImage() {
        return postImage;
    }

    public void setPostImage(int postImage) {
        this.postImage = postImage;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
