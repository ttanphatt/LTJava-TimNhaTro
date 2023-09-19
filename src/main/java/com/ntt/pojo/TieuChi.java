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
@Table(name = "tieu_chi")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TieuChi.findAll", query = "SELECT t FROM TieuChi t"),
    @NamedQuery(name = "TieuChi.findById", query = "SELECT t FROM TieuChi t WHERE t.id = :id"),
    @NamedQuery(name = "TieuChi.findByDiaChiMongMuon", query = "SELECT t FROM TieuChi t WHERE t.diaChiMongMuon = :diaChiMongMuon"),
    @NamedQuery(name = "TieuChi.findBySoNguoiMongMuon", query = "SELECT t FROM TieuChi t WHERE t.soNguoiMongMuon = :soNguoiMongMuon"),
    @NamedQuery(name = "TieuChi.findByGiaMongMuon", query = "SELECT t FROM TieuChi t WHERE t.giaMongMuon = :giaMongMuon")})
public class TieuChi implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 200)
    @Column(name = "dia_chi_mong_muon")
    private String diaChiMongMuon;
    @Column(name = "so_nguoi_mong_muon")
    private Integer soNguoiMongMuon;
    @Column(name = "gia_mong_muon")
    private Long giaMongMuon;
    @JoinColumn(name = "id_nguoi_dung", referencedColumnName = "id")
    @ManyToOne
    private NguoiDung idNguoiDung;

    public TieuChi() {
    }

    public TieuChi(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDiaChiMongMuon() {
        return diaChiMongMuon;
    }

    public void setDiaChiMongMuon(String diaChiMongMuon) {
        this.diaChiMongMuon = diaChiMongMuon;
    }

    public Integer getSoNguoiMongMuon() {
        return soNguoiMongMuon;
    }

    public void setSoNguoiMongMuon(Integer soNguoiMongMuon) {
        this.soNguoiMongMuon = soNguoiMongMuon;
    }

    public Long getGiaMongMuon() {
        return giaMongMuon;
    }

    public void setGiaMongMuon(Long giaMongMuon) {
        this.giaMongMuon = giaMongMuon;
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
        if (!(object instanceof TieuChi)) {
            return false;
        }
        TieuChi other = (TieuChi) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ntt.pojo.TieuChi[ id=" + id + " ]";
    }
    
}
