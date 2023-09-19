/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntt.repository.impl;

import com.ntt.pojo.BaiViet;
import com.ntt.repository.HinhAnhRepository;
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
public class HinhAnhRepositoryImpl implements HinhAnhRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public void deleteHinhAnhByBaiViet(BaiViet baiViet) {
        Session session = factory.getObject().getCurrentSession();
        String hql = "DELETE FROM HinhAnh bl WHERE bl.idBaiViet = :baiViet";
        session.createQuery(hql)
                .setParameter("baiViet", baiViet)
                .executeUpdate();
    }
}
