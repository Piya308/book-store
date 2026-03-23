package com.priyanka.order_service.client.catalog;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
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
@Retry(name = "catalog-service", fallbackMethod = "getFallMethodForGetProductByCode")
@CircuitBreaker(name = "catalog-service")
    public Optional<Product> getProductByCode(String code) {
        logger.info("fetching getProductByCode");
            Product product = restClient.get()
                    .uri("/api/products/{code}", code)
                    .retrieve()
                    .body(Product.class);
            return Optional.ofNullable(product);
    }

    //when  we write the fallback method for @retry it provides the method parameter and the extra param Throwable to capture exception
    public Optional<Product> getFallMethodForGetProductByCode(String code, Throwable throwable) {
        System.out.println("ProductServiceClient.getFallMethodForGetProductByCode"+ code);
        return Optional.empty();
    }
}
