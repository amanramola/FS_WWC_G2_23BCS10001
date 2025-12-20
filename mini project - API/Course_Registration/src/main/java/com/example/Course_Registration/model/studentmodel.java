package com.example.Course_Registration.model;

class InvalidStudentxception extends Exception{
    public InvalidStudentxception(String message){
        super("Invalid Student data :"+message);
    }
}
public class studentmodel {
    private int id;
    private String name;
    private String course;

    public studentmodel() {}

    public studentmodel(int id, String name, String course) {
        this.id = id;
        this.name = name;
        this.course = course;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    
    public String getCourse() {
        return course;
    }
    public void setCourse(String course) {
        this.course = course;
    }
}
