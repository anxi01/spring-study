package com.example.bookmanager.repository;

import com.example.bookmanager.domain.Book;
import java.time.LocalDateTime;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BookRepository extends JpaRepository<Book, Long> {

  List<Book> findByCategoryIsNullAndNameEqualsAndCreatedAtGreaterThanEqualAndUpdatedAtGreaterThanEqual(
      String name, LocalDateTime createdAt, LocalDateTime updatedAt);

  @Query(value = "select b from Book b "
      + "where b.name = :name and b.createdAt >= :createdAt and b.updatedAt >= :updatedAt and b.category is null")
      List<Book>findByNameRecently(
          @Param("name") String name,
          @Param("createdAt") LocalDateTime createdAt,
          @Param("updatedAt") LocalDateTime updatedAt);

  @Query(value = "select * from book", nativeQuery = true)
  List<Book> findAllCustom();

  @Transactional
  @Modifying
  @Query(value = "update book set category = 'IT전문서'", nativeQuery = true)
  int updateCategories();
}
