/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntt.pojo;

import java.io.Serializable;
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
import javax.persistence.Transient;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Admins
 */
@Entity
@Table(name = "follow")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Follow.findAll", query = "SELECT f FROM Follow f"),
    @NamedQuery(name = "Follow.findById", query = "SELECT f FROM Follow f WHERE f.id = :id"),
    @NamedQuery(name = "Follow.findByTrangThai", query = "SELECT f FROM Follow f WHERE f.trangThai = :trangThai")})
public class Follow implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 45)
    @Column(name = "trang_thai")
    private String trangThai;
    @JoinColumn(name = "id_chu_tro", referencedColumnName = "id")
    @ManyToOne
    private NguoiDung idChuTro;
    @JoinColumn(name = "id_khach_hang", referencedColumnName = "id")
    @ManyToOne
    private NguoiDung idKhachHang;

    @Transient
    private String tenNguoiDangBai;
    @Transient
    private Integer idChuBaiViet;
    
    public Follow() {
    }

    public Follow(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public NguoiDung getIdChuTro() {
        return idChuTro;
    }

    public void setIdChuTro(NguoiDung idChuTro) {
        this.idChuTro = idChuTro;
    }

    public NguoiDung getIdKhachHang() {
        return idKhachHang;
    }

    public void setIdKhachHang(NguoiDung idKhachHang) {
        this.idKhachHang = idKhachHang;
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
        if (!(object instanceof Follow)) {
            return false;
        }
        Follow other = (Follow) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ntt.pojo.Follow[ id=" + id + " ]";
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

    /**
     * @return the idChuBaiViet
     */
    public Integer getIdChuBaiViet() {
        return idChuBaiViet;
    }

    /**
     * @param idChuBaiViet the idChuBaiViet to set
     */
    public void setIdChuBaiViet(Integer idChuBaiViet) {
        this.idChuBaiViet = idChuBaiViet;
    }
    
}
