package com.example.webbanhang.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "nhanviens")
public class NhanVien {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotEmpty(message = "Tên nhan viên không được để trống")
    @Size(max = 100, message = "Tên nhan viên không được vượt quá 100 ký tự")
    private String tenNhanVien;
    @NotEmpty(message = "Noi sinh không được để trống")
    private String noiSinh;
    @FutureOrPresent(message = "Ngày bắt đầu phải lớn hơn hoặc bằng thời điểm hiện tại")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate startDate;
    private String image;

    @ManyToOne
    @JoinColumn(name = "PhongBan_id")
    private PhongBan phongban;

    @Size(max = 3, message = "phai la nam hoac nu")
    private String phai;
    private int luong;
}
