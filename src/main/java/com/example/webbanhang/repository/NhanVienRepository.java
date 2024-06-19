package com.example.webbanhang.repository;

import com.example.webbanhang.model.NhanVien;
import com.example.webbanhang.model.PhongBan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface NhanVienRepository extends JpaRepository<NhanVien, Long> {
    @Query("SELECT p FROM NhanVien p WHERE p.tenNhanVien LIKE %:keyword% OR p.noiSinh LIKE %:keyword%")
    List<NhanVien> findByKeyword(@Param("keyword") String keyword );
}