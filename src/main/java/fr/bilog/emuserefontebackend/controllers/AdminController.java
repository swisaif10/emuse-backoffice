package fr.bilog.emuserefontebackend.controllers;

import fr.bilog.emuserefontebackend.entities.AppAdmin;
import fr.bilog.emuserefontebackend.models.SignupAdminRequest;
import fr.bilog.emuserefontebackend.repositories.AppUserRepository;
import fr.bilog.emuserefontebackend.services.AccountService;
import fr.bilog.emuserefontebackend.services.impl.AccountServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/appAdmin")
public class AdminController {
    private AppUserRepository appUserRepository;
    private AccountService accountService;
    @GetMapping("/allUsers")
    ResponseEntity<?> getAllUsers() {
        return ResponseEntity.ok(appUserRepository.findAll());
    }
    @PostMapping("/addAdmin")
    ResponseEntity<?> addAdmin(@RequestBody SignupAdminRequest signupAdminRequest) {
        AppAdmin appAdmin = accountService.addAppAdmin(signupAdminRequest.getNom(),signupAdminRequest.getPrenom(),signupAdminRequest.getEmail(),signupAdminRequest.getUsername(),signupAdminRequest.getPassword());
        if (appAdmin != null){
            return ResponseEntity.ok("registre ok");
        }else {
            return ResponseEntity.badRequest().body("registre error");
        }
    }
}
