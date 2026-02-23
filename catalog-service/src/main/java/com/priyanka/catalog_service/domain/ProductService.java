package com.priyanka.catalog_service.domain;

public interface ProductService {

    PagedResult<ProductDto> getProducts(int pageNo);
}
