package com.priyanka.catalog_service.domain;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String msg) {
        super(msg);
    }

    public static ProductNotFoundException createWithCode(String code) {
        return new ProductNotFoundException("Product not found with given code:-" + code);
    }
}
