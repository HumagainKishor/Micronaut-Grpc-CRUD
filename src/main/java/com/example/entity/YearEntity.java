package com.example.entity;

import jakarta.inject.Singleton;
import jakarta.persistence.*;

import java.util.List;

@Singleton
@Entity
public class YearEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    int value;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
    public void setValue(int value){
        this.value = value;
    }

    @OneToMany(mappedBy = "year", cascade = CascadeType.ALL)
    private List<BookEntity> books;
}
