/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntt.service.impl;

import com.ntt.pojo.LoaiBaiViet;
import com.ntt.repository.LoaiBaiVietRepository;
import com.ntt.service.LoaiBaiVietService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ThanhThuyen
 */
@Service
public class LoaiBaiVietServiceImpl implements LoaiBaiVietService{
    @Autowired
    private LoaiBaiVietRepository loaiBaiViet;
    @Override
    public List<LoaiBaiViet> getLoaiBaiViet() {
       return this.loaiBaiViet.getLoaiBaiViet();
    }

    @Override
    public void deleteBaiVietById(int baiVietId) {
        this.loaiBaiViet.deleteBaiVietById(baiVietId);
    }
    
}
