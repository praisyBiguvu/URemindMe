package com.example.version3_355app.ui.to_do;

import java.sql.Time;
import java.util.Date;

public class TodoModel {
    private String Assignment;
    private Date date;
    private Time time;
    private String course;

    public TodoModel(String Assignment, Date date, Time time, String course){
        this.Assignment= Assignment;
        this.date=date;
        this.time=time;
        this.course=course;
    }

    @Override
    public String toString() {
        return "TodoModel{" +
                "Assignment='" + Assignment + '\'' +
                ", date=" + date +
                ", time=" + time +
                ", course='" + course + '\'' +
                '}';
    }

    public String getAssignment() {
        return Assignment;
    }

    public void setAssignment(String assignment) {
        Assignment = assignment;
    }

    public Date getDate() {
        
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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
}

