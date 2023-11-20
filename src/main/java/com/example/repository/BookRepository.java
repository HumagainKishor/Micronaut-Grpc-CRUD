package com.example.repository;

import com.example.Book;
import com.example.entity.BookEntity;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;
@Repository

public interface BookRepository extends CrudRepository<BookEntity, Long> {

}
