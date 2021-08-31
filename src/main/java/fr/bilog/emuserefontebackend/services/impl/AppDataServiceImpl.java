package fr.bilog.emuserefontebackend.services.impl;

import fr.bilog.emuserefontebackend.entities.article.Article;
import fr.bilog.emuserefontebackend.entities.article.Departement;
import fr.bilog.emuserefontebackend.entities.article.Provenance;
import fr.bilog.emuserefontebackend.entities.article.Region;
import fr.bilog.emuserefontebackend.entities.profiles.ApplicationType;
import fr.bilog.emuserefontebackend.entities.profiles.Role;
import fr.bilog.emuserefontebackend.entities.userProfile.ActivitesPreferencesType;
import fr.bilog.emuserefontebackend.entities.userProfile.DureeActivitesModeresType;
import fr.bilog.emuserefontebackend.entities.userProfile.DureeActivitesSoutenuesType;
import fr.bilog.emuserefontebackend.entities.userProfile.SignesCliniqueType;
import fr.bilog.emuserefontebackend.models.AppAdminData;
import fr.bilog.emuserefontebackend.models.DepartementRequest;
import fr.bilog.emuserefontebackend.models.ProvenanceRequest;
import fr.bilog.emuserefontebackend.models.RegionRequest;
import fr.bilog.emuserefontebackend.repositories.*;
import fr.bilog.emuserefontebackend.services.AppDataService;
import fr.bilog.emuserefontebackend.utilities.FileUtilities;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
@Transactional
public class AppDataServiceImpl implements AppDataService {
    private RoleRepository roleRepository;
    private ActivitesPreferencesTypeRepository activitesPreferencesTypeRepository;
    private DureeActivitesModeresTypeRepository dureeActivitesModeresTypeRepository;
    private DureeActivitesSoutenuesTypeRepository dureeActivitesSoutenuesTypeRepository;
    private DepartementRepository departementRepository;
    private RegionRepository regionRepository;
    private ProvenanceRepository provenanceRepository;
    private ArticleRepository articleRepository;
    private ApplicationTypeRepository applicationTypeRepository;
    private SignesCliniqueTypeRepository signesCliniqueTypeRepository;

    @Override
    public AppAdminData initAppAdminData() {
        AppAdminData appAdminData = new AppAdminData();
        appAdminData.setApplicationTypes(applicationTypeRepository.findAll());
        appAdminData.setActivitesSoutenuesTypes(dureeActivitesSoutenuesTypeRepository.findAll());
        appAdminData.setActivitesModeresTypes(dureeActivitesModeresTypeRepository.findAll());
        appAdminData.setActivitesPreferencesTypes(activitesPreferencesTypeRepository.findAll());
        appAdminData.setDepartements(departementRepository.findAll());
        appAdminData.setProvenances(provenanceRepository.findAll());
        appAdminData.setRegions(regionRepository.findAll());
        return appAdminData;
    }

    @Override
    public Role addRole(String roleName) {
        return roleRepository.save(new Role(null, roleName));
    }

    @Override
    public void addActivitesPreferencesType(String libelle) {
        activitesPreferencesTypeRepository.save(new ActivitesPreferencesType(null, libelle, new Date()));
    }

    @Override
    public void addDureeActivitesModeresType(String libelle) {
        dureeActivitesModeresTypeRepository.save(new DureeActivitesModeresType(null, libelle, new Date()));
    }

    @Override
    public void addDureeActivitesSoutenuesType(String libelle) {
        dureeActivitesSoutenuesTypeRepository.save(new DureeActivitesSoutenuesType(null, libelle, new Date()));

    }

    @Override
    public Provenance addPovenance(ProvenanceRequest provenanceRequest) {
        Provenance provenance = new Provenance();
        provenance.setLibelle(provenanceRequest.getLibelle());
        provenance = provenanceRepository.save(provenance);
        if (provenanceRequest.getFile() != null) {
            String imageFileName = provenance.getId().toString() + provenanceRequest.getFile().getOriginalFilename().substring(provenanceRequest.getFile().getOriginalFilename().lastIndexOf(".")).trim();
            FileUtilities.saveProvenanceImage(imageFileName, provenanceRequest.getFile());
            String baseUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .replacePath(null)
                    .build()
                    .toUriString();
            provenance.setImage(baseUrl + "/staticfiles/images/provenance/" + imageFileName);
        }
        return provenance;
    }

    @Override
    public Provenance updateProvenance(ProvenanceRequest provenanceRequest, Long idProvenance) {
        Optional<Provenance> provenance = provenanceRepository.findById(idProvenance);
        if (provenance.isPresent()) {
            provenance.get().setLibelle(provenanceRequest.getLibelle());
            if (provenanceRequest.getFile() != null) {
                String imageFileName = provenance.get().getId().toString() + provenanceRequest.getFile().getOriginalFilename().substring(provenanceRequest.getFile().getOriginalFilename().lastIndexOf(".")).trim();
                FileUtilities.saveProvenanceImage(imageFileName, provenanceRequest.getFile());
                String baseUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                        .replacePath(null)
                        .build()
                        .toUriString();
                provenance.get().setImage(baseUrl + "/staticfiles/images/provenance/" + imageFileName);
            }
            return provenance.get();
        }
        return null;
    }

    @Override
    public boolean deleteProvenance(Long idProvenance) {
        Optional<Provenance> provenance = provenanceRepository.findById(idProvenance);
        if (provenance.isPresent()) {
            if (provenance.get().getImage() != null && !provenance.get().getImage().isEmpty()) {
                FileUtilities.deleteProvenanceImage(provenance.get().getId() + provenance.get().getImage().substring(provenance.get().getImage().lastIndexOf(".")).trim());
            }
            List<Article> articles = articleRepository.findAllByProvenance(provenance.get());
            articles.forEach(article -> {
                article.setProvenance(null);
            });
            provenanceRepository.delete(provenance.get());
            return true;
        }
        return false;
    }

    @Override
    public Departement addDepartement(DepartementRequest departementRequest) {
        Departement departement = new Departement();
        departement.setLibelle(departementRequest.getLibelle());
        if (departementRequest.getIdRegion() != null) {
            Optional<Region> region = regionRepository.findById(departementRequest.getIdRegion());
            if (region.isPresent()) {
                departement.setRegion(region.get());
            }
        }
        return departementRepository.save(departement);
    }

    @Override
    public Departement updateDepartement(DepartementRequest departementRequest, Long idDepartement) {
        Optional<Departement> departement = departementRepository.findById(idDepartement);
        if (departement.isPresent()) {
            if (departementRequest.getLibelle() != null) {
                departement.get().setLibelle(departementRequest.getLibelle());
            }
            if (departementRequest.getIdRegion() != null) {
                Optional<Region> region = regionRepository.findById(departementRequest.getIdRegion());
                if (region.isPresent()) {
                    departement.get().setRegion(region.get());
                }
            }
            return departement.get();
        }
        return null;
    }

    @Override
    public boolean deleteDepartement(Long idDepartement) {
        Optional<Departement> departement = departementRepository.findById(idDepartement);
        if (departement.isPresent()) {
            List<Article> articles = articleRepository.findAllByDepartement(departement.get());
            articles.forEach(article -> {
                article.setDepartement(null);
            });
            departementRepository.delete(departement.get());
            return true;
        }
        return false;
    }

    @Override
    public Region addRegion(RegionRequest regionRequest) {
        Region region = new Region();
        region.setLibelle(regionRequest.getLibelle());
        region.setSupported(regionRequest.isSupported());
        return regionRepository.save(region);
    }

    @Override
    public Region updateRegion(RegionRequest regionRequest, Long idRegion) {
        Optional<Region> region = regionRepository.findById(idRegion);
        if (region.isPresent()) {
            if (regionRequest.getLibelle() != null) {
                region.get().setLibelle(regionRequest.getLibelle());
            }
            region.get().setSupported(regionRequest.isSupported());
            return region.get();
        }
        return null;
    }

    @Override
    public boolean deleteRegion(Long idRegion) {
        Optional<Region> region = regionRepository.findById(idRegion);
        if (region.isPresent()) {
            List<Departement> departements = departementRepository.findAllByRegion(region.get());
            departements.forEach(departement -> {
                departement.setRegion(null);
            });
            regionRepository.delete(region.get());
            return true;
        }
        return false;
    }

    @Override
    public ApplicationType addApplicationType(String libelle) {
        ApplicationType applicationType = new ApplicationType();
        applicationType.setLibelle(libelle);
        return applicationTypeRepository.save(applicationType);
    }

    @Override
    public ApplicationType updateApplicationType(String libelle, Long idApplicationType) {
        Optional<ApplicationType> applicationType = applicationTypeRepository.findById(idApplicationType);
        if (applicationType.isPresent()) {
            if (applicationType.get().getLibelle() != null) {
                applicationType.get().setLibelle(libelle);
            }
            return applicationType.get();
        }
        return null;
    }

    @Override
    public boolean deleteApplicationType(Long idApplicationType) {
        Optional<ApplicationType> applicationType = applicationTypeRepository.findById(idApplicationType);
        if (applicationType.isPresent()) {
            applicationTypeRepository.delete(applicationType.get());
            return true;
        }
        return false;
    }

    @Override
    public void addSignesCiniquesType(String libelle) {
        signesCliniqueTypeRepository.save(new SignesCliniqueType(null,libelle));
    }
}
