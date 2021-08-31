package fr.bilog.emuserefontebackend.controllers;

import fr.bilog.emuserefontebackend.entities.profiles.AppUser;
import fr.bilog.emuserefontebackend.models.*;
import fr.bilog.emuserefontebackend.security.JwtUtils;
import fr.bilog.emuserefontebackend.security.UserDetailsImpl;
import fr.bilog.emuserefontebackend.services.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    private AccountService accountService;
    private AuthenticationManager authenticationManager;
    private JwtUtils jwtUtils;


    @PostMapping("/signup")
    ResponseEntity<?> signUp(@RequestBody UserCreationRequest userCreationRequest) {
        AppUser appUser = accountService.addAppUser(userCreationRequest );
        if (appUser != null) {
            return ResponseEntity.ok("registre ok");
        } else {
            return ResponseEntity.badRequest().body("registre error");
        }
    }

    @PostMapping("/signin")
    public ResponseEntity<?> signIn(@RequestBody LoginRequest loginRequest) {
        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        } catch (BadCredentialsException e) {
            return ResponseEntity.badRequest().body(new HttpResponse("301", "Error username or passowrd"));


        }
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        /*  List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());*/


        return ResponseEntity.ok(new LoginResponse(jwt, "200"
        ));
    }

}
