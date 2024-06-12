package com.example.webbanhang.controller;
import com.example.webbanhang.model.Course;
import com.example.webbanhang.service.CourseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
@Controller
@RequestMapping("/courses")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @Value("${upload.path}")
    private String uploadPath;
    @GetMapping
    public String showCourseList(Model model) {
        model.addAttribute("courses", courseService.getAllCourses());
        return "courses/course-list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("course", new Course());
        return "courses/add-course";
    }

    @PostMapping("/add")
    public String addCourse(@Valid Course course, BindingResult result,
                             @RequestParam("images") MultipartFile imageFile, Model model) {
        if (result.hasErrors()) {
            return "courses/add-course";
        }
        try {
            if (!imageFile.isEmpty()) {
                String filename = imageFile.getOriginalFilename();
                Path path = Paths.get(uploadPath + filename);
                Files.write(path, imageFile.getBytes());
                course.setImage(filename);
            }
            courseService.addCourse(course);
        } catch (IOException e) {
            e.printStackTrace();
            return "courses/add-course";
        }
        return "redirect:/courses";
    }


    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Course course = courseService.getCourseById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Course Id:" + id));
        model.addAttribute("course",course);
        return "courses/update-course";
    }

    @PostMapping("/update/{id}")
    public String updateCourse(@PathVariable Long id, @Valid Course course,
                                BindingResult result, @RequestParam("images") MultipartFile imageFile, Model model) {
        if (result.hasErrors()) {
            course.setId(id);
            return "courses/update-course";
        }
        try {
            if (!imageFile.isEmpty()) {
                String filename = imageFile.getOriginalFilename();
                Path path = Paths.get(uploadPath + filename);
                Files.write(path, imageFile.getBytes());
                course.setImage(filename);
            }
            courseService.updateCourse(course);
        } catch (IOException e) {
            e.printStackTrace();
            return "courses/update-course";
        }
        return "redirect:/courses";
    }
    @GetMapping("/delete/{id}")
    public String deleteCourse(@PathVariable Long id) {
        courseService.deleteCourseById(id);
        return "redirect:/courses";
    }
    @GetMapping("/search")
    public String searchCourse(@RequestParam("keyword") String keyword, Model model) {
        List<Course> searchResults = new ArrayList<>();
        if (keyword != null && !keyword.trim().isEmpty()) {
            searchResults = courseService.searchCourse(keyword.trim());
        }
        model.addAttribute("courses", searchResults);
        return "courses/course-list";
    }
}
