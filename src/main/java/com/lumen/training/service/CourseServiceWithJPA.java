package com.lumen.training.service;

import com.lumen.training.dto.CourseDto;
import com.lumen.training.dto.StudentDto;
import com.lumen.training.exception.definition.ResourceNotFound;
import com.lumen.training.repository.CourseRepository;
import com.lumen.training.repository.StudentRepository;
import com.lumen.training.request.Course;
import com.lumen.training.request.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceWithJPA {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    CourseRepository courseRepository;

    /**
     * This method will be used to create new courses
     * @param course
     * @return
     */
    public ResponseEntity<String> createNewCourse(Course course){
        CourseDto courseDto = new CourseDto();
        courseDto.setCourseId(course.getCourseId());
        courseDto.setCourseName(course.getCourseName());
        courseDto.setCourseTeacher(course.getCourseTeacher());

        System.out.println("*********course"+ course.getCourseId() +course.getCourseName());

        List<StudentDto> studentDtoList = new ArrayList<>();
        courseDto.setStudentList(studentDtoList);
        if(null != course.getStudentList()){
            for(Student student : course.getStudentList()){
                StudentDto studentDto = new StudentDto();
                studentDto.setStudentId(student.getStudentId());
                studentDto.setStudentName(student.getStudentName());
                studentDto.setAddress(student.getAddress());
                studentDto.setCourseDto(courseDto);
                studentDtoList.add(studentDto);
            }
        }
        System.out.println("*****courseDto" + courseDto.getCourseId() + courseDto.getCourseName());
        courseRepository.save(courseDto);
        return new ResponseEntity<>("New course Added", HttpStatus.CREATED);
    }

    /**
     * this method will be used to get all courses
     * @return List<course>
     */
    public List<Course> getAllCourses(){
        List<Course> courseList = new ArrayList<>();
        List<CourseDto> CourseDtoList =  courseRepository.findAll();
        for(CourseDto courseDto : CourseDtoList){
            Course course = new Course();
            course.setCourseId(courseDto.getCourseId());
            course.setCourseName(courseDto.getCourseName());
            course.setCourseTeacher(courseDto.getCourseTeacher());
            List<Student> studentList = new ArrayList<>();
            System.out.println("test");
            course.setStudentList(studentList);
            if(null != courseDto.getStudentList()){
                for(StudentDto studentDto : courseDto.getStudentList()){
                    Student student = new Student();
                    student.setStudentId(studentDto.getStudentId());
                    student.setStudentName(studentDto.getStudentName());
                    student.setAddress(studentDto.getAddress());
                    studentList.add(student);
                }
            }
            courseList.add(course);
        }

        return courseList;
    }

    /**
     * This method will be used to get  course based on course Name
     * @param courseId
     * @return
     */
    public ResponseEntity<Course> getCourseInformationById(int courseId) throws ResourceNotFound{
        Optional<CourseDto> courseOptional = courseRepository.findById(Long.valueOf(courseId));
        if(courseOptional.isPresent()){
            CourseDto courseDto = courseOptional.get();
            Course course = new Course();
            course.setCourseId(courseDto.getCourseId());
            course.setCourseName(courseDto.getCourseName());
            course.setCourseTeacher(courseDto.getCourseTeacher());
            List<Student> studentList = new ArrayList<>();
            course.setStudentList(studentList);
            if(null != courseDto.getStudentList()){
                for(StudentDto studentDto : courseDto.getStudentList()){
                    Student student = new Student();
                    student.setStudentId(studentDto.getStudentId());
                    student.setStudentName(studentDto.getStudentName());
                    student.setAddress(studentDto.getAddress());
                    studentList.add(student);
                }
            }
            return new ResponseEntity<>(course, HttpStatus.ACCEPTED);
        }else{
            // throwing User Defined Exception
            throw new ResourceNotFound("No Record Found");
        }
    }

    /**
     *This method will be used to get course information by Name
     * @param courseName
     * @return
     * @throws ResourceNotFound
     */
    public ResponseEntity<Course> getcourseInformationByName(String courseName) throws ResourceNotFound{
        Optional<CourseDto> courseOptional = courseRepository.getCourseInfoByCourseName(courseName);
        if(courseOptional.isPresent()){
            CourseDto courseDto = courseOptional.get();
            Course course = new Course();
            course.setCourseId(courseDto.getCourseId());
            course.setCourseName(courseDto.getCourseName());
            course.setCourseTeacher(courseDto.getCourseTeacher());
            List<Student> studentList = new ArrayList<>();
            course.setStudentList(studentList);
            if(null != courseDto.getStudentList()){
                for(StudentDto studentDto : courseDto.getStudentList()){
                    Student student = new Student();
                    student.setStudentId(studentDto.getStudentId());
                    student.setStudentName(studentDto.getStudentName());
                    student.setAddress(studentDto.getAddress());
                    studentList.add(student);
                }
            }
            return new ResponseEntity<>(course, HttpStatus.ACCEPTED);
        }else{
            // throwing User Defined Exception
            throw new ResourceNotFound("No Record Found");
        }
    }

    /**
     * This method will be used to delete course based on course Id
     * @param courseId
     * @return
     */
    public ResponseEntity<String> deleteCourseInformationById(int courseId){
        courseRepository.deleteById(Long.valueOf(courseId));
        return new ResponseEntity<>("Deleted", HttpStatus.ACCEPTED);
    }


    /**
     * This method will be used to update course Information based on course ID
     * @param courseId
     * @param courses
     * @return
     * @throws ResourceNotFound
     */
    public ResponseEntity<String> updateCourseInformationBasedOnId(int courseId, Course courses) throws ResourceNotFound{
        Optional<CourseDto> courseOptional = courseRepository.findById(Long.valueOf(courseId));
        if(courseOptional.isPresent()){
            CourseDto course = courseOptional.get();
            course.setCourseName(courses.getCourseName());
            courseRepository.save(course);
            return new ResponseEntity<>("Record Updated", HttpStatus.ACCEPTED);
        }else{
            // throwing User Defined Exception
            throw new ResourceNotFound("No Record Found");
        }
    }
}
