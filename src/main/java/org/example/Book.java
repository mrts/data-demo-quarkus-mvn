package org.example;

import jakarta.persistence.Basic;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.NaturalId;
import org.hibernate.envers.Audited;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.Set;

import static jakarta.persistence.EnumType.STRING;

@Audited
@Entity
public class Book {
    @Id
    public String isbn;

    @NaturalId
    public String title;

    @NaturalId(mutable = true)
    public LocalDate publicationDate;

    public String text;

    @Enumerated(STRING)
    @Basic(optional = false)
    public Type type = Type.Book;

    @ManyToOne
    public Publisher publisher;

    @ManyToMany(mappedBy = Author_.BOOKS)
    Set<Author> authors;

    @NotNull
    public int pages;

    public BigDecimal price;
    public BigInteger quantitySold;

    public Book(String isbn, String title, String text) {
        this.isbn = isbn;
        this.title = title;
        this.text = text;
    }

    public Book() {}

    @Override
    public String toString() {
        return isbn + " : " + title + " [" + type + "]";
    }
}

