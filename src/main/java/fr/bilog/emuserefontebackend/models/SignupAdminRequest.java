package fr.bilog.emuserefontebackend.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignupAdminRequest {
    private String username;
    private String password;
    private String nom;
    private String prenom;
    private String email;
}
