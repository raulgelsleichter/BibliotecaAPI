package com.bookstore.api.service.impl;

import com.bookstore.api.dto.*;
import com.bookstore.api.entity.*;
import com.bookstore.api.exception.ResourceNotFoundException;
import com.bookstore.api.mapper.BookMapper;
import com.bookstore.api.repository.*;
import com.bookstore.api.service.BookService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepo;
    private final CategoryRepository categoryRepo;
    private final AuthorRepository authorRepo;

    public BookServiceImpl(BookRepository bookRepo, CategoryRepository categoryRepo, AuthorRepository authorRepo) {
        this.bookRepo = bookRepo;
        this.categoryRepo = categoryRepo;
        this.authorRepo = authorRepo;
    }

    @Override
    public BookDTO create(BookCreateDTO dto) {
        Category cat = categoryRepo.findById(dto.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));

        Book book = new Book();
        book.setTitle(dto.getTitle());
        book.setIsbn(dto.getIsbn());
        book.setPrice(dto.getPrice());
        book.setCategory(cat);

        if (dto.getAuthorIds() != null) {
            Set<Author> authors = dto.getAuthorIds().stream()
                    .map(id -> authorRepo.findById(id)
                            .orElseThrow(() -> new ResourceNotFoundException("Author not found: " + id)))
                    .collect(Collectors.toSet());
            book.setAuthors(authors);
        }

        Book saved = bookRepo.save(book);
        return BookMapper.toDTO(saved);
    }

    @Override
    public BookDTO update(Long id, BookUpdateDTO dto) {
        Book book = bookRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found"));

        book.setTitle(dto.getTitle());
        book.setIsbn(dto.getIsbn());
        book.setPrice(dto.getPrice());

        if (dto.getCategoryId() != null) {
            Category cat = categoryRepo.findById(dto.getCategoryId())
                    .orElseThrow(() -> new ResourceNotFoundException("Category not found"));
            book.setCategory(cat);
        }

        if (dto.getAuthorIds() != null) {
            Set<Author> authors = dto.getAuthorIds().stream()
                    .map(aid -> authorRepo.findById(aid)
                            .orElseThrow(() -> new ResourceNotFoundException("Author not found: " + aid)))
                    .collect(Collectors.toSet());
            book.setAuthors(authors);
        }

        Book updated = bookRepo.save(book);
        return BookMapper.toDTO(updated);
    }

    @Override
    public void delete(Long id) {
        Book book = bookRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found"));
        bookRepo.delete(book);
    }

    @Override
    public BookDTO findById(Long id) {
        return BookMapper.toDTO(
                bookRepo.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Book not found"))
        );
    }

    @Override
    public List<BookDTO> findAll() {
        return bookRepo.findAll()
                .stream()
                .map(BookMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<BookDTO> findByTitle(String title) {
        return bookRepo.findByTitleContainingIgnoreCase(title)
                .stream()
                .map(BookMapper::toDTO)
                .collect(Collectors.toList());
    }
}
