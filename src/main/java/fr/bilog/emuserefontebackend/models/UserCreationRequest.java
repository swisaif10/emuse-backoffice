package fr.bilog.emuserefontebackend.models;


import fr.bilog.emuserefontebackend.entities.userProfile.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCreationRequest {
    private String username;
     private String password ;
    private String sexe;
    private float taille;
    private float poid;
    private Date dateNaissance;
    private Long idDureeActivitesModeresType;
    private Long idDureeActivitesSoutenuesType;
    private List<Integer> activitesPreferencesTypes;
    private Long idApplicationType;
}
