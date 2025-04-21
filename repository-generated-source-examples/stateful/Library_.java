package org.example;

import jakarta.annotation.Generated;
import jakarta.annotation.Nonnull;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import java.util.List;
import java.util.Optional;
import static java.util.Optional.ofNullable;
import org.hibernate.Session;

@Dependent
@Generated("org.hibernate.processor.HibernateProcessor")
public class Library_ implements Library {

	static final String SUMMARIZE = "select b.isbn, b.title, listagg(a.name, ' & ')\nfrom Book b join b.authors a\ngroup by b\norder by b.isbn\n";
	static final String DELETE_String = "delete from Book b where b.isbn = :isbn";

	
	/**
	 * Find {@link Book} by {@link Book#title title}.
	 *
	 * @see org.example.Library#byTitle(String)
	 **/
	@Override
	public List<Book> byTitle(String title) {
		var _builder = session.getFactory().getCriteriaBuilder();
		var _query = _builder.createQuery(Book.class);
		var _entity = _query.from(Book.class);
		_query.where(
				title==null
					? _entity.get(Book_.title).isNull()
					: _builder.like(_entity.get(Book_.title), title)
		);
		return session.createSelectionQuery(_query)
				.getResultList();
	}
	
	/**
	 * Find {@link Book} by {@link Book#isbn isbn}.
	 *
	 * @see org.example.Library#byIsbn(String)
	 **/
	@Override
	public Optional<Book> byIsbn(@Nonnull String isbn) {
		if (isbn == null) throw new IllegalArgumentException("Null isbn");
		return ofNullable(session.find(Book.class, isbn));
	}
	
	/**
	 * Execute the query {@value #SUMMARIZE}.
	 *
	 * @see org.example.Library#summarize()
	 **/
	@Override
	public List<Summary> summarize() {
		return session.createSelectionQuery(SUMMARIZE, Summary.class)
				.getResultList();
	}
	
	/**
	 * Execute the query {@value #DELETE_String}.
	 *
	 * @see org.example.Library#delete(String)
	 **/
	@Override
	public void delete(String isbn) {
		session.createMutationQuery(DELETE_String)
				.setParameter("isbn", isbn)
				.executeUpdate();
	}
	
	protected final @Nonnull Session session;
	
	@Inject
	public Library_(@Nonnull Session session) {
		this.session = session;
	}
	
	@Override
	public @Nonnull Session getSession() {
		return session;
	}
	
	/**
	 * Find {@link Book}.
	 *
	 * @see org.example.Library#allBooks()
	 **/
	@Override
	public List<Book> allBooks() {
		var _builder = session.getFactory().getCriteriaBuilder();
		var _query = _builder.createQuery(Book.class);
		var _entity = _query.from(Book.class);
		_query.where(
		);
		return session.createSelectionQuery(_query)
				.getResultList();
	}

}

