package fr.bilog.emuserefontebackend.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FiltreUserRequest {

    private String sexe;
    private float taille;
    private float poid;
    private String dateNaissance;
    private String creationDate;
    private String updateDate;
    private Long applicationTypeId;
    private Long dureeActivitesModeresTypeId;
    private Long dureeActivitesSoutenuesTypeId;
}
