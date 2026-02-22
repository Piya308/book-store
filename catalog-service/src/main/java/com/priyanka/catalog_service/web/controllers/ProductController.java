package com.priyanka.catalog_service.web.controllers;

import com.priyanka.catalog_service.domain.PagedResult;
import com.priyanka.catalog_service.domain.ProductDto;
import com.priyanka.catalog_service.domain.ProductEntity;
import com.priyanka.catalog_service.domain.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    ProductController(ProductService productServce){
        this.productService = productServce;
    }

    @GetMapping
    PagedResult<ProductDto> getProducts(@RequestParam(name = "page", defaultValue = "1") int pageNo){
        return productService.getProducts(pageNo);
    }
}
