/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntt.formatter;

import com.ntt.pojo.LoaiBaiViet;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

/**
 *
 * @author ThanhThuyen
 */
public class LoaiBaiVietFormatter implements Formatter<LoaiBaiViet>{

    @Override
    public String print(LoaiBaiViet role, Locale locale) {
       return String.valueOf(role.getId());
    }

    @Override
    public LoaiBaiViet parse(String text, Locale locale) throws ParseException {
       return new LoaiBaiViet(Integer.parseInt(text));
    }
    
}
