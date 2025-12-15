package com.bookstore.api.mapper;

import com.bookstore.api.dto.*;
import com.bookstore.api.entity.*;
import java.util.stream.Collectors;

public class BookMapper {

    public static BookDTO toDTO(Book book) {
        if (book == null) return null;
        BookDTO dto = new BookDTO();
        dto.setId(book.getId());
        dto.setTitle(book.getTitle());
        dto.setIsbn(book.getIsbn());
        dto.setPrice(book.getPrice());
        if (book.getCategory() != null) {
            dto.setCategoryId(book.getCategory().getId());
            dto.setCategoryName(book.getCategory().getName());
        }
        if (book.getAuthors() != null) {
            dto.setAuthorIds(book.getAuthors().stream().map(Author::getId).collect(Collectors.toSet()));
            dto.setAuthorNames(book.getAuthors().stream().map(Author::getName).collect(Collectors.toSet()));
        }
        return dto;
    }
}
