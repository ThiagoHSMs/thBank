package com.wallet.api.controller;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wallet.api.model.User;
import com.wallet.api.repository.UserRepository;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {
    
    @org.springframework.beans.factory.annotation.Autowired
        private UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User loginRequest) {
    System.out.println("Tentativa de login com e-mail: " + loginRequest.getEmail());
    
    Optional<User> user = userRepository.findByEmail(loginRequest.getEmail());

    if (user.isPresent()) {
        System.out.println("Usuário encontrado no banco: " + user.get().getEmail());
        System.out.println("Senha no banco: " + user.get().getPassword());
        System.out.println("Senha digitada: " + loginRequest.getPassword());

        if (user.get().getPassword().equals(loginRequest.getPassword())) {
            return ResponseEntity.ok(user.get());
        }
    } else {
        System.out.println("Usuário NÃO encontrado no banco.");
    }

    return ResponseEntity.status(401).body("E-mail ou senha inválidos");
}
    
}
