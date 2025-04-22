package com.rootcodeinterview.online_library_system.DTO;

import java.util.List;

public class UserDTO {

    private Long id;
    private String name;
    private String email;
    private List<BorrowRecordDTO> borrowRecords;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<BorrowRecordDTO> getBorrowRecords() {
        return borrowRecords;
    }

    public void setBorrowRecords(List<BorrowRecordDTO> borrowRecords) {
        this.borrowRecords = borrowRecords;
    }
}
