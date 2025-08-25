package com.jwt.Service;

import com.jwt.dto.LoginDto;
import com.jwt.dto.RoleDto;
import com.jwt.dto.SignUpDto;

public interface AuthService {
    String login(LoginDto loginDto);
    String registerUser(SignUpDto signUpDto);

    String createRole(RoleDto roleDto);
}
