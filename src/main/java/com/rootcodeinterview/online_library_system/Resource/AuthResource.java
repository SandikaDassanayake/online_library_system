package com.rootcodeinterview.online_library_system.Resource;

import com.rootcodeinterview.online_library_system.DTO.AuthRequestDTO;
import com.rootcodeinterview.online_library_system.DTO.AuthResponseDTO;
import com.rootcodeinterview.online_library_system.Entity.User;
import com.rootcodeinterview.online_library_system.Service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthResource {

    private final AuthService authService;


    public AuthResource(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user){
        return ResponseEntity.ok(authService.register(user));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody AuthRequestDTO request){
        return ResponseEntity.ok(authService.login(request));
    }
}
