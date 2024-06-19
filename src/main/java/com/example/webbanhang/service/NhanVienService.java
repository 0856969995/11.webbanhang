package com.example.webbanhang.service;

import com.example.webbanhang.model.NhanVien;
import com.example.webbanhang.repository.NhanVienRepository;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Transactional
public class NhanVienService {
    private final NhanVienRepository nhanvienRepository;
    // Retrieve all products from the database
    public List<NhanVien> getAllNhanViens() {
        return nhanvienRepository.findAll();
    }
    // Retrieve a product by its id
    public Optional<NhanVien> getNhanVienById(Long id) {
        return nhanvienRepository.findById(id);
    }
    // Add a new product to the database
    public NhanVien addNhanVien(NhanVien course) {
        return nhanvienRepository.save(course);
    }
    // Update an existing product
    public NhanVien updateNhanVien(@NotNull @org.jetbrains.annotations.NotNull NhanVien nhanvien) {
        NhanVien existingNhanVien = nhanvienRepository.findById(nhanvien.getId())
                .orElseThrow(() -> new IllegalStateException("Course with ID " +
                        nhanvien.getId() + " does not exist."));
        existingNhanVien.setTenNhanVien(nhanvien.getTenNhanVien());
        existingNhanVien.setNoiSinh(nhanvien.getNoiSinh());
        existingNhanVien.setStartDate(nhanvien.getStartDate());
        existingNhanVien.setImage(nhanvien.getImage());
        existingNhanVien.setPhai(nhanvien.getPhai());
        existingNhanVien.setLuong(nhanvien.getLuong());
        existingNhanVien.setPhongban(nhanvien.getPhongban());

        return nhanvienRepository.save(existingNhanVien);
    }
    // Delete a product by its id
    public void deleteNhanVienById(Long id) {
        if (!nhanvienRepository.existsById(id)) {
            throw new IllegalStateException("Course with ID " + id + " does not exist.");
        }
        nhanvienRepository.deleteById(id);
    }
    public List<NhanVien> searchNhanVien(String keyword) {
        return nhanvienRepository.findByKeyword(keyword);
    }
}
