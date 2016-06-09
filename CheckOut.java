import javax.swing.UIManager.LookAndFeelInfo;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.Border;
import javax.swing.*;
import java.sql.*;
import java.util.Date;
import java.text.SimpleDateFormat;

public class CheckOut extends JFrame {

    private JLabel judul;
    private JLabel id;
    private JTextField field_id;
    private JLabel status;
    private JRadioButton field_cancel;
    private JRadioButton field_finish;
    private ButtonGroup group_button;    
    private JLabel check_out;
    private JTextField field_check_out;
    private JLabel descr;
    private JTextArea field_descr;
    private JButton reset;
    private JButton update;
    private JButton back;
    
    //date
    private SimpleDateFormat sdfDate;
    private Date now;

    //database
    private String id_customer;
    private String query;
    private Koneksidatabase db;
    private PreparedStatement pst;
    private ResultSet rs;

    //Constructor 
    public CheckOut(){

        this.setTitle("Check Out");
        this.setSize(525,380);

        //pane with null layout
        JPanel contentPane = new JPanel(null);
        contentPane.setPreferredSize(new Dimension(525,380));
        contentPane.setBackground(new Color(153,153,255));

        judul = new JLabel();
        judul.setBounds(15,20,450,45);
        judul.setBackground(new Color(214,217,223));
        judul.setEnabled(true);
        judul.setFont(new Font("Cambria",0,40));
        judul.setText("Check Out");
        judul.setVisible(true);
        
        id = new JLabel();
        id.setBounds(20,80,90,35);
        id.setBackground(new Color(214,217,223));
        id.setEnabled(true);
        id.setFont(new Font("Cambria",0,15));
        id.setText("ID");
        id.setVisible(true);

        field_id = new JTextField();
        field_id.setBounds(175,80,200,35);
        field_id.setBackground(new Color(255,255,255));
        field_id.setEnabled(true);
        field_id.setFont(new Font("Cambria",0,12));
        field_id.setText("ID Transaction");
        field_id.setVisible(true);
        
        field_id.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                field_id.setText("");
            }
        });

        status = new JLabel();
        status.setBounds(20,120,120,30);
        status.setBackground(new Color(214,217,223));
        status.setEnabled(true);
        status.setFont(new Font("Cambria",0,15));
        status.setText("Status");
        status.setVisible(true);

        field_cancel = new JRadioButton();
        field_cancel.setBounds(175,120,90,35);
        field_cancel.setBackground(new Color(214,217,223));
        field_cancel.setEnabled(true);
        field_cancel.setFont(new Font("Cambria",0,12));
        field_cancel.setText("Cancel");
        field_cancel.setVisible(true);
        field_cancel.setActionCommand("c");

        field_finish = new JRadioButton();
        field_finish.setBounds(275,120,90,35);
        field_finish.setBackground(new Color(214,217,223));
        field_finish.setEnabled(true);
        field_finish.setFont(new Font("Cambria",0,12));
        field_finish.setText("Finish");
        field_finish.setVisible(true);
        field_finish.setActionCommand("f");
        
        group_button = new ButtonGroup();
        group_button.add(field_cancel);
        group_button.add(field_finish);

        check_out = new JLabel();
        check_out.setBounds(20,160,90,35);
        check_out.setBackground(new Color(214,217,223));
        check_out.setEnabled(true);
        check_out.setFont(new Font("Cambria",0,15));
        check_out.setText("Check Out");
        check_out.setVisible(true);

        field_check_out = new JTextField();
        field_check_out.setBounds(175,160,200,35);
        field_check_out.setBackground(new Color(255,255,255));
        field_check_out.setEnabled(false);
        field_check_out.setFont(new Font("Cambria",0,12));
        sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        now = new Date();
        field_check_out.setText(sdfDate.format(now));
        field_check_out.setVisible(true);

        descr = new JLabel();
        descr.setBounds(20,200,90,35);
        descr.setBackground(new Color(214,217,223));
        descr.setEnabled(true);
        descr.setFont(new Font("Cambria",0,15));
        descr.setText("Description");
        descr.setVisible(true);

        field_descr = new JTextArea();
        field_descr.setBounds(175,200,200,100);
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
                     
        reset = new JButton();
        reset.setBounds(155,320,75,35);
        reset.setBackground(new Color(214,217,223));
        reset.setEnabled(true);
        reset.setFont(new Font("Cambria",0,12));
        reset.setText("Reset");
        reset.setVisible(true);
        
        reset.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                field_id.setText("");
                group_button.clearSelection();
                now = new Date();
                field_check_out.setText(sdfDate.format(now));
                field_descr.setText("");
            }
        });

        update = new JButton();
        update.setBounds(240,320,75,35);
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

        back = new JButton();
        back.setBounds(325,320,75,35);
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

        //adding components to contentPane panel
        contentPane.add(judul);
        contentPane.add(id);
        contentPane.add(field_id);
        contentPane.add(status);
        contentPane.add(field_cancel);
        contentPane.add(field_finish);
        contentPane.add(check_out);
        contentPane.add(field_check_out);
        contentPane.add(descr);
        contentPane.add(field_descr);
        contentPane.add(reset);
        contentPane.add(update);
        contentPane.add(back);

        //adding panel to JFrame and seting of window position and close operation
        this.add(contentPane);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.pack();
        this.setVisible(true);
        this.setResizable(false); //no maximaze
    }

    private void update_data (MouseEvent evt) {
        try{
            //update data from database
            query = "UPDATE transactions SET check_out = ?, ket = ?, status = ?  WHERE id = ?";
            db   = new Koneksidatabase();
            pst = db.koneksi.prepareStatement(query);
            pst.setString(1,field_check_out.getText());
            pst.setString(2,field_descr.getText());
            pst.setString(3,group_button.getSelection().getActionCommand());
            pst.setString(4,field_id.getText());
            pst.execute();
            db.koneksi.close();
            JOptionPane.showMessageDialog(null, "Update Succsessfull");
            dispose();
            new MainMenu();
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
                new CheckOut();
            }
        });
    }

}