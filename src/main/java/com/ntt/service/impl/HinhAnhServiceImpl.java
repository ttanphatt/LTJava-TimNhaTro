/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntt.service.impl;

import com.ntt.pojo.BaiViet;
import com.ntt.repository.HinhAnhRepository;
import com.ntt.service.HinhAnhService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ThanhThuyen
 */
@Service
public class HinhAnhServiceImpl implements HinhAnhService{
    @Autowired 
    private HinhAnhRepository hinhAnhRepository;
    @Override
    public void deleteHinhAnhByBaiViet(BaiViet baiViet) {
        this.hinhAnhRepository.deleteHinhAnhByBaiViet(baiViet);
    }
}
