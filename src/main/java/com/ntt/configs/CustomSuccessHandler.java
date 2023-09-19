/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntt.configs;

import java.io.IOException;
import java.util.Collection;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 *
 * @author ThanhThuyen
 */
public class CustomSuccessHandler implements AuthenticationSuccessHandler{
    private RedirectStrategy redirectStrategy= new DefaultRedirectStrategy();
    @Override
    public void onAuthenticationSuccess(HttpServletRequest hsr, HttpServletResponse hsr1, Authentication a) 
            throws IOException, ServletException {
        Collection<? extends GrantedAuthority> authorities=a.getAuthorities();
        
        for(GrantedAuthority authority:authorities){
            if(authority.getAuthority().equals("ROLE_CHUTRO"))
            {
                redirectStrategy.sendRedirect(hsr, hsr1, "/canhan");
                return ;
            } else if(authority.getAuthority().equals("ROLE_ADMIN"))
            {
                redirectStrategy.sendRedirect(hsr, hsr1, "/admin");
                return ;
            }
        }
        redirectStrategy.sendRedirect(hsr, hsr1, "/");
       
    }

   
    
}
