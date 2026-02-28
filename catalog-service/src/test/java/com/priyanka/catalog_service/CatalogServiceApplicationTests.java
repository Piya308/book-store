package com.priyanka.catalog_service;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

class CatalogServiceApplicationTests extends AbstractIT {

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
}
