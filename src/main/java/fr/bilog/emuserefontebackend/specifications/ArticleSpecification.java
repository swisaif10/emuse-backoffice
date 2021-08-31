package fr.bilog.emuserefontebackend.specifications;

import fr.bilog.emuserefontebackend.entities.article.Article;
import fr.bilog.emuserefontebackend.entities.article.Departement;
import fr.bilog.emuserefontebackend.entities.article.Provenance;
import fr.bilog.emuserefontebackend.entities.profiles.AppAdmin;
import fr.bilog.emuserefontebackend.models.FiltreArticleRquest;
import fr.bilog.emuserefontebackend.repositories.AppAdminRepository;
import fr.bilog.emuserefontebackend.repositories.DepartementRepository;
import fr.bilog.emuserefontebackend.repositories.ProvenanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
public class ArticleSpecification {
    @Autowired
    ProvenanceRepository provenanceRepository;
    @Autowired
    DepartementRepository departementRepository;
    @Autowired
    AppAdminRepository appAdminRepository;


    public Specification<Article> getArticles(FiltreArticleRquest filtreArticleRquest) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            Optional<AppAdmin> appAdmin = appAdminRepository.findByUsername(authentication.getName());
            if (appAdmin.isPresent()) {
                predicates.add(criteriaBuilder.equal(root.get("appAdmin"), appAdmin.get()));
            }

            if (filtreArticleRquest.getProvenanceId() != null) {
                Optional<Provenance> provenance = provenanceRepository.findById(filtreArticleRquest.getProvenanceId());
                if (provenance.isPresent()) {
                    predicates.add(criteriaBuilder.equal(root.get("provenance"), provenance.get()));
                }
            }
            if (filtreArticleRquest.getDepartementId() != null) {
                Optional<Departement> departement = departementRepository.findById(filtreArticleRquest.getDepartementId());
                if (departement.isPresent()) {
                    predicates.add(criteriaBuilder.equal(root.get("departement"), departement.get()));
                }
            }
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");

            if (filtreArticleRquest.getTitle() != null && !filtreArticleRquest.getTitle().isEmpty()) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("title")),
                        "%" + filtreArticleRquest.getTitle().toLowerCase() + "%"));
            }
            if (filtreArticleRquest.getDescription() != null && !filtreArticleRquest.getDescription().isEmpty()) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("description")),
                        "%" + filtreArticleRquest.getDescription().toLowerCase() + "%"));
            }
            if (filtreArticleRquest.getUrl() != null && !filtreArticleRquest.getUrl().isEmpty()) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("url")),
                        "%" + filtreArticleRquest.getUrl().toLowerCase() + "%"));
            }
            if (filtreArticleRquest.getCreationDate() != null && !filtreArticleRquest.getCreationDate().isEmpty()) {
                Date creationDate = null;
                try {
                    creationDate = format.parse(filtreArticleRquest.getCreationDate());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                predicates.add(criteriaBuilder.greaterThan(root.get("creationDate"), creationDate));
            }
            if (filtreArticleRquest.getExpirationDate() != null && !filtreArticleRquest.getExpirationDate().isEmpty()) {
                Date expirationDate = null;
                try {
                    expirationDate = format.parse(filtreArticleRquest.getExpirationDate());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                predicates.add(criteriaBuilder.greaterThan(root.get("expirationDate"), expirationDate));
            }
            if (filtreArticleRquest.getModificationDate() != null && !filtreArticleRquest.getModificationDate().isEmpty()) {
                Date modificationDate = null;
                try {
                    modificationDate = format.parse(filtreArticleRquest.getModificationDate());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                predicates.add(criteriaBuilder.greaterThan(root.get("modificationDate"), modificationDate));
            }
            if (filtreArticleRquest.getPublicationDate() != null && !filtreArticleRquest.getPublicationDate().isEmpty()) {
                Date publicationDate = null;
                try {
                    publicationDate = format.parse(filtreArticleRquest.getPublicationDate());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                predicates.add(criteriaBuilder.greaterThan(root.get("publicationDate"), publicationDate));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
