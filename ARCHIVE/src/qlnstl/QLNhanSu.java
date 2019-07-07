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
public class QLNhanSu implements Serializable{
    private String maBoPhan, maPhong, maNhanVien, hoTen, ngaySinh, gioiTinh, chucVu, CMND,
            noiCap, ngayCap, chucDanh, loaiHD, ngayHetHan, ngayKy, ghiChu;

    public QLNhanSu(String maBoPhan, String maPhong, String maNhanVien, String hoTen, String ngaySinh, String gioiTinh, String CMND, String ngayCap, String noiCap, String chucVu, String chucDanh, String loaiHD, String ngayKy,  String ngayHetHan, String ghiChu) {
        this.maBoPhan = maBoPhan;
        this.maPhong = maPhong;
        this.maNhanVien = maNhanVien;
        this.hoTen = hoTen;
        this.ngaySinh = ngaySinh;
        this.gioiTinh = gioiTinh;
        this.chucVu = chucVu;
        this.CMND = CMND;
        this.noiCap = noiCap;
        this.ngayCap = ngayCap;
        this.chucDanh = chucDanh;
        this.loaiHD = loaiHD;
        this.ngayHetHan = ngayHetHan;
        this.ngayKy = ngayKy;
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

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getChucVu() {
        return chucVu;
    }

    public void setChucVu(String chucVu) {
        this.chucVu = chucVu;
    }

    public String getCMND() {
        return CMND;
    }

    public void setCMND(String CMND) {
        this.CMND = CMND;
    }

    public String getNoiCap() {
        return noiCap;
    }

    public void setNoiCap(String noiCap) {
        this.noiCap = noiCap;
    }

    public String getNgayCap() {
        return ngayCap;
    }

    public void setNgayCap(String ngayCap) {
        this.ngayCap = ngayCap;
    }

    public String getChucDanh() {
        return chucDanh;
    }

    public void setChucDanh(String chucDanh) {
        this.chucDanh = chucDanh;
    }

    public String getLoaiHD() {
        return loaiHD;
    }

    public void setLoaiHD(String loaiHD) {
        this.loaiHD = loaiHD;
    }

    public String getNgayHetHan() {
        return ngayHetHan;
    }

    public void setNgayHetHan(String ngayHetHan) {
        this.ngayHetHan = ngayHetHan;
    }

    public String getNgayKy() {
        return ngayKy;
    }

    public void setNgayKy(String ngayKy) {
        this.ngayKy = ngayKy;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

}
