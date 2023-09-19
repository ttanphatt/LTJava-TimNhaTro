/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntt.service.impl;

import com.ntt.pojo.TrangThaiBaiViet;
import com.ntt.repository.LoaiTrangThaiRepository;
import com.ntt.service.LoaiTrangThaiService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ThanhThuyen
 */
@Service
public class LoaiTrangThaiServiceImpl implements LoaiTrangThaiService{
    @Autowired
    private LoaiTrangThaiRepository loaiTrangThaiRepo;
    @Override
    public List<TrangThaiBaiViet> getLoaiTrangThai() {
        return this.loaiTrangThaiRepo.getLoaiTrangThai();
    }

    @Override
    public List<TrangThaiBaiViet> getLoaiTrangThaiAll() {
        return this.loaiTrangThaiRepo.getLoaiTrangThaiAll();
    }
}
