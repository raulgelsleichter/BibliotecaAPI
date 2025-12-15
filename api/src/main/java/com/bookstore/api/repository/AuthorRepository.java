package com.bookstore.api.repository;

import com.bookstore.api.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    // Buscar autores pelo nome (opcional)
    List<Author> findByNameContainingIgnoreCase(String name);

}
