package project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Login extends JFrame implements ActionListener {

    Database connDatabase = new Database();

    private String name = "";
    private String passw = "";
    private String namet = "";
    private String passwt = "";
    static Connection connection;

    JLabel UserName = new JLabel("Username: ");
    JLabel Password = new JLabel("Password: ");
    JLabel library = new JLabel("Admin Login");
    JPanel panel = new JPanel();
    JPanel panel1 = new JPanel();
    JPanel panel2 = new JPanel();
    JPanel panel3 = new JPanel();
    JButton LoginButton = new JButton("Sign in");
    JButton Cancel = new JButton("Exit");
    JTextField NameTxt = new JTextField(15);
    JPasswordField PssTxt = new JPasswordField(15);

    public Login() {
        setLayout(new FlowLayout());
        setTitle("Admin Login");
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        LoginButton.setBackground(Color.WHITE);
        Cancel.setBackground(Color.WHITE);
        UserName.setForeground(Color.WHITE);
        Password.setForeground(Color.WHITE);
        library.setForeground(Color.WHITE);
        panel.add(library);
        panel1.add(UserName);
        panel1.add(NameTxt);
        panel2.add(Password);
        panel2.add(PssTxt);
        panel3.add(LoginButton);
        panel3.add(Cancel);
        
        panel.setPreferredSize(new Dimension(300, 50));
        Color blue3 = new Color(62,96,111);
        panel.setBackground(blue3);
        
        Color blue2 = new Color(145,170,180);
        panel1.setPreferredSize(new Dimension(300, 50));
        panel1.setBackground(blue2);
        
        panel2.setPreferredSize(new Dimension(300, 50));
        panel2.setBackground(blue2);
        
        panel3.setPreferredSize(new Dimension(300, 50));
        panel3.setBackground(blue3);
        
        add(panel);
        add(panel1);
        add(panel2);
        add(panel3);
        
        
        this.getContentPane().setBackground(blue3);
        NameTxt.addActionListener(this);
        PssTxt.addActionListener(this);
        LoginButton.addActionListener(this);
        Cancel.addActionListener(this);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent a) {
        namet = this.NameTxt.getText();
        passwt = String.valueOf(this.PssTxt.getPassword());
        if (a.getSource() == LoginButton) {
            try {
                Connection con = connDatabase.connect();

                //String url = "jdbc:sqlite:C:\\Users\\Ercan\\Documents\\NetBeansProjects\\Project\\Project\\libraryDB.db";
                //con=DriverManager.getConnection(url,"admin","admin");
                String sr = "SELECT * FROM USERS WHERE USERNAME=? AND PASSWORD=?";
                PreparedStatement ps = con.prepareStatement(sr);
                ps.setString(1, namet);
                ps.setString(2, passwt);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    new AdminTable();
                    this.dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "The User Name and Password you entered is incorrect !");
                }
                ps.close();
                con.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        } else if (a.getSource() == Cancel) {
            this.dispose();
        }
    }
}
