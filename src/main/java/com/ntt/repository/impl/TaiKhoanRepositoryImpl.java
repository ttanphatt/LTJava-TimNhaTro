/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntt.repository.impl;

import com.ntt.pojo.BaiViet;
import com.ntt.pojo.BinhLuan;
import com.ntt.pojo.Follow;
import com.ntt.pojo.LoaiTaiKhoan;
import com.ntt.pojo.NguoiDung;
import com.ntt.pojo.TrangThaiBaiViet;
import com.ntt.repository.BaiVietRepository;
import com.ntt.repository.BinhLuanRepository;
import com.ntt.repository.FollowRepository;
import com.ntt.repository.TaiKhoanRepository;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ThanhThuyen
 */
@Repository
@Transactional
public class TaiKhoanRepositoryImpl implements TaiKhoanRepository {

    @Autowired
    private LocalSessionFactoryBean sessionFactory;
    @Autowired
    private TaiKhoanRepository taiKhoanRepo;
    @Autowired
    private BaiVietRepository baiVietRepo;
    @Autowired
    private FollowRepository followRepo;
    @Autowired
    private BinhLuanRepository binhLuanRepo;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean addTaiKhoan(NguoiDung nguoidung) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        try {
            session.save(nguoidung);
            Follow fl = new Follow();
            fl.setIdKhachHang(nguoidung);
            session.save(fl);
            return true;
        } catch (HibernateException ex) {
            System.err.println(ex.getMessage());
        }
        return false;
    }

    @Override
    public List<NguoiDung> getTaiKhoan(String username) {
        Session s = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = s.getCriteriaBuilder();
        CriteriaQuery<NguoiDung> query = builder.createQuery(NguoiDung.class);
        Root root = query.from(NguoiDung.class);
        query = query.select(root);

        if (!username.isEmpty()) {
            Predicate p = builder.equal(root.get("tenTaiKhoan").as(String.class), username.trim());
            query = query.where(p);
        }

        Query q = s.createQuery(query);
        return q.getResultList();
    }

    @Override
    public LoaiTaiKhoan getLoaiTaiKhoan(String tenLoaiTaiKhoan) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        Query q = session.createQuery("From LoaiTaiKhoan Where tenLoaiTaiKhoan = :tenLoaiTaiKhoan");
        q.setParameter("tenLoaiTaiKhoan", tenLoaiTaiKhoan);
        return (LoaiTaiKhoan) q.getSingleResult();

    }

    @Override
    public NguoiDung getTaiKhoanbyTenTK(String tenTK) {
        Session s = this.sessionFactory.getObject().getCurrentSession();
        Query q = s.createQuery("FROM NguoiDung WHERE tenTaiKhoan=:tenTK");
        q.setParameter("tenTK", tenTK);

        return (NguoiDung) q.getSingleResult();
    }

    @Override
    public NguoiDung getTaiKhoanId(int id) {
        Session s = this.sessionFactory.getObject().getCurrentSession();
        org.hibernate.query.Query q = s.createQuery("FROM NguoiDung WHERE id= :i");
        q.setParameter("i", id);
        return (NguoiDung) q.getSingleResult();
    }

    @Override
    public List<NguoiDung> getTaiKhoansByYear(int year) {
        Session s = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = s.getCriteriaBuilder();
        CriteriaQuery<NguoiDung> query = builder.createQuery(NguoiDung.class);
        Root root = query.from(NguoiDung.class);
        query = query.select(root).where(builder.equal(builder.function("YEAR", Integer.class, root.get("ngayTao")), year));
        Query q = s.createQuery(query);

        return q.getResultList();
    }

    @Override
    public List<NguoiDung> getTaiKhoansByMonth(int year, int month) {
        Session s = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = s.getCriteriaBuilder();
        CriteriaQuery<NguoiDung> query = builder.createQuery(NguoiDung.class);
        Root root = query.from(NguoiDung.class);

        Predicate yearPre = builder.equal(builder.function("YEAR", Integer.class, root.get("ngayTao")), year);
        Predicate monthPre = builder.equal(builder.function("MONTH", Integer.class, root.get("ngayTao")), month);

        query = query.select(root).where(builder.and(yearPre, monthPre));
        Query q = s.createQuery(query);

        return q.getResultList();
    }

    @Override
    public List<NguoiDung> getTaiKhoansByQuarter(int year, int quarter) {
        int startMonth = (quarter - 1) * 3 + 1;
        int endMonth = startMonth + 2;

        LocalDate startLocalDate = LocalDate.of(year, startMonth, 1);
        LocalDate endLocalDate = LocalDate.of(year, endMonth, YearMonth.of(year, endMonth).lengthOfMonth());

        Date startDate = java.sql.Date.valueOf(startLocalDate);
        Date endDate = java.sql.Date.valueOf(endLocalDate);

        Session session = sessionFactory.getObject().getCurrentSession();
        String query = "FROM NguoiDung nd WHERE nd.ngayTao BETWEEN :startDate AND :endDate";
        return session.createQuery(query, NguoiDung.class)
                .setParameter("startDate", startDate)
                .setParameter("endDate", endDate)
                .getResultList();
    }

    @Override
    public boolean deleteTaiKhoan(int idTaiKhoan) {
        Session s = this.sessionFactory.getObject().getCurrentSession();
        NguoiDung nguoiDung = this.taiKhoanRepo.getTaiKhoanId(idTaiKhoan);
        try {
            s.delete(nguoiDung);
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public void deleteBaiVietById(int baiVietId) {
        Session session = sessionFactory.getObject().getCurrentSession();
        String hql = "DELETE FROM NguoiDung nd WHERE nd.baiViet.id = :baiVietId";
        session.createQuery(hql)
                .setParameter("baiVietId", baiVietId)
                .executeUpdate();
    }

    @Override
    public List<NguoiDung> getTaiKhoanAll() {
        Session s = this.sessionFactory.getObject().getCurrentSession();
        Query q = s.createQuery("From NguoiDung");
        return q.getResultList();
    }

    @Override
    public boolean updateTrangThaiTaiKhoan(NguoiDung nguoidung) {
        Session s = this.sessionFactory.getObject().getCurrentSession();
        try {
            nguoidung.setKiemDuyet("KIEM_DUYET_2");
            s.update(nguoidung);
            return true;
        } catch (HibernateException e) {
            System.err.println(e.getMessage());
        }
        return false;
    }

    

    @Override
    public boolean updateTaiKhoan(NguoiDung nguoiDung) {
        Session s = this.sessionFactory.getObject().getCurrentSession();
        try {

            s.update(nguoiDung);
            return true;
        } catch (HibernateException e) {
            System.err.println(e.getMessage());
        }
        return false;
    }


    @Override
    public boolean updateNguoiDung(NguoiDung nguoidung) {
        Session s = this.sessionFactory.getObject().getCurrentSession();
        try {
            s.update(nguoidung);
            return true;
        } catch (HibernateException e) {
            System.err.println(e.getMessage());
        }
        return false;
    }
}
