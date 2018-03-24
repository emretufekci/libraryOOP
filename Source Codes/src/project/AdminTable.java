package project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.DefaultTableModel;

public class AdminTable extends JFrame implements ActionListener {

    Database dataList = new Database();
    JButton AddButton = new JButton("Add New Book");
    JButton DeleteButton = new JButton("Delete A Book");
    JButton UpdateStock = new JButton("Update Stock");
    JButton ExitButton = new JButton("Exit");

    public AdminTable() {
        setLayout(new FlowLayout());
        setTitle("Admin Table");
        setSize(900, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        String dizi[] = {"Book ID", "Book Name", "Author Name", "Publisher", "Book Type", "Number Of Prints", "Copyright Date", "Number Of Stock"};
        DefaultTableModel model = new DefaultTableModel(dizi, 0);
        JTable table = new JTable(model);  
        
        table.setPreferredSize(new Dimension(800, 200));
        table.setPreferredSize(new Dimension(800, 200));
        Color blue3 = new Color(203, 219, 215);
        table.setBackground(blue3);
        JScrollPane pane = new JScrollPane(table);
       
        
        pane.setPreferredSize(new Dimension(800, 200));
        for (BookDomain list : dataList.list()) {

            model.addRow(list.getObjects());

        }
        add(pane);
        add(AddButton);
        add(DeleteButton);
        add(UpdateStock);
        add(ExitButton);
        AddButton.addActionListener(this);
        DeleteButton.addActionListener(this);
        UpdateStock.addActionListener(this);
        ExitButton.addActionListener(this);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent a) {
        if (a.getSource() == AddButton) {
            new Add();
            this.dispose();
        } else if (a.getSource() == DeleteButton) {
            new Delete();
            this.dispose();
        } else if (a.getSource() == UpdateStock) {
            new UpdateStockOfBook();
            this.dispose();
        } else if (a.getSource() == ExitButton) {
            this.dispose();
        }
    }
}
