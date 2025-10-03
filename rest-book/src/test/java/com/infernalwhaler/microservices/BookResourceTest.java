package com.infernalwhaler.microservices;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.startsWith;

@QuarkusTest
class BookResourceTest {
    @Test
    void shouldCreateABook() {
        given()
                .formParam("title", "Quarkus")
                .formParam("author", "Antonio")
                .formParam("year", 2020)
                .formParam("genre", "IT")
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON)

                .when()
                .post("/api/books")

                .then()
                .statusCode(201)
                .body("isbn_13", startsWith("13-"))
                .body("title", is("Quarkus"),
                        "author", is("Antonio"),
                        "year_of_publication", is(2020),
                        "genre", is("IT"),
                        "creation_date", startsWith("20"),
                        "isbn_13", is("13-mock"));
    }

}