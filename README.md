# RestWebServices-Demo
RestWebServices-Demo with JPA and Mapping

Use IntelliJ to use this project.

URL: http://rest-web-services-demo-local-kubeodc-test.corp.intranet/services

http://localhost:8080/rest-web-services-demo-local-kubeodc-test.corp.intranet/services/

http://localhost:8080/services/createNewCourse
http://localhost:8080/services/getAllCourses
http://localhost:8080/services/getCourseInformationById?courseId=1
http://localhost:8080/services/getCourseInformationByName/java
http://localhost:8080/services/updateCourseInformation?CourseId=55
http://localhost:8080/services/deleteCourseInformation?courseId=55

COURSE: COURSE_ID, COURSE_NAME, COURSE_TEACHER

STUDENTS: STUDENT_ID, STUDENT_NAME, ADDRESS, COURSE_ID

Course can have multiple students=> One to Many mapping


{
    "courseId": 1,
    "courseName": "java",
    "courseTeacher": "sid",
    "studentList": [
        {
            "studentId": 3,
            "studentName": "Mike",
            "address": "Pune"
        },
        {
            "studentId": 4,
            "studentName": "Jade",
            "address": "BLR"
        }
    ]
}


# SQL Script

DROP SCHEMA IF EXISTS `student`;

CREATE SCHEMA `student`;

use `student`;

SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `COURSE`;

CREATE TABLE `COURSE` (
  `COURSE_ID` int NOT NULL AUTO_INCREMENT,
  `COURSE_NAME` varchar(128) DEFAULT NULL,
  `COURSE_TEACHER` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`COURSE_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `STUDENTS`;

CREATE TABLE `STUDENTS` (
  `STUDENT_ID` int NOT NULL AUTO_INCREMENT,
  `STUDENT_NAME` varchar(45) DEFAULT NULL,
  `ADDRESS` varchar(45) DEFAULT NULL,
  `COURSE_ID` int DEFAULT NULL,
  PRIMARY KEY (`STUDENT_ID`),
  KEY `FK_DETAIL_idx` (`COURSE_ID`),
  CONSTRAINT `FK_DETAIL` FOREIGN KEY (`COURSE_ID`) 
  REFERENCES `COURSE` (`COURSE_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

SET FOREIGN_KEY_CHECKS = 1;






