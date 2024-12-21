package com.remaslover.libraryapp.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String author;
    private Integer year;

    @ManyToOne
    private User user;

    public Book(String title, String author, Integer year, User user) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.user = user;
    }
}
