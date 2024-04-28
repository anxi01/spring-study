package com.example.bookmanager.service;

import com.example.bookmanager.domain.Book;
import com.example.bookmanager.repository.BookRepository;
import java.util.List;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookService {

  private final BookRepository bookRepository;

  @Transactional
  public List<Book> getAll() {
    /*
      Converter를 구현할 때
      convertToDatabaseColumn
      convertToEntityAttribute
      정방향, 역방향 모두 구현해야함. 하나만 구현하면 null로 표시되고 데이터 유실될 수 있음.
     */
    List<Book> books = bookRepository.findAll();

    books.forEach(System.out::println);

    return books;
  }
}
