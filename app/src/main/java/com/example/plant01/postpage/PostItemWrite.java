package com.example.plant01.postpage;

import java.util.Date;

public class PostItemWrite {

    private String content;
    private String contentImg;
    private String userID;
    private Date postDate;


    public PostItemWrite(){ }

    public PostItemWrite(String content, String contentImg, String userID) {
        this.content = content;
        this.contentImg = contentImg;
        this.userID = userID;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContentImg() {
        return contentImg;
    }

    public void setContentImg(String contentImg) {
        this.contentImg = contentImg;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }
}


