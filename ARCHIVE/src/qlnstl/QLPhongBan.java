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
public class QLPhongBan implements Serializable{
    private String maPhong, maBoPhan, tenPhong, ngayThanhLap, ghiChu;

    public QLPhongBan(String maBoPhan, String maPhong, String tenPhong, String ngayThanhLap, String ghiChu) {
        this.maPhong = maPhong;
        this.maBoPhan = maBoPhan;
        this.tenPhong = tenPhong;
        this.ngayThanhLap = ngayThanhLap;
        this.ghiChu = ghiChu;
    }

    
    public String getMaPhong() {
        return maPhong;
    }

    public void setMaPhong(String maPhong) {
        this.maPhong = maPhong;
    }

    public String getMaBoPhan() {
        return maBoPhan;
    }

    public void setMaBoPhan(String maBoPhan) {
        this.maBoPhan = maBoPhan;
    }

    public String getTenPhong() {
        return tenPhong;
    }

    public void setTenPhong(String tenPhong) {
        this.tenPhong = tenPhong;
    }

    public String getNgayThanhLap() {
        return ngayThanhLap;
    }

    public void setNgayThanhLap(String ngayThanhLap) {
        this.ngayThanhLap = ngayThanhLap;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

}
