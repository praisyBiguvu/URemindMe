package com.example.version3_355app.ui.to_do;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TodoModel {
    private int id;
    private String Assignment;
    private int month;
    private int day;
    private int year;
    private Time time;
    private String course;

    @Override
    public String toString() {
        return id+ " " +Assignment + "("+ course + ")"+  "\n" +
                month + "/" + day + "/" + year + "   @ " + time ;
    }

    public Date date(int m, int d, int y) {

        String M = String.valueOf(m);
        String D = String.valueOf(d);
        String Y = String.valueOf(y);
        String df = M.concat("/").concat(D).concat("/").concat(Y);
        Date dfinal = null;
        try {
            dfinal = new SimpleDateFormat("dd/MM/yyyy").parse(df);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dfinal;

    }

    public TodoModel(int id, String Assignment, int month, int day, int year, Time time, String course) {
        this.Assignment = Assignment;
        this.month=month;
        this.day=day;
        this.year=year;
        Date d = date(month, day, year);
        this.time = time;
        this.course = course;

    }

    public String getAssignment() {
        return Assignment;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setAssignment(String assignment) {
        Assignment = assignment;
    }


    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}


