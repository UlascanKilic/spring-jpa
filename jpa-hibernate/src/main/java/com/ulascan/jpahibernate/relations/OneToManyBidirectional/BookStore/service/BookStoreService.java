package com.ulascan.jpahibernate.relations.OneToManyBidirectional.BookStore.service;

import com.github.javafaker.Faker;
import com.ulascan.jpahibernate.relations.OneToManyBidirectional.BookStore.entity.Author;
import com.ulascan.jpahibernate.relations.OneToManyBidirectional.BookStore.entity.Book;
import com.ulascan.jpahibernate.relations.OneToManyBidirectional.BookStore.repository.IAuthorRepository;
import com.ulascan.jpahibernate.relations.OneToManyBidirectional.BookStore.repository.IBookRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookStoreService {

    private final IAuthorRepository authorRepository;
    private final IBookRepository bookRepository;
    private final Faker faker;

    public BookStoreService(IAuthorRepository authorRepository,
                            IBookRepository bookRepository){
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;

        faker = new Faker();
    }

    public void addAuthorWithBooks(){
        Author author = new Author();
        author.setName(faker.book().author());
        author.setAge(45);
        author.setGenre(faker.book().genre());

        for(int i = 1; i < 5; i++){
            Book book = new Book();
            book.setPublisher(faker.book().publisher());
            book.setTitle(faker.book().title());

            author.addBook(book);
        }


        authorRepository.save(author);

        Author author1 = authorRepository.fetchById(1L);

        author1.getBooks().forEach(
                book -> {
                    System.out.println(book.getTitle());
                }
        );

        Book book = bookRepository.fetchById(1L);

        System.out.println(book.getTitle());

        List<Book> books = bookRepository.fetchAllBooks();

    }

    @Transactional
    public void deleteBookOfAuthor(){

        Author author = authorRepository.fetchById(1L);
        Book book = author.getBooks().get(0);

        author.removeBook(book);
    }

    @Transactional
    public void deleteAllBooksOfAuthor(){
        Author author = authorRepository.fetchById(1L);
        author.removeBooks();
    }
}
