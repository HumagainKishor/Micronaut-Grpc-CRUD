package com.example.controller;

import com.example.Book;
import com.example.BookServiceGrpc;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;

@Controller("/books")

public class BookController {
    private final BookServiceGrpc.BookServiceBlockingStub bookServiceStub;

    public BookController(BookServiceGrpc.BookServiceBlockingStub bookServiceStub) {
        this.bookServiceStub = bookServiceStub;
    }


    @Post("/create")
    public HttpResponse<?> createBook(@Body Book book) {
        Book createdBook = bookServiceStub.createBook(book);
        return HttpResponse.created(createdBook);
    }


}
