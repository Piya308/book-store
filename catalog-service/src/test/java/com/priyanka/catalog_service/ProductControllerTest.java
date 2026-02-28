package com.priyanka.catalog_service;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

import com.priyanka.catalog_service.domain.ProductDto;
import io.restassured.http.ContentType;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.jdbc.Sql;

@Sql("/test-data.sql")
public class ProductControllerTest extends AbstractIT {

    //    @Test
    //    void shouldCreateProduct() {
    //
    //    }

    @Test
    void shouldReturnProducs() {
        given().contentType(ContentType.JSON) // 1. Build the request with JSON content type
                .get("/api/products") // 2. Send a GET request to /api/products
                .then() // 3. Start response validation
                .statusCode(200) // 4. Assert HTTP status is 200 OK
                .body("data", hasSize(10)) // 5. Assert "data" array has 10 items
                .body("totalElements", is(15)) // 6. Assert total elements = 15
                .body("pageNumber", is(1)) // 7. Assert current page = 1
                .body("totalPages", is(2)) // 8. Assert total pages = 2
                .body("isFirst", is(true)) // 9. Assert this is the first page
                .body("isLast", is(false)) // 10. Assert not the last page
                .body("hasNext", is(true)) // 11. Assert there is a next page
                .body("hasPrevious", is(false)); // 12. Assert there is no previous page
    }

    @Test
    void shouldReturnNotFoundWhenProductCodeNotExists() {
        String code = "invalid_product_code";
        given().contentType(ContentType.JSON)
                .get("/api/products/{code}", code)
                .then()
                .statusCode(404)
                .body("status", is(404))
                .body("title", is("Product not found"));
    }

    @Test
    void shouldGetProductByCode() {
        ProductDto product = given().contentType(ContentType.JSON)
                .when()
                .get("/api/products/{code}", "P100")
                .then()
                .statusCode(200)
                .assertThat()
                .extract()
                .body()
                .as(ProductDto.class);
        assertThat(product.code()).isEqualTo("P100");
        assertThat(product.name()).isEqualTo("The Hunger Games");
        assertThat(product.description()).isEqualTo("Winning will make you famous. Losing means certain death...");
        assertThat(product.price()).isEqualTo(new BigDecimal("34.0"));
    }
}
