package com.ulascan.jpahibernate.relations.OneToManyBidirectional.BookStore.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Entity
@Getter
@Setter
public class Author implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String genre;
    private int age;

    /*
    !!!!! Always Cascade from Parent-Side to Child-Side
    * Cascading from child-side to parent-side is a code smell and bad practice and it is a clear
    signal that it is time to review your Domain Model and application design. Think how
    improper or illogical it is for a child to cascade the creation of its parent! On one hand,
    a child cannot exist without a parent, while on the other hand, the child cascades the
    creation of his parent. This is not logical, right? So, as a rule of thumb, always cascade
    from parent-side to child-side, as in the following example (this is one of the most
    important advantages of using bidirectional associations). In this case, we cascade from
    the Author side to the Book side, so we add the cascade type in the Author entity
    * @OneToMany(cascade = CascadeType.ALL)
    */

    /*
    ! Don’t Forget to Set mappedBy on the Parent-Side
    The mappedBy attribute characterizes a bidirectional association and must be set on the
    parent-side. In other words, for a bidirectional @OneToMany association, set mappedBy
    to @OneToMany on the parent-side and add @ManyToOne on the child-side referenced
    by mappedBy. Via mappedBy, the bidirectional @OneToMany association signals that it
    mirrors the @ManyToOne child-side mapping. In this case, we add in Author entity to the
    following
    * @OneToMany(cascade = CascadeType.ALL,mappedBy = "author")
    */

    /*
    ! Set orphanRemoval on the Parent-Side
    Setting orphanRemoval on the parent-side guarantees the removal of children without
    references. In other words, orphanRemoval is good for cleaning up dependent objects
    that should not exist without a reference from an owner object. In this case, we add
    orphanRemoval to the Author entity
    * @OneToMany(cascade = CascadeType.ALL, mappedBy = "author",orphanRemoval = true)
    */

    @OneToMany(cascade = CascadeType.ALL,
    mappedBy = "author", orphanRemoval = true)
    private List<Book> books = new ArrayList<>();


    /*
    ! Keep Both Sides of the Association in Sync
    * You can easily keep both sides of the association in sync via helper methods added to
    * the parent-side. Commonly, the addChild(), removeChild(), and removeChildren()
    */
    public void addBook(Book book){
        this.books.add(book);
        book.setAuthor(this);
    }

    public void removeBook(Book book){
        book.setAuthor(null);
        this.books.remove(book);
    }
    public void removeBooks(){
        Iterator<Book> iterator = this.books.iterator();
        while(iterator.hasNext()){
            Book book = iterator.next();
            book.setAuthor(null);
            iterator.remove();
        }
    }
}
