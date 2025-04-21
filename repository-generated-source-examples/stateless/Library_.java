package org.example;

import jakarta.annotation.Generated;
import jakarta.annotation.Nonnull;
import jakarta.data.Sort;
import jakarta.data.exceptions.DataException;
import jakarta.data.exceptions.EmptyResultException;
import jakarta.data.exceptions.EntityExistsException;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.persistence.NoResultException;
import jakarta.persistence.NonUniqueResultException;
import jakarta.persistence.PersistenceException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static java.util.Optional.ofNullable;
import org.hibernate.StatelessSession;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.query.Order;
import static org.hibernate.query.Order.by;
import static org.hibernate.query.SortDirection.*;

@RequestScoped
@Generated("org.hibernate.processor.HibernateProcessor")
@jakarta.transaction.Transactional
public class Library_ implements Library {

	static final String SUMMARIZE = "select b.isbn, b.title, listagg(a.name, ' & ')\nfrom Book b join b.authors a\ngroup by b\norder by b.isbn\n";

	
	@Override
	public void add(@Nonnull Book book) {
		if (book == null) throw new IllegalArgumentException("Null book");
		try {
			session.insert(book);
		}
		catch (ConstraintViolationException exception) {
			throw new EntityExistsException(exception.getMessage(), exception);
		}
		catch (PersistenceException exception) {
			throw new DataException(exception.getMessage(), exception);
		}
	}
	
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
		try {
			return session.createSelectionQuery(_query)
				.getResultList();
		}
		catch (PersistenceException exception) {
			throw new DataException(exception.getMessage(), exception);
		}
	}
	
	/**
	 * Find {@link Book} by {@link Book#isbn isbn}.
	 *
	 * @see org.example.Library#byIsbn(String)
	 **/
	@Override
	public Optional<Book> byIsbn(@Nonnull String isbn) {
		if (isbn == null) throw new IllegalArgumentException("Null isbn");
		try {
			return ofNullable(session.get(Book.class, isbn));
		}
		catch (PersistenceException exception) {
			throw new DataException(exception.getMessage(), exception);
		}
	}
	
	protected @Nonnull StatelessSession session;
	
	@Inject
	public Library_(@Nonnull StatelessSession session) {
		this.session = session;
	}
	
	public @Nonnull StatelessSession session() {
		return session;
	}
	
	/**
	 * Find {@link Book}.
	 *
	 * @see org.example.Library#allBooks(Sort)
	 **/
	@Override
	public List<Book> allBooks(Sort<Book> bookSort) {
		var _builder = session.getFactory().getCriteriaBuilder();
		var _query = _builder.createQuery(Book.class);
		var _entity = _query.from(Book.class);
		_query.where(
		);
		var _orders = new ArrayList<Order<? super Book>>();
		_orders.add(by(Book.class, bookSort.property(),
							bookSort.isAscending() ? ASCENDING : DESCENDING,
							bookSort.ignoreCase()));
		try {
			return session.createSelectionQuery(_query)
				.setOrder(_orders)
				.getResultList();
		}
		catch (PersistenceException exception) {
			throw new DataException(exception.getMessage(), exception);
		}
	}
	
	/**
	 * Execute the query {@value #SUMMARIZE}.
	 *
	 * @see org.example.Library#summarize()
	 **/
	@Override
	public List<Summary> summarize() {
		try {
			return session.createSelectionQuery(SUMMARIZE, Summary.class)
				.getResultList();
		}
		catch (PersistenceException exception) {
			throw new DataException(exception.getMessage(), exception);
		}
	}
	
	/**
	 * Find {@link Book} by {@link Book#isbn isbn}.
	 *
	 * @see org.example.Library#delete(String)
	 **/
	@Override
	public void delete(@Nonnull String isbn) {
		if (isbn == null) throw new IllegalArgumentException("Null isbn");
		var _builder = session.getFactory().getCriteriaBuilder();
		var _query = _builder.createCriteriaDelete(Book.class);
		var _entity = _query.from(Book.class);
		_query.where(
				_builder.equal(_entity.get(Book_.isbn), isbn)
		);
		try {
			session.createMutationQuery(_query)
				.executeUpdate();
		}
		catch (NoResultException exception) {
			throw new EmptyResultException(exception.getMessage(), exception);
		}
		catch (NonUniqueResultException exception) {
			throw new jakarta.data.exceptions.NonUniqueResultException(exception.getMessage(), exception);
		}
		catch (PersistenceException exception) {
			throw new DataException(exception.getMessage(), exception);
		}
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
		var _orders = new ArrayList<Order<? super Book>>();
		_orders.add(by(Book.class, "isbn", ASCENDING, false));
		try {
			return session.createSelectionQuery(_query)
				.setOrder(_orders)
				.getResultList();
		}
		catch (PersistenceException exception) {
			throw new DataException(exception.getMessage(), exception);
		}
	}

}

