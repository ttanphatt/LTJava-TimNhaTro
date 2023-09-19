/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntt.repository.impl;

import com.ntt.pojo.ThongBao;
import com.ntt.repository.ThongBaoRepository;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ThanhThuyen
 */
@Repository
@Transactional
public class ThongBaoRepositoryImpl implements ThongBaoRepository{
    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<ThongBao> getThongBao() {
        Session s= this.factory.getObject().getCurrentSession();
        Query q=s.createQuery("From ThongBao");
        return q.getResultList();
    }
    
}
