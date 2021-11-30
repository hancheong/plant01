package com.example.plant01.postpage;

import java.util.ArrayList;
import java.util.Date;

public class Writeinfo {
    private String title;
    private ArrayList<String> contents;
    private String publisher;
    private Date date;



    public Writeinfo(String title, ArrayList<String> contents, String publisher, Date date) {
        this.title = title;
        this.contents = contents;
        this.publisher = publisher;
        this.date = date;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<String> getContents() {
        return this.contents;
    }

    public void setContents(ArrayList<String> contents) {
        this.contents = contents;
    }

    public String getPublisher() {
        return this.publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
