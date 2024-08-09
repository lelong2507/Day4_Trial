package org.example.repository;

import org.example.entity.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends CrudRepository<Book, Integer> {
    // Get Book by Price
    Book findByPrice(double price);
    // Get Book by Number of Page
    List<Book> findByNumberOfPageLessThan(int numberOfPage);
    // List by character name of book
    // Query using JPQL
    @Query("SELECT b FROM Book b WHERE b.name LIKE %?1%")
    List<Book> findByCharacter(String character);
    // Query using Native SQL
    @Query(value = "select * from book b where b.price < ?1 and b.numberOfPage >= ?2", nativeQuery = true)
    List<Book> getBookWherePriceLessThanAndNumberOfPageGreaterThan(double price, int numberOfPage);

}
