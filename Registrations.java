import javax.swing.UIManager.LookAndFeelInfo;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.Border;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class Registrations extends JFrame {

    private JLabel judul;
    private JLabel nama;
    private JTextField field_nama;
    private JLabel jk;
    private JRadioButton field_l;
    private JRadioButton field_p;
    private ButtonGroup group_button;
    private JLabel no_telp;
    private JTextField field_no_telp;
    private JLabel alamat;
    private JTextArea field_alamat;
    private JButton reset;
    private JButton save;
    private JButton update;
    private JButton delete;
    private JButton back;
    //tabel
    private DefaultTableModel data_tabel;
    private JTable panel_tabel;
    private JScrollPane skrol_tabel;
    //database
    String id_customer;
    String query;
    Koneksidatabase db;
    PreparedStatement pst;
    Statement stat;
    ResultSet rs;

    //Constructor 
    public Registrations(){

        this.setTitle("Registration");
        this.setSize(525,555);

        //pane with null layout
        JPanel contentPane = new JPanel(null);
        contentPane.setPreferredSize(new Dimension(525,555));
        contentPane.setBackground(new Color(153,153,255));

        judul = new JLabel();
        judul.setBounds(15,20,450,45);
        judul.setBackground(new Color(214,217,223));
        judul.setEnabled(true);
        judul.setFont(new Font("Cambria",0,40));
        judul.setText("Customer Registration");
        judul.setVisible(true);
        
        nama = new JLabel();
        nama.setBounds(20,80,90,35);
        nama.setBackground(new Color(214,217,223));
        nama.setEnabled(true);
        nama.setFont(new Font("Cambria",0,15));
        nama.setText("Nama");
        nama.setVisible(true);

        field_nama = new JTextField();
        field_nama.setBounds(175,80,200,35);
        field_nama.setBackground(new Color(255,255,255));
        field_nama.setEnabled(true);
        field_nama.setFont(new Font("Cambria",0,12));
        field_nama.setText("Nama");
        field_nama.setVisible(true);
        
        field_nama.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                field_nama.setText("");
            }
        });

        jk = new JLabel();
        jk.setBounds(20,120,120,30);
        jk.setBackground(new Color(214,217,223));
        jk.setEnabled(true);
        jk.setFont(new Font("Cambria",0,15));
        jk.setText("Gender");
        jk.setVisible(true);

        field_l = new JRadioButton();
        field_l.setBounds(175,120,90,35);
        field_l.setBackground(new Color(214,217,223));
        field_l.setEnabled(true);
        field_l.setFont(new Font("Cambria",0,12));
        field_l.setText("Male");
        field_l.setVisible(true);
        field_l.setActionCommand("l");

        field_p = new JRadioButton();
        field_p.setBounds(275,120,90,35);
        field_p.setBackground(new Color(214,217,223));
        field_p.setEnabled(true);
        field_p.setFont(new Font("Cambria",0,12));
        field_p.setText("Female");
        field_p.setVisible(true);
        field_p.setActionCommand("p");
        
        group_button = new ButtonGroup();
        group_button.add(field_l);
        group_button.add(field_p);

        no_telp = new JLabel();
        no_telp.setBounds(20,160,90,35);
        no_telp.setBackground(new Color(214,217,223));
        no_telp.setEnabled(true);
        no_telp.setFont(new Font("Cambria",0,15));
        no_telp.setText("No Telepon");
        no_telp.setVisible(true);

        field_no_telp = new JTextField();
        field_no_telp.setBounds(175,160,200,35);
        field_no_telp.setBackground(new Color(255,255,255));
        field_no_telp.setEnabled(true);
        field_no_telp.setFont(new Font("Cambria",0,12));
        field_no_telp.setText("No Telepon");
        field_no_telp.setVisible(true);
        
        field_no_telp.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                field_no_telp.setText("");
            }
        });

        alamat = new JLabel();
        alamat.setBounds(20,200,90,35);
        alamat.setBackground(new Color(214,217,223));
        alamat.setEnabled(true);
        alamat.setFont(new Font("Cambria",0,15));
        alamat.setText("Address");
        alamat.setVisible(true);

        field_alamat = new JTextArea();
        field_alamat.setBounds(175,200,200,100);
        field_alamat.setBackground(new Color(255,255,255));
        field_alamat.setEnabled(true);
        field_alamat.setFont(new Font("Cambria",0,12));
        field_alamat.setText("Address");
        field_alamat.setVisible(true);
        
        field_alamat.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                field_alamat.setText("");
            }
        });
                     
        reset = new JButton();
        reset.setBounds(65,320,75,35);
        reset.setBackground(new Color(214,217,223));
        reset.setEnabled(true);
        reset.setFont(new Font("Cambria",0,12));
        reset.setText("Reset");
        reset.setVisible(true);
        
        reset.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                field_nama.setText("");
                group_button.clearSelection();
                field_no_telp.setText("");
                field_alamat.setText("");
            }
        });

        update = new JButton();
        update.setBounds(150,320,75,35);
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
        delete.setBounds(235,320,75,35);
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
        save.setBounds(320,320,75,35);
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
        back.setBounds(405,320,75,35);
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

        String t_title[] = {"ID","Nama","Jenis Kelaminn","No Telepon","Alamat"};
        data_tabel  = new DefaultTableModel(null,t_title);

        panel_tabel = new JTable();
        panel_tabel.setModel(data_tabel);
        panel_tabel.setEnabled(true);

        skrol_tabel = new JScrollPane();
        skrol_tabel.getViewport().add(panel_tabel);
        skrol_tabel.setBounds(10,360,515,195);
        getDataTabelCustomer();

        //adding components to contentPane panel
        contentPane.add(judul);
        contentPane.add(nama);
        contentPane.add(field_nama);
        contentPane.add(jk);
        contentPane.add(field_l);
        contentPane.add(field_p);
        contentPane.add(no_telp);
        contentPane.add(field_no_telp);
        contentPane.add(alamat);
        contentPane.add(field_alamat);
        contentPane.add(reset);
        contentPane.add(update);
        contentPane.add(delete);
        contentPane.add(save);
        contentPane.add(back);
        contentPane.add(skrol_tabel);

        //adding panel to JFrame and seting of window position and close operation
        this.add(contentPane);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.pack();
        this.setVisible(true);
        this.setResizable(false); //no maximaze
    }

    private void getDataTabelCustomer(){
        try{
            //get data from database
            db   = new Koneksidatabase();
            stat = db.koneksi.createStatement();
            rs   = stat.executeQuery("SELECT * FROM customers");
            while(rs.next()){
                String t_id   = rs.getString("id");
                String t_nama = rs.getString("nama");
                String t_jk   = rs.getString("jk");
                String convert_jk;
                if(t_jk.equals("l")){
                    convert_jk = "Laki-laki";
                }
                else{
                    convert_jk = "Perempuan";
                }
                String t_no_tlp = rs.getString("no_tlp");
                String t_alamat = rs.getString("alamat");
                String[] t_data = {t_id,t_nama,convert_jk,t_no_tlp,t_alamat};
                data_tabel.addRow(t_data);
            }
            rs.close();
            stat.close();
            db.koneksi.close();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void update_data (MouseEvent evt) {
        id_customer = JOptionPane.showInputDialog(null, "Input ID Customers ?");
        try{
            //update data from database
            query = "UPDATE customers SET nama = ?, jk = ?, no_tlp = ?, alamat = ? WHERE id = ?";
            db   = new Koneksidatabase();
            pst = db.koneksi.prepareStatement(query);
            pst.setString(1,field_nama.getText());
            pst.setString(2,group_button.getSelection().getActionCommand());
            pst.setString(3,field_no_telp.getText());
            pst.setString(4,field_alamat.getText());
            pst.setString(5,id_customer);
            pst.execute();
            db.koneksi.close();
            JOptionPane.showMessageDialog(null, "Update Succsessfull");
            dispose();
            new Registrations();
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void delete_data (MouseEvent evt) {
        try{
            id_customer = JOptionPane.showInputDialog(null, "Input ID Customers ?");
            //delete data from database
            query = "DELETE FROM customers WHERE id = ?";
            db   = new Koneksidatabase();
            pst = db.koneksi.prepareStatement(query);
            pst.setString(1,id_customer);
            pst.execute();
            db.koneksi.close();
            JOptionPane.showMessageDialog(null, "Delete Succsessfull");
            dispose();
            new Registrations();
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void save_data (MouseEvent evt) {
        if(field_nama.getText().isEmpty() && field_no_telp.getText().isEmpty() && field_alamat.getText().isEmpty()){
            JOptionPane.showMessageDialog(null,"Please Input All Filed");
        }
        else {
            try{
                //save data from database
                query = "INSERT INTO customers(nama,jk,no_tlp,alamat) VALUES(?,?,?,?)";
                db   = new Koneksidatabase();
                pst = db.koneksi.prepareStatement(query);
                pst.setString(1,field_nama.getText());
                pst.setString(2,group_button.getSelection().getActionCommand());
                pst.setString(3,field_no_telp.getText());
                pst.setString(4,field_alamat.getText());
                pst.execute();
                db.koneksi.close();
                JOptionPane.showMessageDialog(null, "Save Succsessfull");
                dispose();
                new Registrations();
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
                new Registrations();
            }
        });
    }

}