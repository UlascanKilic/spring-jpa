package com.ulascan.jpahibernate.relations.OneToManyBidirectional.BookStore.repository;

import com.ulascan.jpahibernate.relations.OneToManyBidirectional.BookStore.entity.Author;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional(readOnly = true)
public interface IAuthorRepository extends JpaRepository<Author, Long> {
    @Query("SELECT a FROM Author a JOIN FETCH a.books WHERE a.id = ?1")
    Author fetchById(Long id);

}
