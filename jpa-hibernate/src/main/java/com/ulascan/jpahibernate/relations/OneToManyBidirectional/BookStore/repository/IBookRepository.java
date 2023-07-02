package com.ulascan.jpahibernate.relations.OneToManyBidirectional.BookStore.repository;

import com.ulascan.jpahibernate.relations.OneToManyBidirectional.BookStore.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IBookRepository extends JpaRepository<Book, Long> {
    @Query("SELECT b from Book b JOIN FETCH b.author WHERE b.id = ?1")
    Book fetchById(Long id);

    @Query("SELECT b from Book b JOIN FETCH b.author")
    List<Book> fetchAllBooks();

}
