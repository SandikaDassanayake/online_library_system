package com.rootcodeinterview.online_library_system.DTO;

public class ReturnRequestDTO {
    private String username;
    private String bookTitle;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }
}
