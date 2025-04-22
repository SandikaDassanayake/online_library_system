package com.rootcodeinterview.online_library_system.Service;


import com.rootcodeinterview.online_library_system.Entity.User;
import com.rootcodeinterview.online_library_system.Repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user= userRepository.findByName(username).orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));


        return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(),new ArrayList<>());
    }
}
