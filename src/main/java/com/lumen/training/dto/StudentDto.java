package com.lumen.training.dto;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "STUDENTS")
public class StudentDto {

    @Id
    @Column(name = "STUDENT_ID")
    private int studentId;

    @Column(name = "STUDENT_NAME")
    private String studentName;

    @Column(name = "ADDRESS")
    private String address;

    @ManyToOne (fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinColumn( name = "COURSE_ID",  nullable = true)
    private CourseDto courseDto;
}
