package com.webTechProject.webtech_app.data;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.webTechProject.webtech_app.model.Book;

@Stateless
@LocalBean
public class BookDAO {

	@PersistenceContext
	private EntityManager em;
	
	public List<Book> getAllBooks() {
		Query query = em.createQuery("SELECT b FROM Book b");
		return query.getResultList();
	}

	public Book getBook(int id) {
		return em.find(Book.class, id);
	}
	
	public List<Book> getBookByName(String name) {
		Query query = em.createQuery("SELECT b FROM Book as b WHERE b.bookName  LIKE ?1");
		query.setParameter(1, "%" +name.toUpperCase() +"%");
		return query.getResultList();
	}

	public void save(Book book) {
		em.persist(book);
	}

	public void update(Book book) {
		em.merge(book);
	}

	public void delete(int id) {
		em.remove(getBook(id));
	}
	
}
