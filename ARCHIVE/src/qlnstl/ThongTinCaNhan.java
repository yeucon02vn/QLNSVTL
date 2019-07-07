/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qlnstl;

import java.io.Serializable;

/**
 *
 * @author Ngoc
 */
public class ThongTinCaNhan implements Serializable{
    private String maNhanVien, noiSinh, nguyenQuan, diaChiThuongTru, diaChiTamTru, soDienThoai, danToc,
                    tonGiao, quocTich, tiengNgonNgu, trinhDoNgonNgu, hocVan, hocHam, 
                    ngayVaoDoan, tenDoanThe, chucVuDoan, ghiChu;

    public ThongTinCaNhan(String maNhanVien, String noiSinh, String nguyenQuan, String diaChiThuongTru, String diaChiTamTru, String soDienThoai, String danToc, String tonGiao, String quocTich, String tiengNgonNgu, String trinhDoNgonNgu, String hocVan, String hocHam, String ngayVaoDoan, String tenDoanThe, String chucVuDoan, String ghiChu) {
        this.maNhanVien = maNhanVien;
        this.noiSinh = noiSinh;
        this.nguyenQuan = nguyenQuan;
        this.diaChiThuongTru = diaChiThuongTru;
        this.diaChiTamTru = diaChiTamTru;
        this.soDienThoai = soDienThoai;
        this.danToc = danToc;
        this.tonGiao = tonGiao;
        this.quocTich = quocTich;
        this.tiengNgonNgu = tiengNgonNgu;
        this.trinhDoNgonNgu = trinhDoNgonNgu;
        this.hocVan = hocVan;
        this.hocHam = hocHam;
        this.ngayVaoDoan = ngayVaoDoan;
        this.tenDoanThe = tenDoanThe;
        this.chucVuDoan = chucVuDoan;
        this.ghiChu = ghiChu;
    }

    public String getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public String getNoiSinh() {
        return noiSinh;
    }

    public void setNoiSinh(String noiSinh) {
        this.noiSinh = noiSinh;
    }

    public String getNguyenQuan() {
        return nguyenQuan;
    }

    public void setNguyenQuan(String nguyenQuan) {
        this.nguyenQuan = nguyenQuan;
    }

    public String getDiaChiThuongTru() {
        return diaChiThuongTru;
    }

    public void setDiaChiThuongTru(String diaChiThuongTru) {
        this.diaChiThuongTru = diaChiThuongTru;
    }

    public String getDiaChiTamTru() {
        return diaChiTamTru;
    }

    public void setDiaChiTamTru(String diaChiTamTru) {
        this.diaChiTamTru = diaChiTamTru;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getDanToc() {
        return danToc;
    }

    public void setDanToc(String danToc) {
        this.danToc = danToc;
    }

    public String getTonGiao() {
        return tonGiao;
    }

    public void setTonGiao(String tonGiao) {
        this.tonGiao = tonGiao;
    }

    public String getQuocTich() {
        return quocTich;
    }

    public void setQuocTich(String quocTich) {
        this.quocTich = quocTich;
    }

    public String getTiengNgonNgu() {
        return tiengNgonNgu;
    }

    public void setTiengNgonNgu(String tiengNgonNgu) {
        this.tiengNgonNgu = tiengNgonNgu;
    }

    public String getTrinhDoNgonNgu() {
        return trinhDoNgonNgu;
    }

    public void setTrinhDoNgonNgu(String trinhDoNgonNgu) {
        this.trinhDoNgonNgu = trinhDoNgonNgu;
    }

    public String getHocVan() {
        return hocVan;
    }

    public void setHocVan(String hocVan) {
        this.hocVan = hocVan;
    }

    public String getHocHam() {
        return hocHam;
    }

    public void setHocHam(String hocHam) {
        this.hocHam = hocHam;
    }

    public String getNgayVaoDoan() {
        return ngayVaoDoan;
    }

    public void setNgayVaoDoan(String ngayVaoDoan) {
        this.ngayVaoDoan = ngayVaoDoan;
    }

    public String getTenDoanThe() {
        return tenDoanThe;
    }

    public void setTenDoanThe(String tenDoanThe) {
        this.tenDoanThe = tenDoanThe;
    }

    public String getChucVuDoan() {
        return chucVuDoan;
    }

    public void setChucVuDoan(String chucVuDoan) {
        this.chucVuDoan = chucVuDoan;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }
    
}
