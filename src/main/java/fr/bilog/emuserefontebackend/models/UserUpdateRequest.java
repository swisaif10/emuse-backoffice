package fr.bilog.emuserefontebackend.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserUpdateRequest {

    private boolean isEnabled;
    private float taille;
    private float poid;
    private Date dateNaissance;
    private Date updateDate;
    private String sexe;
    private Long idApplicationType;


}
