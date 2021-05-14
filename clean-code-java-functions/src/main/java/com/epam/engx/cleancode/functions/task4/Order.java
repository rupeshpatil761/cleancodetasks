package com.epam.engx.cleancode.functions.task4;

import com.epam.engx.cleancode.functions.task4.thirdpartyjar.Product;

import java.util.Iterator;
import java.util.List;

public class Order {

    private List<Product> products;

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public List<Product> getProducts() {
        return products;
    }

    public Double getPriceOfAvailableProducts() {
        return getTotalPriceOfAvailableProducts();
    }

    private Double getTotalPriceOfAvailableProducts(){
        double orderPrice = 0.0;
        for (Product p : products) {
            if(p.isAvailable()) {
                orderPrice += p.getProductPrice();
            }
        }
        return orderPrice;
    }
}
