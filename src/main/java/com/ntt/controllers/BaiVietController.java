/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntt.controllers;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.ntt.pojo.BaiViet;
import com.ntt.pojo.BinhLuan;
import com.ntt.pojo.Follow;
import com.ntt.pojo.NguoiDung;
import com.ntt.pojo.TrangThaiBaiViet;
import com.ntt.service.BaiVietService;
import com.ntt.service.BinhLuanService;
import com.ntt.service.FollowService;
import com.ntt.service.LoaiBaiVietService;
import com.ntt.service.LoaiTrangThaiService;
import com.ntt.service.NguoiDungService;
import com.ntt.service.TaiKhoanService;
import com.ntt.service.impl.LoaiTrangThaiServiceImpl;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.Past;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author ThanhThuyen
 */
@Controller
@Transactional
public class BaiVietController {

    @Autowired
    private BaiVietService baivietService;
    @Autowired
    private LoaiBaiVietService loaiBaiViet;
    @Autowired
    private TaiKhoanService taikhoan;
    @Autowired
    private NguoiDungService ngdungService;
    @Autowired
    private BinhLuanService binhluanService;
    @Autowired
    private FollowService followService;
    @Autowired
    private JavaMailSender emailSender;
    @Autowired
    private LoaiTrangThaiService loaiTrangThaiService;

    @GetMapping("/dangbai")
    public String list(Model model, Authentication authen, @RequestParam Map<String, String> params,
            @RequestParam(name = "errMsg", required = false) String errMsg) {
        model.addAttribute("nguoidung", this.taikhoan.getTaiKhoan(authen.getName()).get(0));
        model.addAttribute("baiviet_role", this.loaiBaiViet.getLoaiBaiViet());
        model.addAttribute("taikhoan", this.taikhoan.getTaiKhoan(authen.getName()).get(0));
        model.addAttribute("baiviet", new BaiViet());
        Date date = new Date();
        model.addAttribute("date", date);
        model.addAttribute("errMsg", errMsg);
        return "dangbai";

    }

    @ModelAttribute
    public void commonAttr(Model model) {
        model.addAttribute("trangThai_role", this.loaiTrangThaiService.getLoaiTrangThai());
    }

    @RequestMapping("/thtin_bviet")
    public String bvietThTin(Model model, @RequestParam Map<String, String> params, Authentication authen) {
        String errMsg = "";

        int id = Integer.parseInt(params.get("baivietId"));

        BaiViet bv = (BaiViet) this.baivietService.getBaiVietById(id);
        if (authen != null) {

            NguoiDung nd = this.taikhoan.getTaiKhoan(authen.getName()).get(0);
            model.addAttribute("taikhoan", this.taikhoan.getTaiKhoan(authen.getName()).get(0));
            model.addAttribute("follows", this.followService.getFollowsKhachHang(nd).get(0));
            model.addAttribute("followlist", this.followService.getFollowsKhachHang(nd));
            model.addAttribute("nguoidung", this.taikhoan.getTaiKhoan(authen.getName()).get(0));
        }
        model.addAttribute("BaiViet", this.baivietService.getBaiVietById(id));
        model.addAttribute("binhluan", new BinhLuan());
        model.addAttribute("binhluans", this.binhluanService.getBinhLuan(id));
        model.addAttribute("flChuTro", this.followService.getFollowsChuTro(bv.getIdNguoiDung()));
        model.addAttribute("follow", new Follow());
        return "thtin_bviet";
    }

    @PostMapping("/thtin_bviet_bl")
    public String handleBinhLuan(@ModelAttribute("binhluan") BinhLuan binhluan,
            Authentication authen,
            @RequestParam Map<String, String> params,
            RedirectAttributes redirectAttributes) {
        String errMsg = "";
        String baivietIdParam = params.get("baivietId");

        // Kiểm tra xem người dùng đã đăng nhập chưa
        if (authen.getName() != null) {
            // Nếu binhluan có id, đây là trường hợp cập nhật bình luận
            if (binhluan.getId() != null) {
                BinhLuan existingBinhLuan = binhluanService.getBinhLuanById(binhluan.getId());
                if (existingBinhLuan != null) {
                    existingBinhLuan.setNoiDung(binhluan.getNoiDung());
                    binhluanService.updateBinhLuan(existingBinhLuan);
                } else {
                    errMsg = "Không tìm thấy bình luận cần cập nhật.";
                }
            } else {
                // Đây là trường hợp thêm bình luận mới
                binhluanService.addBinhLuan(binhluan);
            }

            // Chuyển hướng đến trang hiển thị bài viết hoặc trang khác tùy vào yêu cầu của bạn
            redirectAttributes.addAttribute("baivietId", baivietIdParam);
            return "redirect:/thtin_bviet";
        }

        // Nếu người dùng chưa đăng nhập, bạn có thể xử lý lỗi hoặc điều hướng đến trang đăng nhập
        errMsg = "Bạn cần đăng nhập để thực hiện thao tác này.";
        // Xử lý lỗi hoặc chuyển hướng đến trang đăng nhập tại đây

        return "thtin_bviet";
    }

    @PostMapping("/thtin_bviet_fl")
    public String addFollow(Model model, @ModelAttribute(value = "follow") Follow follow, Authentication authen,
            @RequestParam Map<String, String> params
    ) {
        String ms = "";
        if (authen.getName() != null) {
            if (this.followService.addFollow(follow) == true) {

                return "redirect:/";
            } else {
                ms = "?� c� l?i x�y ra";
            }
        }
        return "index";
    }

    @PostMapping("/thtin_bviet_xn")
    public String xacNhan(Model model, Authentication authen,
            @RequestParam Map<String, String> params
    ) {
        String ms = "";
        int id = Integer.parseInt(params.get("baivietId"));
        BaiViet idBaiViet = (BaiViet) this.baivietService.getBaiVietById(id);
        if (authen != null) {
            if (this.baivietService.updateTrangThai(idBaiViet) == true) {
                return "forward:/thtin_bviet";
            } else {
                ms = "?� c� l?i x�y ra";
            }

        }
        return "index";
    }

    @PostMapping("/dangbai")
    public String add(Model model, @ModelAttribute(value = "baiviet") @Valid BaiViet baiviet,
            @RequestParam Map<String, String> params,
            Authentication authen, BindingResult rs
    ) throws UnsupportedEncodingException {
        String errMsg = "";
        if (baiviet.getLoaiBaiViet().getId() == 1) {
            if (baiviet.getTenBaiViet() == null || baiviet.getTenBaiViet().isEmpty()) {
                errMsg = "Vui lòng nhập tên bài viết";
                return "redirect:/dangbai?errMsg=" + URLEncoder.encode(errMsg, "UTF-8");
            } else if (baiviet.getTenBaiViet().length() < 5) {
                errMsg = "Tên bài viết phải có ít nhất 5 ký tự";
                return "redirect:/dangbai?errMsg=" + URLEncoder.encode(errMsg, "UTF-8");
            }

            if (baiviet.getPhamViCanTim() == null || baiviet.getPhamViCanTim().isEmpty()) {
                errMsg = "Vui lòng nhập pham vi";
                return "redirect:/dangbai?errMsg=" + URLEncoder.encode(errMsg, "UTF-8");
            } else if (baiviet.getPhamViCanTim().length() < 5) {
                errMsg = "Pham vi can tim phải có ít nhất 5 ký tự";
                return "redirect:/dangbai?errMsg=" + URLEncoder.encode(errMsg, "UTF-8");
            }
            if (baiviet.getDienTich() == null || baiviet.getDienTich().isEmpty()) {
                errMsg = "Vui lòng nhap dien tich phong cua ban";
                return "redirect:/dangbai?errMsg=" + URLEncoder.encode(errMsg, "UTF-8");
            } else if (baiviet.getDienTich().length() < 2) {
                errMsg = "Dien tich phải có ít nhất 5 ký tự";
                return "redirect:/dangbai?errMsg=" + URLEncoder.encode(errMsg, "UTF-8");
            }
            if (baiviet.getDiaChiCt() == null || baiviet.getDiaChiCt().isEmpty()) {
                errMsg = "Vui lòng nhap dia chi chi tiet cua ";
                return "redirect:/dangbai?errMsg=" + URLEncoder.encode(errMsg, "UTF-8");
            } else if (baiviet.getDiaChiCt().length() < 10) {
                errMsg = "Dia chi chi tiet phải có ít nhất 5 ký tự";
                return "redirect:/dangbai?errMsg=" + URLEncoder.encode(errMsg, "UTF-8");
            }

        }

        if (baiviet.getLoaiBaiViet().getId() == 2) {
            if (baiviet.getTenBaiViet() == null || baiviet.getTenBaiViet().isEmpty()) {
                errMsg = "Vui lòng nhập tên bài viết";
                return "redirect:/dangbai?errMsg=" + URLEncoder.encode(errMsg, "UTF-8");
            } else if (baiviet.getTenBaiViet().length() < 5) {
                errMsg = "Tên bài viết phải có ít nhất 5 ký tự";
                return "redirect:/dangbai?errMsg=" + URLEncoder.encode(errMsg, "UTF-8");
            }
            if (baiviet.getPhamViCanTim() == null || baiviet.getPhamViCanTim().isEmpty()) {
                errMsg = "Vui lòng nhập phạm vi cần tìm";
                return "redirect:/dangbai?errMsg=" + URLEncoder.encode(errMsg, "UTF-8");
            } else if (baiviet.getPhamViCanTim().length() < 5) {
                errMsg = "Pham vi can tim phải có ít nhất 5 ký tự";
                return "redirect:/dangbai?errMsg=" + URLEncoder.encode(errMsg, "UTF-8");
            }
        }

        if (authen.getName() != null) {

            if (this.baivietService.addBaiViet(baiviet) == true) {

                return "redirect:/";
            } else {
                errMsg = "Đã có lỗi xảy ra";
            }
        }

        return "redirect:/dangbai?errMsg=" + URLEncoder.encode(errMsg, "UTF-8");
    }

    @RequestMapping("/capnhat")
    public String bvietCapNhatThTin(Model model, @RequestParam Map<String, String> params, Authentication authen
    ) {
        int id = Integer.parseInt(params.get("baivietId").toString());
        if (authen != null) {
            model.addAttribute("taikhoan", this.taikhoan.getTaiKhoan(authen.getName()).get(0));
        }
        model.addAttribute("BaiViet", this.baivietService.getBaiVietById(id));
        model.addAttribute("nguoidung", this.taikhoan.getTaiKhoan(authen.getName()).get(0));

        return "capnhat";
    }

    @PostMapping("/capnhat")
    public String update(Model model, @ModelAttribute(value = "baiviet") BaiViet baiviet
    ) {
        String errMsg = "";

        if (this.baivietService.updateBaiViet(baiviet) == true) {
            return "redirect:/canhan";
        } else {
            errMsg = "?� c� l?i x�y ra";
        }

        return "capnhat";
    }

    @PostMapping("/thtin_bviet_tt")
    public String capNhatTrangThai(@RequestParam Map<String, String> params, Authentication authen
    ) {
        int id = Integer.parseInt(params.get("baivietId").toString());
        BaiViet baiviet = (BaiViet) this.baivietService.getBaiVietById(id);
        NguoiDung ndChuTro = this.taikhoan.getTaiKhoanId(baiviet.getIdNguoiDung().getId());
        List<Follow> fls = this.followService.getFollowsChuTro(ndChuTro);
        List<TrangThaiBaiViet> lsts = this.loaiTrangThaiService.getLoaiTrangThaiAll();
        for (TrangThaiBaiViet lst : lsts) {
            if (lst.getId() == 1) {
                baiviet.setLoaiTrangThai(lst);
                baivietService.saveBaiViet(baiviet);
                SimpleMailMessage message = new SimpleMailMessage();
                for (Follow fl : fls) {
                    message.setTo(fl.getIdKhachHang().getEmail());
                    message.setSubject("Có bài viet moi!! vào xem");
                    message.setText(fl.getIdChuTro().getTenNguoiDung() + " da dang bai viet moi");
                    emailSender.send(message);
                }

                return "redirect:/admin";
            }
        }

        return "thtin_bviet";
    }

    @PostMapping("/thtin_bviet_tuchoi")
    public String capNhatTrangThaiTuChoi(@RequestParam Map<String, String> params
    ) {
        int id = Integer.parseInt(params.get("baivietId").toString());
        System.out.println(id);
        BaiViet baiviet = (BaiViet) this.baivietService.getBaiVietById(id);
        System.out.println(baiviet.getTenBaiViet());
        List<TrangThaiBaiViet> lsts = this.loaiTrangThaiService.getLoaiTrangThaiAll();
        for (TrangThaiBaiViet lst : lsts) {
            if (lst.getId() == 3) {
                baiviet.setLoaiTrangThai(lst);
                baivietService.saveBaiViet(baiviet);
                return "redirect:/admin";
            }
        }

        return "thtin_bviet";
    }

}
