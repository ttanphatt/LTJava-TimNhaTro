/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ntt.service;

import com.ntt.pojo.TrangThaiBaiViet;
import java.util.List;

/**
 * @author Admins
=======
 * @author ThanhThuyen
 */
public interface LoaiTrangThaiService {
     List<TrangThaiBaiViet> getLoaiTrangThai();
     List<TrangThaiBaiViet> getLoaiTrangThaiAll();
}
