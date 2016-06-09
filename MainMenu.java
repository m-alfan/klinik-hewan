import javax.swing.UIManager.LookAndFeelInfo;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import javax.swing.border.Border;
import javax.swing.*;
import java.util.Date;
import java.text.SimpleDateFormat;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class MainMenu extends JFrame
{
    private JPanel logo;
    private JLabel name;
    private JLabel alamat;
    private JLabel telp;
    private JLabel kota;
    private JLabel date;
    private JPanel dashboard;
    private JPanel registration;
    private JPanel transaction;
    private JPanel report;
    private JPanel logout;
    private JButton check_out;

    //tabel
    private DefaultTableModel data_tabel;
    private JTable panel_tabel;
    private JScrollPane skrol_tabel;

    //Constructor 
    public MainMenu()
    {

        this.setTitle("Dashboard");
        this.setSize(1080,500);

        //pane with null layout
        JPanel contentPane = new JPanel(null);
        contentPane.setPreferredSize(new Dimension(1080,500));
        contentPane.setBackground(new Color(153,153,255));
        
        //icon for logo
        ImageIcon imageIcon = new ImageIcon("image/logo.jpg");
        Image image  = imageIcon.getImage();
        Image newimg = image.getScaledInstance(90, 90,  java.awt.Image.SCALE_SMOOTH);
        imageIcon    = new ImageIcon(newimg);        
        JLabel logo  = new JLabel("", imageIcon, JLabel.CENTER);
        //logo.setBorder(BorderFactory.createEtchedBorder(1));
        logo.setBounds(5,14,90,90);
        logo.setEnabled(true);
        logo.setVisible(true);
        
        name = new JLabel();
        name.setBounds(110,14,270,30);
        name.setBackground(new Color(214,217,223));
        name.setEnabled(true);
        name.setFont(new Font("Cambria",0,30));
        name.setText("Klinik Hewan ABC");
        name.setVisible(true);
        
        alamat = new JLabel();
        alamat.setBounds(110,47,238,18);
        alamat.setBackground(new Color(214,217,223));
        alamat.setEnabled(true);
        alamat.setFont(new Font("Cambria",0,12));
        alamat.setText("Jalan Raya Pasar Minggu RT 001/007 No.25");
        alamat.setVisible(true);
        
        telp = new JLabel();
        telp.setBounds(110,64,158,25);
        telp.setBackground(new Color(214,217,223));
        telp.setEnabled(true);
        telp.setFont(new Font("Cambria",0,12));
        telp.setText("Telp. (021) - 9876 537");
        telp.setVisible(true);
        
        kota = new JLabel();
        kota.setBounds(110,83,200,21);
        kota.setBackground(new Color(214,217,223));
        kota.setEnabled(true);
        kota.setFont(new Font("Cambria",0,12));
        kota.setText("Jakarta Selatan, DKI Jakarta");
        kota.setVisible(true);
        
        SimpleDateFormat sdfDate = new SimpleDateFormat("EEEE, dd MMMM yyyy");//dd/MM/yyyy
        Date now = new Date();
        date = new JLabel();
        date.setBounds(705,14,254,30);
        date.setBackground(new Color(214,217,223));
        date.setEnabled(true);
        date.setFont(new Font("Cambria",0,12));
        date.setText("Today : "+sdfDate.format(now));
        date.setVisible(true);
        
        //icon for dashboard
        ImageIcon imageIcon_dashboard = new ImageIcon("image/dashboard.png");
        Image image_dashboard  = imageIcon_dashboard.getImage();
        Image newimg_dashboard = image_dashboard.getScaledInstance(70, 70,  java.awt.Image.SCALE_SMOOTH);
        imageIcon    = new ImageIcon(newimg_dashboard);        
        JLabel dashboard  = new JLabel("", imageIcon, JLabel.CENTER);
        //logo.setBorder(BorderFactory.createEtchedBorder(1));
        dashboard.setBounds(705,50,70,70);
        dashboard.setEnabled(true);
        dashboard.setVisible(true);

        //icon for regustration
        ImageIcon imageIcon_registration = new ImageIcon("image/registration.png");
        Image image_registration  = imageIcon_registration.getImage();
        Image newimg_registration = image_registration.getScaledInstance(70, 70,  java.awt.Image.SCALE_SMOOTH);
        imageIcon    = new ImageIcon(newimg_registration);        
        JLabel registration  = new JLabel("", imageIcon, JLabel.CENTER);
        //logo.setBorder(BorderFactory.createEtchedBorder(1));
        registration.setBounds(780,50,70,70);
        registration.setEnabled(true);
        registration.setVisible(true);
        
        //icon for transaction
        ImageIcon imageIcon_transaction = new ImageIcon("image/transaction.png");
        Image image_transaction  = imageIcon_transaction.getImage();
        Image newimg_transaction = image_transaction.getScaledInstance(70, 70,  java.awt.Image.SCALE_SMOOTH);
        imageIcon    = new ImageIcon(newimg_transaction);        
        JLabel transaction  = new JLabel("", imageIcon, JLabel.CENTER);
        //logo.setBorder(BorderFactory.createEtchedBorder(1));
        transaction.setBounds(855,50,70,70);
        transaction.setEnabled(true);
        transaction.setVisible(true);
        
        //icon for report
        ImageIcon imageIcon_report = new ImageIcon("image/report.png");
        Image image_report  = imageIcon_report.getImage();
        Image newimg_report = image_report.getScaledInstance(70, 70,  java.awt.Image.SCALE_SMOOTH);
        imageIcon    = new ImageIcon(newimg_report);        
        JLabel report  = new JLabel("", imageIcon, JLabel.CENTER);
        //logo.setBorder(BorderFactory.createEtchedBorder(1));
        report.setBounds(930,50,70,70);
        report.setEnabled(true);
        report.setVisible(true);
        
        //icon for logout
        ImageIcon imageIcon_logout = new ImageIcon("image/logout.png");
        Image image_logout  = imageIcon_logout.getImage();
        Image newimg_logout = image_logout.getScaledInstance(70, 70,  java.awt.Image.SCALE_SMOOTH);
        imageIcon    = new ImageIcon(newimg_logout);        
        JLabel logout  = new JLabel("", imageIcon, JLabel.CENTER);
        //logo.setBorder(BorderFactory.createEtchedBorder(1));
        logout.setBounds(1005,50,70,70);
        logout.setEnabled(true);
        logout.setVisible(true);
        
        dashboard.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                dispose();
                new MainMenu();
            }
        });
        
        registration.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                JOptionPane.showMessageDialog(null,"Registration");
                dispose();
                new Registrations();
            }
        });
        
        transaction.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                JOptionPane.showMessageDialog(null,"Transaction");
                dispose();
                new Transactions();
            }
        });
        
        report.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                JOptionPane.showMessageDialog(null,"Report");
                new Reports();
            }
        });
        
        logout.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                 int result = JOptionPane.showConfirmDialog(null, "Do you want to logout ?","Confirmation", JOptionPane.YES_NO_OPTION);
                 if(result == JOptionPane.YES_OPTION) {
                     dispose();
                     new Login();
                 } 
            }
        });

        check_out = new JButton();
        check_out.setBounds(965,135,115,35);
        check_out.setBackground(new Color(214,217,223));
        check_out.setEnabled(true);
        check_out.setFont(new Font("Cambria",0,14));
        check_out.setText("Check Out");
        check_out.setVisible(true);

        check_out.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                JOptionPane.showMessageDialog(null,"Check Out");
                dispose();
                new CheckOut();
            }
        });

        String t_title[] = {"No","ID","Nama","No Telepon","Check In","Type","Price","Animal","Ras","Description"};
        data_tabel  = new DefaultTableModel(null,t_title);

        panel_tabel = new JTable();
        panel_tabel.setModel(data_tabel);
        panel_tabel.setEnabled(true);
        panel_tabel.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        skrol_tabel = new JScrollPane();
        skrol_tabel.getViewport().add(panel_tabel);
        skrol_tabel.setBounds(10,180,1070,290);
        getDataTabelTransaction();
        
        //adding components to contentPane panel
        contentPane.add(alamat);
        contentPane.add(dashboard);
        contentPane.add(date);
        contentPane.add(kota);
        contentPane.add(logo);
        contentPane.add(logout);
        contentPane.add(name);
        contentPane.add(registration);
        contentPane.add(report);
        contentPane.add(check_out);
        contentPane.add(telp);
        contentPane.add(transaction);
        contentPane.add(skrol_tabel);

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
            ResultSet rs   = stat.executeQuery("SELECT customers.nama, customers.no_tlp, transactions.* FROM customers ,transactions WHERE transactions.customer_id = customers.id AND transactions.status = 'p';");
            int a = 1;
            while(rs.next()){
                String t_no = Integer.toString(a);
                String t_id        = rs.getString("id");
                String t_nama      = rs.getString("nama");
                String t_no_tlp    = rs.getString("no_tlp");
                String t_check_in  = rs.getString("check_in");
                String t_type      = rs.getString("j_transaction");
                String t_harga     = rs.getString("harga");
                String t_animal    = rs.getString("j_hewan");
                String t_ras       = rs.getString("ras");
                String t_descr     = rs.getString("ket");
                String[] t_data = {t_no,t_id,t_nama,t_no_tlp,t_check_in,t_type,t_harga,t_animal,t_ras,t_descr};
                data_tabel.addRow(t_data);
                a++;
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
    
     public static void main(String[] args){
        System.setProperty("swing.defaultlaf", "com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MainMenu();
            }
        });
    }

}