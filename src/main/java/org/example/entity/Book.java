package org.example.entity;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "book")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int idBook;

    @Column(name = "name")
    private String name;

    @Column(name = "author")
    private String author;

    @Column(name = "category")
    private String category;

    @Column(name = "isbn")
    private String isbn;

    @Column(name = "price")
    private double price;

    @Column(name = "numberOfPage")
    private int numberOfPage;

    @Column(name = "publishDate")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate publishDate;
}
