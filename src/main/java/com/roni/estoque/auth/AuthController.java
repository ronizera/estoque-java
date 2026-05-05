package com.roni.estoque.auth;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService service;

    public AuthController(AuthService service){
        this.service = service;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public AuthResponse register(@RequestBody @Valid AuthRequest dto){
        return service.register(dto);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody @Valid AuthRequest dto){
        return service.login(dto);
    }
}
