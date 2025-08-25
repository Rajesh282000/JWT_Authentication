package com.jwt.Service;


import com.jwt.config.JwtTokenProvider;
import com.jwt.dto.LoginDto;
import com.jwt.dto.RoleDto;
import com.jwt.dto.SignUpDto;
import com.jwt.entity.Role;
import com.jwt.entity.User;
import com.jwt.repo.RoleRepository;
import com.jwt.repo.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService {


    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;




    public AuthServiceImpl(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String login(LoginDto loginDto) {

        // 01 - AuthenticationManager is used to authenticate the user
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsername(),
                loginDto.getPassword()
        ));

        /* 02 - SecurityContextHolder is used to allows the rest of the application to know
        that the user is authenticated and can use user data from Authentication object */
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // 03 - Generate the token based on username and secret key
        String token = jwtTokenProvider.generateToken(authentication);

        // 04 - Return the token to controller
        return token;
    }

    @Override
    public String registerUser(SignUpDto signUpDto) {
        // Check if user exists
        if (userRepository.existsByUsername(signUpDto.getUsername())) {
            throw new RuntimeException("Username is already taken!");
        }
        if (userRepository.existsByEmail(signUpDto.getEmail())) {
            throw new RuntimeException("Email is already registered!");
        }

        // Create new user
        User user = new User();
        user.setName(signUpDto.getName());
        user.setUsername(signUpDto.getUsername());
        user.setEmail(signUpDto.getEmail());
        user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));

        // Assign default role (e.g., ROLE_USER)
        Role role = roleRepository.findByName("USER")
                .orElseThrow(() -> new RuntimeException("Default role not found"));
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRoles(roles);

        userRepository.save(user);
        return "User registered successfully!";
    }

    @Override
    public String createRole(RoleDto roleDto) {
        Optional<Role> existingRole = roleRepository.findByName(roleDto.getName());
        if (existingRole.isPresent()) {
            throw new RuntimeException("Role already exists!");
        }
        Role role = new Role();
        role.setName(roleDto.getName());
        roleRepository.save(role);
        return "Role created successfully!";
    }
}
