package com.ti.mvcprodukty;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import static com.ti.mvcprodukty.Category.*;

@Repository
public class ProductRepository {
    private final List<Product> products;

    public ProductRepository() {
        products = new ArrayList<>();
        products.add(new Product("Chipsy", 3.99, GROCERIES));
        products.add(new Product("Ziemniaki", 6.39, GROCERIES));
        products.add(new Product("Chlep", 11.02, GROCERIES));
        products.add(new Product("Proszek do prania", 44.99, HOUSEHOLD));
        products.add(new Product("Rower", 3999, OTHER));
        products.add(new Product("Mydło", 3.99, HOUSEHOLD));
        products.add(new Product("Płyn do naczyń", 7.49, HOUSEHOLD));
    }

    public void add(Product product) {
        products.add(product);
    }

    public List<Product> findByCategory(Category category) {
        return products.stream()
                .filter(product -> product.getCategory() == category)
                .toList();
    }

    public List<Product> getAllProducts() {
        return products;
    }

    public double sum(List<Product> products) {
        return products.stream()
                .mapToDouble(product -> product.getPrice())
                .sum();
    }
}
