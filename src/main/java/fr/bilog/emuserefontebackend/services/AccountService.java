package fr.bilog.emuserefontebackend.services;

import fr.bilog.emuserefontebackend.entities.profiles.AppAdmin;
import fr.bilog.emuserefontebackend.entities.profiles.AppUser;
import fr.bilog.emuserefontebackend.models.SignupAdminRequest;
import fr.bilog.emuserefontebackend.models.UserCreationRequest;

import java.util.List;

public interface AccountService {
    AppUser addAppUser(UserCreationRequest userCreationRequest);

    AppAdmin addAppAdmin(String nom, String prenom, String email, String username, String password);

    AppAdmin updateAppAdmin(SignupAdminRequest signupAdminRequest, Long id);

    List<AppAdmin> getAllAdmins();

    boolean deleteAppAdmin(Long idAdmin);

    AppAdmin addAppSuperAdmin(String nom, String prenom, String email, String username, String password);

}
