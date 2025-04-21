package org.example;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotNull;
import org.hibernate.envers.Audited;

import java.util.Set;

@Audited
@Entity
public class Author {
    @Id
    public String ssn;

    @NotNull
    public String name;

    public Address address;

    @ManyToMany
    Set<Book> books;
}

