package com.postgrad.base;

import java.util.Date;

public class ProgressUpdate {
    private Date date;
    private String content;

    // Constructor
    public ProgressUpdate(Date date, String content) {
        this.date = date;
        this.content = content;
    }

    // Getters and Setters
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    // toString method
    @Override
    public String toString() {
        return "Progress Update:\n" +
               "-----------------------------\n" +
               "Date    : " + new java.text.SimpleDateFormat("dd MMM, yyyy (EEE)").format(date) + "\n" +
               "Content : " + content + "\n" +
               "-----------------------------";
    }
}