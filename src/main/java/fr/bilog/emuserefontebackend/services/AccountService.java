package fr.bilog.emuserefontebackend.services;

import fr.bilog.emuserefontebackend.entities.AppAdmin;
import fr.bilog.emuserefontebackend.entities.AppUser;
import fr.bilog.emuserefontebackend.entities.Role;

public interface AccountService {
    Role addRole(String roleName);
    AppUser addAppUser(String username, String password);
    AppAdmin addAppAdmin(String nom, String prenom, String email, String username, String password);
    }
