/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntt.service.impl;

import com.ntt.pojo.LoaiTaiKhoan;
import com.ntt.repository.LoaiTaiKhoanRepository;
import com.ntt.service.LoaiTaiKhoanService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ThanhThuyen
 */
@Service
public class LoaiTaiKhoanServiceImpl implements LoaiTaiKhoanService{

    @Autowired
    private LoaiTaiKhoanRepository loaiTaiKhoanRepository;
    
    @Override
    public List<LoaiTaiKhoan> getLoaiTaiKhoan() {
        return this.loaiTaiKhoanRepository.getLoaiTaiKhoan();
    }
}
