package com.jacrnet.userservice.presentation.controller;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.jacrnet.userservice.presentation.dto.*;
import com.jacrnet.userservice.service.UserDetailServiceImpl;
import com.jacrnet.userservice.utils.JwtUtils;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    @Autowired
    private UserDetailServiceImpl userDetailService;

    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("/log-in")
    public ResponseEntity<AuthResponse> login(@RequestBody @Valid AuthLoginRequest userRequest){
        return new ResponseEntity<>(this.userDetailService.loginUser(userRequest), HttpStatus.OK);
    }
    @PostMapping("/validate")
    public ResponseEntity<Void> validateToken(@RequestBody TokenValidationRequest tokenValidationRequest) {
        try {
            // Validar el token usando jwtUtils
            DecodedJWT decodedJWT = jwtUtils.validateToken(tokenValidationRequest.getToken());
            return ResponseEntity.ok().build();  // Si es válido, responder 200 OK
        } catch (JWTVerificationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();  // Si no es válido, retornar 401 Unauthorized
        }
    }
}
