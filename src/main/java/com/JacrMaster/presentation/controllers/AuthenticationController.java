package com.JacrMaster.presentation.controllers;

import com.JacrMaster.presentation.dto.AuthLoginRequest;
import com.JacrMaster.presentation.dto.AuthResponse;
import com.JacrMaster.services.user.UserDetailServiceImpl;
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

    @PostMapping("/log-in")
    @CrossOrigin(origins = "http://localhost:5173")
    public ResponseEntity<AuthResponse> login(@RequestBody @Valid AuthLoginRequest userRequest){
        return new ResponseEntity<>(this.userDetailService.loginUser(userRequest), HttpStatus.OK);
    }
}
