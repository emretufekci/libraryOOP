package project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

public class Reservation extends JFrame implements ActionListener {

    Database dataList = new Database();
    JButton AddButtonReservation = new JButton("Reserve");
    JButton ExitButton = new JButton("Exit");
    JTable table;
    
    
    static ArrayList bookIdMakeGlobal = new ArrayList();

    public Reservation(int[] takeID) {

        setLayout(new FlowLayout());
        setTitle("Reservation");
        setSize(900, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        String dizi[] = {"Book ID", "Book Name", "Author Name", "Publisher", "Book Type", "Number Of Prints", "Copyright Date", "Number Of Stock"};
        DefaultTableModel model = new DefaultTableModel(dizi, 0);
        table = new JTable(model);
        table.setPreferredSize(new Dimension(800, 200));
        table.setPreferredSize(new Dimension(800, 200));
        Color blue3 = new Color(203, 219, 215);
        table.setBackground(blue3);
        JScrollPane pane = new JScrollPane(table);
        pane.setPreferredSize(new Dimension(800, 200));

        for (BookDomain list : dataList.basketList(takeID)) {

            model.addRow(list.getObjects());

        }
        add(pane);
        add(AddButtonReservation);
        add(ExitButton);
        AddButtonReservation.addActionListener(this);
        ExitButton.addActionListener(this);
        setVisible(true);
    }
  
    public static void reservationMakeGlobalID(int[] value){
        
        for(int makeGlobal=0;makeGlobal<value.length;makeGlobal++){
            if(value[makeGlobal]!=0)
            bookIdMakeGlobal.add(value[makeGlobal]);
        }
    }

    public void actionPerformed(ActionEvent a) {
        if (a.getSource() == AddButtonReservation) {
            
             //for(int dropQuantity:bookIdMakeGlobal)
             //System.out.println("X="+(dropQuantity+1));
             
             //veritabanindan kitap sayisi dusuluyor
             
           dataList.dropBookQuantity(bookIdMakeGlobal);
           
//            for (Object i : bookIdMakeGlobal) {
//               // System.out.println("book id value "+i);
//            }
//rezervasyon bittikten sonra yeni rezervasyondan once arraylist temizlenmeli
            bookIdMakeGlobal.clear();
            JOptionPane.showMessageDialog(null, "All Records Reservated");
            new Table();
            this.dispose();
        } else if (a.getSource() == ExitButton) {
            this.dispose();
        }
    }
}
