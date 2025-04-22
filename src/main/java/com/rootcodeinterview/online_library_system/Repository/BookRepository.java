package com.rootcodeinterview.online_library_system.Repository;

import com.rootcodeinterview.online_library_system.Entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByAvailableCopiesGreaterThan(int minCopies);

    Optional<Book> findByTitleIgnoreCase(String title);

}
