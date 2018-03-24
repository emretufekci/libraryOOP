package project;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
public class UpdateStockOfBook extends JFrame implements ActionListener{
    
    JTextField Bookid=new JTextField(20);
    JButton UpdateButton=new JButton("Update a Book");
    JButton CancelButton=new JButton("Cancel");
    
    JPanel panel=new JPanel();
    JPanel panel1=new JPanel();
    JPanel panel2=new JPanel();
    JPanel panel3=new JPanel();
    JPanel panel4=new JPanel();
    JPanel panel5=new JPanel();
    JPanel panel6=new JPanel();
    JPanel panel7=new JPanel();
    JPanel panel8=new JPanel();
    JPanel panel9=new JPanel();
    
    JLabel id=new JLabel();
    JLabel name=new JLabel();
    JLabel aut=new JLabel();
    JLabel pub=new JLabel();
    JLabel bokt=new JLabel();
    JLabel nup=new JLabel();
    JLabel copyd=new JLabel();
    JTextField nus=new JTextField("Number Of Stock",20);
    JLabel updata=new JLabel();
    
    Database dataUpdate = new Database();
    JButton Search=new JButton("Search");
    
    UpdateStockOfBook(){
        setLayout(new FlowLayout());
        setTitle("Update Stock Of Book");
        setSize(600,400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        panel.add(new JLabel("Enter a Book ID to Update Stock Of Book: "));
        panel.add(Bookid);
        panel.add(Search);
        panel1.add(new JLabel("Book ID:                       "));
        panel1.add(id);
        panel2.add(new JLabel("Book Name:              "));
        panel2.add(name);
        panel3.add(new JLabel("Author Name:      "));
        panel3.add(aut);
        panel4.add(new JLabel("Publisher:         "));
        panel4.add(pub);
        panel5.add(new JLabel("Book Type:        "));
        panel5.add(bokt);
        panel6.add(new JLabel("Number Of Prints: "));
        panel6.add(nup);
        panel7.add(new JLabel("Copyright Date:  "));
        panel7.add(copyd);
        panel8.add(nus);
        
        
        
        panel9.add(UpdateButton);
        panel9.add(CancelButton);
        panel1.setPreferredSize(new Dimension (700,30));
        panel2.setPreferredSize(new Dimension (700,30));
        panel3.setPreferredSize(new Dimension (700,30));
        panel4.setPreferredSize(new Dimension (700,30));
        panel5.setPreferredSize(new Dimension (700,30));
        panel6.setPreferredSize(new Dimension (700,30));
        panel7.setPreferredSize(new Dimension (700,30));
        panel8.setPreferredSize(new Dimension (700,30));
        panel9.setPreferredSize(new Dimension (700,60));
        add(panel);
        add(panel1);
        add(panel2);
        add(panel3);
        add(panel4);
        add(panel5);
        add(panel6);
        add(panel7);
        add(panel8);
        add(panel9);
        Search.addActionListener(this);
        UpdateButton.addActionListener(this);
        CancelButton.addActionListener(this);
        setVisible(true);
    }
    public void actionPerformed(ActionEvent a){
        String search=Bookid.getText();
        String stockNum = nus.getText();
        if(a.getSource()==Search){
            if(Bookid.getText().equals("")){
          JOptionPane.showMessageDialog(null,"Please, give ID number.");
        } else{
            try {
            Connection con=dataUpdate.connect();
            
            //String url="jdbc:sqlite:C:\\Users\\STLab_4G\\Downloads\\Project\\Project\\libraryDB.db";
            //con=DriverManager.getConnection(url,"admin","admin");
            String sr="SELECT * FROM BOOK WHERE BOOKID=?";
            PreparedStatement ps=con.prepareStatement(sr);                   
            ps.setString(1,search);
            ResultSet rs = ps.executeQuery();
                  if (rs.next()) {
                        id.setText(String.valueOf(rs.getInt("BOOKID")));
                        name.setText(rs.getString("BOOKNAME"));
                        aut.setText(rs.getString("AUTHORNAME"));
                        pub.setText(rs.getString("PUBLISHER"));
                        bokt.setText(rs.getString("BOOKTYPE"));
                        nup.setText(String.valueOf(rs.getInt("NUMOFPRINTS")));
                        copyd.setText(rs.getString("COPYRIGHTDATE"));
                        
                        nus.setText(String.valueOf(rs.getInt("NUMOFSTOCK")));
                  }
                  else{
                  JOptionPane.showMessageDialog(null,"Book ID does not exist, Please find another Book ID");
                  }
                  ps.close();
                  con.close();
            } 
            catch (Exception ex) {
                ex.printStackTrace();
            }
          }
        }
            if(a.getSource()==UpdateButton){
            int dialogButton = JOptionPane.YES_NO_OPTION;
            int dialogResult=JOptionPane.showConfirmDialog(null,"Are you sure you want to update this record ?","Warning",dialogButton);
            if(dialogResult == 0) {
                int sentID=Integer.valueOf(search);
                int stockNumber=Integer.valueOf(stockNum);
                    dataUpdate.UpdateStock(stockNumber,sentID);
                    new AdminTable();
                    this.dispose();
            }
            else {
                new AdminTable();
                this.dispose();
            }
          }
            if(a.getSource()==CancelButton){
               new Table();
               this.dispose();
            }
    }
}
    

