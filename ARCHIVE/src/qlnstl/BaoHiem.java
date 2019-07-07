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
public class BaoHiem {
    private String maNhanVien, maLuong, maSoBaoHiem, ngayCapSo, noiCapSo, ghiChu;

    public BaoHiem(String maNhanVien, String maLuong, String maSoBaoHiem, String ngayCapSo, String noiCapSo, String ghiChu) {
        this.maNhanVien = maNhanVien;
        this.maLuong = maLuong;
        this.maSoBaoHiem = maSoBaoHiem;
        this.ngayCapSo = ngayCapSo;
        this.noiCapSo = noiCapSo;
        this.ghiChu = ghiChu;
    }

    public String getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public String getMaLuong() {
        return maLuong;
    }

    public void setMaLuong(String maLuong) {
        this.maLuong = maLuong;
    }

    public String getMaSoBaoHiem() {
        return maSoBaoHiem;
    }

    public void setMaSoBaoHiem(String maSoBaoHiem) {
        this.maSoBaoHiem = maSoBaoHiem;
    }

    public String getNgayCapSo() {
        return ngayCapSo;
    }

    public void setNgayCapSo(String ngayCapSo) {
        this.ngayCapSo = ngayCapSo;
    }

    public String getNoiCapSo() {
        return noiCapSo;
    }

    public void setNoiCapSo(String noiCapSo) {
        this.noiCapSo = noiCapSo;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }
    
}
