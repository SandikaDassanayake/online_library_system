package com.rootcodeinterview.online_library_system.Mapper;

import com.rootcodeinterview.online_library_system.DTO.BorrowRecordDTO;
import com.rootcodeinterview.online_library_system.DTO.UserDTO;
import com.rootcodeinterview.online_library_system.Entity.BorrowRecord;
import com.rootcodeinterview.online_library_system.Entity.User;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface BorrowRecordMapper extends EntityMapper<BorrowRecordDTO, BorrowRecord>{

    @Mapping(target = "user", source = "user", qualifiedByName = "userIdOnly")
    BorrowRecordDTO toDto(BorrowRecord borrowRecord);

    @Named("userIdOnly")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    UserDTO toUserId(User user);
}
