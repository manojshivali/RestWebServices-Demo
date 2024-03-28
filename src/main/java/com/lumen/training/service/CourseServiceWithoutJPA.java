package com.lumen.training.service;

import com.lumen.training.dto.CourseDto;
import com.lumen.training.dto.StudentDto;
import com.lumen.training.exception.definition.ResourceNotFound;
import com.lumen.training.request.Course;
import com.lumen.training.request.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceWithoutJPA {

    /*DAY 4 :
        this local variable will be used to store course
        All CRUD Operation will be performed for DAY 4 activity
        */

    private List<Course> courseList = new ArrayList<>();

    /**
     * This method will be used to create new courses
     * @param course
     * @return
     */
    public ResponseEntity<String> createNewCourse(Course course){
        courseList.add(course);
        return new ResponseEntity<>("New course Added", HttpStatus.CREATED);
    }



     /**
      * this method will be used to get all courses
      * @return List<course>
      */

    public List<Course> getAllCourses(){
        return courseList;
    }

    /**
     * This method will be used to get  course based on course Name
     * @param courseId
     * @return
     */

    public ResponseEntity<Course> getCourseInformationById(int courseId) throws ResourceNotFound{
       Optional<Course> courseOptional =  courseList.stream().filter(x -> x.getCourseId() == courseId).findAny();
       if(courseOptional.isPresent()){
           return new ResponseEntity<>(courseOptional.get(), HttpStatus.ACCEPTED);
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
        Optional<Course> courseOptional =  courseList.stream().filter(x -> x.getCourseName().equalsIgnoreCase(courseName)).findAny();
        if(courseOptional.isPresent()){
            return new ResponseEntity<>(courseOptional.get(), HttpStatus.ACCEPTED);
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
        courseList.removeIf( x -> x.getCourseId() == courseId);
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
        Optional<Course> courseOptional =  courseList.stream().filter(x -> x.getCourseId() == courseId).findAny();
        if(courseOptional.isPresent()){
            courseList.removeIf( x -> x.getCourseId() == courseId);
            Course course = courseOptional.get();
            course.setCourseName(courses.getCourseName());
            courseList.add(course);
            return new ResponseEntity<>("Record Updated", HttpStatus.ACCEPTED);
        }else{
            // throwing User Defined Exception
            throw new ResourceNotFound("No Record Found");
        }
    }

}
