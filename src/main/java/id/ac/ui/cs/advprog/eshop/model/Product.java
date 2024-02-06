package id.ac.ui.cs.advprog.eshop.model;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter @Setter
public class Product {
    private String productId;
    private String productName;
    private int productQuantity;
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(productId, product.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId);
    }


}