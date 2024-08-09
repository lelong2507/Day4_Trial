package org.example;



import org.example.config.*;
import org.example.entity.Book;
import org.example.repository.BookRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Main {
    static ApplicationContext applicationContext = new AnnotationConfigApplicationContext(JPAConfig.class);
    static BookRepository bookRepository = (BookRepository) applicationContext.getBean("bookRepository");
    public static void main(String[] args) {
//        createNewBook();
//        readBook();
//        readBookById(2);
//        updateBook(1);
//        deleteBookById(1);
//        findBookByMoney(40.0);
//        findBookByNumberOfPage(652);
        findByCharacter("God");
//        findByPriceAndNumberOfPage(45,600);
    }

    private static void createNewBook() {
        Book b =  new Book();
        b.setName("Angry Angle");
        b.setAuthor("SidneySheldon");
        b.setCategory("GoodBook");
        b.setIsbn("ISIBF20052003");
        b.setNumberOfPage(651);
        b.setPrice(40.0);
        b.setPublishDate(LocalDate.parse("2011-05-20"));


        Book newBook = bookRepository.save(b);

        if(newBook != null) {
            System.out.println("A new book saved successfully, bookId = " + b.getIdBook());
        }
    }

    private static void readBook(){
        List<Book> bookList = (List<Book>) bookRepository.findAll();
        System.out.println("Found " + bookList.size());
        for(Book b : bookList){
            System.out.println(b.toString());
        }
    }

    private static void readBookById(int id) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if (optionalBook.isPresent()) {
            Book b = optionalBook.get();
            System.out.println("Found a book with bookId= " + id);
            System.out.println(b.toString());
        } else {
            System.out.println("Not found with bookId = " + id);
        }
    }

    private static void updateBook(int id){
        Optional<Book> optionalBook = bookRepository.findById(id);
        if (optionalBook.isPresent()) {
            Book b = optionalBook.get();
            System.out.println("Update a book with bookId= " + id);
            System.out.println("Book Before updating");
            b.setName("Win by yourself");
            b.setAuthor("Danniel Washington");
            bookRepository.save(b);
            System.out.println("Book After updating");
            System.out.println(b.toString());
        } else {
            System.out.println("Cannot find book");
        }
    }

    private static void deleteBookById(int id){
        Optional<Book> optionalBook = bookRepository.findById(id);
        if (optionalBook.isPresent()) {
            Book b = optionalBook.get();
            System.out.println("Delete book success with bookId = " + id);
            bookRepository.delete(b);
            readBook();
        }
    }

    private static void findBookByMoney(double price){
        Book bookResult = bookRepository.findByPrice(price);
            System.out.println(bookResult.toString());
    }

    private static void findBookByNumberOfPage(int numberOfPage){
        List<Book> bookList = bookRepository.findByNumberOfPageLessThan(numberOfPage);
        for(Book b : bookList) {
            System.out.println(b.toString());
        }
    }

    private static void findByCharacter(String name){
        List<Book> bookList = bookRepository.findByCharacter(name);
        for(Book b : bookList) {
            System.out.println(b.toString());
        }
    }

    private static void findByPriceAndNumberOfPage(double price, int numberOfPage){
        List<Book> bookList = bookRepository.getBookWherePriceLessThanAndNumberOfPageGreaterThan(price, numberOfPage);
        for(Book b : bookList) {
            System.out.println(b.toString());
        }
    }

}