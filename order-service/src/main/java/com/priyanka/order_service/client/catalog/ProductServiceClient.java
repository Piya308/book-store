package com.priyanka.order_service.client.catalog;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.Optional;

@Component
public class ProductServiceClient {
    private static final Logger logger = LoggerFactory.getLogger(ProductServiceClient.class);

    private final RestClient restClient;

    public ProductServiceClient(RestClient restClient) {
        this.restClient = restClient;
    }

    public Optional<Product> getProductByCode(String code) {
        logger.info("fetching getProductByCode");
        try {
            Product product = restClient.get()
                    .uri("/api/products/{code}", code)
                    .retrieve()
                    .body(Product.class);
            return Optional.ofNullable(product);
        }
        catch (Exception e){
            logger.info("error while fetching product by code");
            return Optional.empty();
        }
    }
}
