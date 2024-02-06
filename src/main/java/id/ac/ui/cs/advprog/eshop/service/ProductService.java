package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;

import java.util.List;

public interface ProductService {
    public Product create(Product product);
    public List<Product> findAll();

    boolean delete(String productId);
    Product findById(String productId);
    Product update(Product product);


}