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
@Table(name = "loai_bai_viet")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LoaiBaiViet.findAll", query = "SELECT l FROM LoaiBaiViet l"),
    @NamedQuery(name = "LoaiBaiViet.findById", query = "SELECT l FROM LoaiBaiViet l WHERE l.id = :id"),
    @NamedQuery(name = "LoaiBaiViet.findByTenLoaiBaiViet", query = "SELECT l FROM LoaiBaiViet l WHERE l.tenLoaiBaiViet = :tenLoaiBaiViet")})
public class LoaiBaiViet implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 45)
    @Column(name = "ten_loai_bai_viet")
    private String tenLoaiBaiViet;
    @OneToMany(mappedBy = "loaiBaiViet")
    @JsonIgnore
    private Set<BaiViet> baiVietSet;

    public LoaiBaiViet() {
    }

    public LoaiBaiViet(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTenLoaiBaiViet() {
        return tenLoaiBaiViet;
    }

    public void setTenLoaiBaiViet(String tenLoaiBaiViet) {
        this.tenLoaiBaiViet = tenLoaiBaiViet;
    }

    @XmlTransient
    public Set<BaiViet> getBaiVietSet() {
        return baiVietSet;
    }

    public void setBaiVietSet(Set<BaiViet> baiVietSet) {
        this.baiVietSet = baiVietSet;
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
        if (!(object instanceof LoaiBaiViet)) {
            return false;
        }
        LoaiBaiViet other = (LoaiBaiViet) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ntt.pojo.LoaiBaiViet[ id=" + id + " ]";
    }
    
}
