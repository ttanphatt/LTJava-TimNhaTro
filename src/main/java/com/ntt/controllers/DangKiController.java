/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntt.controllers;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.ntt.pojo.NguoiDung;
import com.ntt.service.LoaiTaiKhoanService;
import com.ntt.service.TaiKhoanService;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author ThanhThuyen
 */
@Controller
@Transactional
public class DangKiController {

    @Autowired
    private Cloudinary cloudinary;
    @Autowired
    private TaiKhoanService taikhoanDetailsService;

    @Autowired
    private LoaiTaiKhoanService loaiTaiKhoansv;

    @GetMapping("/dangki")
    public String dangkiView(Model model, @RequestParam(name = "errMsg", required = false) String errMsg) {
        model.addAttribute("user", new NguoiDung());
        model.addAttribute("errMsg", errMsg);
        return "dangki";

    }

    @ModelAttribute
    public void commonAttr(Model model) {
        model.addAttribute("user_role", this.loaiTaiKhoansv.getLoaiTaiKhoan());
    }

    @PostMapping("/dangki")
    public String dangki(Model model, @ModelAttribute(value = "user") NguoiDung nguoidung) throws UnsupportedEncodingException {

        String errMsg = "";
        if (nguoidung.getTenNguoiDung() == null || nguoidung.getTenNguoiDung().isEmpty()) {
            errMsg = "Vui lòng nhập tên nguoi dung";
            return "redirect:/dangki?errMsg=" + URLEncoder.encode(errMsg, "UTF-8");
        }

        if (nguoidung.getEmail() == null || nguoidung.getEmail().isEmpty()) {
            errMsg = "Vui lòng nhập email cua ban";
            return "redirect:/dangki?errMsg=" + URLEncoder.encode(errMsg, "UTF-8");
        } else {
            String emailPattern = "^[A-Za-z0-9+_.-]+@(.+)$";
            if (!Pattern.matches(emailPattern, nguoidung.getEmail())) {
                errMsg = "Email không hợp lệ";
                return "redirect:/dangki?errMsg=" + URLEncoder.encode(errMsg, "UTF-8");
            }
        }
        if (nguoidung.getSdt() == null || nguoidung.getSdt().isEmpty()) {
            errMsg = "Vui lòng nhập tên tài khoản";
            return "redirect:/dangki?errMsg=" + URLEncoder.encode(errMsg, "UTF-8");
        } else if (!nguoidung.getSdt().matches("\\d{10,}")) {
            errMsg = "Số điện thoại phải chứa ít nhất 10 chữ số";
            return "redirect:/dangki?errMsg=" + URLEncoder.encode(errMsg, "UTF-8");
        }
        if (nguoidung.getTenTaiKhoan() == null || nguoidung.getTenTaiKhoan().isEmpty()) {
            errMsg = "Vui lòng nhập tên tài khoản";
            return "redirect:/dangki?errMsg=" + URLEncoder.encode(errMsg, "UTF-8");
        }
        if (nguoidung.getMatKhau() == null || nguoidung.getMatKhau().isEmpty()) {
            errMsg = "Vui lòng nhập mat khau";
            return "redirect:/dangki?errMsg=" + URLEncoder.encode(errMsg, "UTF-8");
        }
        if (nguoidung.getXacNhanMatKhau() == null || nguoidung.getXacNhanMatKhau().isEmpty()) {
            errMsg = "Vui lòng nhập xác nhan mat khau";
            return "redirect:/dangki?errMsg=" + URLEncoder.encode(errMsg, "UTF-8");
        }

        if (nguoidung.getMatKhau().equals(nguoidung.getXacNhanMatKhau())) {
            if (this.taikhoanDetailsService.addTaiKhoan(nguoidung) == true) {

                return "redirect:/dangnhap";
            } else {
                errMsg = "Đã có lỗi xãy ra";
            }
        } else {
            errMsg = "Mật khẩu không khớp";
        }

        return "redirect:/dangki?errMsg=" + URLEncoder.encode(errMsg, "UTF-8");
    }
}
