package service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.demo.bookstore_app.models.Book;
import com.demo.bookstore_app.repositories.BookRepository;
import com.demo.bookstore_app.services.BookService;

@SpringBootTest
@ActiveProfiles({"test"})
public class BookServiceTest {
	
	
	@Autowired
	private BookRepository bookRepository;
	
	
	@Autowired
	private BookService bookService;
	
	@Test
	public void testUpdateBooks() throws Exception{
		Long bookId = 1L;
		Book existingBook = new Book();
		existingBook.setBookId(bookId);
		existingBook.setBookName("The litlle Prince");
		
		Book updatedDetails = new Book();
		updatedDetails.setBookName("Vol de unit");
		
		when (bookRepository.findById(bookId)).thenReturn(Optional.of(existingBook));
		
		when (bookRepository.save(any(Book.class))).thenAnswer(i -> i.getArguments() [0]);
		
		Book result = bookService.updateBook(bookId, updatedDetails);
		
		Assertions.assertNotNull(result);
		Assertions.assertEquals("Vol de unit", result.getBookName());
		
		verify(bookRepository).findById(bookId);
		verify(bookRepository).save(existingBook);
	}
}
