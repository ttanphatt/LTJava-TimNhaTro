/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ntt.repository;

import com.ntt.pojo.NguoiDung;
import java.util.List;
import java.util.Map;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Admins
 */
public interface NguoiDungRepository {
    List<NguoiDung> getTTNguoiDung(String tenNguoiDangNhap);
    NguoiDung getTaiKhoanbyTenTK(String username);
    boolean authUser(String tenTaiKhoan, String matKhau);
    NguoiDung addUser(NguoiDung user);
    Object getNgDungById(int id);
    NguoiDung doiMatKhau(NguoiDung a);
    List<NguoiDung> getNgDungAll();

}
