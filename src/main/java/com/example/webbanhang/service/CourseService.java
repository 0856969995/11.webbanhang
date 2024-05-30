package com.example.webbanhang.service;

import com.example.webbanhang.model.Course;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class CourseService {
    private List<Course> listCourses= new  ArrayList<>();
    public void addCourse(Course newProduct) {listCourses.add(newProduct);}
    public List<Course> GetAll() {return listCourses;}
}
