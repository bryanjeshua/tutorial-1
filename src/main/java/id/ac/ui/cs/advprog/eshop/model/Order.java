package id.ac.ui.cs.advprog.eshop.model;
import java.util.List;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Order {
    private String id;
    private List<Product> products;
    private long orderTime;
    private String author;
    private String status;

    public Order(String id, List<Product> products, long orderTime, String author) {
        this.id = id;
        this.products = products;
        this.orderTime = orderTime;
        this.author = author;
        this.status = "WAITING_PAYMENT";
    }
    public Order(String id, List<Product> products, long orderTime, String author, String status) {
        this.id = id;
        this.products = products;
        this.orderTime = orderTime;
        this.author = author;
        this.status = status;
    }
}
