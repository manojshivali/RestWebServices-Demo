package com.lumen.training.dto;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "COURSE")
public class CourseDto {

    @Id
    @Column(name = "COURSE_ID")
    private int courseId;

    @Column(name = "COURSE_NAME")
    private String courseName;

    @Column(name = "COURSE_TEACHER")
    private String courseTeacher;

    @OneToMany(mappedBy = "courseDto", cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
    private List<StudentDto> studentList;
}
