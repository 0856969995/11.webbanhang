package com.example.webbanhang.controller;

import com.example.webbanhang.model.PhongBan;
import com.example.webbanhang.service.PhongBanService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
@Controller
@RequiredArgsConstructor
public class PhongBanController {
    @Autowired
    private final PhongBanService phongbanService;

    @GetMapping("/phongbans/add")
    public String showAddForm(Model Model) {
        Model.addAttribute("phongban", new PhongBan());
        return "/phongbans/add-phongban";
    }

    @PostMapping("/phongbans/add")
    public String addCategory(@Valid PhongBan phongban, BindingResult result) {
        if (result.hasErrors()) {
            return "/phongbans/add-phongban";
        }
        phongbanService.addPhongBan(phongban);
        return "redirect:/phongbans";
    }

    // Hiển thị danh sách danh mục
    @GetMapping("/phongbans")
    public String listPhongBans(Model Model) {
        List<PhongBan> phongbans = phongbanService.getAllPhongBans();
        Model.addAttribute("phongbans", phongbans);
        return "/phongbans/phongban-list";
    }

    // GET request to show category edit form
    @GetMapping("/phongbans/edit/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model Model) {
        PhongBan phongban = phongbanService.getPhongBanById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid category Id:"
                        + id));
        Model.addAttribute("phongban", phongban);
        return "/phongbans/update-phongban";
    }
    // POST request to update category
    @PostMapping("/phongbans/update/{id}")
    public String updatePhongBan(@PathVariable("id") Long id, @Valid PhongBan phongban,
                                 BindingResult result, Model Model) {
        if (result.hasErrors()) {
            phongban.setId(id);
            return "/phongbans/update-phongban";
        }
        phongbanService.updatePhongBan(phongban);
        Model.addAttribute("phongbans", phongbanService.getAllPhongBans());
        return "redirect:/phongbans";
    }
    // GET request for deleting category
    @GetMapping("/phongbans/delete/{id}")
    public String deletePhongBan(@PathVariable("id") Long id, Model Model) {
        PhongBan phongban = phongbanService.getPhongBanById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid category Id:"
                        + id));
        phongbanService.deletePhongBanById(id);
        Model.addAttribute("phongbans", phongbanService.getAllPhongBans());
        return "redirect:/phongbans";
    }
}
