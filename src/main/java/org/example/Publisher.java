package org.example;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import org.hibernate.envers.Audited;

import java.util.Set;

@Audited
@Entity
public class Publisher {
    @Id
    public long id;

    @NotNull
    public String name;

    @OneToMany(mappedBy = Book_.PUBLISHER)
    Set<Book> books;
}
