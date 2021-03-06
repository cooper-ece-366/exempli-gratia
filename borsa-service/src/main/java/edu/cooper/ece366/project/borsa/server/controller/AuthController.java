package edu.cooper.ece366.project.borsa.server.controller;

import edu.cooper.ece366.project.borsa.server.RestApiServer;
import edu.cooper.ece366.project.borsa.server.exception.BadRequestException;
import edu.cooper.ece366.project.borsa.server.model.AuthProvider;
import edu.cooper.ece366.project.borsa.server.model.Portfolio;
import edu.cooper.ece366.project.borsa.server.model.User;
import edu.cooper.ece366.project.borsa.server.payload.ApiResponse;
import edu.cooper.ece366.project.borsa.server.payload.AuthResponse;
import edu.cooper.ece366.project.borsa.server.payload.LoginRequest;
import edu.cooper.ece366.project.borsa.server.payload.SignUpRequest;
import edu.cooper.ece366.project.borsa.server.repository.AssetRepository;
import edu.cooper.ece366.project.borsa.server.repository.PortfolioRepository;
import edu.cooper.ece366.project.borsa.server.repository.UserRepository;
import edu.cooper.ece366.project.borsa.server.security.TokenProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PortfolioRepository portfolioRepository;

    @Autowired
    private AssetRepository assetRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenProvider tokenProvider;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = tokenProvider.createToken(authentication);
        return ResponseEntity.ok(new AuthResponse(token));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logoutAuthenticatedUser(@Valid @RequestBody String logoutRequest) {
        LOGGER.info(logoutRequest);
        return ResponseEntity.ok(new AuthResponse(logoutRequest));
    }


    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        if(userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new BadRequestException("Email address already in use.");
        }

        // Creating user's account
        User user = new User();
        user.setName(signUpRequest.getName());
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(signUpRequest.getPassword());
        user.setProvider(AuthProvider.local);

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        User result = userRepository.save(user);

//        Portfolio portfolio = new Portfolio();
//        user.setPortfolio(portfolio);
//        Portfolio presult = portfolioRepository.save(portfolio);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/user/me")
                .buildAndExpand(result.getId()).toUri();

        return ResponseEntity.created(location)
                .body(new ApiResponse(true, "User registered successfully@"));
    }

}
