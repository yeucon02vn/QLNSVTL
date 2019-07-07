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
public class BangCongThuViec implements Serializable{
    String tenBoPhan, tenPhong, maNhanVien, hoTen, ngaySinh, luongThuViec, thang, nam, soNgayCong, soNgayNghi, soGioLam, ghiChu;

    public BangCongThuViec(String tenBoPhan, String tenPhong, String maNhanVien, String luongThuViec, String thang, String nam, String soNgayCong, String soNgayNghi, String soGioLam, String ghiChu) {
        this.tenBoPhan = tenBoPhan;
        this.tenPhong = tenPhong;
        this.maNhanVien = maNhanVien;
        this.luongThuViec = luongThuViec;
        this.thang = thang;
        this.nam = nam;
        this.soNgayCong = soNgayCong;
        this.soNgayNghi = soNgayNghi;
        this.soGioLam = soGioLam;
        this.ghiChu = ghiChu;
    }
    
    public BangCongThuViec(String tenBoPhan, String tenPhong, String maNhanVien, String hoTen, String ngaySinh, String luongThuViec, String thang, String nam, String soNgayCong, String soNgayNghi, String soGioLam) {
        this.tenBoPhan = tenBoPhan;
        this.tenPhong = tenPhong;
        this.maNhanVien = maNhanVien;
        this.hoTen = hoTen;
        this.ngaySinh = ngaySinh;
        this.luongThuViec = luongThuViec;
        this.thang = thang;
        this.nam = nam;
        this.soNgayCong = soNgayCong;
        this.soNgayNghi = soNgayNghi;
        this.soGioLam = soGioLam;
    }

    public String getTenBoPhan() {
        return tenBoPhan;
    }

    public void setTenBoPhan(String tenBoPhan) {
        this.tenBoPhan = tenBoPhan;
    }

    public String getTenPhong() {
        return tenPhong;
    }

    public void setTenPhong(String tenPhong) {
        this.tenPhong = tenPhong;
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

    public String getLuongThuViec() {
        return luongThuViec;
    }

    public void setLuongThuViec(String luongThuViec) {
        this.luongThuViec = luongThuViec;
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
