package project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;

public class Add extends JFrame implements ActionListener {

    Database dataAdd = new Database();
    JButton AddButton = new JButton("ADD NEW BOOK");
    JButton ClearButton = new JButton("CLEAR");
    JButton BackButton = new JButton("GO BACK");
    JTextField ID = new JTextField(25);
    JTextField name = new JTextField(25);
    JTextField author = new JTextField(25);
    JTextField publisher = new JTextField(25);
    JTextField booktype = new JTextField(25);
    JTextField numofprints = new JTextField(25);
    JTextField numofstock = new JTextField(25);
    String[] day = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18",
        "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
    String[] month = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
    String[] year = {"1986", "1987", "1988", "1989", "1990", "1991", "1992", "1993", "1994", "1995", "1996", "1997", "1998", "1999", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016"};
    JComboBox copyrightDay = new JComboBox(day);
    JComboBox copyrightMonth = new JComboBox(month);
    JComboBox copyrightYear = new JComboBox(year);
    JPanel panel = new JPanel();
    JPanel panel1 = new JPanel();

    public Add() {
        setLayout(new FlowLayout());
        setTitle("Add");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        panel.add(new JLabel("Book ID:                    "));
        panel.add(ID);
        panel.add(new JLabel("Book Name:            "));
        panel.add(name);
        panel.add(new JLabel("Author Name:         "));
        panel.add(author);
        panel.add(new JLabel("Publisher:                "));
        panel.add(publisher);
        panel.add(new JLabel("Book Type:              "));
        panel.add(booktype);
        panel.add(new JLabel("Number Of Prints: "));
        panel.add(numofprints);
        panel1.add(new JLabel("Copyright Date:                                 "));
        panel1.add(copyrightDay);
        panel1.add(copyrightMonth);
        panel1.add(copyrightYear);
        panel1.add(new JLabel("Number Of Stock: "));
        panel1.add(numofstock);
        panel1.add(AddButton);
        panel1.add(ClearButton);
        panel1.add(BackButton);
        panel.setPreferredSize(new Dimension(400, 150));
        panel1.setPreferredSize(new Dimension(400, 100));
        add("WEST", panel);
        add("WEST", panel1);
        AddButton.addActionListener(this);
        ClearButton.addActionListener(this);
        BackButton.addActionListener(this);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent a) {
        String cop = copyrightDay.getSelectedItem() + "." + copyrightMonth.getSelectedItem() + "." + copyrightYear.getSelectedItem();

        if (a.getSource() == AddButton) {
            if (ID.getText().equals("") || name.getText().equals("") || author.getText().equals("") || publisher.getText().equals("") || booktype.getText().equals("") || numofprints.getText().equals("") || numofstock.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "! Fill in the blank fields.");
            } else {
                BookDomain bdn = new BookDomain();
                bdn.setBookID(Integer.parseInt(ID.getText()));
                bdn.setBookName(name.getText());
                bdn.setAuthorName(author.getText());
                bdn.setPublisher(publisher.getText());
                bdn.setBookType(booktype.getText());
                bdn.setNumOfPrints(Integer.parseInt(numofprints.getText()));
                bdn.setCopyrightDate(cop);
                bdn.setNumOfStock(Integer.parseInt(numofstock.getText()));
                dataAdd.insertUser(bdn);
                new AdminTable();
                this.dispose();
            }
        } else if (a.getSource() == BackButton) {
            new AdminTable();
            this.dispose();
        }else if(a.getSource()==ClearButton){
            ID.setText("") ;
            name.setText("") ;
            author.setText(""); 
            publisher.setText("") ;
            booktype.setText("");
            numofprints.setText("");
            numofstock.setText("");
        }
    }
}
