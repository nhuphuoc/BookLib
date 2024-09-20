package org.example.booklibrary.controller;

import org.example.booklibrary.entity.Author;
import org.example.booklibrary.entity.Book;
import org.example.booklibrary.service.Impl.AuthorServiceImpl;
import org.example.booklibrary.service.Impl.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/test")
public class TestController {
  private static final Logger LOGGER = LoggerFactory.getLogger(TestController.class);
  private AuthorServiceImpl authorServiceImpl;
  private BookServiceImpl bookServiceImpl;

  @Autowired
  public TestController(AuthorServiceImpl authorServiceImpl, BookServiceImpl bookServiceImpl) {
    this.authorServiceImpl = authorServiceImpl;
    this.bookServiceImpl = bookServiceImpl;
  }

  @GetMapping("/hello")
  ResponseEntity<String> hello() {
    LOGGER.info("hello");
    return ResponseEntity.ok("Hello World");
  }

  @GetMapping("/dummies")
  public String initDummiesData() throws ParseException {
    List<Author> authors = new ArrayList<>();
    List<Book> books = new ArrayList<>();
    LOGGER.info("Started creating dummies data");

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    String[][] data = {
      {"J.K. Rowling", "Harry Potter and the Philosopher's Stone", "Fantasy", "1997-06-26", "120"},
      {"George Orwell", "1984", "Dystopian", "1949-06-08", "50"},
      {"J.R.R. Tolkien", "The Hobbit", "Fantasy", "1937-09-21", "80"},
      {"Jane Austen", "Pride and Prejudice", "Romance", "1813-01-28", "70"},
      {"F. Scott Fitzgerald", "The Great Gatsby", "Tragedy", "1925-04-10", "60"},
      {"Mark Twain", "The Adventures of Tom Sawyer", "Adventure", "1876-06-10", "90"},
      {"Leo Tolstoy", "War and Peace", "Historical", "1869-01-01", "40"},
      {"Herman Melville", "Moby-Dick", "Adventure", "1851-10-18", "30"},
      {"Mary Shelley", "Frankenstein", "Gothic", "1818-01-01", "100"},
      {"Charlotte Brontë", "Jane Eyre", "Romance", "1847-10-16", "85"}
    };

    for (String[] entry : data) {
      Author author = new Author();
      author.setName(entry[0]);

      Book book = new Book();
      book.setTitle(entry[1]);
      book.setGenre(entry[2]);
      book.setPublicationDate(sdf.parse(entry[3]));
      book.setQuantity(Integer.parseInt(entry[4]));
      book.setAuthor(author);

      author.setBook(book);

      authors.add(author);
      books.add(book);
    }
    LOGGER.info("Completed set up dummies data. Total book: {}, authors: {}", books.size(), authors.size() );

    authorServiceImpl.saveAll(authors);
    bookServiceImpl.saveAll(books);

    return "10 dummies records initialized successfully.";
  }
}
