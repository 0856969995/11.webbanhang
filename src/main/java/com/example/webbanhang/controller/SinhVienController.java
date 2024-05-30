package com.example.webbanhang.controller;

import com.example.webbanhang.model.SinhVien;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;
import java.util.UUID;

@Controller
public class SinhVienController {
    @Value("${upload.dir}")
    private String uploadDir;
    @GetMapping("/sinhvien")
    public String showForm(Model model) {
        model.addAttribute("sinhVien", new SinhVien());
        return "sinhvien/form-sinhvien";
    }

    @PostMapping("/sinhvien")
    public String submitForm(@Valid SinhVien sinhVien, BindingResult bindingResult,
                             @RequestParam("imageFile") MultipartFile imageFile, Model model) {
        if (bindingResult.hasErrors()) {
            return "sinhvien/form-sinhvien";
        }

        try {
            // Kiểm tra nếu thư mục upload không tồn tại, tạo mới
            Path path = Paths.get(uploadDir);
            if (!Files.exists(path)) {
                Files.createDirectories(path);
            }

            // Lưu ảnh vào thư mục upload
            String fileName = System.currentTimeMillis() + "_" + imageFile.getOriginalFilename();
            Path filePath = Paths.get(uploadDir, fileName);
            Files.write(filePath, imageFile.getBytes());

            // Lưu đường dẫn ảnh vào đối tượng sinhVien
            sinhVien.setImageName("/images/" + fileName);
        } catch (IOException e) {
            // Ghi log lỗi và thông báo cho người dùng
            Logger logger = LoggerFactory.getLogger(this.getClass());
            logger.error("Error uploading file", e);
            model.addAttribute("errorMessage", "Có lỗi xảy ra trong quá trình tải lên tệp. Vui lòng thử lại.");
            return "sinhvien/form-sinhvien";
        }

        model.addAttribute("sinhVien", sinhVien);
        model.addAttribute("message", "Sinh viên đã được thêm thành công!");
        return "sinhvien/result-sinhvien";
    }
}