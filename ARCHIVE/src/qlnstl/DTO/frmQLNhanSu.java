/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qlnstl.DTO;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.plaf.TableUI;
import javax.swing.table.DefaultTableModel;
import qlnstl.QLNhanSu;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
public class frmQLNhanSu extends javax.swing.JFrame {

    DateFormat dateFormat = new SimpleDateFormat("MM/dd/YYYY");
    Date date = new Date();
    Calendar cal = Calendar.getInstance();
    DefaultTableModel model;

    private Connection conn;
    public int temp =1 ;
    /**
     * Creates new form frmPersonelManagement
     */
    public frmQLNhanSu() {
        initComponents();
        
        getCBboxBP();
        getCBboxPB();
        
        
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        
        model = (DefaultTableModel) jTable2.getModel();
        txtNgaySinh.setText(" " + dateFormat.format(date));
        txtNgayCap.setText(" " + dateFormat.format(date));
        txtNgayKy.setText(" " + dateFormat.format(date));
        txtNgayHetHan.setText(" " + dateFormat.format(date));
        
        showNS();
        
        EnableBnt();
    }
    
    public void EnableBnt() {
        if (temp == 0) {
            btnMoi.setEnabled(false);
            btnThem.setEnabled(false);
            btnXoa.setEnabled(false);
            btnSua.setEnabled(false);
            btnXuat.setEnabled(false);
        }
        else if (temp == 1) {
            btnMoi.setEnabled(true);
            btnThem.setEnabled(true);
            btnXoa.setEnabled(true);
            btnSua.setEnabled(true);
            btnXuat.setEnabled(true);
        }
    }
    
    public ArrayList<QLNhanSu> nsList() {
        ArrayList<QLNhanSu> nsList = new ArrayList<>();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=QLNS;" + "username=sa;password=123456");
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM TblTTNVCoBan");
            ResultSet rs = ps.executeQuery();
            QLNhanSu ns;
            while (rs.next()) {
                ns = new QLNhanSu(
                    rs.getString("MaBoPhan"),
                    rs.getString("MaPhong"),
                    rs.getString("MaNV"),
                    rs.getString("HoTen"),
                    rs.getString("NgaySinh"),
                    rs.getString("GioiTinh"),
                    rs.getString("CMTND"),
                    rs.getString("NgayCap"),
                    rs.getString("NoiCap"),
                    rs.getString("ChucVu"),
                    rs.getString("ChucDanh"),
                    rs.getString("LoaiHD"),
                    rs.getString("NgayKy"),
                    rs.getString("NgayHetHan"),
                    rs.getString("GhiChu")
                );
                nsList.add(ns);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return nsList;
    }
    
    private void getCBboxBP() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=QLNS;" + "username=sa;password=123456");
            PreparedStatement ns = conn.prepareStatement("SELECT MaBoPhan FROM TblBoPhan ORDER BY MaBoPhan");
            ResultSet rs = ns.executeQuery();
            while (rs.next()) {
                cbxMaBoPhan.addItem(rs.getString("MaBoPhan"));
            }
            //cbxID.setModel(modelCombo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void getCBboxPB() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=QLNS;" + "username=sa;password=123456");
            PreparedStatement ns = conn.prepareStatement("SELECT MaPhong FROM TblPhongBan ORDER BY MaPhong");
            ResultSet rs = ns.executeQuery();
            while (rs.next()) {
                cbxMaPhong.addItem(rs.getString("MaPhong"));
            }
            //cbxID.setModel(modelCombo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showNS() {
        ArrayList<QLNhanSu> nscn = nsList();
        DefaultTableModel model = (DefaultTableModel)jTable2.getModel();
        Object[] row = new Object[17];
        for (int i=0;i<nscn.size();i++) {
            row[0]=nscn.get(i).getMaBoPhan();
            row[1]=nscn.get(i).getMaPhong();
            row[2]=nscn.get(i).getMaNhanVien();
            row[3]=nscn.get(i).getHoTen();
            row[4]=nscn.get(i).getNgaySinh();
            row[5]=nscn.get(i).getGioiTinh();
            row[6]=nscn.get(i).getCMND();
            row[7]=nscn.get(i).getNgayCap();
            row[8]=nscn.get(i).getNoiCap();
            row[9]=nscn.get(i).getChucVu();
            row[10]=nscn.get(i).getChucDanh();
            row[11]=nscn.get(i).getLoaiHD();
            row[12]=nscn.get(i).getNgayKy();
            row[13]=nscn.get(i).getNgayHetHan();
            row[14]=nscn.get(i).getGhiChu();
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
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        lblHeader = new javax.swing.JLabel();
        lblMaBoPhan = new javax.swing.JLabel();
        cbxMaBoPhan = new javax.swing.JComboBox<>();
        lblMaPhong = new javax.swing.JLabel();
        cbxMaPhong = new javax.swing.JComboBox<>();
        lblMaNhanVien = new javax.swing.JLabel();
        txtMaNhanVien = new javax.swing.JTextField();
        lblHoTen = new javax.swing.JLabel();
        txtHoTen = new javax.swing.JTextField();
        lblNgaySinh = new javax.swing.JLabel();
        txtNgaySinh = new javax.swing.JTextField();
        lblGioiTinh = new javax.swing.JLabel();
        cbxGioiTinh = new javax.swing.JComboBox<>();
        lblCMND = new javax.swing.JLabel();
        txtCMND = new javax.swing.JTextField();
        lblNgayCap = new javax.swing.JLabel();
        txtNgayCap = new javax.swing.JTextField();
        lolNoiCap = new javax.swing.JLabel();
        txtNoiCap = new javax.swing.JTextField();
        lblChucVu = new javax.swing.JLabel();
        txtChucVu = new javax.swing.JTextField();
        lblLoaiHD = new javax.swing.JLabel();
        txtLoaiHD = new javax.swing.JTextField();
        lblNgayHetHan = new javax.swing.JLabel();
        txtNgayHetHan = new javax.swing.JTextField();
        lblGhiChu = new javax.swing.JLabel();
        txtGhiChu = new javax.swing.JTextField();
        lblNgayKy = new javax.swing.JLabel();
        txtNgayKy = new javax.swing.JTextField();
        lblChucDanh = new javax.swing.JLabel();
        txtChucDanh = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        lblHeader1 = new javax.swing.JLabel();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnMoi = new javax.swing.JButton();
        btnXuat = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel2.setBackground(new java.awt.Color(107, 195, 196));

        jPanel3.setBackground(new java.awt.Color(3, 100, 117));
        jPanel3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel3.setPreferredSize(new java.awt.Dimension(937, 64));

        lblLogo.setFont(new java.awt.Font("Verdana", 0, 36)); // NOI18N
        lblLogo.setForeground(new java.awt.Color(254, 255, 250));
        lblLogo.setText("Quản lý nhân sự");

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

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 0, new java.awt.Color(107, 195, 196)));

        jPanel6.setBackground(new java.awt.Color(3, 100, 117));

        lblHeader.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        lblHeader.setForeground(new java.awt.Color(254, 255, 250));
        lblHeader.setText("Thông tin cơ bản");

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

        lblMaBoPhan.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        lblMaBoPhan.setForeground(new java.awt.Color(3, 100, 117));
        lblMaBoPhan.setText("Mã bộ phận");

        cbxMaBoPhan.setBackground(new java.awt.Color(107, 195, 196));
        cbxMaBoPhan.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        cbxMaBoPhan.setForeground(new java.awt.Color(3, 100, 117));
        cbxMaBoPhan.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(3, 100, 117)));

        lblMaPhong.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        lblMaPhong.setForeground(new java.awt.Color(3, 100, 117));
        lblMaPhong.setText("Mã phòng");

        cbxMaPhong.setBackground(new java.awt.Color(107, 195, 196));
        cbxMaPhong.setEditable(true);
        cbxMaPhong.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        cbxMaPhong.setForeground(new java.awt.Color(3, 100, 117));
        cbxMaPhong.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(3, 100, 117)));

        lblMaNhanVien.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        lblMaNhanVien.setForeground(new java.awt.Color(3, 100, 117));
        lblMaNhanVien.setText("Mã nhân viên");

        txtMaNhanVien.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        txtMaNhanVien.setForeground(new java.awt.Color(3, 100, 117));
        txtMaNhanVien.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(3, 100, 117)));
        txtMaNhanVien.setEnabled(false);
        txtMaNhanVien.setMinimumSize(new java.awt.Dimension(3, 26));
        txtMaNhanVien.setPreferredSize(new java.awt.Dimension(3, 26));

        lblHoTen.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        lblHoTen.setForeground(new java.awt.Color(3, 100, 117));
        lblHoTen.setText("Họ tên");

        txtHoTen.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        txtHoTen.setForeground(new java.awt.Color(3, 100, 117));
        txtHoTen.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(3, 100, 117)));
        txtHoTen.setMinimumSize(new java.awt.Dimension(3, 26));
        txtHoTen.setPreferredSize(new java.awt.Dimension(3, 26));

        lblNgaySinh.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        lblNgaySinh.setForeground(new java.awt.Color(3, 100, 117));
        lblNgaySinh.setText("Ngày sinh");

        txtNgaySinh.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        txtNgaySinh.setForeground(new java.awt.Color(3, 100, 117));
        txtNgaySinh.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(3, 100, 117)));
        txtNgaySinh.setMinimumSize(new java.awt.Dimension(3, 26));
        txtNgaySinh.setPreferredSize(new java.awt.Dimension(3, 26));

        lblGioiTinh.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        lblGioiTinh.setForeground(new java.awt.Color(3, 100, 117));
        lblGioiTinh.setText("Giới tính");

        cbxGioiTinh.setBackground(new java.awt.Color(107, 195, 196));
        cbxGioiTinh.setEditable(true);
        cbxGioiTinh.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        cbxGioiTinh.setForeground(new java.awt.Color(3, 100, 117));
        cbxGioiTinh.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nam", "Nữ" }));
        cbxGioiTinh.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(3, 100, 117)));

        lblCMND.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        lblCMND.setForeground(new java.awt.Color(3, 100, 117));
        lblCMND.setText("CMND");

        txtCMND.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        txtCMND.setForeground(new java.awt.Color(3, 100, 117));
        txtCMND.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(3, 100, 117)));
        txtCMND.setMinimumSize(new java.awt.Dimension(3, 26));
        txtCMND.setPreferredSize(new java.awt.Dimension(3, 26));
        txtCMND.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCMNDKeyPressed(evt);
            }
        });

        lblNgayCap.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        lblNgayCap.setForeground(new java.awt.Color(3, 100, 117));
        lblNgayCap.setText("Ngày cấp");

        txtNgayCap.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        txtNgayCap.setForeground(new java.awt.Color(3, 100, 117));
        txtNgayCap.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(3, 100, 117)));
        txtNgayCap.setMinimumSize(new java.awt.Dimension(3, 26));
        txtNgayCap.setPreferredSize(new java.awt.Dimension(3, 26));

        lolNoiCap.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        lolNoiCap.setForeground(new java.awt.Color(3, 100, 117));
        lolNoiCap.setText("Nơi cấp");

        txtNoiCap.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        txtNoiCap.setForeground(new java.awt.Color(3, 100, 117));
        txtNoiCap.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(3, 100, 117)));
        txtNoiCap.setMinimumSize(new java.awt.Dimension(3, 26));
        txtNoiCap.setPreferredSize(new java.awt.Dimension(3, 26));

        lblChucVu.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        lblChucVu.setForeground(new java.awt.Color(3, 100, 117));
        lblChucVu.setText("Chức vụ");

        txtChucVu.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        txtChucVu.setForeground(new java.awt.Color(3, 100, 117));
        txtChucVu.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(3, 100, 117)));
        txtChucVu.setMinimumSize(new java.awt.Dimension(3, 26));
        txtChucVu.setPreferredSize(new java.awt.Dimension(3, 26));

        lblLoaiHD.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        lblLoaiHD.setForeground(new java.awt.Color(3, 100, 117));
        lblLoaiHD.setText("Loại HĐ");

        txtLoaiHD.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        txtLoaiHD.setForeground(new java.awt.Color(3, 100, 117));
        txtLoaiHD.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(3, 100, 117)));
        txtLoaiHD.setMinimumSize(new java.awt.Dimension(3, 26));
        txtLoaiHD.setPreferredSize(new java.awt.Dimension(3, 26));

        lblNgayHetHan.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        lblNgayHetHan.setForeground(new java.awt.Color(3, 100, 117));
        lblNgayHetHan.setText("Ngày hết hạn");

        txtNgayHetHan.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        txtNgayHetHan.setForeground(new java.awt.Color(3, 100, 117));
        txtNgayHetHan.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(3, 100, 117)));
        txtNgayHetHan.setMinimumSize(new java.awt.Dimension(3, 26));
        txtNgayHetHan.setPreferredSize(new java.awt.Dimension(3, 26));

        lblGhiChu.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        lblGhiChu.setForeground(new java.awt.Color(3, 100, 117));
        lblGhiChu.setText("Ghi chú");

        txtGhiChu.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        txtGhiChu.setForeground(new java.awt.Color(3, 100, 117));
        txtGhiChu.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(3, 100, 117)));
        txtGhiChu.setMinimumSize(new java.awt.Dimension(3, 26));
        txtGhiChu.setPreferredSize(new java.awt.Dimension(3, 26));

        lblNgayKy.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        lblNgayKy.setForeground(new java.awt.Color(3, 100, 117));
        lblNgayKy.setText("Ngày ký");

        txtNgayKy.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        txtNgayKy.setForeground(new java.awt.Color(3, 100, 117));
        txtNgayKy.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(3, 100, 117)));
        txtNgayKy.setMinimumSize(new java.awt.Dimension(3, 26));
        txtNgayKy.setPreferredSize(new java.awt.Dimension(3, 26));

        lblChucDanh.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        lblChucDanh.setForeground(new java.awt.Color(3, 100, 117));
        lblChucDanh.setText("Chức danh");

        txtChucDanh.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        txtChucDanh.setForeground(new java.awt.Color(3, 100, 117));
        txtChucDanh.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(3, 100, 117)));
        txtChucDanh.setMinimumSize(new java.awt.Dimension(3, 26));
        txtChucDanh.setPreferredSize(new java.awt.Dimension(3, 26));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(lblMaBoPhan)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cbxMaBoPhan, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                        .addComponent(lblHoTen)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                        .addComponent(lblMaPhong)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cbxMaPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                        .addComponent(lblMaNhanVien)
                        .addGap(18, 18, 18)
                        .addComponent(txtMaNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                        .addComponent(lblNgaySinh)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(38, 38, 38)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblGioiTinh)
                    .addComponent(lolNoiCap)
                    .addComponent(lblNgayCap)
                    .addComponent(lblCMND)
                    .addComponent(lblChucVu))
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCMND, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel5Layout.createSequentialGroup()
                            .addGap(18, 18, 18)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtNoiCap, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cbxGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtNgayCap, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel5Layout.createSequentialGroup()
                            .addGap(18, 18, 18)
                            .addComponent(txtChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(38, 38, 38)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblGhiChu)
                            .addComponent(lblNgayKy)
                            .addComponent(lblLoaiHD)
                            .addComponent(lblChucDanh))
                        .addGap(36, 36, 36))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(lblNgayHetHan)
                        .addGap(18, 18, 18)))
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtLoaiHD, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtGhiChu, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtChucDanh, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNgayHetHan, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNgayKy, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtChucDanh, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblMaBoPhan)
                        .addComponent(cbxMaBoPhan, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblGioiTinh)
                        .addComponent(cbxGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblChucDanh)))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(txtLoaiHD, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblMaNhanVien)
                                        .addComponent(lblCMND))
                                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txtNgayHetHan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblNgayHetHan))
                                    .addComponent(txtCMND, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblMaPhong)
                                    .addComponent(cbxMaPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(txtMaNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblNgayKy)
                                .addComponent(txtNoiCap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lolNoiCap)
                                .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtNgayKy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblHoTen, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblLoaiHD)
                        .addComponent(lblChucVu)))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblGhiChu)
                    .addComponent(txtGhiChu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNgayCap)
                    .addComponent(lblNgaySinh)
                    .addComponent(txtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNgayCap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29))
        );

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 1, 1, 1, new java.awt.Color(107, 195, 196)));

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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
        btnXuat.setBorder(null);
        btnXuat.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnXuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(65, 65, 65)
                .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(65, 65, 65)
                .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(65, 65, 65)
                .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(65, 65, 65)
                .addComponent(btnXuat, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(76, 76, 76))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXuat, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jTable2.setBackground(new java.awt.Color(254, 255, 250));
        jTable2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jTable2.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jTable2.setForeground(new java.awt.Color(3, 100, 117));
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã bộ phận", "Mã phòng", "Mã nhân viên", "Họ tên", "Ngày sinh", "Giới tính", "CMND", "Ngày cấp", "Nơi cấp", "Chức vụ", "Chức danh", "Loại HĐ", "Ngày ký", "Ngày hết hạn", "Ghi chú"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable2.setGridColor(new java.awt.Color(3, 100, 117));
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable2);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 847, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
        this.setState(JFrame .ICONIFIED);
    }//GEN-LAST:event_lblMiniMouseClicked

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=QLNS;" + "username=sa;password=123456");
            String sql = "INSERT INTO TblTTNVCoBan(MaBoPhan, MaPhong, MaNV, HoTen, NgaySinh, "
                    + "GioiTinh, CMTND, NgayCap, NoiCap, ChucVu, ChucDanh, LoaiHD, NgayKy, NgayHetHan, GhiChu) "
                    + "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, cbxMaBoPhan.getSelectedItem().toString());
            ps.setString(2, cbxMaPhong.getSelectedItem().toString());
            ps.setString(3, txtMaNhanVien.getText());
            ps.setString(4, txtHoTen.getText());
            ps.setString(5, txtNgaySinh.getText());
            ps.setString(6, cbxGioiTinh.getSelectedItem().toString());
            ps.setString(7, txtCMND.getText());
            ps.setString(8, txtNgayCap.getText());
            ps.setString(9, txtNoiCap.getText());
            ps.setString(10, txtChucVu.getText());
            ps.setString(11, txtChucDanh.getText());
            ps.setString(12, txtLoaiHD.getText());
            ps.setString(13, txtNgayKy.getText());
            ps.setString(14, txtNgayHetHan.getText());
            ps.setString(15, txtGhiChu.getText());
            ps.executeUpdate();
            DefaultTableModel model = (DefaultTableModel)jTable2.getModel();
            model.setRowCount(0);
            showNS();
            JOptionPane.showMessageDialog(null, "Thêm thành công!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoiActionPerformed
        // TODO add your handling code here:
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=QLNS;" + "username=sa;password=123456");
            PreparedStatement ns = conn.prepareStatement("SELECT COUNT(*)+1 AS SL FROM TblTTNVCoBan");
            ResultSet rs = ns.executeQuery();
            if (rs.next()) {
                String rnno = rs.getString("SL");
                cbxMaBoPhan.setSelectedIndex(0);
                cbxMaPhong.setSelectedIndex(0);
                txtMaNhanVien.setText("00" + rnno);
                txtHoTen.setText("");
                txtNgaySinh.setText(" " + dateFormat.format(date));
                cbxGioiTinh.setSelectedIndex(0);
                txtCMND.setText("");
                txtNgayCap.setText(" " + dateFormat.format(date));
                txtNoiCap.setText("");
                txtChucVu.setText("");
                txtChucDanh.setText("");
                txtLoaiHD.setText("");
                txtNgayKy.setText(" " + dateFormat.format(date));
                txtNgayHetHan.setText(" " + dateFormat.format(date));
                txtGhiChu.setText("");
            }
            else {
                cbxMaBoPhan.setSelectedIndex(0);
                cbxMaPhong.setSelectedIndex(0);
                txtMaNhanVien.setText("001");
                txtHoTen.setText("");
                txtNgaySinh.setText(" " + dateFormat.format(date));
                cbxGioiTinh.setSelectedIndex(0);
                txtCMND.setText("");
                txtNgayCap.setText(" " + dateFormat.format(date));
                txtNoiCap.setText("");
                txtChucVu.setText("");
                txtChucDanh.setText("");
                txtLoaiHD.setText("");
                txtNgayKy.setText(" " + dateFormat.format(date));
                txtNgayHetHan.setText(" " + dateFormat.format(date));
                txtGhiChu.setText("");
            }
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_btnMoiActionPerformed

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        // TODO add your handling code here:
        int i = jTable2.getSelectedRow();
        cbxMaBoPhan.setSelectedItem(model.getValueAt(i, 0).toString());
        cbxMaPhong.setSelectedItem(model.getValueAt(i, 1).toString());
        txtMaNhanVien.setText(model.getValueAt(i, 2).toString());
        txtHoTen.setText(model.getValueAt(i, 3).toString());
        txtNgaySinh.setText(model.getValueAt(i, 4).toString());
        cbxGioiTinh.setSelectedItem(model.getValueAt(i, 5).toString());
        txtCMND.setText(model.getValueAt(i, 6).toString());
        txtNgayCap.setText(model.getValueAt(i, 7).toString());
        txtNoiCap.setText(model.getValueAt(i, 8).toString());
        txtChucVu.setText(model.getValueAt(i, 9).toString());
        txtChucDanh.setText(model.getValueAt(i, 10).toString());
        txtLoaiHD.setText(model.getValueAt(i, 11).toString());
        txtNgayKy.setText(model.getValueAt(i, 12).toString());
        txtNgayHetHan.setText(model.getValueAt(i, 13).toString());
        txtGhiChu.setText(model.getValueAt(i, 14).toString());
    }//GEN-LAST:event_jTable2MouseClicked

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=QLNS;" + "username=sa;password=123456");
            String sql = "UPDATE TblTTNVCoBan SET MaBoPhan=?, MaPhong=?, HoTen=?, NgaySinh=?, "
                    + "GioiTinh=?, CMTND=?, NgayCap=?, NoiCap=?, ChucVu=?, ChucDanh=?, "
                    + "LoaiHD=?, NgayKy=?, NgayHetHan=?, GhiChu=? WHERE MaNV=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, cbxMaBoPhan.getSelectedItem().toString());
            ps.setString(2, cbxMaPhong.getSelectedItem().toString());
            ps.setString(3, txtHoTen.getText());
            ps.setString(4, txtNgaySinh.getText());
            ps.setString(5, cbxGioiTinh.getSelectedItem().toString());
            ps.setString(6, txtCMND.getText());
            ps.setString(7, txtNgayCap.getText());
            ps.setString(8, txtNoiCap.getText());
            ps.setString(9, txtChucVu.getText());
            ps.setString(10, txtChucDanh.getText());
            ps.setString(11, txtLoaiHD.getText());
            ps.setString(12, txtNgayKy.getText());
            ps.setString(13, txtNgayHetHan.getText());
            ps.setString(14, txtGhiChu.getText());
            ps.setString(15, txtMaNhanVien.getText());
            ps.executeUpdate();
            DefaultTableModel model = (DefaultTableModel)jTable2.getModel();
            model.setRowCount(0);
            showNS();
            JOptionPane.showMessageDialog(null, "Chỉnh sửa thành công!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=QLNS;" + "username=sa;password=123456");
            String sql = "DELETE FROM TblTTNVCoBan WHERE MaNV=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, txtMaNhanVien.getText());
            ps.executeUpdate();
            DefaultTableModel model = (DefaultTableModel)jTable2.getModel();
            model.setRowCount(0);
            showNS();
            JOptionPane.showMessageDialog(null, "Xoá thành công!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnXuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatActionPerformed
        // TODO add your handling code here:
        String link = "F:\\QLNSTL\\src\\Report\\frmBaoCaoNhanSu.jrxml";
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=QLNS;" + "username=sa;password=123456");
            JasperReport jr = JasperCompileManager.compileReport(link);
            
            JasperPrint jp = JasperFillManager.fillReport(jr, null, conn);
            
            JasperViewer.viewReport(jp);
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnXuatActionPerformed

    private void txtCMNDKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCMNDKeyPressed
        // TODO add your handling code here:
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (Character.isLetter(c)) txtCMND.setEditable(false);
        else txtCMND.setEditable(true);
    }//GEN-LAST:event_txtCMNDKeyPressed
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collansed" desc=" Look and feel sensing code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see hnsp://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frmQLNhanSu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmQLNhanSu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmQLNhanSu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmQLNhanSu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmQLNhanSu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnMoi;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JButton btnXuat;
    private javax.swing.JComboBox<String> cbxGioiTinh;
    private javax.swing.JComboBox<String> cbxMaBoPhan;
    private javax.swing.JComboBox<String> cbxMaPhong;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable2;
    private javax.swing.JLabel lblCMND;
    private javax.swing.JLabel lblChucDanh;
    private javax.swing.JLabel lblChucVu;
    private javax.swing.JLabel lblClose;
    private javax.swing.JLabel lblGhiChu;
    private javax.swing.JLabel lblGioiTinh;
    private javax.swing.JLabel lblHeader;
    private javax.swing.JLabel lblHeader1;
    private javax.swing.JLabel lblHoTen;
    private javax.swing.JLabel lblLoaiHD;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblMaBoPhan;
    private javax.swing.JLabel lblMaNhanVien;
    private javax.swing.JLabel lblMaPhong;
    private javax.swing.JLabel lblMini;
    private javax.swing.JLabel lblNgayCap;
    private javax.swing.JLabel lblNgayHetHan;
    private javax.swing.JLabel lblNgayKy;
    private javax.swing.JLabel lblNgaySinh;
    private javax.swing.JLabel lolNoiCap;
    private javax.swing.JTextField txtCMND;
    private javax.swing.JTextField txtChucDanh;
    private javax.swing.JTextField txtChucVu;
    private javax.swing.JTextField txtGhiChu;
    private javax.swing.JTextField txtHoTen;
    private javax.swing.JTextField txtLoaiHD;
    private javax.swing.JTextField txtMaNhanVien;
    private javax.swing.JTextField txtNgayCap;
    private javax.swing.JTextField txtNgayHetHan;
    private javax.swing.JTextField txtNgayKy;
    private javax.swing.JTextField txtNgaySinh;
    private javax.swing.JTextField txtNoiCap;
    // End of variables declaration//GEN-END:variables
}
