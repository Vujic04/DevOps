package repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.demo.bookstore_app.models.Book;
import com.demo.bookstore_app.repositories.BookRepository;

@DataJpaTest
public class BookRepoTest {
	
	@Autowired
	private BookRepository repo;

	@Test
	public void testfindByBookName() {
		Book book = new Book();
		String testTitle = "The little prince";
		book.setBookName(testTitle);
		repo.save(book);
		List<Book> foundBooks = repo.findByBookNameContainingIgnoreCase(testTitle);
		assertFalse(foundBooks.isEmpty());
	}
	
	@Test
	public void testfindByAuthorName() {
		Book book = new Book();
		String testName = "Antoni de Saint";
		book.setAuthorName(testName);
		repo.save(book);
		List<Book> foundBooks = repo.findByAuthorNameContainingIgnoreCase(testName);
		assertFalse(foundBooks.isEmpty());
	}
	
	@Test
	public void testSave() {
		Book b1 = new Book(null, 19.99, "Book A", "New Author", 1976);
		repo.save(b1);
		Long saveBookID = b1.getBookId();
		Book book = repo.findById(saveBookID).orElseThrow();
		
		assertEquals(saveBookID, book.getBookId());
		assertEquals("Book A", book.getBookName());
	}
}
