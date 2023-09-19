/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntt.service;

import com.ntt.pojo.LoaiTaiKhoan;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author ThanhThuyen
 */

public interface LoaiTaiKhoanService{
    List<LoaiTaiKhoan> getLoaiTaiKhoan();
}
