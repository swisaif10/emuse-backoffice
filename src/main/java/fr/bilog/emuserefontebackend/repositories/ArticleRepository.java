package fr.bilog.emuserefontebackend.repositories;

import fr.bilog.emuserefontebackend.entities.article.Article;
import fr.bilog.emuserefontebackend.entities.article.Departement;
import fr.bilog.emuserefontebackend.entities.article.Provenance;
import fr.bilog.emuserefontebackend.entities.profiles.AppAdmin;
import fr.bilog.emuserefontebackend.entities.profiles.ApplicationType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long>, JpaSpecificationExecutor<Article> {
    Page<Article> findAll(Specification<Article> articleSpecification, Pageable pageable);

    List<Article> findAllByAppAdmin(AppAdmin appAdmin);

    void deleteAllByAppAdmin(AppAdmin appAdmin);

    List<Article> findAllByProvenance(Provenance provenance);

    List<Article> findAllByDepartement(Departement departement);

    Page<Article> findAllByApplicationTypesContains(ApplicationType applicationType, Pageable pageable);
}
