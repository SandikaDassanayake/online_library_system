package com.rootcodeinterview.online_library_system.Service;

import com.rootcodeinterview.online_library_system.DTO.BookDTO;
import com.rootcodeinterview.online_library_system.DTO.SearchBookRequestDTO;
import com.rootcodeinterview.online_library_system.Entity.Book;
import com.rootcodeinterview.online_library_system.Exceptions.ResourceNotFoundException;
import com.rootcodeinterview.online_library_system.Mapper.BookMapper;
import com.rootcodeinterview.online_library_system.Repository.BookRepository;
import com.rootcodeinterview.online_library_system.Service.impl.BookServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.data.domain.*;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookServiceImplTest {

    @Mock
    private BookRepository bookRepository;

    @Mock
    private BookMapper bookMapper;

    @InjectMocks
    private BookServiceImpl bookService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createBook_shouldSaveBookAndReturnDTO() {
        BookDTO dto = new BookDTO();
        dto.setTitle("Test Book");

        Book entity = new Book();
        entity.setTitle("Test Book");

        when(bookMapper.toEntity(dto)).thenReturn(entity);
        when(bookRepository.save(entity)).thenReturn(entity);
        when(bookMapper.toDto(entity)).thenReturn(dto);

        BookDTO result = bookService.createBook(dto);

        assertThat(result).isEqualTo(dto);
        verify(bookRepository).save(entity);
    }

    @Test
    void getAvailableBooks_shouldReturnAvailableBookDTOs() {
        Book book = new Book();
        book.setTitle("Available Book");
        List<Book> books = List.of(book);

        BookDTO dto = new BookDTO();
        dto.setTitle("Available Book");

        when(bookRepository.findByAvailableCopiesGreaterThan(0)).thenReturn(books);
        when(bookMapper.toDto(books)).thenReturn(List.of(dto));

        List<BookDTO> result = bookService.getAvailableBooks();

        assertThat(result).hasSize(1).contains(dto);
        verify(bookRepository).findByAvailableCopiesGreaterThan(0);
    }

    @Test
    void search_shouldReturnPagedBooks() {
        SearchBookRequestDTO request = new SearchBookRequestDTO();
        request.setAuthor("Orwell");

        Pageable pageable = PageRequest.of(0, 5);
        Page<Book> bookPage = new PageImpl<>(List.of(new Book()));

        when(bookRepository.findAll(any(Example.class), eq(pageable))).thenReturn(bookPage);

        Page<Book> result = bookService.search(request, pageable);

        assertThat(result.getContent()).hasSize(1);
        verify(bookRepository).findAll(any(Example.class), eq(pageable));
    }

    @Test
    void findByTitle_shouldReturnBook_ifExists() {
        Book book = new Book();
        book.setTitle("1984");

        when(bookRepository.findByTitleIgnoreCase("1984")).thenReturn(Optional.of(book));

        Book result = bookService.findByTitle("1984");

        assertThat(result.getTitle()).isEqualTo("1984");
    }

    @Test
    void findByTitle_shouldThrowException_ifNotFound() {
        when(bookRepository.findByTitleIgnoreCase("Unknown")).thenReturn(Optional.empty());

        assertThatThrownBy(() -> bookService.findByTitle("Unknown"))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("Book not found");
    }

    @Test
    void saveEntity_shouldCallRepositorySave() {
        Book book = new Book();
        book.setTitle("Save me");

        bookService.saveEntity(book);

        verify(bookRepository).save(book);
    }
}
