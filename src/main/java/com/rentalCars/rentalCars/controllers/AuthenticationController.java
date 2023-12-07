package com.rentalCars.rentalCars.controllers;

import com.rentalCars.rentalCars.domain.users.User;
import com.rentalCars.rentalCars.domain.users.dto.AuthenticationDTO;
import com.rentalCars.rentalCars.domain.users.dto.LoginResponseDTO;
import com.rentalCars.rentalCars.domain.users.dto.RegisterDTO;
import com.rentalCars.rentalCars.infra.security.TokenService;
import com.rentalCars.rentalCars.repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO authenticationDTO) {
         UsernamePasswordAuthenticationToken usernamePassword = new UsernamePasswordAuthenticationToken(authenticationDTO.login(), authenticationDTO.password());
         var auth = this.authenticationManager.authenticate(usernamePassword);

         String token = tokenService.generateToken((User) auth.getPrincipal());

         return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO registerDTO) {
        if(this.userRepository.findByLogin(registerDTO.login()) != null)  return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(registerDTO.password());
        User newUser = new User(
                registerDTO.login(),
                encryptedPassword,
                registerDTO.email(),
                registerDTO.phone(),
                registerDTO.hasCar(),
                registerDTO.planType(),
                registerDTO.role()
        );

        userRepository.save(newUser);

        return  ResponseEntity.ok().build();
    }
}
