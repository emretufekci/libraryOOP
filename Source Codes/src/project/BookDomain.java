package project;

public class BookDomain {
    
    private String bookName="";
    private String authorName="";
    private int bookID;
    private String publisher="";
    private String bookType="";
    private int numOfPrints;
    private int numOfStock;
    private String copyrightDate="";
    private boolean operation;
    
      public int getBookID(){
      return bookID;
      }
      public void setBookID(int bookID){
      this.bookID=bookID;
      }
      public String getBookName(){
      return bookName;
      }
      public void setBookName(String bookName){
      this.bookName=bookName;
      }
      public String getAuthorName(){
      return authorName;
      }
      public void setAuthorName(String authorName){
      this.authorName=authorName;
      }
      public String getPublisher(){
      return publisher;
      }
      public void setPublisher(String publisher){
      this.publisher=publisher;
      }
      public String getBookType(){
      return bookType;
      }
      public void setBookType(String bookType){
      this.bookType=bookType;
      }
      public int getNumOfPrints(){
      return numOfPrints;
      }
      public void setNumOfPrints(int numOfPrints){
      this.numOfPrints=numOfPrints;
      }
      public String getCopyrightDate(){
      return copyrightDate;
      }
      public void setCopyrightDate(String copyrightDate){
      this.copyrightDate=copyrightDate;
      }
      public int getNumOfStock(){
      return numOfStock;
      }
      public void setNumOfStock(int numOfStock){
      this.numOfStock=numOfStock;
      }

    public boolean isOperation() {
        return operation;
    }

    public void setOperation(boolean operation) {
        this.operation = operation;
    }
     
      
      
      public Object [] getObjects(){
           
            Object [] BookList = {bookID,bookName,authorName,publisher,bookType,numOfPrints,copyrightDate,numOfStock,operation};
           
            return BookList;
      }
}
