/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntt.repository.impl;

import com.ntt.pojo.Follow;
import com.ntt.pojo.NguoiDung;
import com.ntt.repository.NguoiDungRepository;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Admins
 */
@Repository
@Transactional
public class NguoiDungRepositoryImpl implements NguoiDungRepository {

    @Autowired
    private LocalSessionFactoryBean factory;
    @Autowired
    private BCryptPasswordEncoder passEncoder;

    @Override
    public List<NguoiDung> getTTNguoiDung(String tenNguoiDangNhap) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder builder = s.getCriteriaBuilder();
        CriteriaQuery<NguoiDung> query = builder.createQuery(NguoiDung.class);
        Root root = query.from(NguoiDung.class);
        query = query.select(root);

        if (!tenNguoiDangNhap.isEmpty()) {
            Predicate p = builder.equal(root.get("tenNguoiDangNhap").as(String.class), tenNguoiDangNhap.trim());
            query = query.where(p);
        }

        Query q = s.createQuery(query);
        return q.getResultList();
    }

    @Override
    public boolean authUser(String tenTaiKhoan, String matKhau) {

        NguoiDung u = this.getTaiKhoanbyTenTK(tenTaiKhoan);

        return this.passEncoder.matches(matKhau, u.getMatKhau());
    }

    @Override
    public NguoiDung getTaiKhoanbyTenTK(String username) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("FROM NguoiDung WHERE tenTaiKhoan=:un");
        q.setParameter("un", username);
        return (NguoiDung) q.getSingleResult();
    }

    @Override
    public NguoiDung addUser(NguoiDung user) {
        Session s = this.factory.getObject().getCurrentSession();
        s.save(user);

        return user;
    }

    @Override
    public Object getNgDungById(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        org.hibernate.query.Query q = s.createQuery("FROM NguoiDung WHERE id= :i");
        q.setParameter("i", id);
        return q.getSingleResult();
    }

    @Override
    public NguoiDung doiMatKhau(NguoiDung a) {
        Session s = this.factory.getObject().getCurrentSession();
        s.update(a);
        return a;
    }

    @Override
    public List<NguoiDung> getNgDungAll() {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("From NguoiDung");
        return q.getResultList();
    }

}
