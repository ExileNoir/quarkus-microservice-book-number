package com.infernalwhaler.microservices.book;

import jakarta.json.bind.annotation.JsonbProperty;

/**
 * @author Sdeseure
 * @project rest-book
 * @date 3/10/2025
 */

public class IsbnThirteen {

    @JsonbProperty("isbn_13")
    public String isbn13;
}
