package com.webTechProject.webtech_app.test;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.core.Response;

import org.apache.http.HttpStatus;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.as.controller.interfaces.WildcardInetAddressInterfaceCriteria;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ArchivePaths;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.webTechProject.webtech_app.data.BookDAO;
import com.webTechProject.webtech_app.model.Book;
import com.webTechProject.webtech_app.rest.BookWS;
import com.webTechProject.webtech_app.rest.JaxRsActivator;
import com.webTechProject.webtech_app.test.UtilsDAO;

@RunWith(Arquillian.class)
public class BookWSTest {
	@Deployment
	public static Archive<?> createTestArchive() {
		return ShrinkWrap.create(WebArchive.class, "Test.jar")
				.addClasses( BookDAO.class, Book.class, JaxRsActivator.class, 
					BookWS.class, UtilsDAO.class).addAsManifestResource("META-INF/persistence.xml", "persistense.xml")
				.addAsManifestResource(EmptyAsset.INSTANCE,ArchivePaths.create("beans.xml"));
		
	}
	
	@EJB
	private BookWS bookWS;
	
	@EJB
	private BookDAO bookDAO;
	
	@EJB
	private UtilsDAO utilsDAO;
	
	@Before
	public void setUp() {
		utilsDAO.deleteTable();
		Book book = new Book();
		book.setAuthor("Opey");
		book.setBookCategory("Fantasy");
		book.setBookName("The Cool Dude");
		book.setISBN("12345678");
		book.setPublisher("Book Publisher");
		book.setYearPublished("2017");
		bookDAO.save(book);
		
		Book book1 = new Book();
		book1.setAuthor("Dolla");
		book1.setBookCategory("Fantasy");
		book1.setBookName("The Nice Man");
		book1.setISBN("12342278");
		book1.setPublisher("Book Publisher");
		book1.setYearPublished("2019");
		bookDAO.save(book1);
	}
	
	@Test
	public void testGetAllBooks() {
		Response response = bookWS.findAll();
		List<Book> booksList =(List<Book>) response.getEntity();
		assertEquals(HttpStatus.SC_OK, response.getStatus());
		assertEquals("Data fetch = data persisted", booksList.size(), 2);
		Book book = booksList.get(0);
		assertEquals("Opey", book.getAuthor());
		book = booksList.get(1);
		assertEquals("Dolla", book.getAuthor());
	}
	
	@Test
	public void testGetBookById() {
		Response response = bookWS.findBookById(1);
		Book book = (Book) response.getEntity();
		assertEquals(book.getBookId(), 1);
		assertEquals(book.getISBN(), "1510104453");
		assertEquals(book.getBookName(), "King of Scars");
		assertEquals(book.getAuthor(), "Leigh Bardugo");
		assertEquals(book.getBookCategory(), "Fiction");
		assertEquals(book.getYearPublished(), "2017");
		assertEquals(book.getPublisher(), "Hachette Childrens Books");
	}
	
	@Test
	public void testAddBook() {
	Book book = new Book();
	book.setISBN("1234567");
	book.setBookName("Opeys Book");
	book.setAuthor("Opey");
	book.setBookCategory("Fiction");
	book.setYearPublished("2017");
	book.setPublisher("Opey Ltd");
	Response response = bookWS.saveBook(book);
	assertEquals(HttpStatus.SC_CREATED, response.getStatus());
	book = (Book) response.getEntity();
	assertEquals(book.getBookId(), 3);
	assertEquals(book.getISBN(), "1234567");
	assertEquals(book.getBookName(), "Opeys Book");
	assertEquals(book.getAuthor(), "Opey");
	assertEquals(book.getBookCategory(), "Fiction");
	assertEquals(book.getYearPublished(), "2017");
	assertEquals(book.getPublisher(), "Opey Ltd");
	}

	@Test
	public void testRemoveBook() {
	Response response = bookWS.findAll();
	List<Book> bookList = (List<Book>) response.getEntity();
	assertEquals(bookList.size(), 2);
	bookWS.deleteBook(2);
	response = bookWS.findAll();
	bookList = (List<Book>) response.getEntity();
	assertEquals(bookList.size(), 1);
	response = bookWS.findBookById(2);
	Book book = (Book) response.getEntity();
	assertEquals(HttpStatus.SC_OK, response.getStatus());
	assertEquals(null, book);

	}

	@Test
	public void testUpdateBook() {
	Response response = bookWS.findBookById(2);
	Book book = (Book) response.getEntity();
	book.setAuthor("Richard");
	book.setBookName("NEW BOOK");
	book.setISBN("012345");
	response = bookWS.updateBook(book);
	assertEquals(HttpStatus.SC_OK, response.getStatus());
	book = (Book) response.getEntity();
	assertEquals(book.getAuthor(), "Richard");
	assertEquals(book.getBookName(), "NEW BOOK");
	assertEquals(book.getISBN(), "012345");
	}

	@Test
	public void testSearchbooksByName() {
	Response response = bookWS.findBookByName("The Nice Man");
	List<Book> bookList = (List<Book>) response.getEntity();
	assertEquals(HttpStatus.SC_OK, response.getStatus());
	assertEquals(bookList.size(), 1);
	Book book = bookList.get(0);
	assertEquals("The Nice Man", book.getBookName());
	assertEquals("Fantasy", book.getBookCategory());

	} 

}
