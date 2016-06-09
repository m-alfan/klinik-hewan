import javax.swing.UIManager.LookAndFeelInfo;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.Border;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Transactions extends JFrame {
    private JLabel judul;
    private JLabel customer;
    private JComboBox field_customer;
    private JLabel check_in;
    private JTextField field_check_in;
    private JLabel type;
    private JCheckBox mandi_jamur;
    private JCheckBox mandi_kutu;
    private JCheckBox mandi_biasa;
    private JCheckBox bedah_tulang;
    private JCheckBox vaksin;
    private JCheckBox konsultasi;
    private JCheckBox opers_kecil;
    private JCheckBox opers_besar;
    private JCheckBox rontgen;
    private JCheckBox pemeriksaan_kesehatan;
    private JCheckBox penitipan_hewan;
    private JLabel animal;
    private JRadioButton field_anjing;
    private JRadioButton field_kucing;
    private JRadioButton field_kelinci;
    private ButtonGroup group_button_animal;
    private JLabel ras;
    private JTextField field_ras;
    private JLabel descr;
    private JTextField field_descr;
    private JLabel price;
    private JTextField field_price;
    private JLabel status;
    private JRadioButton field_proses;
    private JRadioButton field_cancel;
    private ButtonGroup group_button_status;
    private JButton reset;
    private JButton save;
    private JButton update;
    private JButton delete;
    private JButton back;
    
    //date
    private SimpleDateFormat sdfDate;
    private Date now;
    
    //tabel
    private DefaultTableModel data_tabel;
    private JTable panel_tabel;
    private JScrollPane skrol_tabel;

    //database
    private String type_transaction;
    private String id_trans;
    private String query;
    private Koneksidatabase db;
    private PreparedStatement pst;
    private Statement stat;
    private ResultSet rs;

    //price total
    private int price_total;

    //Constructor 
    public Transactions(){

        this.setTitle("Transactions");
        this.setSize(800,655);
        
        //pane with null layout
        JPanel contentPane = new JPanel(null);
        contentPane.setPreferredSize(new Dimension(800,655));
        contentPane.setBackground(new Color(153,153,255));

        judul = new JLabel();
        judul.setBounds(15,20,235,43);
        judul.setBackground(new Color(214,217,223));
        judul.setEnabled(true);
        judul.setFont(new Font("Cambria",0,40));
        judul.setText("Transactions");
        judul.setVisible(true);

        customer = new JLabel();
        customer.setBounds(20,80,90,35);
        customer.setBackground(new Color(214,217,223));
        customer.setEnabled(true);
        customer.setFont(new Font("Cambria",0,15));
        customer.setText("Customer");
        customer.setVisible(true);

        field_customer = new JComboBox();
        field_customer.setBounds(160,80,160,35);
        field_customer.setBackground(new Color(214,217,223));
        field_customer.setEnabled(true);
        field_customer.setFont(new Font("Cambria",0,12));
        field_customer.setVisible(true);
        field_customer.addItem("Customer Name");

        try
        {
            //get data from database
            Koneksidatabase db   = new Koneksidatabase();
            Statement stat = db.koneksi.createStatement();
            ResultSet rs   = stat.executeQuery("SELECT id, nama FROM customers;");
            while(rs.next()){
                field_customer.addItem(new ComboItem(rs.getString("nama"), rs.getString("id")));
            }
            rs.close();
            stat.close();
            db.koneksi.close();
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e);
        }

        check_in = new JLabel();
        check_in.setBounds(20,120,90,35);
        check_in.setBackground(new Color(214,217,223));
        check_in.setEnabled(true);
        check_in.setFont(new Font("Cambria",0,15));
        check_in.setText("Check In");
        check_in.setVisible(true);

        field_check_in = new JTextField();
        field_check_in.setBounds(160,120,160,35);
        field_check_in.setBackground(new Color(255,255,255));
        field_check_in.setEnabled(false);
        field_check_in.setFont(new Font("Cambria",0,12));
        sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        now = new Date();
        field_check_in.setText(sdfDate.format(now));
        field_check_in.setVisible(true);

        type = new JLabel();
        type.setBounds(20,160,120,35);
        type.setBackground(new Color(214,217,223));
        type.setEnabled(true);
        type.setFont(new Font("Cambria",0,15));
        type.setText("Transaction Type");
        type.setVisible(true);

        mandi_jamur = new JCheckBox();
        mandi_jamur.setBounds(160,160,90,35);
        mandi_jamur.setBackground(new Color(214,217,223));
        mandi_jamur.setForeground(new Color(0,0,0));
        mandi_jamur.setEnabled(true);
        mandi_jamur.setFont(new Font("Cambria",0,12));
        mandi_jamur.setText("Mandi Jamur");
        mandi_jamur.setVisible(true);

        mandi_jamur.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                if(mandi_jamur.isSelected()){
                    price_total = Integer.parseInt(field_price.getText())+100000;
                    field_price.setText(""+price_total);
                }else{
                    price_total = Integer.parseInt(field_price.getText())-100000;
                    field_price.setText(""+price_total);
                }
            }
        });

        mandi_kutu = new JCheckBox();
        mandi_kutu.setBounds(270,160,90,35);
        mandi_kutu.setBackground(new Color(214,217,223));
        mandi_kutu.setForeground(new Color(0,0,0));
        mandi_kutu.setEnabled(true);
        mandi_kutu.setFont(new Font("Cambria",0,12));
        mandi_kutu.setText("Mandi Kutu");
        mandi_kutu.setVisible(true);

        mandi_kutu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                if(mandi_kutu.isSelected()){
                    price_total = Integer.parseInt(field_price.getText())+100000;
                    field_price.setText(""+price_total);
                }else{
                    price_total = Integer.parseInt(field_price.getText())-100000;
                    field_price.setText(""+price_total);
                }
            }
        });

        mandi_biasa = new JCheckBox();
        mandi_biasa.setBounds(160,180,90,35);
        mandi_biasa.setBackground(new Color(214,217,223));
        mandi_biasa.setForeground(new Color(0,0,0));
        mandi_biasa.setEnabled(true);
        mandi_biasa.setFont(new Font("Cambria",0,12));
        mandi_biasa.setText("Mandi Biasa");
        mandi_biasa.setVisible(true);

        mandi_biasa.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                if(mandi_biasa.isSelected()){
                    price_total = Integer.parseInt(field_price.getText())+50000;
                    field_price.setText(""+price_total);
                }else{
                    price_total = Integer.parseInt(field_price.getText())-50000;
                    field_price.setText(""+price_total);
                }
            }
        });

        bedah_tulang = new JCheckBox();
        bedah_tulang.setBounds(270,180,120,35);
        bedah_tulang.setBackground(new Color(214,217,223));
        bedah_tulang.setForeground(new Color(0,0,0));
        bedah_tulang.setEnabled(true);
        bedah_tulang.setFont(new Font("Cambria",0,12));
        bedah_tulang.setText("Bedah Tulang");
        bedah_tulang.setVisible(true);

        bedah_tulang.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                if(bedah_tulang.isSelected()){
                    price_total = Integer.parseInt(field_price.getText())+250000;
                    field_price.setText(""+price_total);
                }else{
                    price_total = Integer.parseInt(field_price.getText())-250000;
                    field_price.setText(""+price_total);
                }
            }
        });

        vaksin = new JCheckBox();
        vaksin.setBounds(160,200,90,35);
        vaksin.setBackground(new Color(214,217,223));
        vaksin.setForeground(new Color(0,0,0));
        vaksin.setEnabled(true);
        vaksin.setFont(new Font("Cambria",0,12));
        vaksin.setText("Vaksin");
        vaksin.setVisible(true);

        vaksin.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                if(vaksin.isSelected()){
                    price_total = Integer.parseInt(field_price.getText())+75000;
                    field_price.setText(""+price_total);
                }else{
                    price_total = Integer.parseInt(field_price.getText())-75000;
                    field_price.setText(""+price_total);
                }
            }
        });

        opers_kecil = new JCheckBox();
        opers_kecil.setBounds(270,200,120,35);
        opers_kecil.setBackground(new Color(214,217,223));
        opers_kecil.setForeground(new Color(0,0,0));
        opers_kecil.setEnabled(true);
        opers_kecil.setFont(new Font("Cambria",0,12));
        opers_kecil.setText("Operasi Kecil");
        opers_kecil.setVisible(true);

        opers_kecil.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                if(opers_kecil.isSelected()){
                    price_total = Integer.parseInt(field_price.getText())+1500000;
                    field_price.setText(""+price_total);
                }else{
                    price_total = Integer.parseInt(field_price.getText())-1500000;
                    field_price.setText(""+price_total);
                }
            }
        });

        konsultasi = new JCheckBox();
        konsultasi.setBounds(160,220,90,35);
        konsultasi.setBackground(new Color(214,217,223));
        konsultasi.setForeground(new Color(0,0,0));
        konsultasi.setEnabled(true);
        konsultasi.setFont(new Font("Cambria",0,12));
        konsultasi.setText("Konsultasi");
        konsultasi.setVisible(true);

        konsultasi.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                if(konsultasi.isSelected()){
                    price_total = Integer.parseInt(field_price.getText())+200000;
                    field_price.setText(""+price_total);
                }else{
                    price_total = Integer.parseInt(field_price.getText())-200000;
                    field_price.setText(""+price_total);
                }
            }
        });

        rontgen = new JCheckBox();
        rontgen.setBounds(160,240,90,35);
        rontgen.setBackground(new Color(214,217,223));
        rontgen.setForeground(new Color(0,0,0));
        rontgen.setEnabled(true);
        rontgen.setFont(new Font("Cambria",0,12));
        rontgen.setText("Rontgen");
        rontgen.setVisible(true);

        rontgen.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                if(rontgen.isSelected()){
                    price_total = Integer.parseInt(field_price.getText())+200000;
                    field_price.setText(""+price_total);
                }else{
                    price_total = Integer.parseInt(field_price.getText())-200000;
                    field_price.setText(""+price_total);
                }
            }
        });

        opers_besar = new JCheckBox();
        opers_besar.setBounds(270,220,120,35);
        opers_besar.setBackground(new Color(214,217,223));
        opers_besar.setForeground(new Color(0,0,0));
        opers_besar.setEnabled(true);
        opers_besar.setFont(new Font("Cambria",0,12));
        opers_besar.setText("Operasi Besar");
        opers_besar.setVisible(true);

        opers_besar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                if(opers_besar.isSelected()){
                    price_total = Integer.parseInt(field_price.getText())+3000000;
                    field_price.setText(""+price_total);
                }else{
                    price_total = Integer.parseInt(field_price.getText())-3000000;
                    field_price.setText(""+price_total);
                }
            }
        });
        
        pemeriksaan_kesehatan = new JCheckBox();
        pemeriksaan_kesehatan.setBounds(160,260,160,35);
        pemeriksaan_kesehatan.setBackground(new Color(214,217,223));
        pemeriksaan_kesehatan.setForeground(new Color(0,0,0));
        pemeriksaan_kesehatan.setEnabled(true);
        pemeriksaan_kesehatan.setFont(new Font("Cambria",0,12));
        pemeriksaan_kesehatan.setText("Periksa Kesehatan");
        pemeriksaan_kesehatan.setVisible(true);

        pemeriksaan_kesehatan.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                if(pemeriksaan_kesehatan.isSelected()){
                    price_total = Integer.parseInt(field_price.getText())+75000;
                    field_price.setText(""+price_total);
                }else{
                    price_total = Integer.parseInt(field_price.getText())-75000;
                    field_price.setText(""+price_total);
                }
            }
        });

        penitipan_hewan = new JCheckBox();
        penitipan_hewan.setBounds(270,240,120,35);
        penitipan_hewan.setBackground(new Color(214,217,223));
        penitipan_hewan.setForeground(new Color(0,0,0));
        penitipan_hewan.setEnabled(true);
        penitipan_hewan.setFont(new Font("Cambria",0,12));
        penitipan_hewan.setText("Titip Hewan");
        penitipan_hewan.setVisible(true);

        penitipan_hewan.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                if(penitipan_hewan.isSelected()){
                    price_total = Integer.parseInt(field_price.getText())+75000;
                    field_price.setText(""+price_total);
                }else{
                    price_total = Integer.parseInt(field_price.getText())-75000;
                    field_price.setText(""+price_total);
                }
            }
        });


        animal = new JLabel();
        animal.setBounds(380,80,90,35);
        animal.setBackground(new Color(214,217,223));
        animal.setEnabled(true);
        animal.setFont(new Font("Cambria",0,15));
        animal.setText("Animal Type");
        animal.setVisible(true);

        field_anjing = new JRadioButton();
        field_anjing.setBounds(520,80,90,35);
        field_anjing.setBackground(new Color(214,217,223));
        field_anjing.setEnabled(true);
        field_anjing.setFont(new Font("Cambria",0,12));
        field_anjing.setText("Anjing");
        field_anjing.setVisible(true);
        field_anjing.setActionCommand("Anjing");

        field_kucing = new JRadioButton();
        field_kucing.setBounds(600,80,90,35);
        field_kucing.setBackground(new Color(214,217,223));
        field_kucing.setEnabled(true);
        field_kucing.setFont(new Font("Cambria",0,12));
        field_kucing.setText("Kucing");
        field_kucing.setVisible(true);
        field_kucing.setActionCommand("Kucing");

        field_kelinci = new JRadioButton();
        field_kelinci.setBounds(680,80,90,35);
        field_kelinci.setBackground(new Color(214,217,223));
        field_kelinci.setEnabled(true);
        field_kelinci.setFont(new Font("Cambria",0,12));
        field_kelinci.setText("Kelinci");
        field_kelinci.setVisible(true);
        field_kelinci.setActionCommand("Kelinci");

        group_button_animal = new ButtonGroup();
        group_button_animal.add(field_anjing);
        group_button_animal.add(field_kucing);
        group_button_animal.add(field_kelinci);

        ras = new JLabel();
        ras.setBounds(380,120,90,35);
        ras.setBackground(new Color(214,217,223));
        ras.setEnabled(true);
        ras.setFont(new Font("Cambria",0,15));
        ras.setText("Ras");
        ras.setVisible(true);

        field_ras = new JTextField();
        field_ras.setBounds(520,120,160,35);
        field_ras.setBackground(new Color(255,255,255));
        field_ras.setEnabled(true);
        field_ras.setFont(new Font("Cambria",0,12));
        field_ras.setText("Ras");
        field_ras.setVisible(true);

        field_ras.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                field_ras.setText("");
            }
        });

        descr = new JLabel();
        descr.setBounds(380,160,90,35);
        descr.setBackground(new Color(214,217,223));
        descr.setEnabled(true);
        descr.setFont(new Font("Cambria",0,15));
        descr.setText("Description");
        descr.setVisible(true);

        field_descr = new JTextField();
        field_descr.setBounds(520,160,160,35);
        field_descr.setBackground(new Color(255,255,255));
        field_descr.setEnabled(true);
        field_descr.setFont(new Font("Cambria",0,12));
        field_descr.setText("Description");
        field_descr.setVisible(true);

        field_descr.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                field_descr.setText("");
            }
        });

        price = new JLabel();
        price.setBounds(380,200,90,35);
        price.setBackground(new Color(214,217,223));
        price.setEnabled(true);
        price.setFont(new Font("Cambria",0,15));
        price.setText("Price");
        price.setVisible(true);

        field_price = new JTextField();
        field_price.setBounds(520,200,160,35);
        field_price.setBackground(new Color(255,255,255));
        field_price.setEnabled(false);
        field_price.setFont(new Font("Cambria",0,12));
        field_price.setText("0");
        field_price.setVisible(true);

        field_price.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                field_price.setText("0");
            }
        });

        status = new JLabel();
        status.setBounds(380,240,90,35);
        status.setBackground(new Color(214,217,223));
        status.setEnabled(true);
        status.setFont(new Font("Cambria",0,15));
        status.setText("Status");
        status.setVisible(true);

        field_proses = new JRadioButton();
        field_proses.setBounds(520,240,90,35);
        field_proses.setBackground(new Color(214,217,223));
        field_proses.setEnabled(true);
        field_proses.setFont(new Font("Cambria",0,12));
        field_proses.setText("Process");
        field_proses.setVisible(true);
        field_proses.setActionCommand("p");

        field_cancel = new JRadioButton();
        field_cancel.setBounds(600,240,90,35);
        field_cancel.setBackground(new Color(214,217,223));
        field_cancel.setEnabled(true);
        field_cancel.setFont(new Font("Cambria",0,12));
        field_cancel.setText("Cancel");
        field_cancel.setVisible(true);
        field_cancel.setActionCommand("c");

        group_button_status = new ButtonGroup();
        group_button_status.add(field_proses);
        group_button_status.add(field_cancel);

        reset = new JButton();
        reset.setBounds(185,300,75,35);
        reset.setBackground(new Color(214,217,223));
        reset.setEnabled(true);
        reset.setFont(new Font("Cambria",0,12));
        reset.setText("Reset");
        reset.setVisible(true);
        
        reset.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                field_customer.setSelectedItem("Customer Name");
                now = new Date();
                field_check_in.setText(sdfDate.format(now));
                field_price.setText("0");
                field_ras.setText("");
                field_descr.setText("");
                group_button_animal.clearSelection();
                group_button_status.clearSelection();
                mandi_jamur.setSelected(false);
                mandi_biasa.setSelected(false);
                mandi_kutu.setSelected(false);
                bedah_tulang.setSelected(false);
                vaksin.setSelected(false);
                opers_kecil.setSelected(false);
                opers_besar.setSelected(false);
                konsultasi.setSelected(false);
                rontgen.setSelected(false);
                pemeriksaan_kesehatan.setSelected(false);
                penitipan_hewan.setSelected(false);
            }
        });

        update = new JButton();
        update.setBounds(270,300,75,35);
        update.setBackground(new Color(214,217,223));
        update.setEnabled(true);
        update.setFont(new Font("Cambria",0,12));
        update.setText("Update");
        update.setVisible(true);

        update.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                update_data(evt);
            }
        });

        delete = new JButton();
        delete.setBounds(355,300,75,35);
        delete.setBackground(new Color(214,217,223));
        delete.setEnabled(true);
        delete.setFont(new Font("Cambria",0,12));
        delete.setText("Delete");
        delete.setVisible(true);

        delete.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                delete_data(evt);
            }
        });

        save = new JButton();
        save.setBounds(440,300,75,35);
        save.setBackground(new Color(214,217,223));
        save.setEnabled(true);
        save.setFont(new Font("Cambria",0,12));
        save.setText("Save");
        save.setVisible(true);

        save.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                save_data(evt);
            }
        });

        back = new JButton();
        back.setBounds(525,300,75,35);
        back.setBackground(new Color(214,217,223));
        back.setEnabled(true);
        back.setFont(new Font("Cambria",0,12));
        back.setText("Back");
        back.setVisible(true);
        
        back.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                dispose();
                new MainMenu();
            }
        });

        String t_title[] = {"ID","Nama","Check In","Check Out","Type","Price","Animal","Ras","Description","Status"};
        data_tabel  = new DefaultTableModel(null,t_title);

        panel_tabel = new JTable();
        panel_tabel.setModel(data_tabel);
        panel_tabel.setEnabled(true);
        panel_tabel.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        skrol_tabel = new JScrollPane();
        skrol_tabel.getViewport().add(panel_tabel);
        skrol_tabel.setBounds(20,355,775,300);
        getDataTabelTransaction();

        //adding components to contentPane panel
        contentPane.add(judul);
        contentPane.add(customer);
        contentPane.add(field_customer);
        contentPane.add(check_in);
        contentPane.add(field_check_in);
        contentPane.add(type);
        contentPane.add(mandi_jamur);
        contentPane.add(mandi_biasa);
        contentPane.add(mandi_kutu);
        contentPane.add(bedah_tulang);
        contentPane.add(vaksin);
        contentPane.add(opers_kecil);
        contentPane.add(opers_besar);
        contentPane.add(konsultasi);
        contentPane.add(rontgen);
        contentPane.add(pemeriksaan_kesehatan);
        contentPane.add(penitipan_hewan);
        contentPane.add(animal);
        contentPane.add(field_anjing);
        contentPane.add(field_kucing);
        contentPane.add(field_kelinci);
        contentPane.add(ras);
        contentPane.add(field_ras);
        contentPane.add(descr);
        contentPane.add(field_descr);
        contentPane.add(price);
        contentPane.add(field_price);
        contentPane.add(status);
        contentPane.add(field_proses);
        contentPane.add(field_cancel);
        contentPane.add(reset);
        contentPane.add(update);
        contentPane.add(delete);
        contentPane.add(save);
        contentPane.add(back);
        contentPane.add(skrol_tabel,BorderLayout.CENTER);
        
        //adding panel to JFrame and seting of window position and close operation
        this.add(contentPane);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.pack();
        this.setVisible(true);
        this.setResizable(false); //no maximaze
    }

    private void getDataTabelTransaction(){
        try{
            //get data from database
            Koneksidatabase db   = new Koneksidatabase();
            Statement stat = db.koneksi.createStatement();
            ResultSet rs   = stat.executeQuery("SELECT customers.nama, transactions.* FROM customers ,transactions WHERE transactions.customer_id = customers.id ");
            while(rs.next()){
                String t_id        = rs.getString("id");
                String t_nama      = rs.getString("nama");
                String t_check_in  = rs.getString("check_in");
                String t_check_out = rs.getString("check_out");
                String t_type      = rs.getString("j_transaction");
                String t_harga     = rs.getString("harga");
                String t_animal    = rs.getString("j_hewan");
                String t_ras       = rs.getString("ras");
                String t_descr     = rs.getString("ket");
                String t_status    = rs.getString("status");
                String convert_status;
                if(t_status.equals("p")){
                    convert_status = "Process";
                }
                else if(t_status.equals("f")){
                    convert_status = "Finish";
                }
                else{
                    convert_status = "Cancel";
                }
                String[] t_data = {t_id,t_nama,t_check_in,t_check_out,t_type,t_harga,t_animal,t_ras,t_descr,convert_status};
                data_tabel.addRow(t_data);
            }
            rs.close();
            stat.close();
            db.koneksi.close();
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void update_data (MouseEvent evt) {
        id_trans = JOptionPane.showInputDialog(null, "Input ID Transactions ?");
        try{
            //update data from database
            query = "UPDATE transactions SET customer_id = ?, check_in = ?,j_transaction = ?,harga = ?,j_hewan = ?,ras = ?,ket = ?,status = ?  WHERE id = ?";
            db   = new Koneksidatabase();
            pst = db.koneksi.prepareStatement(query);
            type_transaction = "";
            if(mandi_jamur.isSelected()){
                type_transaction = "- Mandi Jamur -";
            }
            if(mandi_biasa.isSelected()){
                type_transaction = type_transaction+"- Mandi Biasa -";
            }
            if(mandi_kutu.isSelected()){
                type_transaction = type_transaction+"- Mandi Kutu -";
            }
            if(vaksin.isSelected()){
                type_transaction = type_transaction+"- Vaksin -";
            }
            if(opers_kecil.isSelected()){
                type_transaction = type_transaction+"- Operasi Kecil -";
            }
            if(opers_besar.isSelected()){
                type_transaction = type_transaction+"- Operasi Besar -";
            }
            if(bedah_tulang.isSelected()){
                type_transaction = type_transaction+"- Bedah Tulang -";
            }
            if(konsultasi.isSelected()){
                type_transaction = type_transaction+"- Konsultasi -";
            }
            if(rontgen.isSelected()){
                type_transaction = type_transaction+"- Rontgen -";
            }
            if(pemeriksaan_kesehatan.isSelected()){
                type_transaction = type_transaction+"- Periksa Kesehatan -";
            }
            if(penitipan_hewan.isSelected()){
                type_transaction = type_transaction+"- Titip Hewan -";
            }
            Object id_cus   = field_customer.getSelectedItem();
            String name_cus = ((ComboItem)id_cus).getValue();
            pst.setString(1,name_cus);
            pst.setString(2,field_check_in.getText());
            pst.setString(3,type_transaction);
            pst.setString(4,field_price.getText());
            pst.setString(5,group_button_animal.getSelection().getActionCommand());
            pst.setString(6,field_ras.getText());
            pst.setString(7,field_descr.getText());
            pst.setString(8,group_button_status.getSelection().getActionCommand());
            pst.setString(9,id_trans);
            pst.execute();
            db.koneksi.close();
            JOptionPane.showMessageDialog(null, "Update Succsessfull");
            dispose();
            new Transactions();
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void delete_data (MouseEvent evt) {
        id_trans = JOptionPane.showInputDialog(null, "Input ID Transactions ?");
        try{
            //delete data from database
            query = "DELETE FROM transactions WHERE id = ?";
            db   = new Koneksidatabase();
            pst = db.koneksi.prepareStatement(query);
            pst.setString(1,id_trans);
            pst.execute();
            db.koneksi.close();
            JOptionPane.showMessageDialog(null, "Delete Succsessfull");
            dispose();
            new Transactions();
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void save_data (MouseEvent evt) {
        if(field_check_in.getText().isEmpty() &&
            field_price.getText().isEmpty() &&
            field_ras.getText().isEmpty() &&
            field_descr.getText().isEmpty()

        ){
            JOptionPane.showMessageDialog(null,"Please Input All Filed");
        }
        else {
            try{
                //save data from database
                query = "INSERT INTO transactions(customer_id,check_in,j_transaction,harga,j_hewan,ras,ket,status) VALUES(?,?,?,?,?,?,?,?)";
                db   = new Koneksidatabase();
                pst = db.koneksi.prepareStatement(query);
                type_transaction = "";
                if(mandi_jamur.isSelected()){
                    type_transaction = "- Mandi Jamur -";
                }
                if(mandi_biasa.isSelected()){
                    type_transaction = type_transaction+"- Mandi Biasa -";
                }
                if(mandi_kutu.isSelected()){
                    type_transaction = type_transaction+"- Mandi Kutu -";
                }
                if(vaksin.isSelected()){
                    type_transaction = type_transaction+"- Vaksin -";
                }
                if(opers_kecil.isSelected()){
                    type_transaction = type_transaction+"- Operasi Kecil -";
                }
                if(opers_besar.isSelected()){
                    type_transaction = type_transaction+"- Operasi Besar -";
                }
                if(bedah_tulang.isSelected()){
                    type_transaction = type_transaction+"- Bedah Tulang -";
                }
                if(konsultasi.isSelected()){
                    type_transaction = type_transaction+"- Konsultasi -";
                }
                if(rontgen.isSelected()){
                    type_transaction = type_transaction+"- Rontgen -";
                }
                if(pemeriksaan_kesehatan.isSelected()){
                type_transaction = type_transaction+"- Periksa Kesehatan -";
                }
                if(penitipan_hewan.isSelected()){
                type_transaction = type_transaction+"- Titip Hewan -";
                }
                
                Object id_cus   = field_customer.getSelectedItem();
                String name_cus = ((ComboItem)id_cus).getValue();
                pst.setString(1,name_cus);
                pst.setString(2,field_check_in.getText());
                pst.setString(3,type_transaction);
                pst.setString(4,field_price.getText());
                pst.setString(5,group_button_animal.getSelection().getActionCommand());
                pst.setString(6,field_ras.getText());
                pst.setString(7,field_descr.getText());
                pst.setString(8,group_button_status.getSelection().getActionCommand());
                pst.execute();
                db.koneksi.close();
                JOptionPane.showMessageDialog(null, "Save Succsessfull");
                dispose();
                new Transactions();
            }
            catch(Exception e)
            {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }

    public static void main(String[] args){
        System.setProperty("swing.defaultlaf", "com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Transactions();
            }
        });
    }

}