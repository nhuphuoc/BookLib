package org.example.booklibrary.controller;

import org.example.booklibrary.entity.Author;
import org.example.booklibrary.service.Impl.AuthorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {

  @Autowired private AuthorServiceImpl authorServiceImpl;

  @GetMapping
  public List<Author> getAllAuthors() {
    return authorServiceImpl.getAllAuthors();
  }

  @GetMapping("/{id}")
  public ResponseEntity<Author> getAuthorById(@PathVariable Long id) {
    Optional<Author> author = authorServiceImpl.getAuthorById(id);
    return author.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
  }

  @PostMapping
  public Author createAuthor(@RequestBody Author author) {
    return authorServiceImpl.saveAuthor(author);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteAuthor(@PathVariable Long id) {
    authorServiceImpl.deleteAuthor(id);
    return ResponseEntity.noContent().build();
  }
}
