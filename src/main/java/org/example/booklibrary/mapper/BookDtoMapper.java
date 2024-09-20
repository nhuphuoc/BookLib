package org.example.booklibrary.mapper;

import org.example.booklibrary.dto.response.BookDto;
import org.example.booklibrary.entity.Book;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BookDtoMapper {
    public BookDto toDto(Book book) {
        BookDto bookDto = new BookDto();
        bookDto.setTitle(book.getTitle());
        bookDto.setAuthorName(book.getAuthor() != null ? book.getAuthor().getName() : null);
        bookDto.setGenre(book.getGenre());
        bookDto.setPublicationDate(book.getPublicationDate());
        bookDto.setQuantity(book.getQuantity());
        return bookDto;
    }
    public List<BookDto> toListDto(List<Book> books) {
        List<BookDto> bookDtos = new ArrayList<>();
        for (Book book : books) {
            bookDtos.add(toDto(book));
        }
        return bookDtos;
    }
}
