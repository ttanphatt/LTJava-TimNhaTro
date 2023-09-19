/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntt.service.impl;

import com.ntt.pojo.ThongBao;
import com.ntt.repository.ThongBaoRepository;
import com.ntt.service.ThongBaoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ThanhThuyen
 */
@Service
public class ThongBaoServiceImpl implements ThongBaoService{
    @Autowired
    private ThongBaoRepository thongbao;
    @Override
    public List<ThongBao> getThongBao() {
       return this.thongbao.getThongBao();
    }
    
    
}
