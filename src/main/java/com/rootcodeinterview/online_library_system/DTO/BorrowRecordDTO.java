package com.rootcodeinterview.online_library_system.DTO;

import java.time.Instant;

public class BorrowRecordDTO {

    private Long id;
    private UserDTO user;
    private BookDTO book;
    private Instant borrowDate;
    private Instant returnDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public BookDTO getBook() {
        return book;
    }

    public void setBook(BookDTO book) {
        this.book = book;
    }

    public Instant getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(Instant borrowDate) {
        this.borrowDate = borrowDate;
    }

    public Instant getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Instant returnDate) {
        this.returnDate = returnDate;
    }
}
