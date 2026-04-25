package com.example.nhom3;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    // Bắt cả đường dẫn gốc "/" và "/department"
    @GetMapping({"/", "/department"})
    public String quanLyDanhMuc() {
        return "department";
    }
    
    @GetMapping("/position")
    public String danhSachNhanSu() {
        return "position";
    }
    
    @GetMapping("/employees")
    public String quanLyNhanVien() {
        return "employee";
    }

    @GetMapping("/employee-position")
    public String quanLyPhanCong() {
        return "employee_position";
    }

}