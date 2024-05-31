package com.example.webbanhang.controller;

import org.springframework.ui.Model;
import com.example.webbanhang.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {
    @Autowired
    private CourseService courseService;
    @GetMapping("/test")
    public String index() {return "home";}
    @GetMapping("")
    public String index(Model model) {
        model.addAttribute("listcourse",courseService.GetAll());
        return "home";
    }
}