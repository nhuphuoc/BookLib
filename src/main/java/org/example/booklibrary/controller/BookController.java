package org.example.booklibrary.controller;

import org.example.booklibrary.dto.response.BookDto;
import org.example.booklibrary.entity.Book;
import org.example.booklibrary.service.Impl.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
public class BookController {

  @Autowired private BookServiceImpl bookServiceImpl;

  @GetMapping
  public ResponseEntity<List<BookDto>> getAllBooks() {
    return new ResponseEntity<>(bookServiceImpl.getAllBooks(), HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Book> getBookById(@PathVariable Long id) {
    Optional<Book> book = bookServiceImpl.getBookById(id);
    return book.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
  }

  @PostMapping
  public Book createBook(@RequestBody Book book) {
    return bookServiceImpl.saveBook(book);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
    bookServiceImpl.deleteBook(id);
    return ResponseEntity.noContent().build();
  }
}
