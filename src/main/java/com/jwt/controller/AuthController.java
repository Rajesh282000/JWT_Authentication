package com.jwt.controller;


import com.jwt.Service.AuthService;
import com.jwt.dto.AuthResponseDto;
import com.jwt.dto.LoginDto;
import com.jwt.dto.RoleDto;
import com.jwt.dto.SignUpDto;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    // Build Login REST API
    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody LoginDto loginDto){

        //01 - Receive the token from AuthService
        String token = authService.login(loginDto);

        //02 - Set the token as a response using JwtAuthResponse Dto class
        AuthResponseDto authResponseDto = new AuthResponseDto();
        authResponseDto.setAccessToken(token);

        //03 - Return the response to the user
        return new ResponseEntity<>(authResponseDto, HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<String> registerUser(@RequestBody SignUpDto signUpDto) {
        String response = authService.registerUser(signUpDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/role")
    public ResponseEntity<String> createRole(@RequestBody RoleDto roleDto) {
        String response = authService.createRole(roleDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
