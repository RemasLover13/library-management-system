package com.remaslover.libraryapp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fio;

    @Column(name = "birth_year")
    private String birthYear;

    @OneToMany(mappedBy = "user")
    private List<Book> books = new ArrayList<>();

    public User(String fio, String birthYear) {
        this.fio = fio;
        this.birthYear = birthYear;
    }
}
