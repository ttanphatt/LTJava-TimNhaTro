/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntt.repository.impl;

import com.ntt.pojo.LoaiTaiKhoan;
import com.ntt.repository.LoaiTaiKhoanRepository;
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
public class LoaiTaiKhoanRepositoryImpl implements LoaiTaiKhoanRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<LoaiTaiKhoan> getLoaiTaiKhoan() {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("From LoaiTaiKhoan Where id!=1");

        return q.getResultList();
    }

}
