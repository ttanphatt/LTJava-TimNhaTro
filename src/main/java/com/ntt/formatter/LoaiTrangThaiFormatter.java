/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntt.formatter;

import com.ntt.pojo.LoaiTaiKhoan;
import com.ntt.pojo.TrangThaiBaiViet;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

/**
 *
 * @author ThanhThuyen
 */
public class LoaiTrangThaiFormatter implements Formatter<TrangThaiBaiViet>{

    @Override
    public String print(TrangThaiBaiViet role, Locale locale) {
        return String.valueOf(role.getId());
    }

    @Override
    public TrangThaiBaiViet parse(String text, Locale locale) throws ParseException {
         return new TrangThaiBaiViet(Integer.parseInt(text));
    }
    
}
