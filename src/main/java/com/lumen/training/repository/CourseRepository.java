package com.lumen.training.repository;

import com.lumen.training.dto.CourseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<CourseDto, Long> {

    @Query(value = "SELECT u FROM CourseDto u where courseName = :courseName")
    Optional<CourseDto> getCourseInfoByCourseName(@Param("courseName") String courseName);

}
