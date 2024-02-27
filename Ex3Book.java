
package practice.gui;


public class Ex3Book {

    @Override
    public String toString() {
        return "Ex3Book{" + "bookName=" + bookName + ", oldPrice=" + oldPrice + ", newPrice=" + newPrice + '}';
    }

    public Ex3Book(String bookName, double oldPrice, double newPrice) {
        this.bookName = bookName;
        this.oldPrice = oldPrice;
        this.newPrice = newPrice;
    }
    
    private String bookName;
    private double oldPrice;
    private double newPrice;
    
    
}
