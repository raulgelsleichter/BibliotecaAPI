package com.bookstore.api.repository;

import com.bookstore.api.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByTitleContainingIgnoreCase(String title);
    List<Book> findByIsbn(String isbn);
    List<Book> findByCategoryId(Long categoryId);
}

