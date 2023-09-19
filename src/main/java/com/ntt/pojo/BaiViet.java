/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntt.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Admins
 */
@Entity
@Table(name = "bai_viet")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BaiViet.findAll", query = "SELECT b FROM BaiViet b"),
    @NamedQuery(name = "BaiViet.findById", query = "SELECT b FROM BaiViet b WHERE b.id = :id"),
    @NamedQuery(name = "BaiViet.findByTenBaiViet", query = "SELECT b FROM BaiViet b WHERE b.tenBaiViet = :tenBaiViet"),
    @NamedQuery(name = "BaiViet.findByHinhAnh", query = "SELECT b FROM BaiViet b WHERE b.hinhAnh = :hinhAnh"),
    @NamedQuery(name = "BaiViet.findByPhamViCanTim", query = "SELECT b FROM BaiViet b WHERE b.phamViCanTim = :phamViCanTim"),
    @NamedQuery(name = "BaiViet.findByNgayDang", query = "SELECT b FROM BaiViet b WHERE b.ngayDang = :ngayDang"),
    @NamedQuery(name = "BaiViet.findBySoNguoi", query = "SELECT b FROM BaiViet b WHERE b.soNguoi = :soNguoi"),
    @NamedQuery(name = "BaiViet.findByGiaThue", query = "SELECT b FROM BaiViet b WHERE b.giaThue = :giaThue"),
    @NamedQuery(name = "BaiViet.findByDienTich", query = "SELECT b FROM BaiViet b WHERE b.dienTich = :dienTich"),
    @NamedQuery(name = "BaiViet.findByDiaChiCt", query = "SELECT b FROM BaiViet b WHERE b.diaChiCt = :diaChiCt"),
    @NamedQuery(name = "BaiViet.findByHinhAnh1", query = "SELECT b FROM BaiViet b WHERE b.hinhAnh1 = :hinhAnh1"),
    @NamedQuery(name = "BaiViet.findByHinhAnh2", query = "SELECT b FROM BaiViet b WHERE b.hinhAnh2 = :hinhAnh2"),
    @NamedQuery(name = "BaiViet.findByHinhAnh3", query = "SELECT b FROM BaiViet b WHERE b.hinhAnh3 = :hinhAnh3")})

public class BaiViet implements Serializable {

    
    
    private static long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 100)
    @Column(name = "ten_bai_viet")
    private String tenBaiViet;
    @Size(max = 500)
    @Column(name = "hinh_anh")
    private String hinhAnh;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "noi_dung")
    private String noiDung;
    @Size(max = 200)
    @Column(name = "pham_vi_can_tim")
    private String phamViCanTim;
    @Column(name = "ngay_dang")
    @Temporal(TemporalType.DATE)
    private Date ngayDang;
    @Column(name = "so_nguoi")
    private Integer soNguoi;
    @Column(name = "gia_thue")
    private Long giaThue;
    @Size(max = 45)
    @Column(name = "dien_tich")
    private String dienTich;
    @Size(max = 200)
    @Column(name = "dia_chi_ct")
    private String diaChiCt;
    @Size(max = 500)
    @Column(name = "hinh_anh1")
    private String hinhAnh1;
    @Size(max = 500)
    @Column(name = "hinh_anh2")
    private String hinhAnh2;
    @Size(max = 500)
    @Column(name = "hinh_anh3")
    private String hinhAnh3;

    @OneToMany(mappedBy = "idBaiViet")
    @JsonIgnore
    private Set<BinhLuan> binhLuanSet;
    @OneToMany(mappedBy = "idBaiViet")
    @JsonIgnore
    private Set<HinhAnh> hinhAnhSet;
    @OneToMany(mappedBy = "idBaiViet")
    @JsonIgnore
    private Set<ThongBao> thongBaoSet;
    @JoinColumn(name = "loai_bai_viet", referencedColumnName = "id")
    @ManyToOne
    private LoaiBaiViet loaiBaiViet;
    @JoinColumn(name = "id_nguoi_dung", referencedColumnName = "id")
    @ManyToOne
    private NguoiDung idNguoiDung;
    @JoinColumn(name = "loai_trang_thai", referencedColumnName = "id")
    @ManyToOne
    private TrangThaiBaiViet loaiTrangThai;

    
    @Transient
    private MultipartFile file;
    @Transient
    private MultipartFile file1;
    @Transient
    private MultipartFile file2;
    @Transient
    private MultipartFile file3;
    @Transient
    private String tenNguoiDangBai;


    public BaiViet() {
    }

    public BaiViet(Integer id) {
        this.id = id;
    }

    public BaiViet(Integer id, String tenBaiViet, String hinhAnh, String noiDung, String phamViCanTim, Date ngayDang, Integer soNguoi, Long giaThue, String dienTich, String diaChiCt, String hinhAnh1, String hinhAnh2, String hinhAnh3, Set<BinhLuan> binhLuanSet, Set<HinhAnh> hinhAnhSet, Set<ThongBao> thongBaoSet, LoaiBaiViet loaiBaiViet, NguoiDung idNguoiDung, TrangThaiBaiViet loaiTrangThai, MultipartFile file, MultipartFile file1, MultipartFile file2, MultipartFile file3, String tenNguoiDangBai) {
        this.id = id;
        this.tenBaiViet = tenBaiViet;
        this.hinhAnh = hinhAnh;
        this.noiDung = noiDung;
        this.phamViCanTim = phamViCanTim;
        this.ngayDang = ngayDang;
        this.soNguoi = soNguoi;
        this.giaThue = giaThue;
        this.dienTich = dienTich;
        this.diaChiCt = diaChiCt;
        this.hinhAnh1 = hinhAnh1;
        this.hinhAnh2 = hinhAnh2;
        this.hinhAnh3 = hinhAnh3;
        this.binhLuanSet = binhLuanSet;
        this.hinhAnhSet = hinhAnhSet;
        this.thongBaoSet = thongBaoSet;
        this.loaiBaiViet = loaiBaiViet;
        this.idNguoiDung = idNguoiDung;
        this.loaiTrangThai = loaiTrangThai;
        this.file = file;
        this.file1 = file1;
        this.file2 = file2;
        this.file3 = file3;
        this.tenNguoiDangBai = tenNguoiDangBai;
    }
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTenBaiViet() {
        return tenBaiViet;
    }

    public void setTenBaiViet(String tenBaiViet) {
        this.tenBaiViet = tenBaiViet;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public String getPhamViCanTim() {
        return phamViCanTim;
    }

    public void setPhamViCanTim(String phamViCanTim) {
        this.phamViCanTim = phamViCanTim;
    }

    public Date getNgayDang() {
        return ngayDang;
    }

    public void setNgayDang(Date ngayDang) {
        this.ngayDang = ngayDang;
    }

    public Integer getSoNguoi() {
        return soNguoi;
    }

    public void setSoNguoi(Integer soNguoi) {
        this.soNguoi = soNguoi;
    }

    public Long getGiaThue() {
        return giaThue;
    }

    public void setGiaThue(Long giaThue) {
        this.giaThue = giaThue;
    }

    public String getDienTich() {
        return dienTich;
    }

    public void setDienTich(String dienTich) {
        this.dienTich = dienTich;
    }

    public String getDiaChiCt() {
        return diaChiCt;
    }

    public void setDiaChiCt(String diaChiCt) {
        this.diaChiCt = diaChiCt;
    }

    public String getHinhAnh1() {
        return hinhAnh1;
    }

    public void setHinhAnh1(String hinhAnh1) {
        this.hinhAnh1 = hinhAnh1;
    }

    public String getHinhAnh2() {
        return hinhAnh2;
    }

    public void setHinhAnh2(String hinhAnh2) {
        this.hinhAnh2 = hinhAnh2;
    }

    public String getHinhAnh3() {
        return hinhAnh3;
    }

    public void setHinhAnh3(String hinhAnh3) {
        this.hinhAnh3 = hinhAnh3;
    }

    @XmlTransient
    public Set<BinhLuan> getBinhLuanSet() {
        return binhLuanSet;
    }

    public void setBinhLuanSet(Set<BinhLuan> binhLuanSet) {
        this.binhLuanSet = binhLuanSet;
    }

    @XmlTransient
    public Set<HinhAnh> getHinhAnhSet() {
        return hinhAnhSet;
    }

    public void setHinhAnhSet(Set<HinhAnh> hinhAnhSet) {
        this.hinhAnhSet = hinhAnhSet;
    }

    @XmlTransient
    public Set<ThongBao> getThongBaoSet() {
        return thongBaoSet;
    }

    public void setThongBaoSet(Set<ThongBao> thongBaoSet) {
        this.thongBaoSet = thongBaoSet;
    }

    public LoaiBaiViet getLoaiBaiViet() {
        return loaiBaiViet;
    }

    public void setLoaiBaiViet(LoaiBaiViet loaiBaiViet) {
        this.loaiBaiViet = loaiBaiViet;
    }

    public NguoiDung getIdNguoiDung() {
        return idNguoiDung;
    }

    public void setIdNguoiDung(NguoiDung idNguoiDung) {
        this.idNguoiDung = idNguoiDung;
    }

    public TrangThaiBaiViet getLoaiTrangThai() {
        return loaiTrangThai;
    }

    public void setLoaiTrangThai(TrangThaiBaiViet loaiTrangThai) {
        this.loaiTrangThai = loaiTrangThai;
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
        if (!(object instanceof BaiViet)) {
            return false;
        }
        BaiViet other = (BaiViet) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ntt.pojo.BaiViet[ id=" + id + " ]";
    }


    /**
     * @param file the file to set
     */
    public void setFile(MultipartFile file) {
        this.file = file;
    }

    /**
     * @return the file1
     */
    public MultipartFile getFile1() {
        return file1;
    }

    /**
     * @param file1 the file1 to set
     */
    public void setFile1(MultipartFile file1) {
        this.file1 = file1;
    }

    /**
     * @return the file2
     */
    public MultipartFile getFile2() {
        return file2;
    }

    /**
     * @param file2 the file2 to set
     */
    public void setFile2(MultipartFile file2) {
        this.file2 = file2;
    }

    /**
     * @return the file3
     */
    public MultipartFile getFile3() {
        return file3;
    }

    /**
     * @param file3 the file3 to set
     */
    public void setFile3(MultipartFile file3) {
        this.file3 = file3;
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
     * @return the serialVersionUID
     */
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    /**
     * @param aSerialVersionUID the serialVersionUID to set
     */
    public static void setSerialVersionUID(long aSerialVersionUID) {
        serialVersionUID = aSerialVersionUID;
    }

    /**
     * @return the file
     */
    public MultipartFile getFile() {
        return file;
    }
    
}
