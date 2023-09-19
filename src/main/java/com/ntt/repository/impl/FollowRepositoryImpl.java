/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntt.repository.impl;

import com.ntt.pojo.Follow;
import com.ntt.pojo.NguoiDung;
import com.ntt.repository.FollowRepository;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.HibernateException;
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
public class FollowRepositoryImpl implements FollowRepository {

    @Autowired
    private LocalSessionFactoryBean factory;
    @Autowired
    private FollowRepository followRepo;

    @Override
    public boolean addFollow(Follow follow) {
        Session s = this.factory.getObject().getCurrentSession();
        try {
            s.save(follow);
            follow.setTrangThai("1");
            return true;
        } catch (HibernateException ex) {
            System.err.println(ex.getMessage());
        }
        return false;
    }

    @Override
    public List<Follow> getFollows() {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("From Follow");
        return q.getResultList();
    }

    @Override
    public List<Follow> getFollowsKhachHang(NguoiDung idKhachHang) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder builder = s.getCriteriaBuilder();
        CriteriaQuery<Follow> query = builder.createQuery(Follow.class);
        Root root = query.from(Follow.class);
        query = query.where(builder.equal(root.get("idKhachHang"), idKhachHang));
//        query = query.orderBy(builder.desc(root.get("id")));
        Query q = s.createQuery(query);

        return q.getResultList();
    }

    @Override
    public Follow getFollow(NguoiDung idKhachHang) {
        Session s = this.factory.getObject().getCurrentSession();
        org.hibernate.query.Query q = s.createQuery("FROM Follow WHERE idKhachHang= :idKhachHang");
        q.setParameter("idKhachHang", idKhachHang);
        return (Follow) q.getResultList().get(0);
    }

    @Override
    public void setTrangThaiFl(Follow follow) {
        if (follow.getTrangThai() == "0") {
            follow.setTrangThai("1");
        }
        if (follow.getTrangThai() == "1") {
            follow.setTrangThai("0");

        }

    }

    @Override
    public boolean deleteFollow(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        Follow follow = this.followRepo.getFollowById(id);

        try {
            s.delete(follow);
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public Follow getFollowById(int idFollow) {
        Session s = this.factory.getObject().getCurrentSession();
        org.hibernate.query.Query q = s.createQuery("FROM Follow WHERE id= :i");
        q.setParameter("i", idFollow);
        return (Follow) q.getSingleResult();
    }

    @Override
    public List<Follow> getFollowsChuTro(NguoiDung idChuTro) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder builder = s.getCriteriaBuilder();
        CriteriaQuery<Follow> query = builder.createQuery(Follow.class);
        Root root = query.from(Follow.class);
        query = query.where(builder.equal(root.get("idChuTro"), idChuTro));
//        query = query.orderBy(builder.desc(root.get("id")));
        Query q = s.createQuery(query);

        return q.getResultList();
    }

    @Override
    public void deleteFollowByNguoiDung(NguoiDung nguoidung) {
         Session session = factory.getObject().getCurrentSession();
        String hql = "DELETE FROM Follow bl WHERE bl.idChuTro = :nguoidung";
        session.createQuery(hql)
                .setParameter("nguoidung", nguoidung)
                .executeUpdate();
    }

    @Override
    public void deleteFollowByNguoiDungKH(NguoiDung nguoidung) {
        Session session = factory.getObject().getCurrentSession();
        String hql = "DELETE FROM Follow bl WHERE bl.idKhachHang = :nguoidung";
        session.createQuery(hql)
                .setParameter("nguoidung", nguoidung)
                .executeUpdate();
    }

}
