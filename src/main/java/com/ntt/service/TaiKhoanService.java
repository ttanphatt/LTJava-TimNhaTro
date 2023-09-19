/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ntt.service;

import com.ntt.pojo.LoaiTaiKhoan;
import com.ntt.pojo.NguoiDung;
import java.util.Date;
import java.util.List;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 *
 * @author ThanhThuyen
 */
public interface TaiKhoanService extends UserDetailsService {
    boolean addTaiKhoan(NguoiDung nguoidung);
    List<NguoiDung> getTaiKhoan(String username);
    LoaiTaiKhoan getLoaiTaiKhoan(String tenLoaiTaiKhoan);
    NguoiDung getTaiKhoanbyTenTK(String tenTK);
    NguoiDung getTaiKhoanId(int id);
    //THUYEN
    List<NguoiDung> getTaiKhoansByYear(int year);
    List<NguoiDung> getTaiKhoansByMonth(int year,int month);
    List<NguoiDung> getTaiKhoansByQuarter(int year, int quarter);
    boolean deleteTaiKhoan(int idTaiKhoan);
    void deleteBaiVietById(int baiVietId);
    List<NguoiDung> getTaiKhoanAll();
    boolean updateTrangThaiTaiKhoan(NguoiDung nguoidung);
    boolean updateTaiKhoan(NguoiDung nguoiDung);
    boolean updateNguoiDung(NguoiDung nguoidung);
}
