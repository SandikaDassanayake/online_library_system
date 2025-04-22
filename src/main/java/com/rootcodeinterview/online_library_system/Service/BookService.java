package com.rootcodeinterview.online_library_system.Service;

import com.rootcodeinterview.online_library_system.DTO.BookDTO;
import com.rootcodeinterview.online_library_system.DTO.SearchBookRequestDTO;
import com.rootcodeinterview.online_library_system.Entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BookService {

    BookDTO createBook(BookDTO bookDTO);

    List<BookDTO> getAvailableBooks();

    Page<Book> search(SearchBookRequestDTO requestDTO, Pageable pageRequest);
}
