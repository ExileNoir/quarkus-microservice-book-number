package com.infernalwhaler.microservices.book;

import jakarta.json.bind.annotation.JsonbDateFormat;
import jakarta.json.bind.annotation.JsonbProperty;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.time.Instant;

/**
 * @author Sdeseure
 * @project rest-book
 * @date 3/10/2025
 */

@Schema(name = "Produces a book")
public class Book {

    @JsonbProperty("isbn_13")
    public String isbn13;
    public String title;
    public String author;
    @JsonbProperty("year_of_publication")
    public int yearOfPublication;
    public String genre;
    @JsonbDateFormat("yyyy/MM/dd")
    @JsonbProperty("creation_date")
    @Schema(implementation = String.class, format = "date")
    public Instant creationDate;

    public Book(String title, String author, int yearOfPublication, String genre) {
        this.title = title;
        this.author = author;
        this.yearOfPublication = yearOfPublication;
        this.genre = genre;
        creationDate = Instant.now();
    }


    @Override
    public String toString() {
        return "Book{" +
                "isbn13='" + isbn13 + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", yearOfPublication=" + yearOfPublication +
                ", genre='" + genre + '\'' +
                ", creationDate=" + creationDate +
                '}';
    }
}
