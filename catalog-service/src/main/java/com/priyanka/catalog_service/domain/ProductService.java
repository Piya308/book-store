package com.priyanka.catalog_service.domain;

import java.util.Optional;

public interface ProductService {

    PagedResult<ProductDto> getProducts(int pageNo);

    Optional<ProductDto> getProductByCode(String code);

}
