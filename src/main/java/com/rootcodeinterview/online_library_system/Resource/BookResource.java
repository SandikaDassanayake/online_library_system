package com.rootcodeinterview.online_library_system.Resource;

import com.rootcodeinterview.online_library_system.Constants.Constants;
import com.rootcodeinterview.online_library_system.DTO.BookDTO;
import com.rootcodeinterview.online_library_system.DTO.SearchBookRequestDTO;
import com.rootcodeinterview.online_library_system.Entity.Book;
import com.rootcodeinterview.online_library_system.Service.BookService;
import jakarta.validation.constraints.Min;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookResource {
    private final BookService bookService;

    public BookResource(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/available")
    public ResponseEntity<List<BookDTO>> getAvailableBooks() {
        return ResponseEntity.ok(bookService.getAvailableBooks());
    }

    @PostMapping("/search")
    public ResponseEntity<Page<Book>> search(
            @RequestBody SearchBookRequestDTO requestDTO,
            @Min(1)
            @RequestParam(name = "page", required = false, defaultValue = Constants.PAGINATION_DEFAULT_PAGE) Integer page,

            @Min(10)
            @RequestParam(name = "page_size", required = false, defaultValue = Constants.PAGINATION_DEFAULT_PAGE_SIZE) Integer pageSize
    ) {

        Pageable pageRequest = PageRequest.of(page - 1, pageSize);
        return new ResponseEntity<>(bookService.search(requestDTO, pageRequest), HttpStatus.CREATED);
    }
}
