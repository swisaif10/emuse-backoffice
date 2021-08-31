package fr.bilog.emuserefontebackend.services;

import fr.bilog.emuserefontebackend.entities.article.Departement;
import fr.bilog.emuserefontebackend.entities.article.Provenance;
import fr.bilog.emuserefontebackend.entities.article.Region;
import fr.bilog.emuserefontebackend.entities.profiles.ApplicationType;
import fr.bilog.emuserefontebackend.entities.profiles.Role;
import fr.bilog.emuserefontebackend.entities.userProfile.SignesCliniqueType;
import fr.bilog.emuserefontebackend.models.AppAdminData;
import fr.bilog.emuserefontebackend.models.DepartementRequest;
import fr.bilog.emuserefontebackend.models.ProvenanceRequest;
import fr.bilog.emuserefontebackend.models.RegionRequest;

public interface AppDataService {

    AppAdminData initAppAdminData();

    Role addRole(String roleName);

    void addActivitesPreferencesType(String libelle);

    void addDureeActivitesModeresType(String libelle);

    void addDureeActivitesSoutenuesType(String libelle);

    Provenance addPovenance(ProvenanceRequest provenanceRequest);

    Provenance updateProvenance(ProvenanceRequest provenanceRequest, Long idProvenance);

    boolean deleteProvenance(Long idProvenance);


    Departement addDepartement(DepartementRequest departementRequest);

    Departement updateDepartement(DepartementRequest departementRequest, Long idDepartement);

    boolean deleteDepartement(Long idProvenance);

    Region addRegion(RegionRequest regionRequest);

    Region updateRegion(RegionRequest regionRequest, Long idRegion);

    boolean deleteRegion(Long idRegion);

    ApplicationType addApplicationType(String libelle);

    ApplicationType updateApplicationType(String libelle, Long idApplicationType);

    boolean deleteApplicationType(Long idApplicationType);

    void addSignesCiniquesType(String libelle);
}
