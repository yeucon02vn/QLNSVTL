/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import net.proteanit.sql.DbUtils;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import qlnstl.BangCongKhoiDieuHanh;
import qlnstl.BangCongThuViec;
import qlnstl.BangCongKhoiSanXuat;
import qlnstl.BangCongKhoiVanChuyen;
import qlnstl.BangCongKhoiVanPhong;
/**
 *
 * @author Ngoc
 */
public class frmTraCuuLuong extends javax.swing.JPanel {

    DefaultTableModel modelDH;
    DefaultTableModel modelVP;
    DefaultTableModel modelSX;
    DefaultTableModel modelVC;
    DefaultTableModel modelTV;

    private Connection conn;
    /**
     * Creates new form frmThongTinNhanVien
     */
    public frmTraCuuLuong() {
        initComponents();
        
        showTV();
        showDH();
        showSX();
        showVC();
        showVP();
    }
    
    public ArrayList<BangCongThuViec> tvList() {
        ArrayList<BangCongThuViec> tvList = new ArrayList<>();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=QLNS;" + "username=sa;password=123456");
            PreparedStatement ps = conn.prepareStatement("SELECT TenBoPhan, TenPhong, TblHoSoThuViec.MaNV, HoTen, NgaySinh, LuongTViec, Thang, Nam, SoNgayCong, SoNgayNghi, SoGioLamThem"
                    + " FROM TblBangCongThuViec, TblHoSoThuViec"
                    + " WHERE TblBangCongThuViec.MaNV=TblHoSoThuViec.MaNV");
            ResultSet rs = ps.executeQuery();
            BangCongThuViec tv;
            while (rs.next()) {
                tv = new BangCongThuViec(
                    rs.getString("TenBoPhan"),
                    rs.getString("TenPhong"),
                    rs.getString("MaNV"),
                    rs.getString("HoTen"),
                    rs.getString("NgaySinh"),
                    rs.getString("LuongTViec"),
                    rs.getString("Thang"),
                    rs.getString("Nam"),
                    rs.getString("SoNgayCong"),
                    rs.getString("SoNgayNghi"),
                    rs.getString("SoGioLamThem")
                );
                tvList.add(tv);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return tvList;
    }

    public void showTV() {
        ArrayList<BangCongThuViec> tv = tvList();
        DefaultTableModel model = (DefaultTableModel)tblTV.getModel();
        Object[] row = new Object[17];
        for (int i=0;i<tv.size();i++) {
            row[0]=tv.get(i).getTenBoPhan();
            row[1]=tv.get(i).getTenPhong();
            row[2]=tv.get(i).getMaNhanVien();
            row[3]=tv.get(i).getHoTen();
            row[4]=tv.get(i).getNgaySinh();
            row[5]=tv.get(i).getLuongThuViec();
            row[6]=tv.get(i).getThang();
            row[7]=tv.get(i).getNam();
            row[8]=tv.get(i).getSoNgayCong();
            row[9]=tv.get(i).getSoNgayNghi();
            row[10]=tv.get(i).getSoGioLam();
            model.addRow(row);
        };
    }
    
    public ArrayList<BangCongKhoiDieuHanh> dhList() {
        ArrayList<BangCongKhoiDieuHanh> dhList = new ArrayList<>();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=QLNS;" + "username=sa;password=123456");
            PreparedStatement ps = conn.prepareStatement("SELECT TblTTNVCoBan.MaNV, HoTen, NgaySinh, LCB, PhuCapCVu, PhuCapKhac, Thang, Nam, SoNgayCongThang, SoNgayNghi, SoGioLamThem"
                    + " FROM TblCongKhoiDieuHanh, TblTTNVCoBan"
                    + " WHERE TblCongKhoiDieuHanh.MaNV=TblTTNVCoBan.MaNV");
            ResultSet rs = ps.executeQuery();
            BangCongKhoiDieuHanh dh;
            while (rs.next()) {
                dh = new BangCongKhoiDieuHanh(
                    rs.getString("MaNV"),
                    rs.getString("HoTen"),
                    rs.getString("NgaySinh"),
                    rs.getString("LCB"),
                    rs.getString("PhuCapCVu"),
                    rs.getString("PhuCapKhac"),
                    rs.getString("Thang"),
                    rs.getString("Nam"),
                    rs.getString("SoNgayCongThang"),
                    rs.getString("SoNgayNghi"),
                    rs.getString("SoGioLamThem")
                );
                dhList.add(dh);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return dhList;
    }
    
    public void showDH() {
        ArrayList<BangCongKhoiDieuHanh> dh = dhList();
        DefaultTableModel model = (DefaultTableModel)tblDH.getModel();
        Object[] row = new Object[17];
        for (int i=0;i<dh.size();i++) {
            row[0]=dh.get(i).getMaNhanVien();
            row[1]=dh.get(i).getHoTen();
            row[2]=dh.get(i).getNgaySinh();
            row[3]=dh.get(i).getLuongCoBan();
            row[4]=dh.get(i).getPhuCap();
            row[5]=dh.get(i).getPhuCapKhac();
            row[6]=dh.get(i).getThang();
            row[7]=dh.get(i).getNam();
            row[8]=dh.get(i).getSoNgayCong();
            row[9]=dh.get(i).getSoNgayNghi();
            row[10]=dh.get(i).getSoGioLam();
            model.addRow(row);
        };
    }
    
    public ArrayList<BangCongKhoiSanXuat> sxList() {
        ArrayList<BangCongKhoiSanXuat> sxList = new ArrayList<>();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=QLNS;" + "username=sa;password=123456");
            PreparedStatement ps = conn.prepareStatement("SELECT TblTTNVCoBan.MaNV, HoTen, NgaySinh, LCB, PhuCapCVu, Thang, Nam, SoNgayCongThang, SoNgayNghi, SoGioLamThem "
                    + " FROM TblCongKhoiSanXuat, TblTTNVCoBan"
                    + " WHERE TblCongKhoiSanXuat.MaNV=TblTTNVCoBan.MaNV");
            ResultSet rs = ps.executeQuery();
            BangCongKhoiSanXuat sx;
            while (rs.next()) {
                sx = new BangCongKhoiSanXuat(
                    rs.getString("MaNV"),
                    rs.getString("HoTen"),
                    rs.getString("NgaySinh"),
                    rs.getString("LCB"),
                    rs.getString("PhuCapCVu"),
                    rs.getString("Thang"),
                    rs.getString("Nam"),
                    rs.getString("SoNgayCongThang"),
                    rs.getString("SoNgayNghi"),
                    rs.getString("SoGioLamThem")
                );
                sxList.add(sx);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return sxList;
    }
    
    public void showSX() {
        ArrayList<BangCongKhoiSanXuat> sx = sxList();
        DefaultTableModel model = (DefaultTableModel)tblSX.getModel();
        Object[] row = new Object[17];
        for (int i=0;i<sx.size();i++) {
            row[0]=sx.get(i).getMaNhanVien();
            row[1]=sx.get(i).getLuongCoBan();
            row[2]=sx.get(i).getPhuCap();
            row[3]=sx.get(i).getThang();
            row[4]=sx.get(i).getNam();
            row[5]=sx.get(i).getSoNgayCong();
            row[6]=sx.get(i).getSoNgayNghi();
            row[7]=sx.get(i).getSoGioLam();
            row[8]=sx.get(i).getGhiChu();
            row[9]=sx.get(i).getMaPhong();
            model.addRow(row);
        };
    }
    
    public ArrayList<BangCongKhoiVanChuyen> vcList() {
        ArrayList<BangCongKhoiVanChuyen> vcList = new ArrayList<>();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=QLNS;" + "username=sa;password=123456");
            PreparedStatement ps = conn.prepareStatement("SELECT TblTTNVCoBan.MaNV, HoTen, NgaySinh, LCB, PhuCapCVu, PhuCapKhac, Thang, Nam, SoNgayCongThang, SoNgayNghi, SOGioLamThem "
                    + " FROM TblCongKhoiVanChuyen, TblTTNVCoBan"
                    + " WHERE TblCongKhoiVanChuyen.MaNV=TblTTNVCoBan.MaNV");
            ResultSet rs = ps.executeQuery();
            BangCongKhoiVanChuyen vc;
            while (rs.next()) {
                vc = new BangCongKhoiVanChuyen(
                    rs.getString("MaNV"),
                    rs.getString("HoTen"),
                    rs.getString("NgaySinh"),
                    rs.getString("LCB"),
                    rs.getString("PhuCapCVu"),
                    rs.getString("PhuCapKhac"),
                    rs.getString("Thang"),
                    rs.getString("Nam"),
                    rs.getString("SoNgayCongThang"),
                    rs.getString("SoNgayNghi"),
                    rs.getString("SoGioLamThem")
                );
                vcList.add(vc);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return vcList;
    }
    
    public void showVC() {
        ArrayList<BangCongKhoiVanChuyen> vc = vcList();
        DefaultTableModel model = (DefaultTableModel)tblVC.getModel();
        Object[] row = new Object[17];
        for (int i=0;i<vc.size();i++) {
            row[0]=vc.get(i).getMaNhanVien();
            row[1]=vc.get(i).getHoTen();
            row[2]=vc.get(i).getNgaySinh();
            row[3]=vc.get(i).getLuongCoBan();
            row[4]=vc.get(i).getPhuCap();
            row[5]=vc.get(i).getPhuCapKhac();
            row[6]=vc.get(i).getThang();
            row[7]=vc.get(i).getNam();
            row[8]=vc.get(i).getSoNgayCong();
            row[9]=vc.get(i).getSoNgayNghi();
            row[10]=vc.get(i).getSoGioLam();
            model.addRow(row);
        };
    }

    public ArrayList<BangCongKhoiVanPhong> vpList() {
        ArrayList<BangCongKhoiVanPhong> vpList = new ArrayList<>();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=QLNS;" + "username=sa;password=123456");
            PreparedStatement ps = conn.prepareStatement("SELECT TblTTNVCoBan.MaNV, HoTen, NgaySinh, LCB, PhuCapCVu, PhuCapKhac, Thang, Nam, SoNgayCongThang, SoNgayNghi, SoGioLamThem "
                    + " FROM TblCongKhoiVanPHong, TblTTNVCoBan"
                    + " WHERE TblCongKhoiVanPHong.MaNV=TblTTNVCoBan.MaNV");
            ResultSet rs = ps.executeQuery();
            BangCongKhoiVanPhong vp;
            while (rs.next()) {
                vp = new BangCongKhoiVanPhong(
                    rs.getString("MaNV"),
                    rs.getString("HoTen"),
                    rs.getString("NgaySinh"),
                    rs.getString("LCB"),
                    rs.getString("PhuCapCVu"),
                    rs.getString("PhuCapKhac"),
                    rs.getString("Thang"),
                    rs.getString("Nam"),
                    rs.getString("SoNgayCongThang"),
                    rs.getString("SoNgayNghi"),
                    rs.getString("SoGioLamThem")
                );
                vpList.add(vp);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return vpList;
    }
        
    public void showVP() {
        ArrayList<BangCongKhoiVanPhong> vp = vpList();
        DefaultTableModel model = (DefaultTableModel)tblVP.getModel();
        Object[] row = new Object[17];
        for (int i=0;i<vp.size();i++) {
            row[0]=vp.get(i).getMaNhanVien();
            row[1]=vp.get(i).getHoTen();
            row[2]=vp.get(i).getNgaySinh();
            row[3]=vp.get(i).getLuongCoBan();
            row[4]=vp.get(i).getPhuCap();
            row[5]=vp.get(i).getPhuCapKhac();
            row[6]=vp.get(i).getThang();
            row[7]=vp.get(i).getNam();
            row[8]=vp.get(i).getSoNgayCong();
            row[9]=vp.get(i).getSoNgayNghi();
            row[10]=vp.get(i).getSoGioLam();
            model.addRow(row);
        };
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        pnThuViec = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblTV = new javax.swing.JTable();
        lblSearch = new javax.swing.JLabel();
        txtSearchTV = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        pnKhoiSanXuat = new javax.swing.JPanel();
        jSeparator3 = new javax.swing.JSeparator();
        jScrollPane7 = new javax.swing.JScrollPane();
        tblSX = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        txtSearchSX = new javax.swing.JTextField();
        lblSearch1 = new javax.swing.JLabel();
        pnKhoiDieuHanh = new javax.swing.JPanel();
        jSeparator2 = new javax.swing.JSeparator();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblDH = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        txtSearchDH = new javax.swing.JTextField();
        lblSearch2 = new javax.swing.JLabel();
        pnKhoiVanPhong = new javax.swing.JPanel();
        jSeparator5 = new javax.swing.JSeparator();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblVP = new javax.swing.JTable();
        jButton4 = new javax.swing.JButton();
        txtSearchVP = new javax.swing.JTextField();
        lblSearch3 = new javax.swing.JLabel();
        lblPhuCapVC = new javax.swing.JPanel();
        jSeparator4 = new javax.swing.JSeparator();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblVC = new javax.swing.JTable();
        jButton5 = new javax.swing.JButton();
        txtSearchVC = new javax.swing.JTextField();
        lblSearch4 = new javax.swing.JLabel();

        jPanel2.setBackground(new java.awt.Color(107, 195, 196));

        jPanel4.setBackground(new java.awt.Color(107, 195, 196));

        pnThuViec.setBackground(new java.awt.Color(254, 255, 250));
        pnThuViec.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 0, new java.awt.Color(107, 195, 196)));

        jSeparator1.setBackground(new java.awt.Color(3, 100, 117));
        jSeparator1.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(3, 100, 117)));

        tblTV.setBackground(new java.awt.Color(254, 255, 250));
        tblTV.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tblTV.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        tblTV.setForeground(new java.awt.Color(3, 100, 117));
        tblTV.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tên bộ phận", "Tên phòng", "Mã nhân viên", "Họ tên", "Ngày sinh", "Lương thử việc", "Tháng", "Năm", "Số ngày công", "Số ngày nghỉ", "Số ngày làm thêm"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblTV.setGridColor(new java.awt.Color(3, 100, 117));
        tblTV.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblTVMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblTV);

        lblSearch.setBackground(new java.awt.Color(3, 100, 117));
        lblSearch.setFont(new java.awt.Font("Verdana", 1, 20)); // NOI18N
        lblSearch.setForeground(new java.awt.Color(3, 100, 117));
        lblSearch.setText("Tìm kiếm");

        txtSearchTV.setBackground(new java.awt.Color(254, 255, 250));
        txtSearchTV.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        txtSearchTV.setForeground(new java.awt.Color(204, 204, 204));
        txtSearchTV.setText("word...");
        txtSearchTV.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(107, 195, 196)));
        txtSearchTV.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtSearchTVFocusGained(evt);
            }
        });
        txtSearchTV.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchTVKeyReleased(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(254, 255, 250));
        jButton1.setFont(new java.awt.Font("Trebuchet MS", 0, 20)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/qlnstl/src/magnifier.png"))); // NOI18N
        jButton1.setBorder(null);
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout pnThuViecLayout = new javax.swing.GroupLayout(pnThuViec);
        pnThuViec.setLayout(pnThuViecLayout);
        pnThuViecLayout.setHorizontalGroup(
            pnThuViecLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 858, Short.MAX_VALUE)
            .addGroup(pnThuViecLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblSearch)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtSearchTV, javax.swing.GroupLayout.PREFERRED_SIZE, 363, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnThuViecLayout.setVerticalGroup(
            pnThuViecLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnThuViecLayout.createSequentialGroup()
                .addContainerGap(70, Short.MAX_VALUE)
                .addGroup(pnThuViecLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblSearch)
                    .addComponent(txtSearchTV, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 72, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPane1.addTab("Thử việc", pnThuViec);

        pnKhoiSanXuat.setBackground(new java.awt.Color(254, 255, 250));
        pnKhoiSanXuat.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 0, new java.awt.Color(107, 195, 196)));

        jSeparator3.setBackground(new java.awt.Color(3, 100, 117));
        jSeparator3.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(3, 100, 117)));

        tblSX.setBackground(new java.awt.Color(254, 255, 250));
        tblSX.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tblSX.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        tblSX.setForeground(new java.awt.Color(3, 100, 117));
        tblSX.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã nhân viên", "Họ tên", "Ngày sinh", "Lương cơ bản", "Phụ cấp", "Tháng", "Năm", "Số ngày công", "Số ngày nghỉ", "Số giờ làm thêm"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblSX.setGridColor(new java.awt.Color(3, 100, 117));
        tblSX.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSXMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(tblSX);

        jButton2.setBackground(new java.awt.Color(254, 255, 250));
        jButton2.setFont(new java.awt.Font("Trebuchet MS", 0, 20)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/qlnstl/src/magnifier.png"))); // NOI18N
        jButton2.setBorder(null);
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        txtSearchSX.setBackground(new java.awt.Color(254, 255, 250));
        txtSearchSX.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        txtSearchSX.setForeground(new java.awt.Color(204, 204, 204));
        txtSearchSX.setText("word...");
        txtSearchSX.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(107, 195, 196)));
        txtSearchSX.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtSearchSXFocusGained(evt);
            }
        });
        txtSearchSX.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchSXKeyReleased(evt);
            }
        });

        lblSearch1.setBackground(new java.awt.Color(3, 100, 117));
        lblSearch1.setFont(new java.awt.Font("Verdana", 1, 20)); // NOI18N
        lblSearch1.setForeground(new java.awt.Color(3, 100, 117));
        lblSearch1.setText("Tìm kiếm");

        javax.swing.GroupLayout pnKhoiSanXuatLayout = new javax.swing.GroupLayout(pnKhoiSanXuat);
        pnKhoiSanXuat.setLayout(pnKhoiSanXuatLayout);
        pnKhoiSanXuatLayout.setHorizontalGroup(
            pnKhoiSanXuatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator3)
            .addComponent(jScrollPane7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 858, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnKhoiSanXuatLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblSearch1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtSearchSX, javax.swing.GroupLayout.PREFERRED_SIZE, 363, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jButton2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnKhoiSanXuatLayout.setVerticalGroup(
            pnKhoiSanXuatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnKhoiSanXuatLayout.createSequentialGroup()
                .addContainerGap(68, Short.MAX_VALUE)
                .addGroup(pnKhoiSanXuatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblSearch1)
                    .addComponent(txtSearchSX, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 73, Short.MAX_VALUE)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPane1.addTab("Khối sản xuất", pnKhoiSanXuat);

        pnKhoiDieuHanh.setBackground(new java.awt.Color(254, 255, 250));
        pnKhoiDieuHanh.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 0, new java.awt.Color(107, 195, 196)));

        jSeparator2.setBackground(new java.awt.Color(3, 100, 117));
        jSeparator2.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(3, 100, 117)));

        tblDH.setBackground(new java.awt.Color(254, 255, 250));
        tblDH.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tblDH.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        tblDH.setForeground(new java.awt.Color(3, 100, 117));
        tblDH.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã nhân viên", "Họ tên", "Ngày sinh", "Lương cơ bản", "Phụ cấp", "Phụ cấp khác", "Tháng", "Năm", "Số ngày công", "Số ngày nghỉ", "Số ngày làm thêm"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, false, false, false, true, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblDH.setGridColor(new java.awt.Color(3, 100, 117));
        tblDH.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDHMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblDH);

        jButton3.setBackground(new java.awt.Color(254, 255, 250));
        jButton3.setFont(new java.awt.Font("Trebuchet MS", 0, 20)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/qlnstl/src/magnifier.png"))); // NOI18N
        jButton3.setBorder(null);
        jButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        txtSearchDH.setBackground(new java.awt.Color(254, 255, 250));
        txtSearchDH.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        txtSearchDH.setForeground(new java.awt.Color(204, 204, 204));
        txtSearchDH.setText("word...");
        txtSearchDH.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(107, 195, 196)));
        txtSearchDH.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtSearchDHFocusGained(evt);
            }
        });
        txtSearchDH.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchDHKeyReleased(evt);
            }
        });

        lblSearch2.setBackground(new java.awt.Color(3, 100, 117));
        lblSearch2.setFont(new java.awt.Font("Verdana", 1, 20)); // NOI18N
        lblSearch2.setForeground(new java.awt.Color(3, 100, 117));
        lblSearch2.setText("Tìm kiếm");

        javax.swing.GroupLayout pnKhoiDieuHanhLayout = new javax.swing.GroupLayout(pnKhoiDieuHanh);
        pnKhoiDieuHanh.setLayout(pnKhoiDieuHanhLayout);
        pnKhoiDieuHanhLayout.setHorizontalGroup(
            pnKhoiDieuHanhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 858, Short.MAX_VALUE)
            .addComponent(jSeparator2)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnKhoiDieuHanhLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblSearch2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtSearchDH, javax.swing.GroupLayout.PREFERRED_SIZE, 363, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jButton3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnKhoiDieuHanhLayout.setVerticalGroup(
            pnKhoiDieuHanhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnKhoiDieuHanhLayout.createSequentialGroup()
                .addContainerGap(74, Short.MAX_VALUE)
                .addGroup(pnKhoiDieuHanhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblSearch2)
                    .addComponent(txtSearchDH, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 66, Short.MAX_VALUE)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPane1.addTab("Khối điều hành", pnKhoiDieuHanh);

        pnKhoiVanPhong.setBackground(new java.awt.Color(254, 255, 250));
        pnKhoiVanPhong.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 0, new java.awt.Color(107, 195, 196)));

        jSeparator5.setBackground(new java.awt.Color(3, 100, 117));
        jSeparator5.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(3, 100, 117)));

        tblVP.setBackground(new java.awt.Color(254, 255, 250));
        tblVP.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tblVP.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        tblVP.setForeground(new java.awt.Color(3, 100, 117));
        tblVP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã nhân viên", "Họ tên", "Ngày sinh", "Lương cơ bản", "Phụ cấp", "Phụ cấp khác", "Tháng", "Năm", "Số ngày công", "Số ngày nghỉ", "Số ngày làm thêm"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblVP.setGridColor(new java.awt.Color(3, 100, 117));
        tblVP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblVPMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tblVP);

        jButton4.setBackground(new java.awt.Color(254, 255, 250));
        jButton4.setFont(new java.awt.Font("Trebuchet MS", 0, 20)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/qlnstl/src/magnifier.png"))); // NOI18N
        jButton4.setBorder(null);
        jButton4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        txtSearchVP.setBackground(new java.awt.Color(254, 255, 250));
        txtSearchVP.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        txtSearchVP.setForeground(new java.awt.Color(204, 204, 204));
        txtSearchVP.setText("word...");
        txtSearchVP.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(107, 195, 196)));
        txtSearchVP.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtSearchVPFocusGained(evt);
            }
        });
        txtSearchVP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchVPKeyReleased(evt);
            }
        });

        lblSearch3.setBackground(new java.awt.Color(3, 100, 117));
        lblSearch3.setFont(new java.awt.Font("Verdana", 1, 20)); // NOI18N
        lblSearch3.setForeground(new java.awt.Color(3, 100, 117));
        lblSearch3.setText("Tìm kiếm");

        javax.swing.GroupLayout pnKhoiVanPhongLayout = new javax.swing.GroupLayout(pnKhoiVanPhong);
        pnKhoiVanPhong.setLayout(pnKhoiVanPhongLayout);
        pnKhoiVanPhongLayout.setHorizontalGroup(
            pnKhoiVanPhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 858, Short.MAX_VALUE)
            .addComponent(jSeparator5, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnKhoiVanPhongLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblSearch3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtSearchVP, javax.swing.GroupLayout.PREFERRED_SIZE, 363, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jButton4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnKhoiVanPhongLayout.setVerticalGroup(
            pnKhoiVanPhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnKhoiVanPhongLayout.createSequentialGroup()
                .addContainerGap(71, Short.MAX_VALUE)
                .addGroup(pnKhoiVanPhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblSearch3)
                    .addComponent(txtSearchVP, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 72, Short.MAX_VALUE)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPane1.addTab("Khối văn phòng", pnKhoiVanPhong);

        lblPhuCapVC.setBackground(new java.awt.Color(254, 255, 250));
        lblPhuCapVC.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 0, new java.awt.Color(107, 195, 196)));

        jSeparator4.setBackground(new java.awt.Color(3, 100, 117));
        jSeparator4.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(3, 100, 117)));

        tblVC.setBackground(new java.awt.Color(254, 255, 250));
        tblVC.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tblVC.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        tblVC.setForeground(new java.awt.Color(3, 100, 117));
        tblVC.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã nhân viên", "Họ tên", "Ngày sinh", "Lương cơ bản", "Phụ cấp", "Phụ cấp khác", "Tháng", "Năm", "Số ngày công", "Số ngày nghỉ", "Số ngày làm thêm"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblVC.setGridColor(new java.awt.Color(3, 100, 117));
        tblVC.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblVCMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tblVC);

        jButton5.setBackground(new java.awt.Color(254, 255, 250));
        jButton5.setFont(new java.awt.Font("Trebuchet MS", 0, 20)); // NOI18N
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/qlnstl/src/magnifier.png"))); // NOI18N
        jButton5.setBorder(null);
        jButton5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        txtSearchVC.setBackground(new java.awt.Color(254, 255, 250));
        txtSearchVC.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        txtSearchVC.setForeground(new java.awt.Color(204, 204, 204));
        txtSearchVC.setText("word...");
        txtSearchVC.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(107, 195, 196)));
        txtSearchVC.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtSearchVCFocusGained(evt);
            }
        });
        txtSearchVC.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchVCKeyReleased(evt);
            }
        });

        lblSearch4.setBackground(new java.awt.Color(3, 100, 117));
        lblSearch4.setFont(new java.awt.Font("Verdana", 1, 20)); // NOI18N
        lblSearch4.setForeground(new java.awt.Color(3, 100, 117));
        lblSearch4.setText("Tìm kiếm");

        javax.swing.GroupLayout lblPhuCapVCLayout = new javax.swing.GroupLayout(lblPhuCapVC);
        lblPhuCapVC.setLayout(lblPhuCapVCLayout);
        lblPhuCapVCLayout.setHorizontalGroup(
            lblPhuCapVCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator4)
            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 858, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, lblPhuCapVCLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblSearch4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtSearchVC, javax.swing.GroupLayout.PREFERRED_SIZE, 363, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jButton5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        lblPhuCapVCLayout.setVerticalGroup(
            lblPhuCapVCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, lblPhuCapVCLayout.createSequentialGroup()
                .addContainerGap(71, Short.MAX_VALUE)
                .addGroup(lblPhuCapVCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblSearch4)
                    .addComponent(txtSearchVC, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 70, Short.MAX_VALUE)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPane1.addTab("Khối vận chuyển", lblPhuCapVC);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 517, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblTVMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTVMouseClicked

    }//GEN-LAST:event_tblTVMouseClicked

    private void tblSXMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSXMouseClicked

    }//GEN-LAST:event_tblSXMouseClicked

    private void tblDHMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDHMouseClicked

    }//GEN-LAST:event_tblDHMouseClicked

    private void tblVPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblVPMouseClicked

    }//GEN-LAST:event_tblVPMouseClicked

    private void tblVCMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblVCMouseClicked

    }//GEN-LAST:event_tblVCMouseClicked

    private void txtSearchTVFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSearchTVFocusGained
        // TODO add your handling code here:
        if(txtSearchTV.getText().trim().toLowerCase().equals("word...")) {
            txtSearchTV.setText("");
            txtSearchTV.setForeground(new Color(3, 100, 117));
            txtSearchTV.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
        }
    }//GEN-LAST:event_txtSearchTVFocusGained

    private void txtSearchTVKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchTVKeyReleased
        // TODO add your handling code here:
        String search = txtSearchTV.getText().toLowerCase();
        if(search.equals("")) showTV();
        else{
            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=QLNS;" + "username=sa;password=123456");
                PreparedStatement ps = conn.prepareStatement("SELECT TenBoPhan, TenPhong, TblHoSoThuViec.MaNV, HoTen, NgaySinh, LuongTViec, Thang, Nam, SoNgayCong, SoNgayNghi, SoGioLamThem "
                        + "FROM TblBangCongThuViec, TblHoSoThuViec "
                        + "WHERE TblBangCongThuViec.MaNV=TblHoSoThuViec.MaNV AND TblHoSoThuViec.MaNV=?");
                ps.setString(1, search);
                ResultSet rs = ps.executeQuery();
                tblTV.setModel(DbUtils.resultSetToTableModel(rs));
                ps.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }//GEN-LAST:event_txtSearchTVKeyReleased

    private void txtSearchSXFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSearchSXFocusGained
        // TODO add your handling code here:
        if(txtSearchSX.getText().trim().toLowerCase().equals("word...")) {
            txtSearchSX.setText("");
            txtSearchSX.setForeground(new Color(3, 100, 117));
            txtSearchSX.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
        }
    }//GEN-LAST:event_txtSearchSXFocusGained

    private void txtSearchSXKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchSXKeyReleased
        // TODO add your handling code here:
        String search = txtSearchSX.getText().toLowerCase();
        if(search.equals("")) showSX();
        else{
            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=QLNS;" + "username=sa;password=123456");
                PreparedStatement ps = conn.prepareStatement("SELECT TblTTNVCoBan.MaNV, HoTen, NgaySinh, LCB, PhuCapCVu, Thang, Nam, SoNgayCongThang, SoNgayNghi, SoGioLamThem "
                        + "FROM TblCongKhoiSanXuat, TblTTNVCoBan "
                        + "WHERE TblCongKhoiSanXuat.MaNV=TblTTNVCoBan.MaNV AND TblTTNVCoBan.MaNV=?");
                ps.setString(1, search);
                ResultSet rs = ps.executeQuery();
                tblSX.setModel(DbUtils.resultSetToTableModel(rs));
                ps.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }//GEN-LAST:event_txtSearchSXKeyReleased

    private void txtSearchDHFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSearchDHFocusGained
        // TODO add your handling code here:
        if(txtSearchDH.getText().trim().toLowerCase().equals("word...")) {
            txtSearchDH.setText("");
            txtSearchDH.setForeground(new Color(3, 100, 117));
            txtSearchDH.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
        }
    }//GEN-LAST:event_txtSearchDHFocusGained

    private void txtSearchDHKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchDHKeyReleased
        // TODO add your handling code here:
        String search = txtSearchDH.getText().toLowerCase();
        if(search.equals("")) showDH();
        else{
            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=QLNS;" + "username=sa;password=123456");
                PreparedStatement ps = conn.prepareStatement("SELECT TblTTNVCoBan.MaNV, HoTen, NgaySinh, LCB, PhuCapCVu, PhuCapKhac, Thang, Nam, SoNgayCongThang, SoNgayNghi, SoGioLamThem "
                        + "FROM TblCongKhoiDieuHanh, TblTTNVCoBan "
                        + "WHERE TblCongKhoiDieuHanh.MaNV=TblTTNVCoBan.MaNV AND TblTTNVCoBan.MaNV=?");
                ps.setString(1, search);
                ResultSet rs = ps.executeQuery();
                tblDH.setModel(DbUtils.resultSetToTableModel(rs));
                ps.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }//GEN-LAST:event_txtSearchDHKeyReleased

    private void txtSearchVPFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSearchVPFocusGained
        // TODO add your handling code here:
        if(txtSearchVP.getText().trim().toLowerCase().equals("word...")) {
            txtSearchVP.setText("");
            txtSearchVP.setForeground(new Color(3, 100, 117));
            txtSearchVP.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
        }
    }//GEN-LAST:event_txtSearchVPFocusGained

    private void txtSearchVPKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchVPKeyReleased
        // TODO add your handling code here:
        String search = txtSearchVP.getText().toLowerCase();
        if(search.equals("")) showVP();
        else{
            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=QLNS;" + "username=sa;password=123456");
                PreparedStatement ps = conn.prepareStatement("SELECT TblTTNVCoBan.MaNV, HoTen, NgaySinh, LCB, PhuCapCVu, PhuCapKhac, Thang, Nam, SoNgayCongThang, SoNgayNghi, SoGioLamThem "
                        + "FROM TblCongKhoiVanPHong, TblTTNVCoBan "
                        + "WHERE TblCongKhoiVanPHong.MaNV=TblTTNVCoBan.MaNV AND TblTTNVCoBan.MaNV=?");
                ps.setString(1, search);
                ResultSet rs = ps.executeQuery();
                tblVP.setModel(DbUtils.resultSetToTableModel(rs));
                ps.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }//GEN-LAST:event_txtSearchVPKeyReleased

    private void txtSearchVCFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSearchVCFocusGained
        // TODO add your handling code here:
        if(txtSearchVC.getText().trim().toLowerCase().equals("word...")) {
            txtSearchVC.setText("");
            txtSearchVC.setForeground(new Color(3, 100, 117));
            txtSearchVC.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
        }
    }//GEN-LAST:event_txtSearchVCFocusGained

    private void txtSearchVCKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchVCKeyReleased
        // TODO add your handling code here:
        String search = txtSearchVC.getText().toLowerCase();
        if(search.equals("")) showVP();
        else{
            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=QLNS;" + "username=sa;password=123456");
                PreparedStatement ps = conn.prepareStatement("SELECT TblTTNVCoBan.MaNV, HoTen, NgaySinh, LCB, PhuCapCVu, PhuCapKhac, Thang, Nam, SoNgayCongThang, SoNgayNghi, SoGioLamThem "
                        + "FROM TblCongKhoiVanChuyen, TblTTNVCoBan "
                        + "WHERE TblCongKhoiVanChuyen.MaNV=TblTTNVCoBan.MaNV AND TblTTNVCoBan.MaNV=?");
                ps.setString(1, search);
                ResultSet rs = ps.executeQuery();
                tblVC.setModel(DbUtils.resultSetToTableModel(rs));
                ps.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }//GEN-LAST:event_txtSearchVCKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JPanel lblPhuCapVC;
    private javax.swing.JLabel lblSearch;
    private javax.swing.JLabel lblSearch1;
    private javax.swing.JLabel lblSearch2;
    private javax.swing.JLabel lblSearch3;
    private javax.swing.JLabel lblSearch4;
    private javax.swing.JPanel pnKhoiDieuHanh;
    private javax.swing.JPanel pnKhoiSanXuat;
    private javax.swing.JPanel pnKhoiVanPhong;
    private javax.swing.JPanel pnThuViec;
    private javax.swing.JTable tblDH;
    private javax.swing.JTable tblSX;
    private javax.swing.JTable tblTV;
    private javax.swing.JTable tblVC;
    private javax.swing.JTable tblVP;
    private javax.swing.JTextField txtSearchDH;
    private javax.swing.JTextField txtSearchSX;
    private javax.swing.JTextField txtSearchTV;
    private javax.swing.JTextField txtSearchVC;
    private javax.swing.JTextField txtSearchVP;
    // End of variables declaration//GEN-END:variables
}
