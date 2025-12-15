package com.bookstore.api.controller;

import com.bookstore.api.dto.*;
import com.bookstore.api.service.BookService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService service;

    public BookController(BookService service) {
        this.service = service;
    }

    // Create
    @PostMapping
    public ResponseEntity<BookDTO> create(@Valid @RequestBody BookCreateDTO dto) {
        BookDTO created = service.create(dto);
        return ResponseEntity.created(URI.create("/api/books/" + created.getId())).body(created);
    }

    // Update
    @PutMapping("/{id}")
    public ResponseEntity<BookDTO> update(@PathVariable Long id, @Valid @RequestBody BookUpdateDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    // Find by PK
    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    // Find all OR filter by title (?title=xxx)
    @GetMapping
    public ResponseEntity<List<BookDTO>> getAll(@RequestParam(value = "title", required = false) String title) {
        if (title != null && !title.isBlank()) {
            return ResponseEntity.ok(service.findByTitle(title));
        }
        return ResponseEntity.ok(service.findAll());
    }

    // Search endpoint
    @GetMapping("/search")
    public List<BookDTO> searchBooks(@RequestParam String title) {
        return service.findByTitle(title);
    }
}
