/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qlnstl.DTO;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import qlnstl.BangLuongCongTy;
import qlnstl.VanDeTangLuong;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Ngoc
 */
public class frmLuong extends javax.swing.JFrame {

    DateFormat dateFormat = new SimpleDateFormat("MM/dd/YYYY");
    
    Date dateLuong = new Date();
    Date dateTangLuong = new Date();
    
    Calendar cal = Calendar.getInstance();
    
    DefaultTableModel modelLuong;
    DefaultTableModel modelTangLuong;
    
    private Connection conn;
    /**
     * Creates new form frmLuong
     */
    public frmLuong() {
        initComponents();
        
        getCBboxMaNV();
        
        showBL();
        showTL();
        
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        
        modelLuong = (DefaultTableModel)tblLuong.getModel();
        modelTangLuong = (DefaultTableModel)tblTangLuong.getModel();
        
        txtNgaySua.setText(" " + dateFormat.format(dateLuong));
        txtNgayNhap.setText(" " + dateFormat.format(dateLuong));
        txtNgaySuaPC.setText(" " + dateFormat.format(dateLuong));
        
        txtNgayTang.setText(" " + dateFormat.format(dateTangLuong));
    }
    
    private void getCBboxMaNV() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=QLNS;" + "username=sa;password=123456");
            PreparedStatement ps = conn.prepareStatement("SELECT MaNV FROM TblTTNVCoBan ORDER BY MaNV");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                cbxMaNhanVien.addItem(rs.getString("MaNV"));
            }
            //cbxID.setModel(modelCombo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public ArrayList<BangLuongCongTy> blList() {
        ArrayList<BangLuongCongTy> blList = new ArrayList<>();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=QLNS;" + "username=sa;password=123456");
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM TblBangLuongCTy");
            ResultSet rs = ps.executeQuery();
            BangLuongCongTy bl;
            while (rs.next()) {
                bl = new BangLuongCongTy(
                    rs.getString("MaLuong"),
                    rs.getString("ChucVu"),
                    rs.getString("ChucDanh"),
                    rs.getString("LCB"),
                    rs.getString("PCChucVu"),
                    rs.getString("NgayNhap"),
                    rs.getString("LCBMoi"),
                    rs.getString("NgaySua"),
                    rs.getString("LyDo"),
                    rs.getString("PCCVuMoi"),
                    rs.getString("NgaySuaPC"),
                    rs.getString("GhiChu")
                );
                blList.add(bl);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return blList;
    }
    
    public void showBL() {
        ArrayList<BangLuongCongTy> bl = blList();
        DefaultTableModel model = (DefaultTableModel)tblLuong.getModel();
        Object[] row = new Object[17];
        for (int i=0;i<bl.size();i++) {
            row[0]=bl.get(i).getMaLuong();
            row[1]=bl.get(i).getChucVu();
            row[2]=bl.get(i).getChucDanh();
            row[3]=bl.get(i).getLuongCoBan();
            row[4]=bl.get(i).getPhuCapChucVu();
            row[5]=bl.get(i).getNgayNhap();
            row[6]=bl.get(i).getLuongCoBanMoi();
            row[7]=bl.get(i).getNgaySua();
            row[8]=bl.get(i).getLyDo();
            row[9]=bl.get(i).getPhuCapCVuMoi();
            row[10]=bl.get(i).getNgaySua();
            row[11]=bl.get(i).getGhiChu();
            model.addRow(row);
        };
    }
    
    public ArrayList<VanDeTangLuong> tlList() {
        ArrayList<VanDeTangLuong> tlList = new ArrayList<>();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=QLNS;" + "username=sa;password=123456");
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM TblTangLuong");
            ResultSet rs = ps.executeQuery();
            VanDeTangLuong tl;
            while (rs.next()) {
                tl = new VanDeTangLuong(
                    rs.getString("MaNV"),
                    rs.getString("HoTen"),
                    rs.getString("GioiTinh"),
                    rs.getString("ChucVu"),
                    rs.getString("ChucDanh"),
                    rs.getString("LCBCu"),
                    rs.getString("LCBMoi"),
                    rs.getString("PCapCu"),
                    rs.getString("PcapMoi"),
                    rs.getString("NgayTang"),
                    rs.getString("LyDo")
                );
                tlList.add(tl);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return tlList;
    }
    
    public void showTL() {
        ArrayList<VanDeTangLuong> tl = tlList();
        DefaultTableModel model = (DefaultTableModel)tblTangLuong.getModel();
        Object[] row = new Object[17];
        for (int i=0;i<tl.size();i++) {
            row[0]=tl.get(i).getMaNhanVien();
            row[1]=tl.get(i).getHoTen();
            row[2]=tl.get(i).getGioiTinh();
            row[3]=tl.get(i).getChucVu();
            row[4]=tl.get(i).getChucDanh();
            row[5]=tl.get(i).getLuongCoBanCu();
            row[6]=tl.get(i).getLuongCoBanMoi();
            row[7]=tl.get(i).getPhuCapCVCu();
            row[8]=tl.get(i).getPhuCapCVMoi();
            row[9]=tl.get(i).getNgayTang();
            row[10]=tl.get(i).getLyDo();
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

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        lblLogo = new javax.swing.JLabel();
        lblClose = new javax.swing.JLabel();
        lblMini = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        pnLuongNV = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        lblHeader = new javax.swing.JLabel();
        lblMaLuong = new javax.swing.JLabel();
        txtMaLuong = new javax.swing.JTextField();
        lblGhiChu = new javax.swing.JLabel();
        txtGhiChu = new javax.swing.JTextField();
        lblChucDanh = new javax.swing.JLabel();
        txtChucDanh = new javax.swing.JTextField();
        lblChucVu = new javax.swing.JLabel();
        txtChucVu = new javax.swing.JTextField();
        lblNgaySua = new javax.swing.JLabel();
        txtNgaySua = new javax.swing.JTextField();
        lblNgayNhap = new javax.swing.JLabel();
        txtNgayNhap = new javax.swing.JTextField();
        lblLuongCBMoi = new javax.swing.JLabel();
        txtLuongCBMoi = new javax.swing.JTextField();
        lblLuongCB = new javax.swing.JLabel();
        txtLuongCB = new javax.swing.JTextField();
        lblLyDo = new javax.swing.JLabel();
        txtLyDo = new javax.swing.JTextField();
        lblPhuCapCV = new javax.swing.JLabel();
        txtPhuCapCV = new javax.swing.JTextField();
        lblNgaySuaPC = new javax.swing.JLabel();
        txtNgaySuaPC = new javax.swing.JTextField();
        lblPCCVMoi = new javax.swing.JLabel();
        txtPCCVMoi = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel14 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        lblHeader1 = new javax.swing.JLabel();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnMoi = new javax.swing.JButton();
        btnXuat = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblLuong = new javax.swing.JTable();
        pnTangLuong = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        lblHeader3 = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        lblHeader7 = new javax.swing.JLabel();
        btnThem_1 = new javax.swing.JButton();
        btnSua_1 = new javax.swing.JButton();
        btnXoa_1 = new javax.swing.JButton();
        btnMoi_1 = new javax.swing.JButton();
        btnXuat_1 = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JSeparator();
        jScrollPane7 = new javax.swing.JScrollPane();
        tblTangLuong = new javax.swing.JTable();
        lblMaNhanVien = new javax.swing.JLabel();
        cbxMaNhanVien = new javax.swing.JComboBox<>();
        lblHoTen = new javax.swing.JLabel();
        txtHoTen = new javax.swing.JTextField();
        lblPCCVMoi_1 = new javax.swing.JLabel();
        txtPCCVMoi_1 = new javax.swing.JTextField();
        lblChucDanh_1 = new javax.swing.JLabel();
        txtChucDanh_1 = new javax.swing.JTextField();
        lblChucVu_1 = new javax.swing.JLabel();
        txtChucVu_1 = new javax.swing.JTextField();
        lblNgayTang = new javax.swing.JLabel();
        txtNgayTang = new javax.swing.JTextField();
        lblGioiTinh = new javax.swing.JLabel();
        txtGioiTinh = new javax.swing.JTextField();
        lblLuongCBMoi_1 = new javax.swing.JLabel();
        txtLuongCBMoi_1 = new javax.swing.JTextField();
        lblLuongCBCu = new javax.swing.JLabel();
        txtLuongCBCu = new javax.swing.JTextField();
        lblLyDo_1 = new javax.swing.JLabel();
        txtLyDo_1 = new javax.swing.JTextField();
        lblPCCVCu = new javax.swing.JLabel();
        txtPCCVCu = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel2.setBackground(new java.awt.Color(107, 195, 196));

        jPanel3.setBackground(new java.awt.Color(3, 100, 117));
        jPanel3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel3.setPreferredSize(new java.awt.Dimension(937, 64));

        lblLogo.setFont(new java.awt.Font("Verdana", 0, 36)); // NOI18N
        lblLogo.setForeground(new java.awt.Color(254, 255, 250));
        lblLogo.setText("Quản lý lương");

        lblClose.setFont(new java.awt.Font("Trebuchet MS", 0, 20)); // NOI18N
        lblClose.setForeground(new java.awt.Color(254, 255, 250));
        lblClose.setText("X");
        lblClose.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblClose.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lblCloseMousePressed(evt);
            }
        });

        lblMini.setFont(new java.awt.Font("Trebuchet MS", 0, 24)); // NOI18N
        lblMini.setForeground(new java.awt.Color(254, 255, 250));
        lblMini.setText("-");
        lblMini.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblMini.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblMiniMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(lblLogo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblMini, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblClose)
                .addGap(21, 21, 21))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(lblLogo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblClose, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblMini, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(5, 5, 5)))
                .addGap(6010, 6010, 6010))
        );

        jPanel4.setBackground(new java.awt.Color(107, 195, 196));

        pnLuongNV.setBackground(new java.awt.Color(255, 255, 255));
        pnLuongNV.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 0, new java.awt.Color(107, 195, 196)));

        jPanel6.setBackground(new java.awt.Color(3, 100, 117));

        lblHeader.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        lblHeader.setForeground(new java.awt.Color(254, 255, 250));
        lblHeader.setText("Thông tin về lương nhân viên");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addComponent(lblHeader)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblHeader, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
        );

        lblMaLuong.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        lblMaLuong.setForeground(new java.awt.Color(3, 100, 117));
        lblMaLuong.setText("Mã lương");

        txtMaLuong.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        txtMaLuong.setForeground(new java.awt.Color(3, 100, 117));
        txtMaLuong.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(3, 100, 117)));
        txtMaLuong.setEnabled(false);
        txtMaLuong.setMinimumSize(new java.awt.Dimension(3, 26));
        txtMaLuong.setPreferredSize(new java.awt.Dimension(36, 28));

        lblGhiChu.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        lblGhiChu.setForeground(new java.awt.Color(3, 100, 117));
        lblGhiChu.setText("Ghi chú");

        txtGhiChu.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        txtGhiChu.setForeground(new java.awt.Color(3, 100, 117));
        txtGhiChu.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(3, 100, 117)));
        txtGhiChu.setMinimumSize(new java.awt.Dimension(3, 26));
        txtGhiChu.setPreferredSize(new java.awt.Dimension(3, 26));
        txtGhiChu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGhiChuActionPerformed(evt);
            }
        });

        lblChucDanh.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        lblChucDanh.setForeground(new java.awt.Color(3, 100, 117));
        lblChucDanh.setText("Chức danh");

        txtChucDanh.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        txtChucDanh.setForeground(new java.awt.Color(3, 100, 117));
        txtChucDanh.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(3, 100, 117)));
        txtChucDanh.setMinimumSize(new java.awt.Dimension(3, 26));
        txtChucDanh.setPreferredSize(new java.awt.Dimension(36, 28));

        lblChucVu.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        lblChucVu.setForeground(new java.awt.Color(3, 100, 117));
        lblChucVu.setText("Chức vụ");

        txtChucVu.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        txtChucVu.setForeground(new java.awt.Color(3, 100, 117));
        txtChucVu.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(3, 100, 117)));
        txtChucVu.setMinimumSize(new java.awt.Dimension(3, 26));
        txtChucVu.setPreferredSize(new java.awt.Dimension(36, 28));

        lblNgaySua.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        lblNgaySua.setForeground(new java.awt.Color(3, 100, 117));
        lblNgaySua.setText("Ngày sửa");

        txtNgaySua.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        txtNgaySua.setForeground(new java.awt.Color(3, 100, 117));
        txtNgaySua.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(3, 100, 117)));
        txtNgaySua.setMinimumSize(new java.awt.Dimension(3, 26));
        txtNgaySua.setPreferredSize(new java.awt.Dimension(36, 28));

        lblNgayNhap.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        lblNgayNhap.setForeground(new java.awt.Color(3, 100, 117));
        lblNgayNhap.setText("Ngày nhập");

        txtNgayNhap.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        txtNgayNhap.setForeground(new java.awt.Color(3, 100, 117));
        txtNgayNhap.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(3, 100, 117)));
        txtNgayNhap.setMinimumSize(new java.awt.Dimension(3, 26));
        txtNgayNhap.setPreferredSize(new java.awt.Dimension(36, 28));

        lblLuongCBMoi.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        lblLuongCBMoi.setForeground(new java.awt.Color(3, 100, 117));
        lblLuongCBMoi.setText("Lương CB mới");

        txtLuongCBMoi.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        txtLuongCBMoi.setForeground(new java.awt.Color(3, 100, 117));
        txtLuongCBMoi.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(3, 100, 117)));
        txtLuongCBMoi.setMinimumSize(new java.awt.Dimension(3, 26));
        txtLuongCBMoi.setPreferredSize(new java.awt.Dimension(36, 28));
        txtLuongCBMoi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtLuongCBMoiKeyPressed(evt);
            }
        });

        lblLuongCB.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        lblLuongCB.setForeground(new java.awt.Color(3, 100, 117));
        lblLuongCB.setText("Lương cơ bản");

        txtLuongCB.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        txtLuongCB.setForeground(new java.awt.Color(3, 100, 117));
        txtLuongCB.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(3, 100, 117)));
        txtLuongCB.setMinimumSize(new java.awt.Dimension(3, 26));
        txtLuongCB.setPreferredSize(new java.awt.Dimension(36, 28));
        txtLuongCB.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtLuongCBKeyPressed(evt);
            }
        });

        lblLyDo.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        lblLyDo.setForeground(new java.awt.Color(3, 100, 117));
        lblLyDo.setText("Lý do");

        txtLyDo.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        txtLyDo.setForeground(new java.awt.Color(3, 100, 117));
        txtLyDo.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(3, 100, 117)));
        txtLyDo.setMinimumSize(new java.awt.Dimension(3, 26));
        txtLyDo.setPreferredSize(new java.awt.Dimension(36, 28));

        lblPhuCapCV.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        lblPhuCapCV.setForeground(new java.awt.Color(3, 100, 117));
        lblPhuCapCV.setText("PC chức vụ");

        txtPhuCapCV.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        txtPhuCapCV.setForeground(new java.awt.Color(3, 100, 117));
        txtPhuCapCV.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(3, 100, 117)));
        txtPhuCapCV.setMinimumSize(new java.awt.Dimension(3, 26));
        txtPhuCapCV.setPreferredSize(new java.awt.Dimension(36, 28));
        txtPhuCapCV.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPhuCapCVKeyPressed(evt);
            }
        });

        lblNgaySuaPC.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        lblNgaySuaPC.setForeground(new java.awt.Color(3, 100, 117));
        lblNgaySuaPC.setText("Ngày sửa PC");

        txtNgaySuaPC.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        txtNgaySuaPC.setForeground(new java.awt.Color(3, 100, 117));
        txtNgaySuaPC.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(3, 100, 117)));
        txtNgaySuaPC.setMinimumSize(new java.awt.Dimension(3, 26));
        txtNgaySuaPC.setPreferredSize(new java.awt.Dimension(36, 28));

        lblPCCVMoi.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        lblPCCVMoi.setForeground(new java.awt.Color(3, 100, 117));
        lblPCCVMoi.setText("PC CV mới");

        txtPCCVMoi.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        txtPCCVMoi.setForeground(new java.awt.Color(3, 100, 117));
        txtPCCVMoi.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(3, 100, 117)));
        txtPCCVMoi.setMinimumSize(new java.awt.Dimension(3, 26));
        txtPCCVMoi.setPreferredSize(new java.awt.Dimension(36, 28));
        txtPCCVMoi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPCCVMoiKeyPressed(evt);
            }
        });

        jSeparator1.setBackground(new java.awt.Color(3, 100, 117));
        jSeparator1.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(3, 100, 117)));

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));
        jPanel14.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 1, 1, 1, new java.awt.Color(107, 195, 196)));

        jPanel8.setBackground(new java.awt.Color(3, 100, 117));

        lblHeader1.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        lblHeader1.setForeground(new java.awt.Color(254, 255, 250));
        lblHeader1.setText("Chức năng");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addComponent(lblHeader1)
                .addContainerGap(718, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblHeader1, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
        );

        btnThem.setBackground(new java.awt.Color(3, 100, 117));
        btnThem.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        btnThem.setForeground(new java.awt.Color(107, 195, 196));
        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/qlnstl/src/add-group.png"))); // NOI18N
        btnThem.setText("Thêm");
        btnThem.setBorder(null);
        btnThem.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSua.setBackground(new java.awt.Color(3, 100, 117));
        btnSua.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        btnSua.setForeground(new java.awt.Color(107, 195, 196));
        btnSua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/qlnstl/src/edit.png"))); // NOI18N
        btnSua.setText("Sửa");
        btnSua.setBorder(null);
        btnSua.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnXoa.setBackground(new java.awt.Color(3, 100, 117));
        btnXoa.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        btnXoa.setForeground(new java.awt.Color(107, 195, 196));
        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/qlnstl/src/remove-user.png"))); // NOI18N
        btnXoa.setText("Xoá");
        btnXoa.setBorder(null);
        btnXoa.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnMoi.setBackground(new java.awt.Color(3, 100, 117));
        btnMoi.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        btnMoi.setForeground(new java.awt.Color(107, 195, 196));
        btnMoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/qlnstl/src/add-group.png"))); // NOI18N
        btnMoi.setText("Mới");
        btnMoi.setBorder(null);
        btnMoi.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMoiActionPerformed(evt);
            }
        });

        btnXuat.setBackground(new java.awt.Color(3, 100, 117));
        btnXuat.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        btnXuat.setForeground(new java.awt.Color(107, 195, 196));
        btnXuat.setIcon(new javax.swing.ImageIcon("F:\\QLNSTL\\src\\qlnstl\\src\\document.png")); // NOI18N
        btnXuat.setBorder(null);
        btnXuat.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnXuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(159, 159, 159)
                .addComponent(btnMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(65, 65, 65)
                .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(65, 65, 65)
                .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(65, 65, 65)
                .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(65, 65, 65)
                .addComponent(btnXuat, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnXuat, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        tblLuong.setBackground(new java.awt.Color(254, 255, 250));
        tblLuong.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tblLuong.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        tblLuong.setForeground(new java.awt.Color(3, 100, 117));
        tblLuong.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã lương", "Chức vụ", "Chức danh", "Lương cơ bản", "Phụ cấp chức vụ", "Ngày nhập", "Lương cơ bản mới", "Ngày sửa", "Lý do", "Phụ cấp CV mới", "Ngày sửa PC", "Ghi chú"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblLuong.setGridColor(new java.awt.Color(3, 100, 117));
        tblLuong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblLuongMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblLuong);

        javax.swing.GroupLayout pnLuongNVLayout = new javax.swing.GroupLayout(pnLuongNV);
        pnLuongNV.setLayout(pnLuongNVLayout);
        pnLuongNVLayout.setHorizontalGroup(
            pnLuongNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jSeparator1)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnLuongNVLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(pnLuongNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnLuongNVLayout.createSequentialGroup()
                        .addComponent(lblNgaySuaPC)
                        .addGap(18, 18, 18)
                        .addComponent(txtNgaySuaPC, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(lblPCCVMoi))
                    .addGroup(pnLuongNVLayout.createSequentialGroup()
                        .addGroup(pnLuongNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnLuongNVLayout.createSequentialGroup()
                                .addGroup(pnLuongNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblChucDanh)
                                    .addComponent(lblChucVu))
                                .addGap(33, 33, 33))
                            .addGroup(pnLuongNVLayout.createSequentialGroup()
                                .addComponent(lblMaLuong)
                                .addGap(43, 43, 43)))
                        .addGroup(pnLuongNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtMaLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtChucDanh, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addGroup(pnLuongNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblNgaySua)
                            .addComponent(lblPhuCapCV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblNgayNhap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(18, 18, 18)
                .addGroup(pnLuongNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtPCCVMoi, javax.swing.GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE)
                    .addComponent(txtNgaySua, javax.swing.GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE)
                    .addComponent(txtPhuCapCV, javax.swing.GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE)
                    .addComponent(txtNgayNhap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(29, 29, 29)
                .addGroup(pnLuongNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnLuongNVLayout.createSequentialGroup()
                        .addGroup(pnLuongNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblLuongCB)
                            .addComponent(lblLuongCBMoi)
                            .addComponent(lblGhiChu))
                        .addGroup(pnLuongNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(pnLuongNVLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(pnLuongNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtLuongCB, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtLuongCBMoi, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnLuongNVLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtGhiChu, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(pnLuongNVLayout.createSequentialGroup()
                        .addComponent(lblLyDo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtLyDo, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(32, 32, 32))
        );
        pnLuongNVLayout.setVerticalGroup(
            pnLuongNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnLuongNVLayout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17)
                .addGroup(pnLuongNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnLuongNVLayout.createSequentialGroup()
                        .addGroup(pnLuongNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblMaLuong)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnLuongNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtNgayNhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblNgayNhap)
                                .addComponent(txtMaLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(pnLuongNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblChucVu)
                            .addComponent(lblNgaySua)
                            .addComponent(txtNgaySua, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblLuongCBMoi)
                            .addComponent(txtChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnLuongNVLayout.createSequentialGroup()
                        .addGroup(pnLuongNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtLuongCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblLuongCB))
                        .addGap(18, 18, 18)
                        .addComponent(txtLuongCBMoi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(pnLuongNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnLuongNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtLyDo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblLyDo))
                    .addGroup(pnLuongNVLayout.createSequentialGroup()
                        .addGroup(pnLuongNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblChucDanh)
                            .addComponent(txtChucDanh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblPhuCapCV)
                            .addComponent(txtPhuCapCV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(pnLuongNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnLuongNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtNgaySuaPC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblNgaySuaPC))
                    .addGroup(pnLuongNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblGhiChu)
                        .addComponent(txtGhiChu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnLuongNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtPCCVMoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblPCCVMoi)))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Lương công ty", pnLuongNV);

        pnTangLuong.setBackground(new java.awt.Color(255, 255, 255));
        pnTangLuong.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 0, new java.awt.Color(107, 195, 196)));

        jPanel9.setBackground(new java.awt.Color(3, 100, 117));

        lblHeader3.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        lblHeader3.setForeground(new java.awt.Color(254, 255, 250));
        lblHeader3.setText("Thông tin về tăng lương nhân viên");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addComponent(lblHeader3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblHeader3, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
        );

        jPanel15.setBackground(new java.awt.Color(255, 255, 255));
        jPanel15.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 1, 1, 1, new java.awt.Color(107, 195, 196)));

        jPanel16.setBackground(new java.awt.Color(3, 100, 117));

        lblHeader7.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        lblHeader7.setForeground(new java.awt.Color(254, 255, 250));
        lblHeader7.setText("Chức năng");

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addComponent(lblHeader7)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblHeader7, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
        );

        btnThem_1.setBackground(new java.awt.Color(3, 100, 117));
        btnThem_1.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        btnThem_1.setForeground(new java.awt.Color(107, 195, 196));
        btnThem_1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/qlnstl/src/add-group.png"))); // NOI18N
        btnThem_1.setText("Thêm");
        btnThem_1.setBorder(null);
        btnThem_1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnThem_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThem_1ActionPerformed(evt);
            }
        });

        btnSua_1.setBackground(new java.awt.Color(3, 100, 117));
        btnSua_1.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        btnSua_1.setForeground(new java.awt.Color(107, 195, 196));
        btnSua_1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/qlnstl/src/edit.png"))); // NOI18N
        btnSua_1.setText("Sửa");
        btnSua_1.setBorder(null);
        btnSua_1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSua_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSua_1ActionPerformed(evt);
            }
        });

        btnXoa_1.setBackground(new java.awt.Color(3, 100, 117));
        btnXoa_1.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        btnXoa_1.setForeground(new java.awt.Color(107, 195, 196));
        btnXoa_1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/qlnstl/src/remove-user.png"))); // NOI18N
        btnXoa_1.setText("Xoá");
        btnXoa_1.setBorder(null);
        btnXoa_1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnXoa_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoa_1ActionPerformed(evt);
            }
        });

        btnMoi_1.setBackground(new java.awt.Color(3, 100, 117));
        btnMoi_1.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        btnMoi_1.setForeground(new java.awt.Color(107, 195, 196));
        btnMoi_1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/qlnstl/src/add-group.png"))); // NOI18N
        btnMoi_1.setText("Mới");
        btnMoi_1.setBorder(null);
        btnMoi_1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnMoi_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMoi_1ActionPerformed(evt);
            }
        });

        btnXuat_1.setBackground(new java.awt.Color(3, 100, 117));
        btnXuat_1.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        btnXuat_1.setForeground(new java.awt.Color(107, 195, 196));
        btnXuat_1.setIcon(new javax.swing.ImageIcon("F:\\QLNSTL\\src\\qlnstl\\src\\document.png")); // NOI18N
        btnXuat_1.setBorder(null);
        btnXuat_1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnXuat_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuat_1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(168, 168, 168)
                .addComponent(btnMoi_1, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(65, 65, 65)
                .addComponent(btnThem_1, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(65, 65, 65)
                .addComponent(btnSua_1, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(65, 65, 65)
                .addComponent(btnXoa_1, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(65, 65, 65)
                .addComponent(btnXuat_1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnThem_1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnSua_1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnXoa_1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnMoi_1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnXuat_1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jSeparator3.setBackground(new java.awt.Color(3, 100, 117));
        jSeparator3.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(3, 100, 117)));

        tblTangLuong.setBackground(new java.awt.Color(254, 255, 250));
        tblTangLuong.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tblTangLuong.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        tblTangLuong.setForeground(new java.awt.Color(3, 100, 117));
        tblTangLuong.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã nhân viên", "Họ tên", "Giới tính", "Chức vụ", "Chức danh", "Lương CB cũ", "Lương CB mới", "PCCV cũ", "PCCV mới", "Ngày tăng", "Lý do"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblTangLuong.setGridColor(new java.awt.Color(3, 100, 117));
        tblTangLuong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblTangLuongMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(tblTangLuong);

        lblMaNhanVien.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        lblMaNhanVien.setForeground(new java.awt.Color(3, 100, 117));
        lblMaNhanVien.setText("Mã nhân viên");

        cbxMaNhanVien.setBackground(new java.awt.Color(107, 195, 196));
        cbxMaNhanVien.setEditable(true);
        cbxMaNhanVien.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        cbxMaNhanVien.setForeground(new java.awt.Color(3, 100, 117));
        cbxMaNhanVien.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(3, 100, 117)));

        lblHoTen.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        lblHoTen.setForeground(new java.awt.Color(3, 100, 117));
        lblHoTen.setText("Họ tên");

        txtHoTen.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        txtHoTen.setForeground(new java.awt.Color(3, 100, 117));
        txtHoTen.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(3, 100, 117)));
        txtHoTen.setMinimumSize(new java.awt.Dimension(3, 26));
        txtHoTen.setPreferredSize(new java.awt.Dimension(36, 28));

        lblPCCVMoi_1.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        lblPCCVMoi_1.setForeground(new java.awt.Color(3, 100, 117));
        lblPCCVMoi_1.setText("PC CV mới");

        txtPCCVMoi_1.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        txtPCCVMoi_1.setForeground(new java.awt.Color(3, 100, 117));
        txtPCCVMoi_1.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(3, 100, 117)));
        txtPCCVMoi_1.setMinimumSize(new java.awt.Dimension(3, 26));
        txtPCCVMoi_1.setPreferredSize(new java.awt.Dimension(36, 28));
        txtPCCVMoi_1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPCCVMoi_1KeyPressed(evt);
            }
        });

        lblChucDanh_1.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        lblChucDanh_1.setForeground(new java.awt.Color(3, 100, 117));
        lblChucDanh_1.setText("Chức danh");

        txtChucDanh_1.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        txtChucDanh_1.setForeground(new java.awt.Color(3, 100, 117));
        txtChucDanh_1.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(3, 100, 117)));
        txtChucDanh_1.setMinimumSize(new java.awt.Dimension(3, 26));
        txtChucDanh_1.setPreferredSize(new java.awt.Dimension(36, 28));

        lblChucVu_1.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        lblChucVu_1.setForeground(new java.awt.Color(3, 100, 117));
        lblChucVu_1.setText("Chức vụ");

        txtChucVu_1.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        txtChucVu_1.setForeground(new java.awt.Color(3, 100, 117));
        txtChucVu_1.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(3, 100, 117)));
        txtChucVu_1.setMinimumSize(new java.awt.Dimension(3, 26));
        txtChucVu_1.setPreferredSize(new java.awt.Dimension(36, 28));

        lblNgayTang.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        lblNgayTang.setForeground(new java.awt.Color(3, 100, 117));
        lblNgayTang.setText("Ngày tăng");

        txtNgayTang.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        txtNgayTang.setForeground(new java.awt.Color(3, 100, 117));
        txtNgayTang.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(3, 100, 117)));
        txtNgayTang.setMinimumSize(new java.awt.Dimension(3, 26));
        txtNgayTang.setPreferredSize(new java.awt.Dimension(36, 28));

        lblGioiTinh.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        lblGioiTinh.setForeground(new java.awt.Color(3, 100, 117));
        lblGioiTinh.setText("Giới tính");

        txtGioiTinh.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        txtGioiTinh.setForeground(new java.awt.Color(3, 100, 117));
        txtGioiTinh.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(3, 100, 117)));
        txtGioiTinh.setMinimumSize(new java.awt.Dimension(3, 26));
        txtGioiTinh.setPreferredSize(new java.awt.Dimension(36, 28));

        lblLuongCBMoi_1.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        lblLuongCBMoi_1.setForeground(new java.awt.Color(3, 100, 117));
        lblLuongCBMoi_1.setText("Lương CB mới");

        txtLuongCBMoi_1.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        txtLuongCBMoi_1.setForeground(new java.awt.Color(3, 100, 117));
        txtLuongCBMoi_1.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(3, 100, 117)));
        txtLuongCBMoi_1.setMinimumSize(new java.awt.Dimension(3, 26));
        txtLuongCBMoi_1.setPreferredSize(new java.awt.Dimension(36, 28));
        txtLuongCBMoi_1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtLuongCBMoi_1KeyPressed(evt);
            }
        });

        lblLuongCBCu.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        lblLuongCBCu.setForeground(new java.awt.Color(3, 100, 117));
        lblLuongCBCu.setText("Lương CB cũ");

        txtLuongCBCu.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        txtLuongCBCu.setForeground(new java.awt.Color(3, 100, 117));
        txtLuongCBCu.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(3, 100, 117)));
        txtLuongCBCu.setMinimumSize(new java.awt.Dimension(3, 26));
        txtLuongCBCu.setPreferredSize(new java.awt.Dimension(36, 28));
        txtLuongCBCu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtLuongCBCuKeyPressed(evt);
            }
        });

        lblLyDo_1.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        lblLyDo_1.setForeground(new java.awt.Color(3, 100, 117));
        lblLyDo_1.setText("Lý do");

        txtLyDo_1.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        txtLyDo_1.setForeground(new java.awt.Color(3, 100, 117));
        txtLyDo_1.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(3, 100, 117)));
        txtLyDo_1.setMinimumSize(new java.awt.Dimension(3, 26));
        txtLyDo_1.setPreferredSize(new java.awt.Dimension(36, 28));

        lblPCCVCu.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        lblPCCVCu.setForeground(new java.awt.Color(3, 100, 117));
        lblPCCVCu.setText("PC CV cũ ");

        txtPCCVCu.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        txtPCCVCu.setForeground(new java.awt.Color(3, 100, 117));
        txtPCCVCu.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(3, 100, 117)));
        txtPCCVCu.setMinimumSize(new java.awt.Dimension(3, 26));
        txtPCCVCu.setPreferredSize(new java.awt.Dimension(36, 28));
        txtPCCVCu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPCCVCuKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout pnTangLuongLayout = new javax.swing.GroupLayout(pnTangLuong);
        pnTangLuong.setLayout(pnTangLuongLayout);
        pnTangLuongLayout.setHorizontalGroup(
            pnTangLuongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jSeparator3)
            .addGroup(pnTangLuongLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(pnTangLuongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnTangLuongLayout.createSequentialGroup()
                        .addComponent(lblHoTen)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnTangLuongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnTangLuongLayout.createSequentialGroup()
                            .addComponent(lblMaNhanVien)
                            .addGap(18, 18, 18)
                            .addComponent(cbxMaNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(pnTangLuongLayout.createSequentialGroup()
                            .addGroup(pnTangLuongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblChucDanh_1)
                                .addComponent(lblChucVu_1))
                            .addGap(38, 38, 38)
                            .addGroup(pnTangLuongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtChucVu_1, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtChucDanh_1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(29, 29, 29)
                .addGroup(pnTangLuongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblGioiTinh)
                    .addComponent(lblNgayTang)
                    .addComponent(lblPCCVCu)
                    .addComponent(lblPCCVMoi_1))
                .addGap(18, 18, 18)
                .addGroup(pnTangLuongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtPCCVMoi_1, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNgayTang, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPCCVCu, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(pnTangLuongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnTangLuongLayout.createSequentialGroup()
                        .addGroup(pnTangLuongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblLuongCBCu)
                            .addComponent(lblLuongCBMoi_1))
                        .addGap(18, 18, 18)
                        .addGroup(pnTangLuongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtLuongCBCu, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtLuongCBMoi_1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnTangLuongLayout.createSequentialGroup()
                        .addComponent(lblLyDo_1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtLyDo_1, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(35, Short.MAX_VALUE))
            .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane7)
        );
        pnTangLuongLayout.setVerticalGroup(
            pnTangLuongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnTangLuongLayout.createSequentialGroup()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnTangLuongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnTangLuongLayout.createSequentialGroup()
                        .addGroup(pnTangLuongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnTangLuongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cbxMaNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblGioiTinh)
                                .addComponent(lblMaNhanVien)))
                        .addGap(18, 18, 18)
                        .addGroup(pnTangLuongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblChucVu_1)
                            .addComponent(lblNgayTang)
                            .addComponent(txtNgayTang, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblLuongCBMoi_1)
                            .addComponent(txtChucVu_1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnTangLuongLayout.createSequentialGroup()
                        .addGroup(pnTangLuongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtLuongCBCu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblLuongCBCu))
                        .addGap(18, 18, 18)
                        .addComponent(txtLuongCBMoi_1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(pnTangLuongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnTangLuongLayout.createSequentialGroup()
                        .addGroup(pnTangLuongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnTangLuongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtChucDanh_1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblPCCVCu)
                                .addComponent(lblChucDanh_1))
                            .addGroup(pnTangLuongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtPCCVCu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblLyDo_1)))
                        .addGap(18, 18, 18)
                        .addGroup(pnTangLuongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtPCCVMoi_1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblPCCVMoi_1)
                            .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblHoTen)))
                    .addComponent(txtLyDo_1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Vấn đề tăng lương", pnTangLuong);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 507, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 856, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lblCloseMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCloseMousePressed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_lblCloseMousePressed

    private void lblMiniMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblMiniMouseClicked
        // TODO add your handling code here:
        this.setState(JFrame.ICONIFIED);
    }//GEN-LAST:event_lblMiniMouseClicked

    private void tblTangLuongMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTangLuongMouseClicked
        // TODO add your handling code here:
        int i = tblTangLuong.getSelectedRow();
        cbxMaNhanVien.setSelectedItem(modelTangLuong.getValueAt(i, 0).toString());
        txtHoTen.setText(modelTangLuong.getValueAt(i, 1).toString());
        txtGioiTinh.setText(modelTangLuong.getValueAt(i, 2).toString());
        txtChucVu_1.setText(modelTangLuong.getValueAt(i, 3).toString());
        txtChucDanh_1.setText(modelTangLuong.getValueAt(i, 4).toString());
        txtLuongCBCu.setText(modelTangLuong.getValueAt(i, 5).toString());
        txtLuongCBMoi_1.setText(modelTangLuong.getValueAt(i, 6).toString());
        txtPCCVCu.setText(modelTangLuong.getValueAt(i, 7).toString());
        txtPCCVMoi_1.setText(modelTangLuong.getValueAt(i, 8).toString());
        txtNgayTang.setText(modelTangLuong.getValueAt(i, 9).toString());
        txtLyDo_1.setText(modelTangLuong.getValueAt(i, 10).toString());
    }//GEN-LAST:event_tblTangLuongMouseClicked

    private void btnMoi_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoi_1ActionPerformed
        // TODO add your handling code here:
        cbxMaNhanVien.setSelectedIndex(0);
        txtHoTen.setText("");
        txtGioiTinh.setText("");
        txtChucVu_1.setText("");
        txtChucDanh_1.setText("");
        txtLuongCBCu.setText("");
        txtLuongCBMoi_1.setText("");
        txtPCCVCu.setText("");
        txtPCCVMoi_1.setText("");
        txtNgayTang.setText(" " + dateFormat.format(dateTangLuong));
        txtLyDo_1.setText("");
    }//GEN-LAST:event_btnMoi_1ActionPerformed

    private void btnXoa_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoa_1ActionPerformed
        // TODO add your handling code here:
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=QLNS;" + "username=sa;password=123456");
            String sql = "DELETE FROM TblTangLuong WHERE MaNV=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, cbxMaNhanVien.getSelectedItem().toString());
            ps.executeUpdate();
            DefaultTableModel model = (DefaultTableModel)tblTangLuong.getModel();
            model.setRowCount(0);
            showTL();
            JOptionPane.showMessageDialog(null, "Xoá thành công!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_btnXoa_1ActionPerformed

    private void btnSua_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSua_1ActionPerformed
        // TODO add your handling code here:
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=QLNS;" + "username=sa;password=123456");
            String sql = "UPDATE TblTangLuong SET HoTen=?, GioiTinh=?, ChucVu=?, "
            + "ChucDanh=?, LCBCu=?, LCBMoi=?, PCapCu=?, PcapMoi=?, NgayTang=?, LyDo=? WHERE MaNV=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, txtHoTen.getText());
            ps.setString(2, txtGioiTinh.getText());
            ps.setString(3, txtChucVu_1.getText());
            ps.setString(4, txtChucDanh_1.getText());
            ps.setString(5, txtLuongCBCu.getText());
            ps.setString(6, txtLuongCBMoi_1.getText());
            ps.setString(7, txtPCCVCu.getText());
            ps.setString(8, txtPCCVMoi_1.getText());
            ps.setString(9, txtNgayTang.getText());
            ps.setString(10, txtLyDo_1.getText());
            ps.setString(11, cbxMaNhanVien.getSelectedItem().toString());
            ps.executeUpdate();
            DefaultTableModel model = (DefaultTableModel)tblTangLuong.getModel();
            model.setRowCount(0);
            showTL();
            JOptionPane.showMessageDialog(null, "Chỉnh sửa thành công!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_btnSua_1ActionPerformed

    private void btnThem_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThem_1ActionPerformed
        // TODO add your handling code here:
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=QLNS;" + "username=sa;password=123456");
            String sql = "INSERT INTO TblTangLuong(MaNV, HoTen, GioiTinh, ChucVu, "
            + "ChucDanh, LCBCu, LCBMoi, PCapCu, PcapMoi, NgayTang, LyDo) "
            + "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, cbxMaNhanVien.getSelectedItem().toString());
            ps.setString(2, txtHoTen.getText());
            ps.setString(3, txtGioiTinh.getText());
            ps.setString(4, txtChucVu_1.getText());
            ps.setString(5, txtChucDanh_1.getText());
            ps.setString(6, txtLuongCBCu.getText());
            ps.setString(7, txtLuongCBMoi_1.getText());
            ps.setString(8, txtPCCVCu.getText());
            ps.setString(9, txtPCCVMoi_1.getText());
            ps.setString(10, txtNgayTang.getText());
            ps.setString(11, txtLyDo_1.getText());
            ps.executeUpdate();
            DefaultTableModel model = (DefaultTableModel)tblTangLuong.getModel();
            model.setRowCount(0);
            showTL();
            JOptionPane.showMessageDialog(null, "Thêm thành công!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_btnThem_1ActionPerformed

    private void tblLuongMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblLuongMouseClicked
        // TODO add your handling code here:
        int i = tblLuong.getSelectedRow();
        txtMaLuong.setText(modelLuong.getValueAt(i, 0).toString());
        txtChucVu.setText(modelLuong.getValueAt(i, 1).toString());
        txtChucDanh.setText(modelLuong.getValueAt(i, 2).toString());
        txtLuongCB.setText(modelLuong.getValueAt(i, 3).toString());
        txtPhuCapCV.setText(modelLuong.getValueAt(i, 4).toString());
        txtNgayNhap.setText(modelLuong.getValueAt(i, 5).toString());
        txtLuongCBMoi.setText(modelLuong.getValueAt(i, 6).toString());
        txtNgaySua.setText(modelLuong.getValueAt(i, 7).toString());
        txtLyDo.setText(modelLuong.getValueAt(i, 8).toString());
        txtPCCVMoi.setText(modelLuong.getValueAt(i, 9).toString());
        txtNgaySuaPC.setText(modelLuong.getValueAt(i, 10).toString());
        txtGhiChu.setText(modelLuong.getValueAt(i, 11).toString());
    }//GEN-LAST:event_tblLuongMouseClicked

    private void btnMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoiActionPerformed
        // TODO add your handling code here:
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=QLNS;" + "username=sa;password=123456");
            PreparedStatement ps = conn.prepareStatement("SELECT COUNT(*)+1 AS SL FROM TblBangLuongCTy");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String rnno = rs.getString("SL");
               
                txtMaLuong.setText("ml0" + rnno);
                txtChucVu.setText("");
                txtChucDanh.setText("");
                txtLuongCB.setText("");
                txtPhuCapCV.setText("");
                txtNgayNhap.setText(" " + dateFormat.format(dateLuong));
                txtLuongCBMoi.setText("");
                txtNgaySua.setText(" " + dateFormat.format(dateLuong));
                txtLyDo.setText("");
                txtPCCVMoi.setText("");
                txtNgaySuaPC.setText(" " + dateFormat.format(dateLuong));
                txtGhiChu.setText("");
            }
            else {
                txtMaLuong.setText("ml01");
                txtChucVu.setText("");
                txtChucDanh.setText("");
                txtLuongCB.setText("");
                txtPhuCapCV.setText("");
                txtNgayNhap.setText(" " + dateFormat.format(dateLuong));
                txtLuongCBMoi.setText("");
                txtNgaySua.setText(" " + dateFormat.format(dateLuong));
                txtLyDo.setText("");
                txtPCCVMoi.setText("");
                txtNgaySuaPC.setText(" " + dateFormat.format(dateLuong));
                txtGhiChu.setText("");
            }
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_btnMoiActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=QLNS;" + "username=sa;password=123456");
            String sql = "DELETE FROM TblBangLuongCTy WHERE MaLuong=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, txtMaLuong.getText());
            ps.executeUpdate();
            DefaultTableModel model = (DefaultTableModel)tblLuong.getModel();
            model.setRowCount(0);
            showBL();
            JOptionPane.showMessageDialog(null, "Xoá thành công!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=QLNS;" + "username=sa;password=123456");
            String sql = "UPDATE TblBangLuongCTy SET ChucVu=?, ChucDanh=?, LCB=?, PCChucVu=?, "
            + "NgayNhap=?, LCBMoi=?, NgaySua=?, LyDo=?, PCCVuMoi=?, NgaySuaPC=?, GhiChu=? WHERE MaLuong=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, txtChucVu.getText());
            ps.setString(2, txtChucDanh.getText());
            ps.setString(3, txtLuongCB.getText());
            ps.setString(4, txtPhuCapCV.getText());
            ps.setString(5, txtNgayNhap.getText());
            ps.setString(6, txtLuongCBMoi.getText());
            ps.setString(7, txtNgaySua.getText());
            ps.setString(8, txtLyDo.getText());
            ps.setString(9, txtPCCVMoi.getText());
            ps.setString(10, txtNgaySuaPC.getText());
            ps.setString(11, txtGhiChu.getText());
            ps.setString(12, txtMaLuong.getText());
            ps.executeUpdate();
            DefaultTableModel model = (DefaultTableModel)tblLuong.getModel();
            model.setRowCount(0);
            showBL();
            JOptionPane.showMessageDialog(null, "Chỉnh sửa thành công!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=QLNS;" + "username=sa;password=123456");
            String sql = "INSERT INTO TblBangLuongCTy(MaLuong, ChucVu, ChucDanh, LCB, PCChucVu, "
            + "NgayNhap, LCBMoi, NgaySua, LyDo, PCCVuMoi, NgaySuaPC, GhiChu)"
            + "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, txtMaLuong.getText());
            ps.setString(2, txtChucVu.getText());
            ps.setString(3, txtChucDanh.getText());
            ps.setString(4, txtLuongCB.getText());
            ps.setString(5, txtPhuCapCV.getText());
            ps.setString(6, txtNgayNhap.getText());
            ps.setString(7, txtLuongCBMoi.getText());
            ps.setString(8, txtNgaySua.getText());
            ps.setString(9, txtLyDo.getText());
            ps.setString(10, txtPCCVMoi.getText());
            ps.setString(11, txtNgaySuaPC.getText());
            ps.setString(12, txtGhiChu.getText());
            ps.executeUpdate();
            DefaultTableModel model = (DefaultTableModel)tblLuong.getModel();
            model.setRowCount(0);
            showBL();
            JOptionPane.showMessageDialog(null, "Thêm thành công!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void txtGhiChuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGhiChuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGhiChuActionPerformed

    private void btnXuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatActionPerformed
        // TODO add your handling code here:
        String link = "F:\\QLNSTL\\src\\Report\\LuongCongTy.jrxml";
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=QLNS;" + "username=sa;password=123456");
            JasperReport jr = JasperCompileManager.compileReport(link);

            JasperPrint jp = JasperFillManager.fillReport(jr, null, conn);

            JasperViewer.viewReport(jp);
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnXuatActionPerformed

    private void btnXuat_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuat_1ActionPerformed
        // TODO add your handling code here:
        String link = "F:\\QLNSTL\\src\\Report\\VanDeTangLuong.jrxml";
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=QLNS;" + "username=sa;password=123456");
            JasperReport jr = JasperCompileManager.compileReport(link);

            JasperPrint jp = JasperFillManager.fillReport(jr, null, conn);

            JasperViewer.viewReport(jp);
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnXuat_1ActionPerformed

    private void txtPhuCapCVKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPhuCapCVKeyPressed
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (Character.isLetter(c)) txtPhuCapCV.setEditable(false);
        else txtPhuCapCV.setEditable(true);
    }//GEN-LAST:event_txtPhuCapCVKeyPressed

    private void txtPCCVMoiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPCCVMoiKeyPressed
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (Character.isLetter(c)) txtPCCVMoi.setEditable(false);
        else txtPCCVMoi.setEditable(true);
    }//GEN-LAST:event_txtPCCVMoiKeyPressed

    private void txtLuongCBKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLuongCBKeyPressed
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (Character.isLetter(c)) txtLuongCB.setEditable(false);
        else txtLuongCB.setEditable(true);
    }//GEN-LAST:event_txtLuongCBKeyPressed

    private void txtLuongCBMoiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLuongCBMoiKeyPressed
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (Character.isLetter(c)) txtLuongCB.setEditable(false);
        else txtLuongCB.setEditable(true);
    }//GEN-LAST:event_txtLuongCBMoiKeyPressed

    private void txtPCCVCuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPCCVCuKeyPressed
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (Character.isLetter(c)) txtPCCVCu.setEditable(false);
        else txtPCCVCu.setEditable(true);
    }//GEN-LAST:event_txtPCCVCuKeyPressed

    private void txtPCCVMoi_1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPCCVMoi_1KeyPressed
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (Character.isLetter(c)) txtPCCVMoi_1.setEditable(false);
        else txtPCCVMoi_1.setEditable(true);
    }//GEN-LAST:event_txtPCCVMoi_1KeyPressed

    private void txtLuongCBCuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLuongCBCuKeyPressed
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (Character.isLetter(c)) txtLuongCBCu.setEditable(false);
        else txtLuongCBCu.setEditable(true);
    }//GEN-LAST:event_txtLuongCBCuKeyPressed

    private void txtLuongCBMoi_1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLuongCBMoi_1KeyPressed
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (Character.isLetter(c)) txtLuongCBMoi_1.setEditable(false);
        else txtLuongCBMoi_1.setEditable(true);
    }//GEN-LAST:event_txtLuongCBMoi_1KeyPressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frmLuong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmLuong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmLuong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmLuong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmLuong().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnMoi;
    private javax.swing.JButton btnMoi_1;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnSua_1;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnThem_1;
    private javax.swing.JButton btnXoa;
    private javax.swing.JButton btnXoa_1;
    private javax.swing.JButton btnXuat;
    private javax.swing.JButton btnXuat_1;
    private javax.swing.JComboBox<String> cbxMaNhanVien;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblChucDanh;
    private javax.swing.JLabel lblChucDanh_1;
    private javax.swing.JLabel lblChucVu;
    private javax.swing.JLabel lblChucVu_1;
    private javax.swing.JLabel lblClose;
    private javax.swing.JLabel lblGhiChu;
    private javax.swing.JLabel lblGioiTinh;
    private javax.swing.JLabel lblHeader;
    private javax.swing.JLabel lblHeader1;
    private javax.swing.JLabel lblHeader3;
    private javax.swing.JLabel lblHeader7;
    private javax.swing.JLabel lblHoTen;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblLuongCB;
    private javax.swing.JLabel lblLuongCBCu;
    private javax.swing.JLabel lblLuongCBMoi;
    private javax.swing.JLabel lblLuongCBMoi_1;
    private javax.swing.JLabel lblLyDo;
    private javax.swing.JLabel lblLyDo_1;
    private javax.swing.JLabel lblMaLuong;
    private javax.swing.JLabel lblMaNhanVien;
    private javax.swing.JLabel lblMini;
    private javax.swing.JLabel lblNgayNhap;
    private javax.swing.JLabel lblNgaySua;
    private javax.swing.JLabel lblNgaySuaPC;
    private javax.swing.JLabel lblNgayTang;
    private javax.swing.JLabel lblPCCVCu;
    private javax.swing.JLabel lblPCCVMoi;
    private javax.swing.JLabel lblPCCVMoi_1;
    private javax.swing.JLabel lblPhuCapCV;
    private javax.swing.JPanel pnLuongNV;
    private javax.swing.JPanel pnTangLuong;
    private javax.swing.JTable tblLuong;
    private javax.swing.JTable tblTangLuong;
    private javax.swing.JTextField txtChucDanh;
    private javax.swing.JTextField txtChucDanh_1;
    private javax.swing.JTextField txtChucVu;
    private javax.swing.JTextField txtChucVu_1;
    private javax.swing.JTextField txtGhiChu;
    private javax.swing.JTextField txtGioiTinh;
    private javax.swing.JTextField txtHoTen;
    private javax.swing.JTextField txtLuongCB;
    private javax.swing.JTextField txtLuongCBCu;
    private javax.swing.JTextField txtLuongCBMoi;
    private javax.swing.JTextField txtLuongCBMoi_1;
    private javax.swing.JTextField txtLyDo;
    private javax.swing.JTextField txtLyDo_1;
    private javax.swing.JTextField txtMaLuong;
    private javax.swing.JTextField txtNgayNhap;
    private javax.swing.JTextField txtNgaySua;
    private javax.swing.JTextField txtNgaySuaPC;
    private javax.swing.JTextField txtNgayTang;
    private javax.swing.JTextField txtPCCVCu;
    private javax.swing.JTextField txtPCCVMoi;
    private javax.swing.JTextField txtPCCVMoi_1;
    private javax.swing.JTextField txtPhuCapCV;
    // End of variables declaration//GEN-END:variables
}
