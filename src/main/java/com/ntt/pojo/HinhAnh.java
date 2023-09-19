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
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Admins
 */
@Entity
@Table(name = "hinh_anh")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HinhAnh.findAll", query = "SELECT h FROM HinhAnh h"),
    @NamedQuery(name = "HinhAnh.findById", query = "SELECT h FROM HinhAnh h WHERE h.id = :id"),
    @NamedQuery(name = "HinhAnh.findByDuongDan", query = "SELECT h FROM HinhAnh h WHERE h.duongDan = :duongDan")})
public class HinhAnh implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 500)
    @Column(name = "duong_dan")
    private String duongDan;
    @JoinColumn(name = "id_bai_viet", referencedColumnName = "id")
    @ManyToOne
    private BaiViet idBaiViet;

    
    @Transient
    private MultipartFile fileDuongDan;
    
    public HinhAnh() {
    }

    public HinhAnh(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDuongDan() {
        return duongDan;
    }

    public void setDuongDan(String duongDan) {
        this.duongDan = duongDan;
    }

    public BaiViet getIdBaiViet() {
        return idBaiViet;
    }

    public void setIdBaiViet(BaiViet idBaiViet) {
        this.idBaiViet = idBaiViet;
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
        if (!(object instanceof HinhAnh)) {
            return false;
        }
        HinhAnh other = (HinhAnh) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ntt.pojo.HinhAnh[ id=" + id + " ]";
    }

    /**
     * @return the fileDuongDan
     */
    public MultipartFile getFileDuongDan() {
        return fileDuongDan;
    }

    /**
     * @param fileDuongDan the fileDuongDan to set
     */
    public void setFileDuongDan(MultipartFile fileDuongDan) {
        this.fileDuongDan = fileDuongDan;
    }
    
}
