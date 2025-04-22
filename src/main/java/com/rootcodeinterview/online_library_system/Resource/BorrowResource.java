package com.rootcodeinterview.online_library_system.Resource;

import com.rootcodeinterview.online_library_system.DTO.BorrowRecordDTO;
import com.rootcodeinterview.online_library_system.DTO.BorrowRequestDTO;
import com.rootcodeinterview.online_library_system.DTO.ReturnRequestDTO;
import com.rootcodeinterview.online_library_system.Service.BorrowService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/borrow")
public class BorrowResource {

    private final BorrowService borrowService;

    public BorrowResource(BorrowService borrowService) {
        this.borrowService = borrowService;
    }


    @PostMapping
    public ResponseEntity<BorrowRecordDTO> borrowBook(@RequestBody BorrowRequestDTO request) {
        return ResponseEntity.ok(borrowService.borrowBook(request.getUsername(), request.getBookTitle()));
    }


    @PostMapping("/return")
    public ResponseEntity<BorrowRecordDTO> returnBook(@RequestBody ReturnRequestDTO request) {
        return ResponseEntity.ok(borrowService.returnBook(request.getUsername(), request.getBookTitle()));
    }

    @GetMapping("/history")
    public ResponseEntity<List<BorrowRecordDTO>> getAllBorrowRecords() {
        return ResponseEntity.ok(borrowService.getAllRecords());
    }
}
