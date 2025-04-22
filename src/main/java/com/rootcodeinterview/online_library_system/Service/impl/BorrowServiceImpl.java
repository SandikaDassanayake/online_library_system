package com.rootcodeinterview.online_library_system.Service.impl;

import com.rootcodeinterview.online_library_system.DTO.BorrowRecordDTO;
import com.rootcodeinterview.online_library_system.Entity.Book;
import com.rootcodeinterview.online_library_system.Entity.BorrowRecord;
import com.rootcodeinterview.online_library_system.Entity.User;
import com.rootcodeinterview.online_library_system.Exceptions.MainException;
import com.rootcodeinterview.online_library_system.Mapper.BorrowRecordMapper;
import com.rootcodeinterview.online_library_system.Repository.BorrowRecordRepository;
import com.rootcodeinterview.online_library_system.Service.AuthService;
import com.rootcodeinterview.online_library_system.Service.BookService;
import com.rootcodeinterview.online_library_system.Service.BorrowService;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Map;

@Service
public class BorrowServiceImpl implements BorrowService {
    private final BorrowRecordRepository borrowRecordRepository;
    private final AuthService authService;
    private final BookService bookService;
    private final BorrowRecordMapper borrowRecordMapper;

    public BorrowServiceImpl(BorrowRecordRepository borrowRecordRepository,
                             AuthService authService, BookService bookService,
                             BorrowRecordMapper borrowRecordMapper) {
        this.borrowRecordRepository = borrowRecordRepository;
        this.authService = authService;
        this.bookService = bookService;
        this.borrowRecordMapper = borrowRecordMapper;
    }

    @Override
    public BorrowRecordDTO borrowBook(String username, String bookTitle) {
        User user = authService.findByName(username);

        Book book = bookService.findByTitle(bookTitle);

        if (book.getAvailableCopies() < 1)
            throw new MainException(
                    "Book not available",
                    Map.of("bookTitle", bookTitle, "availableCopies", book.getAvailableCopies())
            );

        book.setAvailableCopies(book.getAvailableCopies() - 1);

        BorrowRecord record = new BorrowRecord();
        record.setUser(user);
        record.setBook(book);
        record.setBorrowDate(Instant.now());

        bookService.saveEntity(book);
        return borrowRecordMapper.toDto(borrowRecordRepository.save(record));
    }

@Override
    public BorrowRecordDTO returnBook(String username, String bookTitle) {
        User user = authService.findByName(username);

        Book book = bookService.findByTitle(bookTitle);

        BorrowRecord record = borrowRecordRepository.findByUserAndBookAndReturnDateIsNull(user, book)
                .orElseThrow(() -> new MainException(
                        "Borrow record not found",
                        Map.of("username", username, "bookTitle", bookTitle)
                ));

        record.setReturnDate(Instant.now());
        book.setAvailableCopies(book.getAvailableCopies() + 1);

        borrowRecordRepository.save(record);
        bookService.saveEntity(book);

        return borrowRecordMapper.toDto(record);
    }

    @Override
    public List<BorrowRecordDTO> getAllRecords() {
        return borrowRecordMapper.toDto(borrowRecordRepository.findAll());
    }
}
