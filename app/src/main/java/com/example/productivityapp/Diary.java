package com.example.productivityapp;

import java.util.Date;

public class Diary implements Comparable<Diary>{
    private String str;

    private Date date;

    public Diary(){
        str = "";
        date = new Date();
    }

    public Diary(int month, int day, int year, String str){
        date = new Date(year, month, day);
        this.str = str;
    }

    public Date getDate(){
        return date;
    }

    public String getDiary(){
        return str;
    }

    public void setDate(int month, int day, int year){
        date = new Date(year, month, day);
    }

    public void setDiary(String str){
        this.str = str;
    }

    public int compareTo(Diary d)
    {
        return this.date.compareTo( d.getDate() );
    }

}
