/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ntt.service;

import com.ntt.pojo.NguoiDung;
import java.util.List;
import java.util.Map;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Admins
 */
public interface NguoiDungService {
    List<NguoiDung> getTTNgDung(String tenNguoiDangNhap);
    NguoiDung getTaiKhoanbyTenTK(String username);
    boolean authUser(String tenTaiKhoan, String matKhau);
    NguoiDung addUser(Map<String, String> params, MultipartFile avatar);
//            , MultipartFile hinhAnh);
    Object getNgDungById(int id);
    NguoiDung doiMatKhau(Map<String, String> params);
    List<NguoiDung> getNgDungAll();

}
