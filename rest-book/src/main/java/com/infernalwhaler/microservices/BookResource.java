package com.infernalwhaler.microservices;

import com.infernalwhaler.microservices.book.Book;
import com.infernalwhaler.microservices.book.NumberProxy;
import jakarta.inject.Inject;
import jakarta.json.bind.JsonbBuilder;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.Instant;

@Path("/api/books")
@Tag(name = "Book REST Endpoint")
public class BookResource {

    @Inject
    @RestClient
    NumberProxy numberProxy;

    @Inject
    Logger logger;

    @POST
    @Retry(delay = 2000, maxRetries = 3)
    @Fallback(fallbackMethod = "fallBackOnCreatingABook")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Operation(summary = "Creates a new book", description = "Generates a new book with an Isbn number")
    public Response createBook(@FormParam("title") String title,
                               @FormParam("author") String author,
                               @FormParam("year") int yearOfPublication,
                               @FormParam("genre") String genre) {
        Book book = new Book(title, author, yearOfPublication, genre);
        book.isbn13 = numberProxy.generateIsbnNumbers().isbn13;

        logger.info("book created : " + book);
        return Response
                .status(Response.Status.CREATED)
                .entity(book)
                .build();
    }

    public Response fallBackOnCreatingABook(String title,
                                            String author,
                                            int yearOfPublication,
                                            String genre) throws FileNotFoundException {
        Book book = new Book(title, author, yearOfPublication, genre);
        book.isbn13 = "Isbn_13 will be set later";

        saveBookOnDisk(book);

        logger.warn("book saved on disk : " + book);
        return Response
                .status(Response.Status.PARTIAL_CONTENT)
                .entity(book)
                .build();
    }

    private void saveBookOnDisk(Book book) throws FileNotFoundException {
        String bookJson = JsonbBuilder.create().toJson(book);
        try (PrintWriter out = new PrintWriter("book- "+ Instant.now().toEpochMilli()+".json")) {
            out.println(bookJson);
        }
    }
}
