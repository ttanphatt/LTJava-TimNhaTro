/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntt.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.ntt.pojo.BaiViet;
import com.ntt.pojo.LoaiBaiViet;
import com.ntt.pojo.NguoiDung;
import com.ntt.repository.BaiVietRepository;
import com.ntt.repository.LoaiBaiVietRepository;
import com.ntt.repository.LoaiTaiKhoanRepository;
import com.ntt.repository.TaiKhoanRepository;
import com.ntt.service.BaiVietService;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author ThanhThuyen
 */
@Service
public class BaiVietServiceImpl implements BaiVietService {

    @Autowired
    private Cloudinary cloudinary;
    @Autowired
    private BaiVietRepository baivietRepo;
    @Autowired
    private TaiKhoanRepository taikhoan;
    @Autowired
    private LoaiBaiVietRepository loaiBaiViet;
    @Autowired
    private LoaiTaiKhoanRepository LoaiTaiKhoanRepository;

    

    @Override
    public boolean addBaiViet(BaiViet baiviet) {
        NguoiDung b = this.taikhoan.getTaiKhoan(baiviet.getTenNguoiDangBai()).get(0);
        baiviet.setIdNguoiDung(b);
        try {

            if (baiviet.getIdNguoiDung().getIdLoaiTaiKhoan().getId() == 2) {
                if (!baiviet.getFile().isEmpty()) {
                    Map res = this.cloudinary.uploader().upload(baiviet.getFile().getBytes(),
                            ObjectUtils.asMap("resource_type", "auto"));
                    baiviet.setHinhAnh(res.get("secure_url").toString());

                }
                baiviet.setNgayDang(new Date());

            }
            if (baiviet.getIdNguoiDung().getIdLoaiTaiKhoan().getId() == 3) {
                baiviet.setNgayDang(new Date());
            }

        } catch (IOException ex) {
            System.err.println("== ADD BaiViet ==" + ex.getMessage());
        }
        return this.baivietRepo.addBaiViet(baiviet);
    }

    @Override
    public List<BaiViet> getBaiViet2(String tenBaiViet) {
        return this.baivietRepo.getBaiViet2(tenBaiViet);
    }

    @Override
    public BaiViet loadBaiViet(String tenBaiViet) {
        List<BaiViet> baiviets = this.getBaiViet2(tenBaiViet);
        if (baiviets.isEmpty()) {
            throw new UsernameNotFoundException("Bài Viết Không Tồn Tại!!!");
        }
        BaiViet baiviet = baiviets.get(0);

        baiviet.getIdNguoiDung().getTenNguoiDung();

        return new BaiViet();
    }

    @Override
    public List<Object> getBaiVietByType(String loaiBViet) {
        return this.baivietRepo.getBaiVietByType(loaiBViet);
    }

    @Override
    public List<Object> getBaiViet2Type(int loaiBViet) {
        return this.baivietRepo.getBaiViet2Type(loaiBViet);
    }

    @Override
    public List<Object> getBaiVietByIdNgDung(NguoiDung idNgDung) {
        return this.baivietRepo.getBaiVietByIdNgDung(idNgDung);
    }

    @Override
    public boolean updateBaiViet(BaiViet baiviet) {
        if (baiviet.getLoaiBaiViet().getId() == 1) {
            try {
                if (!baiviet.getFile().isEmpty()) {
                    Map res = this.cloudinary.uploader().upload(baiviet.getFile().getBytes(),
                            ObjectUtils.asMap("resource_type", "auto"));
                    baiviet.setHinhAnh(res.get("secure_url").toString());
                }

            } catch (IOException ex) {
                System.err.println("== UPDATE BaiViet ==" + ex.getMessage());
            }

        }
        return this.baivietRepo.updateBaiViet(baiviet);

    }

    @Override
    public BaiViet addBaiVietAPI(Map<String, String> params,
            MultipartFile hinhanh) {
        BaiViet b = new BaiViet();
        b.setTenBaiViet(params.get("ten"));
        b.setNoiDung(params.get("noidung"));
        b.setPhamViCanTim(params.get("phamvi"));
        b.setNgayDang(null);
        b.setSoNguoi(Integer.parseInt(params.get("soluong").toString()));
        b.setGiaThue(Long.parseLong(params.get("giathue").toString()));
        b.setDienTich(params.get("dientich"));
        b.setDiaChiCt(params.get("diachi"));
        int idtk = Integer.parseInt(params.get("userid"));
        b.setIdNguoiDung(taikhoan.getTaiKhoanId(idtk));
        List<LoaiBaiViet> listLBV = this.loaiBaiViet.getLoaiBaiViet();
        int usertypeId = Integer.parseInt(params.get("usertypeId"));
        for (LoaiBaiViet l : listLBV) {
            if (usertypeId == 2 && l.getId() == 1) {
                b.setLoaiBaiViet(l);
            } else if (usertypeId == 3 && l.getId() == 2) {
                b.setLoaiBaiViet(l);
            } else {
                System.out.println("LỖI");
            }
        }
//        b.setHinhAnh(null);

        if (!hinhanh.isEmpty()) {
            try {
                Map res = this.cloudinary.uploader().upload(hinhanh.getBytes(),
                        ObjectUtils.asMap("resource_type", "auto"));
                b.setHinhAnh(res.get("secure_url").toString());
            } catch (IOException ex) {
                System.err.println("== ADD BaiViet ==" + ex.getMessage());
            }
        } else if (hinhanh.isEmpty()) {
            b.setHinhAnh(null);
        }

        this.baivietRepo.addBaiVietAPI(b);
        return b;
    }

    @Override
    public List<BaiViet> getBaiVietByGiaThue(BigDecimal gia) {
        return this.baivietRepo.getBaiVietByGiaThue(gia);
    }

    @Override
    public List<BaiViet> getBaiVietAll() {
        return this.baivietRepo.getBaiVietAll();
    }

    @Override
    public List<BaiViet> getBaiVietGia(Map<String, String> params) {
        return this.baivietRepo.getBaiVietGia(params);
    }

    @Override
    public List<BaiViet> getBaiVietGiaChuaDuyet() {
        return this.baivietRepo.getBaiVietGiaChuaDuyet();
    }

    @Override
    public boolean updateTrangThai(BaiViet idBaiViet) {
        return this.baivietRepo.updateTrangThai(idBaiViet);
    }

    @Override
    public void saveBaiViet(BaiViet baiviet) {
        this.baivietRepo.saveBaiViet(baiviet);
    }

    @Override
    public void deleteBaiVietByNguoiDung(NguoiDung nguoidung) {
        this.baivietRepo.deleteBaiVietByNguoiDung(nguoidung);
    }

    @Override
    public int getCountOfBaiViet() {
        return this.baivietRepo.getCountOfBaiViet();
    }

    @Override
    public BaiViet getBaiVietById(int id) {
        return this.baivietRepo.getBaiVietById(id);
    }

    public boolean deleteBaiViet(int id) {
        return this.baivietRepo.deleteBaiViet(id);
    }

    public List<BaiViet> getBaiViet() {
        return this.baivietRepo.getBaiViet();
    }

    @Override
    public List<BaiViet> getBaiViet(String tenBaiViet) {
        return this.baivietRepo.getBaiViet(tenBaiViet);
    }

    @Override
    public List<BaiViet> getBBByTen(Map<String, String> params) {
        return this.baivietRepo.getBBByTen(params);

    }

    @Override
    public BaiViet updateBaiVietAPI(BaiViet baiviet) {
        return this.baivietRepo.updateBaiVietAPI(baiviet);
    }

//    @Override
//    public List<BaiViet> getBaiVietTK(String address, BigDecimal price, Integer soNguoi) {
//        return this.baivietRepo.getBaiVietTK(address, price, soNguoi);
//    }
    
    @Override
    public List<BaiViet> getBaiVietTK(String address, BigDecimal price, Integer soNguoi, Map<String, String> params) {
        return this.baivietRepo.getBaiVietTK(address, price, soNguoi, params);
    }

    @Override
    public List<BaiViet> getBaiVietDaDuyet() {
        return this.baivietRepo.getBaiVietDaDuyet();
    }
}
