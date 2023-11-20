package com.example;

import com.example.entity.BookEntity;
import com.example.repository.BookRepository;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import io.micronaut.core.annotation.NonNull;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.Optional;

public class BookServiceImpl extends BookServiceGrpc.BookServiceImplBase {
    @Inject
    BookRepository bookRepository;

    @Override
    @Transactional
    public void createBook(Book request, StreamObserver<Book> responseObserver){
        String title = request.getTitle();// extract the title and author of the book from the request object, which is provided by the client.
        String author = request.getAuthor();
        BookEntity bookEntity = new BookEntity();
        bookEntity.setAuthor(author);
        bookEntity.setTitle(title);
        bookRepository.save(bookEntity);

        //Build the request
        Book book = Book.newBuilder()
                .setId(bookEntity.getId())
                .setAuthor(bookEntity.getAuthor())
                .setTitle(bookEntity.getTitle())
                .build();
        // Send the response to the client
        responseObserver.onNext(book);
        responseObserver.onCompleted();
    }
    @Override
    public void getBook(GetBookRequest request, StreamObserver<Book> responseObserver){
        Long bookId = (long) request.getId();
        Optional<BookEntity> optionalBookEntity = bookRepository.findById(bookId);
        if(optionalBookEntity.isPresent()){
            BookEntity bookEntity = optionalBookEntity.get();
            Book book = Book.newBuilder()
                    .setId(bookEntity.getId())
                    .setAuthor(bookEntity.getAuthor())
                    .setTitle(bookEntity.getTitle())
                    .build();
            responseObserver.onNext(book);
            responseObserver.onCompleted();

        }else {
            responseObserver.onError(Status.NOT_FOUND.withDescription("Book not found").asRuntimeException());
        }
    }
    @Override
    public void deleteBook(DeleteBookRequest request, StreamObserver<Book> responseObserver){
        Long bookId = (long) request.getId();
        Optional<BookEntity> optionalBookEntity = bookRepository.findById(bookId);
        if(optionalBookEntity.isPresent()){
            bookRepository.deleteById(bookId);
            BookEntity bookEntity = optionalBookEntity.get();
            Book book = Book.newBuilder()
                    .setId(bookEntity.getId())
                    .setAuthor(bookEntity.getAuthor())
                    .setTitle(bookEntity.getTitle())
                    .build();
            responseObserver.onNext(book);
            responseObserver.onCompleted();
        }else {
            responseObserver.onError(Status.NOT_FOUND.withDescription("Book not found").asRuntimeException());
        }
    }
    @Override
    public void updateBook(UpdateBookRequest request, StreamObserver<BookResponse> responseObserver){
        Long bookId = (long) request.getId();
        Optional<BookEntity> optionalBookEntity = bookRepository.findById(bookId);
        if(optionalBookEntity.isPresent()){
            BookEntity bookEntity = optionalBookEntity.get();
            String title = request.getTitle();
            String author = request.getAuthor();
            bookEntity.setAuthor(author);
            bookEntity.setTitle(title);
            bookRepository.update(bookEntity);
            BookResponse bookResponse = BookResponse.newBuilder()
                    .setSuccess(true)
                    .build();
            responseObserver.onNext(bookResponse);
            responseObserver.onCompleted();

        }else{
            responseObserver.onError(Status.NOT_FOUND.withDescription("Book not found").asRuntimeException());
        }
    }
}

