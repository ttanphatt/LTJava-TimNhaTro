/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntt.service.impl;

import com.ntt.pojo.Follow;
import com.ntt.pojo.NguoiDung;
import com.ntt.repository.BaiVietRepository;
import com.ntt.repository.FollowRepository;
import com.ntt.repository.TaiKhoanRepository;
import com.ntt.service.FollowService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ThanhThuyen
 */
@Service
public class FollowServiceImpl implements FollowService {

    @Autowired
    private FollowRepository followRepo;
    @Autowired
    private TaiKhoanRepository taikhoanRepo;
    @Autowired
    private BaiVietRepository baivietRepo;

    @Override
    public boolean addFollow(Follow follow) {
//        BaiViet b=(BaiViet) this.baivietRepo.getBaiVietById(follow.getIdChuBaiViet());
        NguoiDung n = this.taikhoanRepo.getTaiKhoanId(follow.getIdChuBaiViet());
        follow.setIdChuTro(n);
        NguoiDung v = this.taikhoanRepo.getTaiKhoan(follow.getTenNguoiDangBai()).get(0);
        follow.setIdKhachHang(v);
        if (follow.getTrangThai() != "1") {
            follow.setTrangThai("0");
        }

        return followRepo.addFollow(follow);
    }

    @Override
    public List<Follow> getFollows() {
        return this.followRepo.getFollows();
    }

    @Override
    public List<Follow> getFollowsKhachHang(NguoiDung idKhachHang) {
        return this.followRepo.getFollowsKhachHang(idKhachHang);
    }

    @Override
    public Follow getFollow(NguoiDung idKhachHang) {
        return this.followRepo.getFollow(idKhachHang);
    }

    @Override
    public void setTrangThaiFl(Follow follow) {
        this.followRepo.setTrangThaiFl(follow);
    }

    @Override
    public boolean deleteFollow(int id) {
        return this.followRepo.deleteFollow(id);
    }

    @Override
    public Follow getFollowById(int idFollow) {
        return this.getFollowById(idFollow);
    }

    @Override
    public List<Follow> getFollowsChuTro(NguoiDung idChuTro) {
        return this.followRepo.getFollowsChuTro(idChuTro);
    }

    @Override
    public void deleteFollowByNguoiDungKH(NguoiDung nguoidung) {
        this.followRepo.deleteFollowByNguoiDungKH(nguoidung);

    }
    @Override
    public void deleteFollowByNguoiDung(NguoiDung nguoidung) {
        this.followRepo.deleteFollowByNguoiDung(nguoidung);
    }
}
