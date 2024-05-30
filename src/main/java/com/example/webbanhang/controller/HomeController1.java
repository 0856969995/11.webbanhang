package com.example.webbanhang.controller;

import org.springframework.ui.Model;
import com.example.webbanhang.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("/home1")
public class HomeController1 {
    @Autowired
    private CourseService courseService;
    @GetMapping("/test")
    public String index() {return "home1";}
    @GetMapping("")
    public String index(Model model) {
        model.addAttribute("listcourse",courseService.GetAll());
        return "home1";
    }
}
