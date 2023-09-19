/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntt.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Admins
 */
@Entity
@Table(name = "loai_tai_khoan")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LoaiTaiKhoan.findAll", query = "SELECT l FROM LoaiTaiKhoan l"),
    @NamedQuery(name = "LoaiTaiKhoan.findById", query = "SELECT l FROM LoaiTaiKhoan l WHERE l.id = :id"),
    @NamedQuery(name = "LoaiTaiKhoan.findByTenLoaiTaiKhoan", query = "SELECT l FROM LoaiTaiKhoan l WHERE l.tenLoaiTaiKhoan = :tenLoaiTaiKhoan")})
public class LoaiTaiKhoan implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 45)
    @Column(name = "ten_loai_tai_khoan")
    private String tenLoaiTaiKhoan;
    @OneToMany(mappedBy = "idLoaiTaiKhoan")
    @JsonIgnore
    private Set<NguoiDung> nguoiDungSet;

    public LoaiTaiKhoan() {
    }

    public LoaiTaiKhoan(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTenLoaiTaiKhoan() {
        return tenLoaiTaiKhoan;
    }

    public void setTenLoaiTaiKhoan(String tenLoaiTaiKhoan) {
        this.tenLoaiTaiKhoan = tenLoaiTaiKhoan;
    }

    @XmlTransient
    public Set<NguoiDung> getNguoiDungSet() {
        return nguoiDungSet;
    }

    public void setNguoiDungSet(Set<NguoiDung> nguoiDungSet) {
        this.nguoiDungSet = nguoiDungSet;
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
        if (!(object instanceof LoaiTaiKhoan)) {
            return false;
        }
        LoaiTaiKhoan other = (LoaiTaiKhoan) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ntt.pojo.LoaiTaiKhoan[ id=" + id + " ]";
    }
    
}
