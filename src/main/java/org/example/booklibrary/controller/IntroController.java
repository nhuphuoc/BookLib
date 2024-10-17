package org.example.booklibrary.controller;

import org.example.booklibrary.dto.response.BookDto;
import org.example.booklibrary.entity.Book;
import org.example.booklibrary.service.Impl.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class IntroController {
  @GetMapping("/public/about")
  public String about(){
    return "Developed by Bui Nhu Phuoc";
  }
  @GetMapping("/private/about")
  public String privateMessage(){
    return "You are viewing this private message";
  }
}
