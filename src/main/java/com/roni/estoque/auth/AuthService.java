package com.roni.estoque.auth;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AuthService {

    private final UsuarioRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthService(
            UsuarioRepository repository,
            PasswordEncoder passwordEncoder,
            JwtService jwtService,
            AuthenticationManager authenticationManager
    ) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public AuthResponse register(AuthRequest dto) {
        //Para verificar se o email existe
        if(repository.findByEmail(dto.getEmail()).isPresent())
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email já cadastrado");

        Usuario usuario = new Usuario();
        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setSenha(passwordEncoder.encode(dto.getSenha()));
        repository.save(usuario);

        String token = jwtService.gerarToken(dto.getEmail());
        return new AuthResponse(token, usuario.getNome(), usuario.getEmail());
    }

    public AuthResponse login(AuthRequest dto){
        //o spring security ja valida o email e senha automatico
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getSenha())
        );

        Usuario usuario = repository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));
        String token = jwtService.gerarToken(dto.getEmail());
        return new AuthResponse(token, usuario.getNome(), usuario.getEmail());
    }
}
