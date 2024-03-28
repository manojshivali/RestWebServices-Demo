package com.lumen.training.controller;

import com.lumen.training.exception.definition.ResourceNotFound;
import com.lumen.training.request.Course;
import com.lumen.training.service.CourseServiceWithJPA;
import com.lumen.training.service.CourseServiceWithoutJPA;
import com.lumen.training.request.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RestWebServiceController {

    /*
    @Autowired
    CourseServiceWithoutJPA studentService;
*/
   @Autowired
    CourseServiceWithJPA studentService;

    @PostMapping(path = "/createNewCourse", consumes = MediaType.APPLICATION_JSON_VALUE, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<String> createNewCourse(@RequestBody Course course){
        System.out.println("**********controller course"+course);
      return studentService.createNewCourse(course);
    }

    @ResponseBody
    @GetMapping(path = "/getAllCourses")
    public List<Course> getAllStudentsWithResponseBody(){
        return studentService.getAllCourses();
    }

    @GetMapping(path = "/getCourseInformationById", produces ={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Course> getCourseInfomationById(@RequestParam(name = "courseId")   int courseId)  throws ResourceNotFound {
        System.out.println("*******controller  courseid"+ courseId);
        return studentService.getCourseInformationById(courseId);
    }

    @GetMapping(path = "/getCourseInformationByName/{courseName}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Course> getCourseInfomationByStudentName(@PathVariable(name = "courseName")   String courseName)  throws ResourceNotFound {
        return studentService.getcourseInformationByName(courseName);
    }

    @DeleteMapping(path = "/deleteCourseInformation", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<String> deleteCourseInformation(@RequestParam(name = "courseId") int courseId){
        return studentService.deleteCourseInformationById(courseId);
    }

    @PutMapping(path = "/updateCourseInformation", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<String> updateCourseInformation(@RequestParam(name = "CourseId")  int courseId, @RequestBody Course course)throws ResourceNotFound{
        return studentService.updateCourseInformationBasedOnId(courseId, course);
    }
}
