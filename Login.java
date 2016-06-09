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
import java.sql.*;


public class Login extends JFrame {

    private JLabel labellogin;
    private JLabel logo;
    private JTextField username;
    private JPasswordField password;
    private JCheckBox remember;
    private JButton loginB;

    //Constructor 
    public Login(){

        this.setTitle("Login");
        this.setSize(340,350);

        //pane with null layout
        JPanel contentPane = new JPanel(null);
        contentPane.setPreferredSize(new Dimension(340,350));
        contentPane.setBackground(new Color(153,153,255));

        ImageIcon imageIcon = new ImageIcon("image/logo.jpg");
        Image image  = imageIcon.getImage();
        Image newimg = image.getScaledInstance(90, 90,  java.awt.Image.SCALE_SMOOTH);
        imageIcon    = new ImageIcon(newimg);        
        JLabel logo  = new JLabel("", imageIcon, JLabel.CENTER);
        logo.setBorder(BorderFactory.createEtchedBorder(1));
        logo.setBounds(15,15,90,90);
        logo.setEnabled(true);
        logo.setVisible(true);
        
        labellogin = new JLabel();
        labellogin.setBounds(120,30,140,60);
        labellogin.setEnabled(true);
        labellogin.setFont(new Font("Cambria",0,40));
        labellogin.setText("Login");
        labellogin.setVisible(true);
        
        username = new JTextField();
        username.setBounds(15,120,320,40);
        username.setEnabled(true);
        username.setFont(new Font("Cambria",0,12));
        username.setText("  Your username");
        username.setVisible(true);

        password = new JPasswordField();
        password.setBounds(15,170,320,40);
        password.setEnabled(true);
        password.setFont(new Font("Cambria",0,12));
        password.setText("  Your password");
        password.setVisible(true);

        remember = new JCheckBox();
        remember.setBounds(14,218,120,50);
        remember.setEnabled(true);
        remember.setFont(new Font("Cambria",0,12));
        remember.setText("Remember me");
        remember.setVisible(true);
        
        loginB = new JButton();
        loginB.setBounds(15,282,320,52);
        loginB.setEnabled(true);
        loginB.setFont(new Font("Cambria",0,12));
        loginB.setText("Login");
        loginB.setVisible(true);
        
		//Call defined methods
		loginB.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				login(evt);
			}
		});
        
        username.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                clear_usernmae(evt);
            }
        });
        
        password.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                clear_password(evt);
            }
        });

        //adding components to contentPane panel
        contentPane.add(labellogin);
        contentPane.add(loginB);
        contentPane.add(logo, BorderLayout.CENTER);
        contentPane.add(password);
        contentPane.add(remember);
        contentPane.add(username);

        //adding panel to JFrame and seting of window position and close operation
        this.add(contentPane);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.pack();
        this.setVisible(true);
        this.setResizable(false); //no maximaze
    }

    //Method mouseClicked for loginB
    private void login (MouseEvent evt) {
        String sql = "SELECT * FROM users WHERE username =? AND password =?";
        try
        {
            Koneksidatabase db = new Koneksidatabase();
            PreparedStatement pst = db.koneksi.prepareStatement(sql);
            pst.setString(1,username.getText());
            pst.setString(2,password.getText());
            ResultSet rs = pst.executeQuery();
            
            if(rs.next())
            {
                JOptionPane.showMessageDialog(null,"Welcome to \"Application Klinik Hewan\"");
                MainMenu dashboard = new MainMenu();
                dashboard.setVisible(true);
                this.setVisible(false);
            }
            else
                JOptionPane.showMessageDialog(null,"Username and Password is Incorrect");
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    //Method mouseClicked for username
    private void clear_usernmae (MouseEvent evt) {
        username.setText("");
    }
    
    //Method mouseClicked for password
    private void clear_password (MouseEvent evt) {
        password.setText("");
    }

    public static void main(String[] args){
        System.setProperty("swing.defaultlaf", "com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Login();
            }
        });
    }

}