package com.priyanka.catalog_service.domain;

import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    static ProductDto toProductDto(ProductEntity productEntity){
        return  new ProductDto(
                productEntity.getCode(),
                productEntity.getName(),
                productEntity.getDescription(),
                productEntity.getImageUrl(),
                productEntity.getPrice()
        );
    }
}
