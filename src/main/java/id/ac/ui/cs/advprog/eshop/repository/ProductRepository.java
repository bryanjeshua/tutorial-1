package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@Repository
public class ProductRepository {
    private List<Product> productData = new ArrayList<>();
    public Product create (Product product) {
        String uniqueID = UUID.randomUUID().toString();
        if(product.getProductId() == null) {
            product.setProductId(uniqueID);
        }
        productData.add(product);
        return product;
    }
    public Iterator<Product> findAll() {
        return productData.iterator();
    }

    public boolean delete(String productId) {
        return productData.removeIf(product -> product.getProductId().equals(productId));
    }

    public Product findById(String productId) {
        for (Product product : productData) {
            if (product.getProductId().equals(productId)) {
                return product;
            }
        }
        return null;
    }

    public Product update(Product product) {
        int index = productData.indexOf(product);
        if (index != -1) {
            productData.set(index, product);
            return product;
        }
        return null;
    }

}
