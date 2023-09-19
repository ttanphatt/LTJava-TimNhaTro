/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntt.repository.impl;

import com.ntt.pojo.LoaiBaiViet;
import com.ntt.repository.LoaiBaiVietRepository;
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
public class LoaiBaiVietRepositoryImpl implements LoaiBaiVietRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<LoaiBaiViet> getLoaiBaiViet() {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("From LoaiBaiViet");
        return q.getResultList();
    }

    @Override
    public void deleteBaiVietById(int baiVietId) {
        Session session = factory.getObject().getCurrentSession();
        String hql = "DELETE FROM LoaiBaiViet nd WHERE nd.baiViet.id = :baiVietId";
        session.createQuery(hql)
                .setParameter("baiVietId", baiVietId)
                .executeUpdate();
    }
}
