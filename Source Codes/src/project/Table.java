package project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

public class Table extends JFrame implements ActionListener {

    Database dataList = new Database();
    JButton AddButtonAdmin = new JButton("Admin");
    JButton SubmitButton = new JButton("Submit");
    JButton ExitButton = new JButton("Exit");
    JTable table;

    //jtable icin satir ve kolonlar olusturuldu
    private static final Object[][] DATA = null;

    private static final String[] COLUMNS = {"Book ID", "Book Name", "Author Name", "Publisher", "Book Type", "Number Of Prints", "Copyright Date", "Number Of Stock", "Operation"};

    //model islemi yapildi
    private DefaultTableModel model = new DefaultTableModel(DATA, COLUMNS) {
        @Override
        public Class<?> getColumnClass(int columnIndex) {
            if (columnIndex == 8) {
                return getValueAt(0, 8).getClass();
            }
            return super.getColumnClass(columnIndex);
        }
    };

    private int minSelectedRow = -8;
    private int maxSelectedRow = -8;
    boolean tableModelListenerIsChanging = false;

    public Table() {
        setLayout(new FlowLayout());
        setTitle("Welcome User - Library");
        setSize(1100, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        table = new JTable(model);
        //checkbox degerli dinleniyor
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting()) {
                    return;
                }
                minSelectedRow = ((DefaultListSelectionModel) e.getSource()).getMinSelectionIndex();
                maxSelectedRow = ((DefaultListSelectionModel) e.getSource()).getMaxSelectionIndex();
            }
        });

        model.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                if (tableModelListenerIsChanging) {
                    return;
                }
                int firstRow = e.getFirstRow();
                int column = e.getColumn();

                if (column != 8 || maxSelectedRow == -8 || minSelectedRow == -8) {
                    return;
                }
                tableModelListenerIsChanging = true;
                boolean value = ((Boolean) model.getValueAt(firstRow, 8)).booleanValue();
                System.out.println("table : " + value);
                for (int i = minSelectedRow; i <= maxSelectedRow; i++) {
                    model.setValueAt(value, i, column);
                    //System.out.println("table : "+value);
                }

                // *** edit: added two lines
                minSelectedRow = -8;
                maxSelectedRow = -8;

                tableModelListenerIsChanging = false;
            }
        });

        table.setPreferredSize(new Dimension(800, 200));
        Color blue3 = new Color(203, 219, 215);

        table.setBackground(blue3);
        JScrollPane pane = new JScrollPane(table);

        pane.setPreferredSize(new Dimension(800, 200));

        for (BookDomain list : dataList.list()) {

            model.addRow(list.getObjects());

        }
        add(pane);

        add(AddButtonAdmin);

        add(SubmitButton);

        add(ExitButton);

        AddButtonAdmin.addActionListener(this);
        SubmitButton.addActionListener(this);
        ExitButton.addActionListener(this);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent a) {
        if (a.getSource() == AddButtonAdmin) {
            //admin girisi
            new Login();
            this.dispose();
        } else if (a.getSource() == SubmitButton) {
            
            //submit tiklanirsa rezevasyon tablosuna gidecek
            

            int[] selection = new int[table.getRowCount()];
            int takeNumbers = 0;
            
            //tabloda secili olan checkboxlarin bulundugu satirin bookId si alinip post edilecek

            for (int i = 0; i < table.getRowCount(); i++) {
                Boolean isChecked = Boolean.valueOf(table.getValueAt(i, 8).toString());

                if (isChecked) {
                    //secim yapilan jcombobox taki satirin id degeri aliniyor
                    //get the values of the columns you need.
                    takeNumbers = Integer.valueOf(table.getValueAt(i, 0).toString());
                    //System.out.println("value : "+takeNumbers);
                    selection[i] = takeNumbers;
                } else {

                }

            }

            new Reservation(selection);

            //bu fonksiyon reservasiyon sayfasina takeID nin global olmasi icin gidiyr.
            Reservation.reservationMakeGlobalID(selection);

            this.dispose();
        } else if (a.getSource() == ExitButton) {
            this.dispose();
        }

    }

}
