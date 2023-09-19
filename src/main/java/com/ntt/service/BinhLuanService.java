/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ntt.service;

import com.ntt.pojo.BaiViet;
import com.ntt.pojo.BinhLuan;
import com.ntt.pojo.NguoiDung;
import java.util.List;

/**
 *
 * @author ThanhThuyen
 */
public interface BinhLuanService {
    List<BinhLuan> getBinhLuan(int idBaiViet);
    boolean addBinhLuan(BinhLuan binhluan);
    //PHAT
    List<Object> getBinhLuanByBV(int idBaiViet);
    BinhLuan addOrUpdateBinhLuan(BinhLuan binhluan);

    //THUYEN
    BinhLuan getBinhLuanById(int idBinhLuan);
    boolean deleteBinhLuan(int id);
    boolean updateBinhLuan(BinhLuan binhLuan);
    public void saveBinhLuan(BinhLuan binhLuan);
    List<BinhLuan> getBinhLuanByNguoiDung(NguoiDung idNguoiDung);
    void deleteBinhLuanByBaiViet(BaiViet baiviet);
    void deleteBinhLuanByNguoiDung(NguoiDung nguoidung);
        
    BinhLuan themBinhLuan(BinhLuan binhluan);

}
