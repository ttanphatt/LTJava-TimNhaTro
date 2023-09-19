/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntt.service.impl;

import com.cloudinary.Cloudinary;
import com.example.security.CustomUser;
import com.cloudinary.utils.ObjectUtils;
import com.ntt.pojo.LoaiTaiKhoan;
import com.ntt.pojo.NguoiDung;
import com.ntt.repository.FollowRepository;
import com.ntt.repository.TaiKhoanRepository;
import com.ntt.service.TaiKhoanService;
import java.io.IOException;
import java.util.Date;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author ThanhThuyen
 */
@Service("taikhoanDetailsService")
public class TaiKhoanServiceImpl implements TaiKhoanService {

    @Autowired
    private Cloudinary cloudinary;
    @Autowired
    private TaiKhoanRepository taikhoanRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private FollowRepository followRepository;

    @Override
    public boolean addTaiKhoan(NguoiDung nguoidung) {

        String pass = nguoidung.getMatKhau();
        nguoidung.setMatKhau(this.passwordEncoder.encode(pass));
        try {
            Map res = this.cloudinary.uploader().upload(nguoidung.getFile().getBytes(),
                    ObjectUtils.asMap("resource_type", "auto"));
            nguoidung.setAvatar(res.get("secure_url").toString());
            Date current = new Date();
            nguoidung.setNgayTao(current);
            if (nguoidung.getIdLoaiTaiKhoan().getId() == 2) {
                Map res2 = this.cloudinary.uploader().upload(nguoidung.getFile().getBytes(),
                        ObjectUtils.asMap("resource_type", "auto"));
                nguoidung.setHinhAnh(res2.get("secure_url").toString());
                nguoidung.setKiemDuyet("KIEM_DUYET_1");
            }
            if (nguoidung.getIdLoaiTaiKhoan().getId() == 3) {
                nguoidung.setKiemDuyet("KIEM_DUYET_2");
                nguoidung.setHinhAnh(null);
            }

        } catch (IOException ex) {
            System.err.println("== ADD BaiViet ==" + ex.getMessage());
        }
//        nguoidung.setLoaiTaiKhoan(NguoiDung.KhachHang);
        return this.taikhoanRepository.addTaiKhoan(nguoidung);
    }

    @Override
    public List<NguoiDung> getTaiKhoan(String username) {
        return this.taikhoanRepository.getTaiKhoan(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<NguoiDung> taikhoans = this.getTaiKhoan(username);

        if (taikhoans.isEmpty()) {
            throw new UsernameNotFoundException("Tai Khoan Khong Ton T?i!!!");
        }
        NguoiDung taikhoan = taikhoans.get(0);

        Set<GrantedAuthority> auth = new HashSet<>();
        auth.add(new SimpleGrantedAuthority(taikhoan.getIdLoaiTaiKhoan().getTenLoaiTaiKhoan()));
        return new CustomUser(taikhoan.getTenTaiKhoan(), taikhoan.getMatKhau(), auth, taikhoan.getKiemDuyet());

    }

    @Override
    public LoaiTaiKhoan getLoaiTaiKhoan(String tenLoaiTaiKhoan) {
        return this.taikhoanRepository.getLoaiTaiKhoan(tenLoaiTaiKhoan);
    }

    @Override
    public NguoiDung getTaiKhoanbyTenTK(String tenTK) {
        return this.taikhoanRepository.getTaiKhoanbyTenTK(tenTK);
    }

    @Override
    public NguoiDung getTaiKhoanId(int id) {
        return this.taikhoanRepository.getTaiKhoanId(id);
    }

    @Override
    public List<NguoiDung> getTaiKhoansByYear(int year) {
        return this.taikhoanRepository.getTaiKhoansByYear(year);
    }

    @Override
    public List<NguoiDung> getTaiKhoansByMonth(int year, int month) {
        return this.taikhoanRepository.getTaiKhoansByMonth(year, month);
    }

    @Override
    public List<NguoiDung> getTaiKhoansByQuarter(int year, int quarter) {
        return this.taikhoanRepository.getTaiKhoansByQuarter(year, quarter);
    }

    @Override
    public boolean deleteTaiKhoan(int idTaiKhoan) {
        return this.taikhoanRepository.deleteTaiKhoan(idTaiKhoan);
    }

    @Override
    public void deleteBaiVietById(int baiVietId) {
        this.taikhoanRepository.deleteBaiVietById(baiVietId);
    }

    @Override
    public List<NguoiDung> getTaiKhoanAll() {
        return this.taikhoanRepository.getTaiKhoanAll();
    }

    @Override
    public boolean updateTrangThaiTaiKhoan(NguoiDung nguoidung) {
        return this.taikhoanRepository.updateTrangThaiTaiKhoan(nguoidung);
    }

//    @Override
//    public boolean updateTaiKhoan(NguoiDung nguoiDung) {
//        Date current = new Date();
//        try {
//            MultipartFile file = nguoiDung.getFile();
//            if (file != null && !file.isEmpty()) {
//                Map res = this.cloudinary.uploader().upload(nguoiDung.getFile().getBytes(),
//                        ObjectUtils.asMap("resource_type", "auto"));
//                nguoiDung.setAvatar(res.get("secure_url").toString());
//            }
//        }
//    }

    public boolean updateTaiKhoan(NguoiDung nguoiDung) {
        return this.taikhoanRepository.updateTaiKhoan(nguoiDung);
    }

    @Override
    public boolean updateNguoiDung(NguoiDung nguoidung) {

        Date current = new Date();
        try {
            MultipartFile file = nguoidung.getFile();
            if (file != null && !file.isEmpty()) {
                Map res = this.cloudinary.uploader().upload(nguoidung.getFile().getBytes(),
                        ObjectUtils.asMap("resource_type", "auto"));
                nguoidung.setAvatar(res.get("secure_url").toString());
            }
        } catch (IOException ex) {
            System.err.println("== UPDATE BaiViet ==" + ex.getMessage());
        }
        nguoidung.setNgayTao(current);
        return this.taikhoanRepository.updateNguoiDung(nguoidung);

    }
}
