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
public class VanDeTangLuong {
    private String maNhanVien, hoTen, gioiTinh, chucVu, chucDanh, luongCoBanCu,
            luongCoBanMoi, phuCapCVCu, phuCapCVMoi, ngayTang, lyDo;

    public VanDeTangLuong(String maNhanVien, String hoTen, String gioiTinh, String chucVu, String chucDanh, String luongCoBanCu, String luongCoBanMoi, String phuCapCVCu, String phuCapCVMoi, String ngayTang, String lyDo) {
        this.maNhanVien = maNhanVien;
        this.hoTen = hoTen;
        this.gioiTinh = gioiTinh;
        this.chucVu = chucVu;
        this.chucDanh = chucDanh;
        this.luongCoBanCu = luongCoBanCu;
        this.luongCoBanMoi = luongCoBanMoi;
        this.phuCapCVCu = phuCapCVCu;
        this.phuCapCVMoi = phuCapCVMoi;
        this.ngayTang = ngayTang;
        this.lyDo = lyDo;
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

    public String getChucDanh() {
        return chucDanh;
    }

    public void setChucDanh(String chucDanh) {
        this.chucDanh = chucDanh;
    }

    public String getLuongCoBanCu() {
        return luongCoBanCu;
    }

    public void setLuongCoBanCu(String luongCoBanCu) {
        this.luongCoBanCu = luongCoBanCu;
    }

    public String getLuongCoBanMoi() {
        return luongCoBanMoi;
    }

    public void setLuongCoBanMoi(String luongCoBanMoi) {
        this.luongCoBanMoi = luongCoBanMoi;
    }

    public String getPhuCapCVCu() {
        return phuCapCVCu;
    }

    public void setPhuCapCVCu(String phuCapCVCu) {
        this.phuCapCVCu = phuCapCVCu;
    }

    public String getPhuCapCVMoi() {
        return phuCapCVMoi;
    }

    public void setPhuCapCVMoi(String phuCapCVMoi) {
        this.phuCapCVMoi = phuCapCVMoi;
    }

    public String getNgayTang() {
        return ngayTang;
    }

    public void setNgayTang(String ngayTang) {
        this.ngayTang = ngayTang;
    }

    public String getLyDo() {
        return lyDo;
    }

    public void setLyDo(String lyDo) {
        this.lyDo = lyDo;
    }
    
    
}
