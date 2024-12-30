package com.remaslover.libraryapp.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

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

    @Column(name = "borrowed_at")
    private LocalDateTime borrowedAt;

    @Transient
    private boolean overdue;


    @ManyToOne
    private User user;

    public Book(String title, String author, Integer year, User user) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.user = user;
    }

    public Book(String title, String author, Integer year, LocalDateTime borrowedAt, boolean overdue, User user) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.borrowedAt = borrowedAt;
        this.overdue = overdue;
        this.user = user;
    }

    public boolean isOverdue() {
        if (borrowedAt == null) {
            return false;
        }
        return borrowedAt.plusDays(10).isBefore(LocalDateTime.now());
    }
}
