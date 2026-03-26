package com.example.product_management.controller;

import com.example.product_management.model.Product;
import com.example.product_management.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // API: GET /api/products
    @GetMapping("/api/products")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    // POST - thêm mới
    @PostMapping("/api/products")
    public Product addProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }

    // PUT - cập nhật
    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable int id,
                                 @RequestBody Product product) {
        return productService.updateProduct(id, product);
    }

    // DELETE - xóa
    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable int id) {
        boolean deleted = productService.deleteProduct(id);
        return deleted ? "Deleted successfully" : "Product not found";
    }
}
