/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qlnstl;

/**
 *
 * @author Ngoc
 */
public class ThaiSan {
    private String maBoPhan, maPhong, maNhanVien, hoTen, ngaySinh, ngayVeSom,
            ngayNghiSinh, ngayLamTroLai, troCapCongTy, ghiChu;

    public ThaiSan(String maBoPhan, String maPhong, String maNhanVien, String hoTen, String ngaySinh, String ngayVeSom, String ngayNghiSinh, String ngayLamTroLai, String troCapCongTy, String ghiChu) {
        this.maBoPhan = maBoPhan;
        this.maPhong = maPhong;
        this.maNhanVien = maNhanVien;
        this.hoTen = hoTen;
        this.ngaySinh = ngaySinh;
        this.ngayVeSom = ngayVeSom;
        this.ngayNghiSinh = ngayNghiSinh;
        this.ngayLamTroLai = ngayLamTroLai;
        this.troCapCongTy = troCapCongTy;
        this.ghiChu = ghiChu;
    }

    public String getMaBoPhan() {
        return maBoPhan;
    }

    public void setMaBoPhan(String maBoPhan) {
        this.maBoPhan = maBoPhan;
    }

    public String getMaPhong() {
        return maPhong;
    }

    public void setMaPhong(String maPhong) {
        this.maPhong = maPhong;
    }

    public String getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getNgayVeSom() {
        return ngayVeSom;
    }

    public void setNgayVeSom(String ngayVeSom) {
        this.ngayVeSom = ngayVeSom;
    }

    public String getNgayNghiSinh() {
        return ngayNghiSinh;
    }

    public void setNgayNghiSinh(String ngayNghiSinh) {
        this.ngayNghiSinh = ngayNghiSinh;
    }

    public String getNgayLamTroLai() {
        return ngayLamTroLai;
    }

    public void setNgayLamTroLai(String ngayLamTroLai) {
        this.ngayLamTroLai = ngayLamTroLai;
    }

    public String getTroCapCongTy() {
        return troCapCongTy;
    }

    public void setTroCapCongTy(String troCapCongTy) {
        this.troCapCongTy = troCapCongTy;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }
    
}
