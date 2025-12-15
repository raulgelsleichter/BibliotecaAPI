package com.bookstore.api.service;

import com.bookstore.api.dto.*;
import java.util.List;

public interface BookService {
    BookDTO create(BookCreateDTO dto);
    BookDTO update(Long id, BookUpdateDTO dto);
    void delete(Long id);
    BookDTO findById(Long id);
    List<BookDTO> findAll();
    List<BookDTO> findByTitle(String title);
}
