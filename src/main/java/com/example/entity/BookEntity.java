package com.example.entity;

import jakarta.inject.Singleton;
import jakarta.persistence.*;

@Singleton
@Entity
public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    String title;
    String author;

    public BookEntity() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
    @ManyToOne
    @JoinColumn(name = "year_id")
    private YearEntity year; // Represents the year this book belongs to
}
