package com.lumen.training.request;

import lombok.Data;

import java.util.List;

@Data
public class Course {
    private int courseId;
    private String courseName;
    private String courseTeacher;

    //Enhanced for Day 5 Exercise
    private List<Student> studentList;
}
