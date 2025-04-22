package com.rootcodeinterview.online_library_system.Service;

import com.rootcodeinterview.online_library_system.DTO.BorrowRecordDTO;

import java.util.List;

public interface BorrowService {

    BorrowRecordDTO borrowBook(String username,String bookTitle);

    BorrowRecordDTO returnBook(String username,String bookTitle);

    List<BorrowRecordDTO> getAllRecords();
}
