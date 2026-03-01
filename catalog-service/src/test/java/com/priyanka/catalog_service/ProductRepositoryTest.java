package com.priyanka.catalog_service;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

import com.priyanka.catalog_service.domain.ProductEntity;
import com.priyanka.catalog_service.domain.ProductRepository;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

@DataJpaTest(
        properties = {
            "spring.test.database.replace=none",
            "spring.datasource.url=jdbc:tc:postgresql:16-alpine:///db",
        })
@Sql("/test-data.sql")
// @Import(
//        TestcontainersConfiguration
//                .class)
// if testing only repository then dont use this with db container , there can be other
// containers such as rabbitmq
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void shouldGetAllProducts() {
        List<ProductEntity> productEntityList = productRepository.findAll();
        ;
        assertThat(productEntityList).hasSize(15);
    }

    @Test
    void shouldGetProductByCode() {
        ProductEntity productEntity = productRepository.findByCode("P102").orElseThrow();
        assertThat(productEntity.getCode()).isEqualTo("P102");
        assertThat(productEntity.getName()).isEqualTo("The Chronicles of Narnia");
    }

    @Test
    void shouldReturnEmptyWhenProductNotFoundByCode() {
        assertThat(productRepository.findByCode("invalid_product_code")).isEmpty();
    }
}
