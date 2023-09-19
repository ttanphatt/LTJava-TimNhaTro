/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntt.controllers;

import com.ntt.pojo.BaiViet;
import com.ntt.pojo.Follow;
import com.ntt.pojo.NguoiDung;
import com.ntt.service.BaiVietService;
import com.ntt.service.BinhLuanService;
import com.ntt.service.FollowService;
import com.ntt.service.TaiKhoanService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author ThanhThuyen
 */
@Controller
public class ThongKeController {

    @Autowired
    private TaiKhoanService taikhoan;
    @Autowired
    private BaiVietService baiviet;
    @Autowired
    private BinhLuanService binhluan;
    @Autowired
    private FollowService follow;
    @Autowired
    private JavaMailSender emailSender;

    @RequestMapping("/admin")
    public String dangNhapAdmin(Model model, Authentication authen) {
        int countChuTro = 0;
        int countKhachHang = 0;
        model.addAttribute("countChuTro", countChuTro);
        model.addAttribute("countKhachHang", countKhachHang);
        UserDetails user = this.taikhoan.loadUserByUsername(authen.getName());
        NguoiDung u = this.taikhoan.getTaiKhoanbyTenTK(user.getUsername());
        model.addAttribute("taikhoan", u);
        return "admin";
    }

    @PostMapping("/admin")
    public String thongkeAdmin(Model model, Authentication authen, @RequestParam("year") int year) {
        List<NguoiDung> ngs = this.taikhoan.getTaiKhoansByYear(year);
        int countChuTro = 0;
        int countKhachHang = 0;
        for (NguoiDung ng : ngs) {
            if (ng.getIdLoaiTaiKhoan().getId() == 2) {
                countChuTro++;
            } else if (ng.getIdLoaiTaiKhoan().getId() == 3) {
                countKhachHang++;
            }

        }

        model.addAttribute("countChuTro", countChuTro);
        model.addAttribute("countKhachHang", countKhachHang);
        model.addAttribute("listNg", ngs);
        return "admin";
    }

    @RequestMapping("/bieudo")
    public String bieuDoAdmin(Model model, Authentication authen) {

        return "bieudo";
    }

    @RequestMapping("/adminmonth")
    public String adminMonth(Model model, Authentication authen) {
        int countChuTro = 0;
        int countKhachHang = 0;
        model.addAttribute("countChuTro", countChuTro);
        model.addAttribute("countKhachHang", countKhachHang);
        UserDetails user = this.taikhoan.loadUserByUsername(authen.getName());
        NguoiDung u = this.taikhoan.getTaiKhoanbyTenTK(user.getUsername());
        model.addAttribute("taikhoan", u);
        return "adminmonth";
    }

    @PostMapping("/adminmonth")
    public String adminMonth(Model model, Authentication authen, @RequestParam("year") int year, @RequestParam("month") int month) {
        List<NguoiDung> ngs = this.taikhoan.getTaiKhoansByMonth(year, month);
        int countChuTro = 0;
        int countKhachHang = 0;
        for (NguoiDung ng : ngs) {
            if (ng.getIdLoaiTaiKhoan().getId() == 2) {
                countChuTro++;
            } else if (ng.getIdLoaiTaiKhoan().getId() == 3) {
                countKhachHang++;
            }

        }
        model.addAttribute("countChuTro", countChuTro);
        model.addAttribute("countKhachHang", countKhachHang);
        model.addAttribute("listNg", ngs);
        return "adminmonth";
    }

    @RequestMapping("/adminquarter")
    public String adminQuater(Model model, Authentication authen) {
        int countChuTro = 0;
        int countKhachHang = 0;
        model.addAttribute("countChuTro", countChuTro);
        model.addAttribute("countKhachHang", countKhachHang);
        UserDetails user = this.taikhoan.loadUserByUsername(authen.getName());
        NguoiDung u = this.taikhoan.getTaiKhoanbyTenTK(user.getUsername());
        model.addAttribute("taikhoan", u);
        return "adminquarter";
    }

    @PostMapping("/adminquarter")
    public String adminQuarter(Model model, Authentication authen, @RequestParam("year") int year, @RequestParam("quarter") int quarter) {
        List<NguoiDung> ngs = this.taikhoan.getTaiKhoansByQuarter(year, quarter);
        int countChuTro = 0;
        int countKhachHang = 0;
        for (NguoiDung ng : ngs) {
            if (ng.getIdLoaiTaiKhoan().getId() == 2) {
                countChuTro++;
            } else if (ng.getIdLoaiTaiKhoan().getId() == 3) {
                countKhachHang++;
            }

        }
        model.addAttribute("countChuTro", countChuTro);
        model.addAttribute("countKhachHang", countKhachHang);
        UserDetails user = this.taikhoan.loadUserByUsername(authen.getName());
        NguoiDung u = this.taikhoan.getTaiKhoanbyTenTK(user.getUsername());
        model.addAttribute("taikhoan", u);
        model.addAttribute("listNg", ngs);
        return "adminquarter";
    }

    @RequestMapping("/adminduyetbai")
    public String adminDuyetBai(Model model, Authentication authen) {
        model.addAttribute("bvChuaDuyet", this.baiviet.getBaiVietAll());
        UserDetails user = this.taikhoan.loadUserByUsername(authen.getName());
        NguoiDung u = this.taikhoan.getTaiKhoanbyTenTK(user.getUsername());
        model.addAttribute("taikhoan", u);
        return "adminduyetbai";
    }

    @RequestMapping("/adminduyettaikhoan")
    public String adminDuyetTaiKhoan(Model model, Authentication authen) {
        model.addAttribute("tkChuaDuyet", this.taikhoan.getTaiKhoanAll());
        UserDetails user = this.taikhoan.loadUserByUsername(authen.getName());
        NguoiDung u = this.taikhoan.getTaiKhoanbyTenTK(user.getUsername());
        model.addAttribute("taikhoan", u);
        return "adminduyettaikhoan";
    }

    @RequestMapping("/thtin_taiKhoan")
    public String thtinTaiKhoan(Model model, Authentication authen, @RequestParam Map<String, String> params) {
        String errMsg = "";
        int id = Integer.parseInt(params.get("taiKhoanId"));
        model.addAttribute("taikhoanduyet", this.taikhoan.getTaiKhoanId(id));
        UserDetails user = this.taikhoan.loadUserByUsername(authen.getName());
        NguoiDung u = this.taikhoan.getTaiKhoanbyTenTK(user.getUsername());
        model.addAttribute("taikhoan", u);
        return "thtin_taiKhoan";
    }

    @PostMapping("/thtin_taiKhoan")
    public String thtinTaiKhoanDuyet(Model model, Authentication authen, @RequestParam Map<String, String> params) {
        String ms = "";
        int id = Integer.parseInt(params.get("taiKhoanId"));
        NguoiDung nguoidung = this.taikhoan.getTaiKhoanId(id);
        if (authen != null) {
            if (this.taikhoan.updateTrangThaiTaiKhoan(nguoidung) == true) {
                SimpleMailMessage message = new SimpleMailMessage();
                message.setTo(nguoidung.getEmail());
                message.setSubject("Tai khoan cua ban da qua kiem duyet");
                message.setText("Ten tai khoan cua ban la: "+nguoidung.getTenTaiKhoan()+"moi dang nhap vao he thong");
                emailSender.send(message);
                return "forward:/adminduyettaikhoan";
            } else {
                ms = "?� c� l?i x�y ra";
            }
        }
        return "index";
    }

    @PostMapping("/thtin_taiKhoanDele")
    public String thtinTaiKhoanDuyetXoa(Model model, Authentication authen, @RequestParam Map<String, String> params) {
        String ms = "";
        int id = Integer.parseInt(params.get("taiKhoanId"));
        NguoiDung nguoidung = this.taikhoan.getTaiKhoanId(id);
        if (authen != null) {
            this.binhluan.deleteBinhLuanByNguoiDung(nguoidung);
            this.follow.deleteFollowByNguoiDung(nguoidung);
            this.baiviet.deleteBaiVietByNguoiDung(nguoidung);
            this.follow.deleteFollowByNguoiDungKH(nguoidung);
            if (this.taikhoan.deleteTaiKhoan(id) == true) {
                return "forward:/adminduyettaikhoan";
            } else {
                ms = "?� c� l?i x�y ra";
            }
        }
        return "index";
    }
}
