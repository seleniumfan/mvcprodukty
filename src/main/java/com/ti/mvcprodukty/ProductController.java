package com.ti.mvcprodukty;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import static com.ti.mvcprodukty.Category.getAllCategoryPlLabels;
import static com.ti.mvcprodukty.Category.getCategoryFromPlLabel;

@Controller
public class ProductController {
    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/")
    public String homePage(Model model) {
        model.addAttribute("categories", getAllCategoryPlLabels());
        return "index";
    }

    @GetMapping("/lista")
    public String showProducts(@RequestParam(required = false, name = "kategoria") String categoryPlLabel, Model model) {
        List<Product> products;
        if (categoryPlLabel.equals("all")) {
            products = productRepository.getAllProducts();
        } else {
            Category category = getCategoryFromPlLabel(categoryPlLabel);
            products = productRepository.findByCategory(category);
        }
        double sum = productRepository.sum(products);
        model.addAttribute("products", products);
        model.addAttribute("sum", sum);
        return "list";
    }

    @GetMapping("/dodaj_produkt")
    public String addProductForm(Model model) {
        model.addAttribute("categories", getAllCategoryPlLabels());
        return "add";
    }

    @PostMapping("/add")
    @ResponseBody
    public String addProduct(@RequestParam String name,
                             @RequestParam double price,
                             @RequestParam String category) {
        Product product = new Product(name, price, getCategoryFromPlLabel(category));
        productRepository.add(product);
        return "<h2 style=\"text-align: center;\">Produkt został dodany</h2>";
    }
}
