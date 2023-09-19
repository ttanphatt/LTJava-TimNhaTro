/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ntt.repository;

import com.ntt.pojo.TrangThaiBaiViet;
import java.util.List;

/**
 *
 * @author ThanhThuyen
 */
public interface LoaiTrangThaiRepository {
    List<TrangThaiBaiViet> getLoaiTrangThai();
    List<TrangThaiBaiViet> getLoaiTrangThaiAll();
}
