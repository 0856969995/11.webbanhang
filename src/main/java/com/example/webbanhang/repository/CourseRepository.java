package com.example.webbanhang.repository;
import com.example.webbanhang.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {
    @Query("SELECT p FROM Course p WHERE p.lectureName LIKE %:keyword% OR p.place LIKE %:keyword%")
    List<Course> findByKeyword(@Param("keyword") String keyword );
}