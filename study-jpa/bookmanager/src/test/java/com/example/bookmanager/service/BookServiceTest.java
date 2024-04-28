package com.example.bookmanager.service;

import static org.junit.jupiter.api.Assertions.*;

import com.example.bookmanager.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BookServiceTest {

  @Autowired
  private BookService bookService;

  @Autowired
  private BookRepository bookRepository;

  @Test
  void converterErrorTest() {
    bookService.getAll();

    bookRepository.findAll().forEach(System.out::println);
  }
}