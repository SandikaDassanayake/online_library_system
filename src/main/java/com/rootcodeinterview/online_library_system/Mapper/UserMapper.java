package com.rootcodeinterview.online_library_system.Mapper;

import com.rootcodeinterview.online_library_system.DTO.UserDTO;
import com.rootcodeinterview.online_library_system.Entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper extends EntityMapper<UserDTO, User> {

}
