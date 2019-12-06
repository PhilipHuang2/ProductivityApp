package com.example.productivityapp;

import java.util.Date;

public class Diary implements Comparable<Diary>{
    private String str;
    private int month;
    private int day;
    private int year;


    public Diary(){
        str = "";
        this.day = 0;
        this.year = 1990;

        this.month = 0;
    }


    public Diary(int month, int day, int year, String str){
        this.month = month;
        this.day = day;
        this.year = year;
        this.str = str;
    }

    public int getMonth(){
        return month;
    }

    public int getDay(){
        return day;
    }

    public int getYear(){
        return year;
    }

    public String getDiary(){
        return str;
    }

    public void setMonth(int month){
        this.month = month;
    }

    public void setDay(int day){
        this.day = day;
    }

    public void setYear(int year){
        this.year = year;
    }

    public void setDiary(String str){
        this.str = str;
    }


    public int compareTo(Diary d)
    {
        if (this.year - d.getYear() == 0){
            if (this.month - d.getMonth() == 0){
                return this.day - d.getDay();
            }
            return this.month - d.getMonth();
        }
        return this.year - d.getYear();
    }

}
