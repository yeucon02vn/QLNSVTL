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
public class QLHoSo implements Serializable {

    private String maPhong, maNhanVien, hoTen, ngaySinh, gioiTinh, diaChi,
            trinhDo, hocHam, viTriTV, ngayTV, ghiChu;

    public QLHoSo(String maPhong, String maNhanVien, String hoTen, String ngaySinh, String gioiTinh, String diaChi, String trinhDo, String hocHam, String viTriTV, String ngayTV, String ghiChu) {
        this.maPhong = maPhong;
        this.maNhanVien = maNhanVien;
        this.hoTen = hoTen;
        this.ngaySinh = ngaySinh;
        this.gioiTinh = gioiTinh;
        this.diaChi = diaChi;
        this.trinhDo = trinhDo;
        this.hocHam = hocHam;
        this.viTriTV = viTriTV;
        this.ngayTV = ngayTV;
        this.ghiChu = ghiChu;
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

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getViTriTV() {
        return viTriTV;
    }

    public void setViTriTV(String viTriTV) {
        this.viTriTV = viTriTV;
    }

    public String getNgayTV() {
        return ngayTV;
    }

    public void setNgayTV(String ngayTV) {
        this.ngayTV = ngayTV;
    }

    public String getTrinhDo() {
        return trinhDo;
    }

    public void setTrinhDo(String trinhDo) {
        this.trinhDo = trinhDo;
    }

    public String getHocHam() {
        return hocHam;
    }

    public void setHocHam(String hocHam) {
        this.hocHam = hocHam;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

}
