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
public class BangCongKhoiVanPhong implements Serializable{
    String maNhanVien, hoTen, ngaySinh, luongCoBan, phuCap, phuCapKhac, thang, nam, soNgayCong, soNgayNghi, soGioLam, ghiChu;

    public BangCongKhoiVanPhong(String maNhanVien, String luongCoBan, String phuCap, String phuCapKhac, String thang, String nam, String soNgayCong, String soNgayNghi, String soGioLam, String ghiChu) {
        this.maNhanVien = maNhanVien;
        this.luongCoBan = luongCoBan;
        this.phuCap = phuCap;
        this.phuCapKhac = phuCapKhac;
        this.thang = thang;
        this.nam = nam;
        this.soNgayCong = soNgayCong;
        this.soNgayNghi = soNgayNghi;
        this.soGioLam = soGioLam;
        this.ghiChu = ghiChu;
    }

    public BangCongKhoiVanPhong(String maNhanVien, String hoTen, String ngaySinh, String luongCoBan, String phuCap, String phuCapKhac, String thang, String nam, String soNgayCong, String soNgayNghi, String soGioLam) {
        this.maNhanVien = maNhanVien;
        this.hoTen = hoTen;
        this.ngaySinh = ngaySinh;
        this.luongCoBan = luongCoBan;
        this.phuCap = phuCap;
        this.phuCapKhac = phuCapKhac;
        this.thang = thang;
        this.nam = nam;
        this.soNgayCong = soNgayCong;
        this.soNgayNghi = soNgayNghi;
        this.soGioLam = soGioLam;
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
    
    public String getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public String getLuongCoBan() {
        return luongCoBan;
    }

    public void setLuongCoBan(String luongCoBan) {
        this.luongCoBan = luongCoBan;
    }

    public String getPhuCap() {
        return phuCap;
    }

    public void setPhuCap(String phuCap) {
        this.phuCap = phuCap;
    }

    public String getPhuCapKhac() {
        return phuCapKhac;
    }

    public void setPhuCapKhac(String phuCapKhac) {
        this.phuCapKhac = phuCapKhac;
    }

    public String getThang() {
        return thang;
    }

    public void setThang(String thang) {
        this.thang = thang;
    }

    public String getNam() {
        return nam;
    }

    public void setNam(String nam) {
        this.nam = nam;
    }

    public String getSoNgayCong() {
        return soNgayCong;
    }

    public void setSoNgayCong(String soNgayCong) {
        this.soNgayCong = soNgayCong;
    }

    public String getSoNgayNghi() {
        return soNgayNghi;
    }

    public void setSoNgayNghi(String soNgayNghi) {
        this.soNgayNghi = soNgayNghi;
    }

    public String getSoGioLam() {
        return soGioLam;
    }

    public void setSoGioLam(String soGioLam) {
        this.soGioLam = soGioLam;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

}
