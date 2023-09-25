package com.tranv.webdoctorcareapi.controller;


import com.tranv.webdoctorcareapi.dto.CreateDoctorDTO;
import com.tranv.webdoctorcareapi.dto.CreatePatientDTO;
import com.tranv.webdoctorcareapi.entity.LoginRequest;

import com.tranv.webdoctorcareapi.entity.Token;
import com.tranv.webdoctorcareapi.service.UsersService;
import com.tranv.webdoctorcareapi.service.UsersServiceImpl;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.SecretKey;
import java.util.Date;


@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UsersService usersService;

    private final SecretKey jwtSecretKey = Keys.secretKeyFor(SignatureAlgorithm.HS512);
    // timestamp (thời gian dạng số) thể hiện thời điểm kết thúc hiệu lực của JWT
    @Value("${jwt.expiration}")
    private long jwtExpiration;

    public AuthController(AuthenticationManager authenticationManager, UsersServiceImpl usersService,
                          PasswordEncoder passwordEncoder) {
    }

    // add mapping for POST /login
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest longinRequest) {
        try {
            // Authenticate the login request
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(longinRequest.getEmail(), longinRequest.getPassword())
            );
            // Generate a JWT token if authentication is successful
            String tempToken = Jwts.builder()
                    .setSubject(authentication.getName())
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + jwtExpiration))
                    .signWith(jwtSecretKey)
                    .compact();
            // Return the token
            Token token = new Token(tempToken);
            return ResponseEntity.ok(token);
        } catch (AuthenticationException exc) {
            // If authentication fails, return an error message
            String errorMessage = "Tài khoản hoặc mật khẩu không đúng";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
        }
    }
    // add mapping for POST /createPatient
    @PostMapping("/createPatient")
    public ResponseEntity<?> createPatient(@RequestBody CreatePatientDTO newPatient) {
        if (usersService.isEmailAlreadyExists(newPatient.getEmail())) {
            // Check if the email already exists
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email đã được sử dụng");
        }
        if (newPatient.getName() != null && newPatient.getEmail() != null && newPatient.getPassword() != null) {
            if (newPatient.getPassword().equals(newPatient.getConfirmPassword())) {
                // Create a new patient account
                usersService.createPatient(newPatient);
                return ResponseEntity.ok("Tạo tài khoản thành công");
            } else {
                // Return an error message if the password confirmation does not match
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Xác nhận mật khẩu không đúng");
            }
        } else {
            //check validate
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Vui lòng điền đầy đủ thông tin đăng ký");
        }
    }

    // add mapping for POST /create Doctor
    @PostMapping("/createDoctor")
    public ResponseEntity<?> createDoctor(@RequestBody CreateDoctorDTO newDoctor) {
        if (usersService.isEmailAlreadyExists(newDoctor.getEmail())) {
            // Check if the email already exists
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email đã được sử dụng");
        }
        if (newDoctor.getName() != null && newDoctor.getEmail() != null && newDoctor.getPassword() != null) {
            if (newDoctor.getPassword().equals(newDoctor.getConfirmPassword())) {
                // Create a new doctor account
                usersService.createDoctor(newDoctor);
                return ResponseEntity.ok("Tạo tài khoản cho bác sỹ thành công");
            } else {
                // Return an error message if the password confirmation does not match
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Xác nhận mật khẩu không đúng");
            }
        } else {
            //check validate
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Vui lòng điền đầy đủ thông tin đăng ký");
        }
    }
}
