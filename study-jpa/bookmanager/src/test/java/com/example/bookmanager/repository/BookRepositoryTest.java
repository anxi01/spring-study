package com.example.bookmanager.repository;

import com.example.bookmanager.domain.Book;
import com.example.bookmanager.domain.Publisher;
import com.example.bookmanager.domain.Review;
import com.example.bookmanager.domain.User;
import com.example.bookmanager.repository.dto.BookStatus;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class BookRepositoryTest {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private PublisherRepository publisherRepository;
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private UserRepository userRepository;

    @Test
    void bookTest(){
        Book book = new Book();
        book.setName("우동한그릇");
        book.setAuthorId(1L);

        bookRepository.save(book);

        System.out.println(bookRepository.findAll());
    }

    @Test
    @Transactional
    void bookRelationTest() {
        givenBookAndReview();

        User user = userRepository.findByEmail("martin@fastcampus.com");

        System.out.println("Review : " + user.getReviews());
        System.out.println("Book : " + user.getReviews().get(0).getBook());
        System.out.println("Publisher : " + user.getReviews().get(0).getBook().getPublisher());
    }

    private void givenBookAndReview() {
        givenReview(givenUser(), givenBook(givenPublisher()));
    }

    private void givenReview(User user, Book book) {
        Review review = new Review();
        review.setTitle("내 인생을 바꾼 책");
        review.setContent("너무너무 재미있고 즐거운 책이었어요.");
        review.setScore(5.0f);
        review.setUser(user);
        review.setBook(book);

        reviewRepository.save(review);
    }

    private Book givenBook(Publisher publisher) {
        Book book = new Book();
        book.setName("JPA 초격차 패키지");
        book.setPublisher(publisher);

        return bookRepository.save(book);
    }

    private User givenUser() {
        return userRepository.findByEmail("martin@fastcampus.com");
    }

    private Publisher givenPublisher() {
        Publisher publisher = new Publisher();
        publisher.setName("패스트캠퍼스");

        return publisherRepository.save(publisher);
    }

    @Test
    void queryTest() {
//        bookRepository.findAll().forEach(System.out::println);
//
//        System.out.println(
//            "findByCategoryIsNullAndNameEqualsAndCreatedAtGreaterThanEqualAndUpdatedAtGreaterThanEqual() : "
//                + bookRepository.findByCategoryIsNullAndNameEqualsAndCreatedAtGreaterThanEqualAndUpdatedAtGreaterThanEqual(
//                "우동한그릇",
//                LocalDateTime.now().minusDays(1L), LocalDateTime.now().minusDays(1L)));

        System.out.println("findByNameRecently() : " + bookRepository.findByNameRecently("우동한그릇",
            LocalDateTime.now().minusDays(1L), LocalDateTime.now().minusDays(1L)));
    }

    @Test
    void nativeQueryTest() {
//        bookRepository.findAll().forEach(System.out::println);
//        bookRepository.findAllCustom().forEach(System.out::println);

        List<Book> books = bookRepository.findAll();

        for (Book book : books) {
            book.setCategory("IT전문서");
        }

        bookRepository.saveAll(books);

        System.out.println(bookRepository.findAll());

        System.out.println("affectedRows : " + bookRepository.updateCategories());

        System.out.println(bookRepository.findAllCustom());
    }

    @Test
    void converterTest() {
        bookRepository.findAll().forEach(System.out::println);

        Book book = new Book();
        book.setName("또 다른 IT 전문서적");
        book.setStatus(new BookStatus(200));

        bookRepository.save(book);

        System.out.println(bookRepository.findRawRecord().values());
    }
}