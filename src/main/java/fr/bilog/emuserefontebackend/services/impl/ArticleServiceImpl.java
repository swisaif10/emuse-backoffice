package fr.bilog.emuserefontebackend.services.impl;


import fr.bilog.emuserefontebackend.entities.article.Article;
import fr.bilog.emuserefontebackend.entities.article.Departement;
import fr.bilog.emuserefontebackend.entities.article.Provenance;
import fr.bilog.emuserefontebackend.entities.profiles.AppAdmin;
import fr.bilog.emuserefontebackend.entities.profiles.ApplicationType;
import fr.bilog.emuserefontebackend.entities.userProfile.ActivitesPreferencesType;
import fr.bilog.emuserefontebackend.models.ArticleCreationRequest;
import fr.bilog.emuserefontebackend.models.FiltreArticleRquest;
import fr.bilog.emuserefontebackend.models.PageDTO;
import fr.bilog.emuserefontebackend.repositories.*;
import fr.bilog.emuserefontebackend.services.ArticleService;
import fr.bilog.emuserefontebackend.specifications.ArticleSpecification;
import fr.bilog.emuserefontebackend.utilities.FileUtilities;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.transaction.Transactional;
import java.util.*;

@AllArgsConstructor
@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {

    private ArticleRepository articleRepository;
    private ArticleSpecification articleSpecification;
    private DepartementRepository departementRepository;
    private ProvenanceRepository provenanceRepository;
    private AppAdminRepository appAdminRepository;
    private ApplicationTypeRepository applicationTypeRepository;

    @Override
    public Article addArticle(ArticleCreationRequest articleCreationRequest) {

        Article article = new Article();
        article.setTitle(articleCreationRequest.getTitle());
        article.setDescription(articleCreationRequest.getDesc());
        article.setUrl(articleCreationRequest.getUrl());
        article.setStatus(0);
        article.setImage("");
        article.setCreationDate(new Date());
        article.setModificationDate(new Date());
        article.setPublicationDate(new Date());
        if (articleCreationRequest.getIdProvenance() != null) {
            Optional<Provenance> provenance = provenanceRepository.findById(articleCreationRequest.getIdProvenance());
            if (provenance.isPresent()) {
                article.setProvenance(provenance.get());
                //System.out.println(provenance.get().getArticles().size());
                // provenance.get().getArticles().add(article);
                //   System.out.println();
            }
        }
        if (articleCreationRequest.getIdDepartement() != null) {
            Optional<Departement> departement = departementRepository.findById(articleCreationRequest.getIdDepartement());
            if (departement.isPresent()) {
                article.setDepartement(departement.get());
                //  departement.get().getArticles().add(article);
            }
        }
        if (articleCreationRequest.getApplicationTypesIds() != null && !articleCreationRequest.getApplicationTypesIds().isEmpty()) {
            Set<ApplicationType> applicationTypes = new HashSet<>();
            articleCreationRequest.getApplicationTypesIds().forEach(applicationTypesId ->{
                Optional<ApplicationType> applicationType = applicationTypeRepository.findById(applicationTypesId.longValue());
                if (applicationType.isPresent()) {
                    applicationTypes.add(applicationType.get());
                }
            });
            article.setApplicationTypes(applicationTypes);
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<AppAdmin> appAdmin = appAdminRepository.findByUsername(authentication.getName());
        if (appAdmin.isPresent()) {
            article.setAppAdmin(appAdmin.get());
        }
        article = articleRepository.save(article);
        if (articleCreationRequest.getFile() != null) {
            String imageFileName = article.getId().toString() + articleCreationRequest.getFile().getOriginalFilename().substring(articleCreationRequest.getFile().getOriginalFilename().lastIndexOf(".")).trim();
            FileUtilities.saveArticleImage(imageFileName, articleCreationRequest.getFile());
            String baseUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .replacePath(null)
                    .build()
                    .toUriString();
            article.setImage(baseUrl + "/staticfiles/images/article/" + imageFileName);
        }
        return article;
    }

    @Override
    public List<Article> getArticles() {
        /*Pageable pageable;
        if (sortBy != null) {
            pageable = PageRequest.of(page, 5, Sort.by(sortBy));
        } else {
            pageable = PageRequest.of(page, 5);
        }*/
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<AppAdmin> appAdmin = appAdminRepository.findByUsername(authentication.getName());
        if (appAdmin.isPresent()) {
            return articleRepository.findAllByAppAdmin(appAdmin.get());
        }
        return new ArrayList<>();
    }

    @Override
    public PageDTO getFilteredArticles(int page, String sortBy, FiltreArticleRquest filtreArticleRquest) {
        Pageable pageable;
        if (sortBy != null) {
            pageable = PageRequest.of(page, 5, Sort.by(sortBy));
        } else {
            pageable = PageRequest.of(page, 5);
        }
        return new PageDTO(articleRepository.findAll(articleSpecification.getArticles(filtreArticleRquest), pageable));
    }

    public Article updateArticle(Long id, ArticleCreationRequest articleCreationRequest) {
        Optional<Article> articleData = articleRepository.findById(id);

        if (articleData.isPresent()) {
            if (articleCreationRequest.getIdProvenance() != null) {
                Optional<Provenance> provenance = provenanceRepository.findById(articleCreationRequest.getIdProvenance());
                if (provenance.isPresent()) {
                    articleData.get().setProvenance(provenance.get());
                }
            }
            if (articleCreationRequest.getIdDepartement() != null) {
                Optional<Departement> departement = departementRepository.findById(articleCreationRequest.getIdDepartement());
                if (departement.isPresent()) {
                    articleData.get().setDepartement(departement.get());
                }
            }
            if (articleCreationRequest.getApplicationTypesIds() != null && !articleCreationRequest.getApplicationTypesIds().isEmpty()) {
                Set<ApplicationType> applicationTypes = new HashSet<>();
                articleCreationRequest.getApplicationTypesIds().forEach(applicationTypesId ->{
                    Optional<ApplicationType> applicationType = applicationTypeRepository.findById(applicationTypesId.longValue());
                    if (applicationType.isPresent()) {
                        applicationTypes.add(applicationType.get());
                    }
                });
                articleData.get().setApplicationTypes(applicationTypes);
            }
            articleData.get().setTitle(articleCreationRequest.getTitle());
            articleData.get().setUrl(articleCreationRequest.getUrl());
            articleData.get().setDescription(articleCreationRequest.getDesc());
            if (articleCreationRequest.getFile() != null) {
                String imageFileName = articleData.get().getId().toString() + articleCreationRequest.getFile().getOriginalFilename().substring(articleCreationRequest.getFile().getOriginalFilename().lastIndexOf(".")).trim();
                FileUtilities.saveArticleImage(imageFileName, articleCreationRequest.getFile());
                String baseUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                        .replacePath(null)
                        .build()
                        .toUriString();
                articleData.get().setImage(baseUrl + "/staticfiles/images/article/" + imageFileName);
            }
        }
        return articleData.get();


    }

    @Override
    public boolean deleteArticle(Long idArticle) {
        Optional<Article> article = articleRepository.findById(idArticle);
        if (article.isPresent()) {
            if (article.get().getImage() != null && !article.get().getImage().isEmpty()) {
                FileUtilities.deleteArticleImage(article.get().getId() + article.get().getImage().substring(article.get().getImage().lastIndexOf(".")).trim());
            }
            articleRepository.delete(article.get());
            return true;
        } else
            return false;
    }
}
