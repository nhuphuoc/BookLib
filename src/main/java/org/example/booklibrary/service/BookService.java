package org.example.booklibrary.service;

import org.example.booklibrary.dto.response.BookDto;
import org.example.booklibrary.entity.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<BookDto> getAllBooks();

    Optional<Book> getBookById(Long id);

    Book saveBook(Book book);

    void deleteBook(Long id);

    void saveAll(List<Book> books);
}
