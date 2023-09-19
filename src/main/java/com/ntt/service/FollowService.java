/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ntt.service;

import com.ntt.pojo.Follow;
import com.ntt.pojo.NguoiDung;
import java.util.List;

/**
 *
 * @author ThanhThuyen
 */
public interface FollowService {
    boolean addFollow(Follow follow);
    List<Follow> getFollows();
    List<Follow> getFollowsKhachHang(NguoiDung idKhachHang);
    Follow getFollow(NguoiDung idKhachHang);
    void setTrangThaiFl(Follow follow);
    boolean deleteFollow(int id);
    Follow getFollowById(int idFollow);
    List<Follow> getFollowsChuTro(NguoiDung idChuTro);
    void deleteFollowByNguoiDung(NguoiDung nguoidung);
    void deleteFollowByNguoiDungKH(NguoiDung nguoidung);
}
