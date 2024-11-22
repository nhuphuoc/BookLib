package org.example.booklibrary.service.Impl;

import org.example.booklibrary.entity.Author;
import org.example.booklibrary.repository.AuthorRepository;
import org.example.booklibrary.service.AuthorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

  private AuthorRepository authorRepository;
  private static final Logger logger = LoggerFactory.getLogger(AuthorServiceImpl.class);

  @Autowired
  public AuthorServiceImpl(AuthorRepository authorRepository) {
    this.authorRepository = authorRepository;
  }

  @Override
  public List<Author> getAllAuthors() {
    logger.info("Fetching all authors");
    List<Author> authors = authorRepository.findAll();
    logger.info("Fetched {} authors", authors.size());
    return authors;
  }

  @Override
  public Optional<Author> getAuthorById(Long id) {
    logger.info("Fetching author with ID: {}", id);
    Optional<Author> author = authorRepository.findById(id);
    if (author.isPresent()) {
      logger.info("Author found: {}", author.get());
    } else {
      logger.warn("No author found with ID: {}", id);
    }
    return author;
  }

  @Override
  public Author saveAuthor(Author author) {
    logger.info("Saving author: {}", author);
    Author savedAuthor = authorRepository.save(author);
    logger.info("Author saved: {}", savedAuthor);
    return savedAuthor;
  }

  @Override
  public void deleteAuthor(Long id) {
    logger.info("Deleting author with ID: {}", id);
    authorRepository.deleteById(id);
    logger.info("Deleted author with ID: {}", id);
  }

  @Override
  public void saveAll(List<Author> authors) {
    authorRepository.saveAll(authors);
  }
}
