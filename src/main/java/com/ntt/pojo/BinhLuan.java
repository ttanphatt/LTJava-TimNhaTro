/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntt.pojo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Admins
 */
@Entity
@Table(name = "binh_luan")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BinhLuan.findAll", query = "SELECT b FROM BinhLuan b"),
    @NamedQuery(name = "BinhLuan.findById", query = "SELECT b FROM BinhLuan b WHERE b.id = :id"),
    @NamedQuery(name = "BinhLuan.findByNoiDung", query = "SELECT b FROM BinhLuan b WHERE b.noiDung = :noiDung"),
    @NamedQuery(name = "BinhLuan.findByNgayBinhLuan", query = "SELECT b FROM BinhLuan b WHERE b.ngayBinhLuan = :ngayBinhLuan"),
    @NamedQuery(name = "BinhLuan.findByHoiDap", query = "SELECT b FROM BinhLuan b WHERE b.hoiDap = :hoiDap")})

public class BinhLuan implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 1000)
    @Column(name = "noi_dung")
    private String noiDung;
    @Column(name = "ngay_binh_luan")
    @Temporal(TemporalType.DATE)
    private Date ngayBinhLuan;
    @Column(name = "hoi_dap")
    private Integer hoiDap;
    @JoinColumn(name = "id_bai_viet", referencedColumnName = "id")
    @ManyToOne
    private BaiViet idBaiViet;
    @JoinColumn(name = "id_nguoi_dung", referencedColumnName = "id")
    @ManyToOne
    private NguoiDung idNguoiDung;
     @Transient
    private String tenBaiVietBinhLuan;
    @Transient
    private Integer idBaiVietBinhLuan;
    @Transient
    private String tenNguoiDangBai;

    
    public BinhLuan() {
    }

    public BinhLuan(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public Date getNgayBinhLuan() {
        return ngayBinhLuan;
    }

    public void setNgayBinhLuan(Date ngayBinhLuan) {
        this.ngayBinhLuan = ngayBinhLuan;
    }

    public Integer getHoiDap() {
        return hoiDap;
    }

    public void setHoiDap(Integer hoiDap) {
        this.hoiDap = hoiDap;
    }

    public BaiViet getIdBaiViet() {
        return idBaiViet;
    }

    public void setIdBaiViet(BaiViet idBaiViet) {
        this.idBaiViet = idBaiViet;
    }

    public NguoiDung getIdNguoiDung() {
        return idNguoiDung;
    }

    public void setIdNguoiDung(NguoiDung idNguoiDung) {
        this.idNguoiDung = idNguoiDung;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BinhLuan)) {
            return false;
        }
        BinhLuan other = (BinhLuan) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ntt.pojo.BinhLuan[ id=" + id + " ]";
    }

    /**
     * @return the tenBaiVietBinhLuan
     */
    public String getTenBaiVietBinhLuan() {
        return tenBaiVietBinhLuan;
    }

    /**
     * @param tenBaiVietBinhLuan the tenBaiVietBinhLuan to set
     */
    public void setTenBaiVietBinhLuan(String tenBaiVietBinhLuan) {
        this.tenBaiVietBinhLuan = tenBaiVietBinhLuan;
    }

    /**
     * @return the idBaiVietBinhLuan
     */
    public Integer getIdBaiVietBinhLuan() {
        return idBaiVietBinhLuan;
    }

    /**
     * @param idBaiVietBinhLuan the idBaiVietBinhLuan to set
     */
    public void setIdBaiVietBinhLuan(Integer idBaiVietBinhLuan) {
        this.idBaiVietBinhLuan = idBaiVietBinhLuan;
    }

    /**
     * @return the tenNguoiDangBai
     */
    public String getTenNguoiDangBai() {
        return tenNguoiDangBai;
    }

    /**
     * @param tenNguoiDangBai the tenNguoiDangBai to set
     */
    public void setTenNguoiDangBai(String tenNguoiDangBai) {
        this.tenNguoiDangBai = tenNguoiDangBai;
    }
    
}
