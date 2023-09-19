/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.security;

import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

/**
 *
 * @author ThanhThuyen
 */
public class CustomUser extends User{
     private String kiemDuyet;
    public CustomUser(String username, String password, Collection<? extends GrantedAuthority> authorities, String kiemDuyet) {
        super(username, password, authorities);
        this.kiemDuyet = kiemDuyet;
    }
    public String getKiemDuyet() {
        return kiemDuyet;
    }
}
