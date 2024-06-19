package com.example.webbanhang.controller;

import com.example.webbanhang.model.Course;
import com.example.webbanhang.model.NhanVien;
import com.example.webbanhang.service.CourseService;
import com.example.webbanhang.service.NhanVienService;
import com.example.webbanhang.service.PhongBanService;
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
@RequestMapping("/nhanviens")
public class NhanVienController {
    @Autowired
    private NhanVienService nhanvienService;
    @Autowired
    private PhongBanService phongbanService;
    @Value("${upload.path}")
    private String uploadPath;
    @GetMapping
    public String showNhanVienList(Model model) {
        model.addAttribute("nhanviens", nhanvienService.getAllNhanViens());
        return "nhanviens/nhanvien-list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("nhanvien", new NhanVien());
        model.addAttribute("phongbans", phongbanService.getAllPhongBans());
        return "nhanviens/add-nhanvien";
    }

    @PostMapping("/add")
    public String addNhanVien(@Valid NhanVien nhanvien, BindingResult result,
                            @RequestParam("images") MultipartFile imageFile, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("phongbans", phongbanService.getAllPhongBans());

            return "nhanviens/add-nhanvien";
        }
        try {
            if (!imageFile.isEmpty()) {
                String filename = imageFile.getOriginalFilename();
                Path path = Paths.get(uploadPath + filename);
                Files.write(path, imageFile.getBytes());
                nhanvien.setImage(filename);
            }
            nhanvienService.addNhanVien(nhanvien);
        } catch (IOException e) {
            e.printStackTrace();
            model.addAttribute("phongbans", phongbanService.getAllPhongBans());
            return "nhanviens/add-nhanvien";
        }
        return "redirect:/nhanviens";
    }


    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        NhanVien nhanvien = nhanvienService.getNhanVienById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Nhan Vien Id:" + id));
        model.addAttribute("nhanvien",nhanvien);
        model.addAttribute("phongbans", phongbanService.getAllPhongBans());
        return "Nhanviens/update-nhanvien";
    }
    @PostMapping("/update/{id}")
    public String updateNhanVien(@PathVariable Long id, @Valid NhanVien nhanvien,
                               BindingResult result, @RequestParam("images") MultipartFile imageFile, Model model) {
        if (result.hasErrors()) {
            nhanvien.setId(id);
            model.addAttribute("phongbans", phongbanService.getAllPhongBans());

            return "Nhanviens/update-nhanvien";
        }
        try {
            if (!imageFile.isEmpty()) {
                String filename = imageFile.getOriginalFilename();
                Path path = Paths.get(uploadPath + filename);
                Files.write(path, imageFile.getBytes());
                nhanvien.setImage(filename);
            }
            nhanvienService.updateNhanVien(nhanvien);
        } catch (IOException e) {
            e.printStackTrace();
            model.addAttribute("phongbans", phongbanService.getAllPhongBans());
            return "nhanviens/update-nhanvien";
        }
        return "redirect:/nhanviens";
    }
    @GetMapping("/delete/{id}")
    public String deleteNhanVien(@PathVariable Long id) {
        nhanvienService.deleteNhanVienById(id);
        return "redirect:/nhanviens";
    }
    @GetMapping("/search")
    public String searchNhanVien(@RequestParam("keyword") String keyword, Model model) {
        List<NhanVien> searchResults = new ArrayList<>();
        if (keyword != null && !keyword.trim().isEmpty()) {
            searchResults = nhanvienService.searchNhanVien(keyword.trim());
        }
        model.addAttribute("nhanviens", searchResults);
        return "nhanviens/nhanvien-list";
    }
}
