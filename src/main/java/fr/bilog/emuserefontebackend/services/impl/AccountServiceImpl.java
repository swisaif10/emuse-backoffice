package fr.bilog.emuserefontebackend.services.impl;

import fr.bilog.emuserefontebackend.entities.AppAdmin;
import fr.bilog.emuserefontebackend.entities.AppUser;
import fr.bilog.emuserefontebackend.entities.Role;
import fr.bilog.emuserefontebackend.entities.User;
import fr.bilog.emuserefontebackend.models.RoleDesignation;
import fr.bilog.emuserefontebackend.repositories.AppAdminRepository;
import fr.bilog.emuserefontebackend.repositories.AppUserRepository;
import fr.bilog.emuserefontebackend.repositories.RoleRepository;
import fr.bilog.emuserefontebackend.repositories.UserRepository;
import fr.bilog.emuserefontebackend.services.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@AllArgsConstructor
@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    private AppUserRepository appUserRepository;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private AppAdminRepository appAdminRepository;

    @Override
    public Role addRole(String roleName) {

        return roleRepository.save(new Role(null, roleName));
    }
    @Override
    public AppUser addAppUser(String username, String password) {
        Optional<User> user = userRepository.findByUsername(username);

        if (user.isPresent()) {
            return null;
        }

        AppUser appUser = new AppUser();
        appUser.setUsername(username);
        appUser.setPassword(passwordEncoder.encode(password));
        appUser.setEnabled(true);
        String str_date = "2021-08-16";
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = formatter.parse(str_date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println(date);
        appUser.setDateNaissance(date);
        appUser.setUpdateDate(new Date());
        appUser.setCreationDate(new Date());
        Role appRole = roleRepository.findByRoleName(RoleDesignation.USER.toString());
        appUser.setRole(appRole);

        return  appUserRepository.save(appUser);
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
        appAdminRepository.save(appAdmin);
        return appAdminRepository.save(appAdmin);
    }
}
