package com.infernalwhaler.microservices.book;

import io.quarkus.test.Mock;
import org.eclipse.microprofile.rest.client.inject.RestClient;

/**
 * @author Sdeseure
 * @project rest-book
 * @date 3/10/2025
 */

@Mock
@RestClient
public class MockNumberProxy implements NumberProxy {

    @Override
    public IsbnThirteen generateIsbnNumbers() {
        IsbnThirteen isbn = new IsbnThirteen();
        isbn.isbn13 = "13-mock";
        return isbn;
    }
}
