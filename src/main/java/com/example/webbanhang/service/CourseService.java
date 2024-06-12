package com.example.webbanhang.service;
import com.example.webbanhang.model.Course;
import com.example.webbanhang.repository.CourseRepository;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class CourseService {
    private final CourseRepository courseRepository;
    // Retrieve all products from the database
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }
    // Retrieve a product by its id
    public Optional<Course> getCourseById(Long id) {
        return courseRepository.findById(id);
    }
    // Add a new product to the database
    public Course addCourse(Course course) {
        return courseRepository.save(course);
    }
    // Update an existing product
    public Course updateCourse(@NotNull @org.jetbrains.annotations.NotNull Course course) {
        Course existingCourse = courseRepository.findById(course.getId())
                .orElseThrow(() -> new IllegalStateException("Course with ID " +
                        course.getId() + " does not exist."));
        existingCourse.setLectureName(course.getLectureName());
        existingCourse.setPlace(course.getPlace());
        existingCourse.setStartDate(course.getStartDate());
        existingCourse.setImage(course.getImage());
        return courseRepository.save(existingCourse);
    }
    // Delete a product by its id
    public void deleteCourseById(Long id) {
        if (!courseRepository.existsById(id)) {
            throw new IllegalStateException("Course with ID " + id + " does not exist.");
        }
        courseRepository.deleteById(id);
    }
    public List<Course> searchCourse(String keyword) {
        return courseRepository.findByKeyword(keyword);
    }
}