package com.rootcodeinterview.online_library_system.Mapper;

import com.rootcodeinterview.online_library_system.DTO.BookDTO;
import com.rootcodeinterview.online_library_system.Entity.Book;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookMapper extends EntityMapper<BookDTO, Book> {
}
