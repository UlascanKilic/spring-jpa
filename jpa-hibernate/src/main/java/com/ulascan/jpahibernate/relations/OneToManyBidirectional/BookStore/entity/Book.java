package com.ulascan.jpahibernate.relations.OneToManyBidirectional.BookStore.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Getter
@Setter
public class Book implements Serializable {
    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String publisher;

    /*
    ! Use Lazy Fetching on Both Sides of the Association
    By default, fetching a parent-side entity will not fetch the children entities. This means
    that @OneToMany is set to lazy. On the other hand, fetching a child entity will eagerly fetch
    its parent-side entity by default. It is advisable to explicitly set @ManyToOne to lazy and
    rely on eager fetching only on a query-basis.
    In this case, the Book entity explicitly maps the @ManyToOne as LAZY
    * @ManyToOne(fetch = FetchType.LAZY)
    */

    /*
    ! Use @JoinColumn to Specify the Join Column Name
    The join column defined by the owner entity (Book) stores the ID value and has a foreign
    key to the Author entity. It is advisable to specify the desired name for this column.
    This way, you avoid potential confusions/mistakes when referring to it (e.g., in native
    queries). In this case, we add @JoinColumn to the Book entity as follows:
    * @JoinColumn(name = "author_id")
    */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private Author author;

    @Override
    public boolean equals(Object obj){
        if(obj == null){
            return false;
        }
        if(this == obj){
            return true;
        }
        if(getClass() != obj.getClass()){
            return false;
        }

        return id != null && id.equals(((Book) obj).id);
    }

    @Override
    public int hashCode(){
        return 2023;
    }
}
