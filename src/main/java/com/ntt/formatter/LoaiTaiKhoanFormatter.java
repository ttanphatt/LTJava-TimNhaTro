/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntt.formatter;

import com.ntt.pojo.LoaiTaiKhoan;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

/**
 *
 * @author ThanhThuyen
 */
public class LoaiTaiKhoanFormatter implements Formatter<LoaiTaiKhoan>{

    @Override
    public String print(LoaiTaiKhoan role, Locale locale) {
        return String.valueOf(role.getId());
    }

    @Override
    public LoaiTaiKhoan parse(String text, Locale locale) throws ParseException {
       return new LoaiTaiKhoan(Integer.parseInt(text));
    }
    
}
//
//  @Override
//    public UserRole parse(String text, Locale locale) throws ParseException {
//        return new UserRole(Integer.parseInt(text));
//    }
//
//    @Override
//    public String print(UserRole role, Locale locale) {
//        return  String.valueOf(role.getIdRole());
//    }