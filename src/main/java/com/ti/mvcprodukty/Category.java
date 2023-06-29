package com.ti.mvcprodukty;

import java.util.Arrays;
import java.util.List;

public enum Category {
    GROCERIES("spożywcze"),
    HOUSEHOLD("domowe"),
    OTHER("inne");

    private String plLabel;

    Category(String plLabel) {
        this.plLabel = plLabel;
    }

    public String getPlLabel() {
        return plLabel;
    }

    public static List<String> getAllCategoryPlLabels() {
        return Arrays.stream(values())
                .map(Category::getPlLabel)
                .toList();
    }

    public static Category getCategoryFromPlLabel(String plLabel) {
        return Arrays.stream(values())
                .filter(category -> category.getPlLabel().equals(plLabel))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Wrong label: " + plLabel));
    }
}
