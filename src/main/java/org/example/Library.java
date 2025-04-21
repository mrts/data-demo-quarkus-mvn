package org.example;

import jakarta.transaction.Transactional;
import org.hibernate.annotations.processing.Find;
import org.hibernate.annotations.processing.HQL;
import org.hibernate.annotations.processing.Pattern;
import org.hibernate.Session;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

// This is a Hibernate Data Repository, Hibernate Processor will generate the implementation.
public interface Library {

    // This gives Hibernate Processor a hint to generate a stateful repository, see
    // https://docs.jboss.org/hibernate/orm/7.0/introduction/html_single/Hibernate_Introduction.html#static-or-instance
    Session getSession();

    @Find
    Optional<Book> byIsbn(String isbn);

    @Find
    List<Book> byTitle(@Pattern String title);

    default void add(Book book) {
        Objects.requireNonNull(book);
        getSession().persist(book);
    }

    @HQL("delete from Book b where b.isbn = :isbn")
    void delete(String isbn);

    @Find
    List<Book> allBooks();

    @HQL("""
            select b.isbn, b.title, listagg(a.name, ' & ')
            from Book b join b.authors a
            group by b
            order by b.isbn
            """)
    List<Summary> summarize();

}
