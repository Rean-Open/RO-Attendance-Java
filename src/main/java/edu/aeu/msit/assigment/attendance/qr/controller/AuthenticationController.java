package edu.aeu.msit.assigment.attendance.qr.controller;

import static org.springframework.http.ResponseEntity.ok;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.aeu.msit.assigment.attendance.qr.handler.AuthenticationRequest;
import edu.aeu.msit.assigment.attendance.qr.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    
    private final AuthenticationManager authenticationManager;
    
    private final JwtTokenProvider jwtTokenProvider;
    
    //private final UserRepository userRepository;
    
    @PostMapping("/signin")
    public ResponseEntity<Map<String, Object>> signin(@RequestBody AuthenticationRequest data) {
        
        try {	
            String username = data.getUsername();
            String password = data.getPassword();
            
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            
            String token = jwtTokenProvider.createToken(authentication);
                       
            Map<String, Object> model = new HashMap<>();
            model.put("username", username);
            model.put("token", token);
            
            return ok(model);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username/password supplied");
        }
    }
}
