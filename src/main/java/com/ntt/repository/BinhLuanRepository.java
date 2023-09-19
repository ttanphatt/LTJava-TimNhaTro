/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ntt.repository;

import com.ntt.pojo.BaiViet;
import com.ntt.pojo.BinhLuan;
import com.ntt.pojo.NguoiDung;
import java.util.List;

/**
 *
 * @author ThanhThuyen
 */
public interface BinhLuanRepository {
    List<BinhLuan> getBinhLuan(int idBaiViet);
    boolean addBinhLuan(BinhLuan binhluan);
    //PHAT
    List<Object> getBinhLuanByBV(int idBaiViet);
    BinhLuan addOrUpdateBinhLuan(BinhLuan binhluan);
    List<Object> getBinhLuanByReply(int reply);
    boolean deleteBinhLuan(int id);

    //THUYEN
    List<BinhLuan> getBinhLuanByNguoiDung(NguoiDung idNguoiDung);
    BinhLuan getBinhLuanById(int idBinhLuan);
    boolean updateBinhLuan(BinhLuan binhLuan);
    void saveBinhLuan(BinhLuan binhLuan);
    void deleteBinhLuanByBaiViet(BaiViet baiviet);
    void deleteBinhLuanByNguoiDung(NguoiDung nguoidung);
    
    BinhLuan themBinhLuan(BinhLuan binhluan);

}
