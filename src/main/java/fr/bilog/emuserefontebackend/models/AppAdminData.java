package fr.bilog.emuserefontebackend.models;

import fr.bilog.emuserefontebackend.entities.article.Departement;
import fr.bilog.emuserefontebackend.entities.article.Provenance;
import fr.bilog.emuserefontebackend.entities.article.Region;
import fr.bilog.emuserefontebackend.entities.profiles.ApplicationType;
import fr.bilog.emuserefontebackend.entities.userProfile.ActivitesPreferencesType;
import fr.bilog.emuserefontebackend.entities.userProfile.DureeActivitesModeresType;
import fr.bilog.emuserefontebackend.entities.userProfile.DureeActivitesSoutenuesType;
import lombok.Data;

import java.util.List;

@Data

public class AppAdminData {
    private List<ApplicationType> applicationTypes;
    private List<Provenance> provenances;
    private List<Departement> departements;
    private List<Region> regions;
    private List<ActivitesPreferencesType> activitesPreferencesTypes;
    private List<DureeActivitesSoutenuesType> activitesSoutenuesTypes;
    private List<DureeActivitesModeresType> activitesModeresTypes;

}
