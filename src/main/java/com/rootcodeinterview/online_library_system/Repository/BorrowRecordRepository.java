package com.rootcodeinterview.online_library_system.Repository;

import com.rootcodeinterview.online_library_system.Entity.Book;
import com.rootcodeinterview.online_library_system.Entity.BorrowRecord;
import com.rootcodeinterview.online_library_system.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BorrowRecordRepository extends JpaRepository<BorrowRecord, Long> {
    Optional<BorrowRecord> findByUserAndBookAndReturnDateIsNull(User user, Book book);


}
