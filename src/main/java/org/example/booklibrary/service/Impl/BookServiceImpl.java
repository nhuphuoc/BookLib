package org.example.booklibrary.service.Impl;

import org.example.booklibrary.controller.TestController;
import org.example.booklibrary.dto.response.BookDto;
import org.example.booklibrary.entity.Book;
import org.example.booklibrary.mapper.BookDtoMapper;
import org.example.booklibrary.repository.BookRepository;
import org.example.booklibrary.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class BookServiceImpl implements BookService {
  private BookRepository bookRepository;
  private BookDtoMapper bookDtoMapper;
  private static final Logger LOGGER = LoggerFactory.getLogger(BookServiceImpl.class);

  @Autowired
  public BookServiceImpl(BookRepository bookRepository, BookDtoMapper bookDtoMapper) {
    this.bookRepository = bookRepository;
    this.bookDtoMapper = bookDtoMapper;
  }

  @Override
  public List<BookDto> getAllBooks() {
    LOGGER.info("Get all books");
    return bookDtoMapper.toListDto(bookRepository.findAll());
  }

  @Override
  public Optional<Book> getBookById(Long id) {
    LOGGER.info("Get book by id {}", id);
    return bookRepository.findById(id);
  }

  @Override
  public Book saveBook(Book book) {
    LOGGER.info("Save book {}", book);
    return bookRepository.save(book);
  }

  @Override
  public void deleteBook(Long id) {
    LOGGER.info("Delete book {}", id);
    bookRepository.deleteById(id);
  }

  @Override
  public void saveAll(List<Book> books) {
    LOGGER.info("Save all books {}", books.size());
    bookRepository.saveAll(books);
  }
}
