package com.rootcodeinterview.online_library_system.Service;


import com.rootcodeinterview.online_library_system.DTO.AuthRequestDTO;
import com.rootcodeinterview.online_library_system.DTO.AuthResponseDTO;
import com.rootcodeinterview.online_library_system.Entity.User;

public interface AuthService {

    String register(User user);

    AuthResponseDTO login(AuthRequestDTO authRequestDTO);

    User findByName(String username);
}
