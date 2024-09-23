package org.example.booklibrary.controller;

import org.example.booklibrary.dto.response.BookDto;
import org.example.booklibrary.entity.Book;
import org.example.booklibrary.service.Impl.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/books", produces = MediaType.APPLICATION_JSON_VALUE)
public class BookController {

  @Autowired private BookServiceImpl bookServiceImpl;

  @GetMapping("/{requestId}")
  public ResponseEntity<List<BookDto>> getAllBooks(@PathVariable String requestId) {
    return new ResponseEntity<>(bookServiceImpl.getAllBooks(requestId), HttpStatus.OK);
  }

  @GetMapping("/{id}/{requestId}")
  public ResponseEntity<Optional<Book>> getBookById(@PathVariable Long id, @PathVariable String requestId) {
    return new ResponseEntity<>(bookServiceImpl.getBookById(id,requestId), HttpStatus.OK);
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
