package com.bank.digital_procurement.controller;

import com.bank.digital_procurement.dto.UserDto;
import com.bank.digital_procurement.dto.request.LoginRequest;
import com.bank.digital_procurement.dto.request.SignupRequest;
import com.bank.digital_procurement.dto.response.ApiResponse;
import com.bank.digital_procurement.dto.response.AuthResponse;
import com.bank.digital_procurement.exception.AppException;
import com.bank.digital_procurement.exception.ErrorCode;
import com.bank.digital_procurement.model.User;
import com.bank.digital_procurement.repository.UserRepository;
import com.bank.digital_procurement.security.JwtUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtil jwtUtil;

    @Value("${jwt.expiration}")
    private Long jwtExpiration;

    @PostMapping("/signin")
    public ResponseEntity<ApiResponse<AuthResponse>> signin(@Valid @RequestBody LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
            );

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String token = jwtUtil.generateToken(userDetails.getUsername());

            AuthResponse authResponse = new AuthResponse(token, userDetails.getUsername(), jwtExpiration);
            ApiResponse<AuthResponse> response = ApiResponse.success(authResponse);

            return ResponseEntity.ok(response);
        } catch (BadCredentialsException e) {
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        } catch (Exception e){
            throw new AppException(ErrorCode.UNCATEGORIZED_EXCEPTION);
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<UserDto>> registerUser(@Valid @RequestBody SignupRequest signupRequest) {
        if(userRepository.existsByUsername(signupRequest.getUsername())) {
            throw new AppException(ErrorCode.USER_EXISTED);
        }

        User newUser = new User(
                null,
                signupRequest.getUsername(),
                encoder.encode(signupRequest.getPassword())
        );
        User savedUser = userRepository.save(newUser);

        UserDto userDto = new UserDto(savedUser.getId(), savedUser.getUsername());
        ApiResponse<UserDto> response = ApiResponse.success(userDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
