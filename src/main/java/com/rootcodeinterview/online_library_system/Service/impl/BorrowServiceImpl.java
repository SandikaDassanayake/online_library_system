package com.rootcodeinterview.online_library_system.Service.impl;

import com.rootcodeinterview.online_library_system.DTO.BorrowRecordDTO;
import com.rootcodeinterview.online_library_system.Entity.Book;
import com.rootcodeinterview.online_library_system.Entity.BorrowRecord;
import com.rootcodeinterview.online_library_system.Entity.User;
import com.rootcodeinterview.online_library_system.Mapper.BorrowRecordMapper;
import com.rootcodeinterview.online_library_system.Repository.BookRepository;
import com.rootcodeinterview.online_library_system.Repository.BorrowRecordRepository;
import com.rootcodeinterview.online_library_system.Repository.UserRepository;
import com.rootcodeinterview.online_library_system.Service.BorrowService;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class BorrowServiceImpl implements BorrowService {
    private final BorrowRecordRepository borrowRecordRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final BorrowRecordMapper borrowRecordMapper;

    public BorrowServiceImpl(BorrowRecordRepository borrowRecordRepository, UserRepository userRepository, BookRepository bookRepository, BorrowRecordMapper borrowRecordMapper) {
        this.borrowRecordRepository = borrowRecordRepository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
        this.borrowRecordMapper = borrowRecordMapper;
    }

    @Override
    public BorrowRecordDTO borrowBook(String username, String bookTitle) {
        User user = userRepository.findByName(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Book book = bookRepository.findByTitleIgnoreCase(bookTitle)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        if (book.getAvailableCopies() < 1)
            throw new RuntimeException("No copies available");

        book.setAvailableCopies(book.getAvailableCopies() - 1);

        BorrowRecord record = new BorrowRecord();
        record.setUser(user);
        record.setBook(book);
        record.setBorrowDate(Instant.now());

        bookRepository.save(book);
        return borrowRecordMapper.toDto(borrowRecordRepository.save(record));
    }

@Override
    public BorrowRecordDTO returnBook(String username, String bookTitle) {
        User user = userRepository.findByName(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Book book = bookRepository.findByTitleIgnoreCase(bookTitle)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        BorrowRecord record = borrowRecordRepository.findByUserAndBookAndReturnDateIsNull(user, book)
                .orElseThrow(() -> new RuntimeException("No active borrow record found"));

        record.setReturnDate(Instant.now());
        book.setAvailableCopies(book.getAvailableCopies() + 1);

        borrowRecordRepository.save(record);
        bookRepository.save(book);

        return borrowRecordMapper.toDto(record);
    }

    @Override
    public List<BorrowRecordDTO> getAllRecords() {
        return borrowRecordMapper.toDto(borrowRecordRepository.findAll());
    }
}
