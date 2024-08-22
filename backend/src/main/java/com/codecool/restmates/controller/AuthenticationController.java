package com.codecool.restmates.controller;

import com.codecool.restmates.model.entity.Member;
import com.codecool.restmates.model.entity.Role;
import com.codecool.restmates.model.entity.RoleType;
import com.codecool.restmates.model.payload.JwtResponse;
import com.codecool.restmates.model.payload.LoginRequest;
import com.codecool.restmates.model.payload.RegisterRequest;
import com.codecool.restmates.repository.MemberRepository;
import com.codecool.restmates.repository.RoleRepository;
import com.codecool.restmates.security.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping(path = "/member")
public class AuthenticationController {
    private final MemberRepository memberRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final JwtUtils jwtUtils;

    @Autowired
    public AuthenticationController(
            MemberRepository memberRepository,
            RoleRepository roleRepository,
            PasswordEncoder passwordEncoder,
            AuthenticationManager authenticationManager,
            JwtUtils jwtUtils) {
        this.memberRepository = memberRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/register")
    public ResponseEntity<Void> createUser(@RequestBody RegisterRequest registerRequest) {
        if (memberRepository.existsByEmail(registerRequest.getEmail())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        Member member = new Member(
                registerRequest.getFirstName(),
                registerRequest.getLastName(),
                registerRequest.getPhoneNumber(),
                registerRequest.getEmail(),
                passwordEncoder.encode(registerRequest.getPassword())
        );

        Optional<Role> defaultRoleType = roleRepository.findByRoleType(RoleType.ROLE_USER);

        if (defaultRoleType.isPresent()) {
            member.setRoles(Set.of(defaultRoleType.get()));
        } else {
            Role defaultRole = new Role(RoleType.ROLE_USER);
            roleRepository.save(defaultRole);
            member.setRoles(Set.of(defaultRole));
        }

        memberRepository.save(member);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtUtils.generateJwtToken(authentication);

        User userDetails = (User) authentication.getPrincipal();

        List<String> roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();

        return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUsername(), roles));
    }

    @GetMapping("/me")
    @PreAuthorize("hasRole('USER')")
    public String me() {
        User user = (User) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        return "Hello " + user.getUsername();
    }

    @GetMapping("/public")
    public String publicEndpoint() {
        return "Public\n";
    }
}
