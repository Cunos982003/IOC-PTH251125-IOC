package com.example.product_management.service;

import com.example.product_management.model.Product;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    private List<Product> products = new ArrayList<>();

    // Constructor giả lập dữ liệu
    public ProductService() {
        products.add(new Product(1, "Laptop", 1000));
        products.add(new Product(2, "Phone", 500));
        products.add(new Product(3, "Tablet", 300));
    }

    public List<Product> getAllProducts() {
        return products;
    }

    public Product addProduct(Product product) {
        products.add(product);
        return product;
    }

    // UPDATE
    public Product updateProduct(int id, Product newProduct) {
        for (Product p : products) {
            if (p.getId() == id) {
                p.setName(newProduct.getName());
                p.setPrice(newProduct.getPrice());
                return p;
            }
        }
        return null;
    }

    // DELETE
    public boolean deleteProduct(int id) {
        return products.removeIf(p -> p.getId() == id);
    }
}