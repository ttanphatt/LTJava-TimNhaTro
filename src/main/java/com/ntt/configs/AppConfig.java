/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntt.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author ThanhThuyen
 */
@Configuration
public class AppConfig {
    @Bean
    public CustomSuccessHandler customSuccessHandler(){
        return new CustomSuccessHandler();
    }
}
