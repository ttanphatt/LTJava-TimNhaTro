/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntt.controllers;

import com.ntt.pojo.NguoiDung;
import com.ntt.service.BaiVietService;
import com.ntt.service.LoaiBaiVietService;
import com.ntt.service.TaiKhoanService;
import java.math.BigDecimal;

import java.util.HashMap;
import java.util.List;

import java.util.Map;
import javax.persistence.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
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
@Transactional
public class IndexContext {

    @Autowired
    private TaiKhoanService taikhoan;
    @Autowired
    private BaiVietService baivietService;
    @Autowired
    private Environment env;

    @RequestMapping("/")
    public String index(Model model, Authentication authen,
            @RequestParam Map<String, String> params,
            @RequestParam(name = "address", required = false) String address,
            @RequestParam(name = "price", required = false) BigDecimal price,
            @RequestParam(name = "soNguoi", required = false) Integer soNguoi) {

        if (authen != null) {
            UserDetails user = this.taikhoan.loadUserByUsername(authen.getName());
            NguoiDung u = this.taikhoan.getTaiKhoanbyTenTK(user.getUsername());
            model.addAttribute("taikhoan", u);
            model.addAttribute("nguoidung", this.taikhoan.getTaiKhoan(authen.getName()).get(0));
        }
        if (params.isEmpty()) {
            model.addAttribute("baiviet", this.baivietService.getBaiVietAll());

        }
        if (!params.isEmpty()) {
            model.addAttribute("baiviet", this.baivietService.getBaiVietTK(address, price, soNguoi, params));
        }

        int count = this.baivietService.getCountOfBaiViet();
        int pageSize = Integer.parseInt(this.env.getProperty("PAGE_SIZE"));
        model.addAttribute("pages", Math.ceil(count * 1.0 / pageSize));
        return "index";

    }

//    @PostMapping("/")
//    public String index(Model model, Authentication authen, @RequestParam("gia") int gia) {
//        BigDecimal giaBigDecimal = new BigDecimal(gia);
//        model.addAttribute("baiviet_1", this.baivietService.getBaiVietByGiaThue(giaBigDecimal));
//        UserDetails user = this.taikhoan.loadUserByUsername(authen.getName());
//        NguoiDung u = this.taikhoan.getTaiKhoanbyTenTK(user.getUsername());
//        model.addAttribute("taikhoan", u);
//        return "index";
//    }
    @RequestMapping("/bando")
    public String bando(Model model, NguoiDung nguoidung, Authentication authen) {

        model.addAttribute("dsBaiViet", this.baivietService.getBaiVietDaDuyet());
        UserDetails user = this.taikhoan.loadUserByUsername(authen.getName());
        NguoiDung u = this.taikhoan.getTaiKhoanbyTenTK(user.getUsername());
        model.addAttribute("taikhoan", u);
        model.addAttribute("nguoidung", this.taikhoan.getTaiKhoan(authen.getName()).get(0));

        return "bando";
    }

}
