package com.example.plant01.postpage;

import java.util.Date;

public class PostItem {
    public  String content;
    public  String contentImg;
    public  String userNick;
    public  String userID;
    public  String board;
    public Date date;
    public  String userImg;
    public int like;

    public PostItem(String content, String contentImg, String userNick, String userID, String board, Date date, int like) {
        this.content = content;
        this.contentImg = contentImg;
        this.userNick = userNick;
        this.userID = userID;
        this.board = board;
        this.date = date;
        this.like = like;
    }

    //불러올때
    public PostItem(String content, String contentImg, String userNick, String board, Date date, int like , String userImg) {
        this.content = content;
        this.contentImg = contentImg;
        this.userNick = userNick;
        this.board = board;
        this.date = date;
        this.userImg = userImg;
        this.like = like;
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
    public String getUserNick() {
        return userNick;
    }
    public void setUserNick(String userNick) {
        this.userNick = userNick;
    }
    public String getUserID() {
        return userID;
    }
    public void setUserID(String userID) {
        this.userID = userID;
    }
    public String getBoard() {
        return board;
    }
    public void setBoard(String board) {
        this.board = board;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public int getLike() {
        return like;
    }
    public void setLike(int like) {
        this.like = like;
    }

    public String getUserImg() {
        return userImg;
    }

    public void setUserImg(String userImg) {
        this.userImg = userImg;
    }
}
