package fr.bilog.emuserefontebackend.services.impl;

import fr.bilog.emuserefontebackend.entities.profiles.*;
import fr.bilog.emuserefontebackend.entities.userProfile.ActivitesPreferencesType;
import fr.bilog.emuserefontebackend.entities.userProfile.DureeActivitesModeresType;
import fr.bilog.emuserefontebackend.entities.userProfile.DureeActivitesSoutenuesType;
import fr.bilog.emuserefontebackend.models.RoleDesignation;
import fr.bilog.emuserefontebackend.models.SignupAdminRequest;
import fr.bilog.emuserefontebackend.models.UserCreationRequest;
import fr.bilog.emuserefontebackend.repositories.*;
import fr.bilog.emuserefontebackend.services.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@AllArgsConstructor
@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    private AppUserRepository appUserRepository;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private AppAdminRepository appAdminRepository;
    private DureeActivitesSoutenuesTypeRepository dureeActivitesSoutenuesTypeRepository;
    private DureeActivitesModeresTypeRepository dureeActivitesModeresTypeRepository;
    private ActivitesPreferencesTypeRepository activitesPreferencesTypeRepository;
    private ApplicationTypeRepository applicationTypeRepository;

    @Override
    public AppUser addAppUser(UserCreationRequest userCreationRequest) {
        Optional<User> user = userRepository.findByUsername(userCreationRequest.getUsername());

        if (user.isPresent()) {
            return null;
        }

        AppUser appUser = new AppUser();
        appUser.setUsername(userCreationRequest.getUsername());
        appUser.setPassword(passwordEncoder.encode(userCreationRequest.getPassword()));
        appUser.setEnabled(true);
        appUser.setPoid(userCreationRequest.getPoid());
        appUser.setTaille(userCreationRequest.getTaille());
        appUser.setSexe(userCreationRequest.getSexe());
        appUser.setDateNaissance(userCreationRequest.getDateNaissance());
        appUser.setUpdateDate(new Date());
        appUser.setCreationDate(new Date());
        Role appRole = roleRepository.findByRoleName(RoleDesignation.USER.toString());
        appUser.setRole(appRole);
        Optional<ApplicationType> applicationType = applicationTypeRepository.findById(userCreationRequest.getIdApplicationType());
        if (applicationType.isPresent()) {
            appUser.setApplicationType(applicationType.get());

        }
        Optional<DureeActivitesSoutenuesType> dureeActivitesSoutenuesType = dureeActivitesSoutenuesTypeRepository.findById(userCreationRequest.getIdDureeActivitesSoutenuesType());
        if (dureeActivitesSoutenuesType.isPresent()) {
            appUser.setDureeActivitesSoutenuesType(dureeActivitesSoutenuesType.get());

        }
        Optional<DureeActivitesModeresType> dureeActivitesModeresType = dureeActivitesModeresTypeRepository.findById(userCreationRequest.getIdDureeActivitesModeresType());

        if (dureeActivitesModeresType.isPresent()) {
            appUser.setDureeActivitesModeresType(dureeActivitesModeresType.get());

        }
        Set<ActivitesPreferencesType> actPrefList = new HashSet<>();

        userCreationRequest.getActivitesPreferencesTypes().forEach(activitesPreferencesType ->{

            Optional<ActivitesPreferencesType> actPref = activitesPreferencesTypeRepository.findById(activitesPreferencesType.longValue());
            if (actPref.isPresent()){
                System.out.println(actPref.get());
                System.out.println(actPrefList.size());
                actPrefList.add(actPref.get());
                System.out.println(actPrefList.size());
            }
            appUser.setActivitesPreferencesTypes(actPrefList);

        });
        return appUserRepository.save(appUser);
    }

    @Override
    public AppAdmin addAppAdmin(String nom, String prenom, String email, String username, String password) {
        Optional<User> user = userRepository.findByUsername(username);

        if (user.isPresent()) {
            return null;
        }

        AppAdmin appAdmin = new AppAdmin();
        appAdmin.setUsername(username);
        appAdmin.setPassword(passwordEncoder.encode(password));
        appAdmin.setEnabled(true);
        appAdmin.setEmail(email);
        appAdmin.setNom(nom);
        appAdmin.setPrenom(prenom);
        Role appRole = roleRepository.findByRoleName(RoleDesignation.ADMIN.toString());
        appAdmin.setRole(appRole);
        return appAdminRepository.save(appAdmin);
    }

    @Override
    public AppAdmin updateAppAdmin(SignupAdminRequest signupAdminRequest, Long id) {
        Optional<AppAdmin> appAdmin = appAdminRepository.findById(id);
        if (appAdmin.isPresent()) {
            Optional<User> user = userRepository.findByUsername(signupAdminRequest.getUsername());
            if (!user.isPresent()) {
                appAdmin.get().setUsername(signupAdminRequest.getUsername());
            }
            appAdmin.get().setPassword(passwordEncoder.encode(signupAdminRequest.getPassword()));
            appAdmin.get().setEnabled(true);
            appAdmin.get().setEmail(signupAdminRequest.getEmail());
            appAdmin.get().setNom(signupAdminRequest.getNom());
            appAdmin.get().setPrenom(signupAdminRequest.getPrenom());
            return appAdmin.get();
        }
        return null;
    }

    @Override
    public List<AppAdmin> getAllAdmins() {
        Role role = roleRepository.findByRoleName(RoleDesignation.ADMIN.toString());
        return appAdminRepository.findAllByRoleAndIsEnabled(role, true);
    }

    @Override
    public boolean deleteAppAdmin(Long idAdmin) {
        Optional<AppAdmin> appAdmin = appAdminRepository.findById(idAdmin);
        if (appAdmin.isPresent()) {
            // articleRepository.deleteAllByAppAdmin(appAdmin.get());
            appAdmin.get().setEnabled(false);
            return true;
        }
        return false;
    }

    @Override
    public AppAdmin addAppSuperAdmin(String nom, String prenom, String email, String username, String password) {
        Optional<User> user = userRepository.findByUsername(username);

        if (user.isPresent()) {
            return null;
        }
        AppAdmin appAdmin = new AppAdmin();
        appAdmin.setUsername(username);
        appAdmin.setPassword(passwordEncoder.encode(password));
        appAdmin.setEnabled(true);
        appAdmin.setEmail(email);
        appAdmin.setNom(nom);
        appAdmin.setPrenom(prenom);
        Role appRole = roleRepository.findByRoleName(RoleDesignation.SUPERADMIN.toString());
        appAdmin.setRole(appRole);
        return appAdminRepository.save(appAdmin);
    }
}
