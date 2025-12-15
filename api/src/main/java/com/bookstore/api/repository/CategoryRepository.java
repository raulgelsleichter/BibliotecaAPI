package com.bookstore.api.repository;

import com.bookstore.api.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    // Buscar por nome contendo (opcional, mas útil)
    List<Category> findByNameContainingIgnoreCase(String name);

}
