package org.example.booklibrary.service.Impl;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;
import java.util.Optional;
import org.example.booklibrary.exception.BookNotFoundException;
import org.example.booklibrary.mapper.BookDtoMapper;
import org.example.booklibrary.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import org.example.booklibrary.dto.response.BookDto;
import org.example.booklibrary.entity.Author;
import org.example.booklibrary.entity.Book;
import org.example.booklibrary.mapper.BookDtoMapper;
import org.example.booklibrary.repository.BookRepository;
import org.example.booklibrary.service.Impl.BookServiceImpl;
import org.example.booklibrary.service.Impl.CacheServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
@ExtendWith(MockitoExtension.class)
class BookServiceImplTest {

  @Mock
  private BookRepository bookRepository;
  @Mock
  private BookDtoMapper bookDtoMapper;

  @InjectMocks
  private BookServiceImpl bookService;
  @BeforeEach
  void setUp() {
    // Setup nếu cần thiết trước mỗi test case
  }

  @Test
  public void testGetAllBooks() {
    // Giả lập dữ liệu trả về từ bookRepository và bookDtoMapper
    List<Book> mockBooks = Arrays.asList(
        new Book(1L, "Book One", null, "Fiction", new Date(), 10),
        new Book(2L, "Book Two", null, "Non-Fiction", new Date(), 5)
    );
    List<BookDto> mockBookDtos = Arrays.asList(
        new BookDto("Title 1", "Book One", "Fiction", new Date(), 10),
        new BookDto("Title 2", "Book Two", "Non-Fiction", new Date(), 5)
    );

    when(bookRepository.findAll()).thenReturn(mockBooks);
    when(bookDtoMapper.toListDto(mockBooks)).thenReturn(mockBookDtos);

    // Gọi phương thức cần test
    List<BookDto> result = bookService.getAllBooks("123");

    // Kiểm tra kết quả trả về
    assertEquals(mockBookDtos, result);
  }

  @Test
  void getAllBooksWithCache() {
    }

  @Test
  public void testGetBookById_BookFound() {
    Long bookId = 1L;
    Book mockBook = new Book(bookId, "Book Title", null, "Genre", null, 1);
    when(bookRepository.existsById(bookId)).thenReturn(true);
    when(bookRepository.findById(bookId)).thenReturn(Optional.of(mockBook));

    Optional<Book> result = bookService.getBookById(bookId, "123");

    assertTrue(result.isPresent());
    assertEquals(mockBook, result.get());
  }

  @Test
  public void testGetBookById_BookNotFound() {
    Long bookId = 1L;
    when(bookRepository.existsById(bookId)).thenReturn(false);

    assertThrows(BookNotFoundException.class, () -> bookService.getBookById(bookId, "123"));
  }

  @Test
  public void testSaveBook() {
    // 1. Dữ liệu giả lập cho book
    Book mockBook = new Book(1L, "Sample Book", new Author(1L, "John Doe", null), "Fiction", new Date(), 10);

    when(bookRepository.save(mockBook)).thenReturn(mockBook);

    // 3. Gọi hàm saveBook trong service và kiểm tra kết quả
    Book result = bookService.saveBook(mockBook);

    // 4. Kiểm tra kết quả trả về có giống với mockBook không
    assertEquals(mockBook, result);
  }

  @Test
  void deleteBook() {
    }

  @Test
  void saveAll() {
    }
}