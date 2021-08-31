package fr.bilog.emuserefontebackend.specifications;

import fr.bilog.emuserefontebackend.entities.profiles.AppUser;
import fr.bilog.emuserefontebackend.entities.profiles.ApplicationType;
import fr.bilog.emuserefontebackend.entities.userProfile.DureeActivitesModeresType;
import fr.bilog.emuserefontebackend.entities.userProfile.DureeActivitesSoutenuesType;
import fr.bilog.emuserefontebackend.models.FiltreUserRequest;
import fr.bilog.emuserefontebackend.repositories.ApplicationTypeRepository;
import fr.bilog.emuserefontebackend.repositories.DureeActivitesModeresTypeRepository;
import fr.bilog.emuserefontebackend.repositories.DureeActivitesSoutenuesTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.Predicate;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
public class UserSpeicification {

    @Autowired
    private ApplicationTypeRepository applicationTypeRepository;

    @Autowired
    private DureeActivitesModeresTypeRepository dureeActivitesModeresTypeRepository;

    @Autowired
    private DureeActivitesSoutenuesTypeRepository dureeActivitesSoutenuesTypeRepository;


    public Specification<AppUser> getUsers(FiltreUserRequest filtreUserRequest) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (filtreUserRequest.getApplicationTypeId() != null) {
                Optional<ApplicationType> applicationType = applicationTypeRepository.findById(filtreUserRequest.getApplicationTypeId());
                if (applicationType.isPresent()) {
                    predicates.add(criteriaBuilder.equal(root.get("applicationType"), applicationType.get()));
                }
            }

            if (filtreUserRequest.getDureeActivitesModeresTypeId() != null) {
                Optional<DureeActivitesModeresType> dureeActivitesModeresType = dureeActivitesModeresTypeRepository.findById(filtreUserRequest.getDureeActivitesModeresTypeId());
                if (dureeActivitesModeresType.isPresent()) {
                    predicates.add(criteriaBuilder.equal(root.get("dureeActivitesModeresType"), dureeActivitesModeresType.get()));
                }
            }

            if (filtreUserRequest.getDureeActivitesSoutenuesTypeId() != null) {
                Optional<DureeActivitesSoutenuesType> dureeActivitesSoutenuesType = dureeActivitesSoutenuesTypeRepository.findById(filtreUserRequest.getDureeActivitesSoutenuesTypeId());
                if (dureeActivitesSoutenuesType.isPresent()) {
                    predicates.add(criteriaBuilder.equal(root.get("dureeActivitesSoutenuesType"), dureeActivitesSoutenuesType.get()));
                }
            }


            if (filtreUserRequest.getTaille() != 0f) {

                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("taille"), filtreUserRequest.getTaille()));

            }

            if (filtreUserRequest.getPoid() != 0f) {

                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("poid"), filtreUserRequest.getPoid()));

            }
            if (filtreUserRequest.getSexe() != null && !filtreUserRequest.getSexe().isEmpty()) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("sexe")),
                        "%" + filtreUserRequest.getSexe().toLowerCase() + "%"));
            }


            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");


            if (filtreUserRequest.getCreationDate() != null && !filtreUserRequest.getCreationDate().isEmpty()) {
                Date creationDate = null;
                try {
                    creationDate = format.parse(filtreUserRequest.getCreationDate());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                predicates.add(criteriaBuilder.greaterThan(root.get("creationDate"), creationDate));
            }
            if (filtreUserRequest.getUpdateDate() != null && !filtreUserRequest.getUpdateDate().isEmpty()) {
                Date updateDate = null;
                try {
                    updateDate = format.parse(filtreUserRequest.getUpdateDate());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                predicates.add(criteriaBuilder.greaterThan(root.get("updateDate"), updateDate));
            }

            if (filtreUserRequest.getDateNaissance() != null && !filtreUserRequest.getDateNaissance().isEmpty()) {
                Date dateNaissance = null;
                try {
                    dateNaissance = format.parse(filtreUserRequest.getDateNaissance());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                predicates.add(criteriaBuilder.greaterThan(root.get(""), dateNaissance));
            }


            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }


}
