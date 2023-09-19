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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Admins
 */
@Entity
@Table(name = "thong_bao")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ThongBao.findAll", query = "SELECT t FROM ThongBao t"),
    @NamedQuery(name = "ThongBao.findById", query = "SELECT t FROM ThongBao t WHERE t.id = :id"),
    @NamedQuery(name = "ThongBao.findByNoiDung", query = "SELECT t FROM ThongBao t WHERE t.noiDung = :noiDung")})
public class ThongBao implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 200)
    @Column(name = "noi_dung")
    private String noiDung;
    @JoinColumn(name = "id_bai_viet", referencedColumnName = "id")
    @ManyToOne
    private BaiViet idBaiViet;
    @JoinColumn(name = "id_nguoi_dung", referencedColumnName = "id")
    @ManyToOne
    private NguoiDung idNguoiDung;

    public ThongBao() {
    }

    public ThongBao(Integer id) {
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
        if (!(object instanceof ThongBao)) {
            return false;
        }
        ThongBao other = (ThongBao) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ntt.pojo.ThongBao[ id=" + id + " ]";
    }
    
}
