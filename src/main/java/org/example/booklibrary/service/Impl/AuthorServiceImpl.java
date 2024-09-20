package org.example.booklibrary.service.Impl;

import org.example.booklibrary.entity.Author;
import org.example.booklibrary.repository.AuthorRepository;
import org.example.booklibrary.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

  private AuthorRepository authorRepository;

  @Autowired
  public AuthorServiceImpl(AuthorRepository authorRepository) {
    this.authorRepository = authorRepository;
  }

  @Override
  public List<Author> getAllAuthors() {
    return authorRepository.findAll();
  }

  @Override
  public Optional<Author> getAuthorById(Long id) {
    return authorRepository.findById(id);
  }

  @Override
  public Author saveAuthor(Author author) {
    return authorRepository.save(author);
  }

  @Override
  public void deleteAuthor(Long id) {
    authorRepository.deleteById(id);
  }

  @Override
  public void saveAll(List<Author> authors) {
    authorRepository.saveAll(authors);
  }
}
