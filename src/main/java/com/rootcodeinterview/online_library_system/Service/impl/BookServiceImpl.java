package com.rootcodeinterview.online_library_system.Service.impl;

import com.rootcodeinterview.online_library_system.DTO.BookDTO;
import com.rootcodeinterview.online_library_system.DTO.SearchBookRequestDTO;
import com.rootcodeinterview.online_library_system.Entity.Book;
import com.rootcodeinterview.online_library_system.Mapper.BookMapper;
import com.rootcodeinterview.online_library_system.Repository.BookRepository;
import com.rootcodeinterview.online_library_system.Service.BookService;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    public BookServiceImpl(BookRepository bookRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }

    @Override
    public BookDTO createBook(BookDTO bookDTO) {
        return bookMapper.toDto(bookRepository.save(bookMapper.toEntity(bookDTO)));
    }

    @Override
    public List<BookDTO> getAvailableBooks() {
        return bookMapper.toDto(bookRepository.findByAvailableCopiesGreaterThan(0));
    }

    @Override
    public Page<Book> search(SearchBookRequestDTO requestDTO, Pageable pageRequest) {
        Book probe = new Book();

        if (requestDTO.getTitle() != null) {
            probe.setTitle(requestDTO.getTitle());
        }
        if (requestDTO.getAuthor() != null) {
            probe.setAuthor(requestDTO.getAuthor());
        }
        if (requestDTO.getPublishedYear() != null) {
            probe.setPublishedYear(requestDTO.getPublishedYear());
        }

        ExampleMatcher matcher = ExampleMatcher.matchingAll()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        Example<Book> example = Example.of(probe, matcher);

        return bookRepository.findAll(example, pageRequest);
    }
}
