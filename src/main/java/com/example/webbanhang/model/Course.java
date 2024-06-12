package com.example.webbanhang.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotEmpty(message = "Tên giảng viên không được để trống")
    @Size(max = 100, message = "Tên giảng viên không được vượt quá 100 ký tự")
    private String lectureName;

    @NotEmpty(message = "Địa điểm không được để trống")
    private String place;

    @FutureOrPresent(message = "Ngày bắt đầu phải lớn hơn hoặc bằng thời điểm hiện tại")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate startDate;
    private String image;
}
