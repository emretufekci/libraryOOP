package project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import static jdk.nashorn.internal.objects.NativeString.search;

public class Database {

    Connection conn = null;

    protected Connection connect() {
        // SQLite connection string
        //this has to be a full path for database
        String url = "jdbc:sqlite:libraryDB.db";

        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    Connection CloseConnect() {
        try {
            this.conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;

    }

    public boolean insertUser(BookDomain record) {
        try {
            conn = this.connect();
            // String sql = "select * from library";
            //System.out.println("Baglandi");
            String sql = "INSERT INTO BOOK (BOOKID, BOOKNAME, AUTHORNAME, PUBLISHER, BOOKTYPE, NUMOFPRINTS, COPYRIGHTDATE, NUMOFSTOCK) VALUES (?,?,?,?,?,?,?,?)";
//            PreparedStatement ps = conn.prepareStatement(sql);
//            ResultSet rs = ps.executeQuery();

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, record.getBookID());
            stmt.setString(2, record.getBookName());
            stmt.setString(3, record.getAuthorName());
            stmt.setString(4, record.getPublisher());
            stmt.setString(5, record.getBookType());
            stmt.setInt(6, record.getNumOfPrints());
            stmt.setString(7, record.getCopyrightDate());
            stmt.setInt(8, record.getNumOfStock());
            stmt.executeUpdate();
            stmt.close();
            conn.close();
            CloseConnect();

            JOptionPane.showMessageDialog(null, "Add Successful");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    public boolean DeleteBook(String bookid) {
        try {
            conn = this.connect();
            String sr = "DELETE FROM BOOK WHERE BOOKID=?";
            PreparedStatement ps = conn.prepareStatement(sr);
            ps.setString(1, bookid);
            ps.executeUpdate();
            ps.close();
            conn.close();
            CloseConnect();
            JOptionPane.showMessageDialog(null, "Delete Successful");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    
    public boolean UpdateStock(int takeStock, int bookID) {
        try {
            conn = this.connect();
            String sr = "UPDATE BOOK SET NUMOFSTOCK=? WHERE BOOKID=?";
                PreparedStatement ps = conn.prepareStatement(sr);
                
                ps.setInt(1, takeStock);
                ps.setInt(2, bookID);

                //System.out.println("num of stock" + bookQuantity);
                //System.out.println("id" + (bookID + 1));

                ps.executeUpdate();
            ps.close();
            conn.close();
            CloseConnect();
            JOptionPane.showMessageDialog(null, "Update Successful");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    

    public List<BookDomain> list() {

        List<BookDomain> lists = new ArrayList<BookDomain>();

        try {
            conn = this.connect();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM BOOK");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                BookDomain bd = new BookDomain();
                bd.setBookID(rs.getInt("BOOKID"));
                bd.setBookName(rs.getString("BOOKNAME"));
                bd.setAuthorName(rs.getString("AUTHORNAME"));
                bd.setPublisher(rs.getString("PUBLISHER"));
                bd.setBookType(rs.getString("BOOKTYPE"));
                bd.setNumOfPrints(rs.getInt("NUMOFPRINTS"));
                bd.setCopyrightDate(rs.getString("COPYRIGHTDATE"));
                bd.setNumOfStock(rs.getInt("NUMOFSTOCK"));
               // bd.setOperation(rs.getBoolean("OPERATION"));
                lists.add(bd);
            }

            ps.close();
            conn.close();
            this.CloseConnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lists;

    }

    public List<BookDomain> basketList(int[] takeID) {
        conn = this.connect();
        List<BookDomain> lists = new ArrayList<BookDomain>();
        for (int bookID : takeID) {
            try {
                //System.out.println(bookID + 1);
                String sr = "SELECT * FROM BOOK WHERE BOOKID=" + (bookID);
                PreparedStatement ps = conn.prepareStatement(sr);
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    BookDomain bd = new BookDomain();
                    bd.setBookID(rs.getInt("BOOKID"));
                    bd.setBookName(rs.getString("BOOKNAME"));
                    bd.setAuthorName(rs.getString("AUTHORNAME"));
                    bd.setPublisher(rs.getString("PUBLISHER"));
                    bd.setBookType(rs.getString("BOOKTYPE"));
                    bd.setNumOfPrints(rs.getInt("NUMOFPRINTS"));
                    bd.setCopyrightDate(rs.getString("COPYRIGHTDATE"));
                    bd.setNumOfStock(rs.getInt("NUMOFSTOCK"));
                    lists.add(bd);
                    ps.close();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        try {
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.CloseConnect();

        return lists;

    }

    public boolean dropBookQuantity(ArrayList takeID) {
        conn = this.connect();
        int numOfBookStocks = 0;

        List<BookDomain> lists = new ArrayList<BookDomain>();
        for (Object bookID : takeID) {
            try {
               // System.out.println("BOOK ID1 " + (bookID));
                String sr = "SELECT * FROM BOOK WHERE BOOKID=" + (bookID);
                PreparedStatement ps = conn.prepareStatement(sr);
                ResultSet rs = ps.executeQuery();
                
                numOfBookStocks=rs.getInt("NUMOFSTOCK");

                String sr2 = "UPDATE BOOK SET NUMOFSTOCK=? WHERE BOOKID=?";
                PreparedStatement ps2 = conn.prepareStatement(sr2);
                int bookQuantity = (numOfBookStocks - 1);
                ps2.setInt(1, bookQuantity);
                ps2.setInt(2, (int) (bookID));

                //System.out.println("num of stock" + bookQuantity);
                //System.out.println("id" + (bookID + 1));

                ps2.executeUpdate();

                ps.close();
                ps2.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        try {
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.CloseConnect();

        return false;
    }

}
