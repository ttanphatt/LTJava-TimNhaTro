/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ntt.service;

import com.ntt.pojo.LoaiBaiViet;
import java.util.List;

/**
 *
 * @author ThanhThuyen
 */
public interface LoaiBaiVietService {
    List<LoaiBaiViet> getLoaiBaiViet();
    void deleteBaiVietById(int baiVietId);
}
