package fr.bilog.emuserefontebackend.services.impl;

import fr.bilog.emuserefontebackend.entities.article.Article;
import fr.bilog.emuserefontebackend.entities.article.Highlight;
import fr.bilog.emuserefontebackend.entities.profiles.AppUser;
import fr.bilog.emuserefontebackend.entities.profiles.ApplicationType;
import fr.bilog.emuserefontebackend.entities.userProfile.ActivitesPreferencesType;
import fr.bilog.emuserefontebackend.models.*;
import fr.bilog.emuserefontebackend.repositories.AppUserRepository;
import fr.bilog.emuserefontebackend.repositories.ApplicationTypeRepository;
import fr.bilog.emuserefontebackend.repositories.ArticleRepository;
import fr.bilog.emuserefontebackend.repositories.HighlightRepository;
import fr.bilog.emuserefontebackend.services.AppUserService;
import fr.bilog.emuserefontebackend.specifications.UserSpeicification;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.function.Function;

@AllArgsConstructor
@Service
@Transactional
public class AppUserServiceImpl implements AppUserService {
    private AppUserRepository appUserRepository;
    private UserSpeicification userSpeicification;
    private ApplicationTypeRepository applicationTypeRepository;
    private ArticleRepository articleRepository;
    private PasswordEncoder passwordEncoder;
    private HighlightRepository highlightRepository;
    private ModelMapper modelMapper;
    @Override
    public List<AppUser> getAllUsers() {
      /*  Pageable pageable;
        if (sortBy != null) {
            pageable = PageRequest.of(page, 5, Sort.by(sortBy));
        } else {
            pageable = PageRequest.of(page, 5);
        }*/
        return appUserRepository.findAll();
    }


    @Override
    public AppUser updateAppUser(Long id, UserUpdateRequest userUpdateRequest) {
        Optional<AppUser> userData = appUserRepository.findById(id);
        if (userData.isPresent()) {
            userData.get().setEnabled(userUpdateRequest.isEnabled());
            userData.get().setSexe(userUpdateRequest.getSexe());
            userData.get().setDateNaissance(userUpdateRequest.getDateNaissance());
            userData.get().setUpdateDate(new Date());
            userData.get().setPoid(userUpdateRequest.getPoid());
            userData.get().setTaille(userUpdateRequest.getTaille());
            Optional<ApplicationType> applicationType = applicationTypeRepository.findById(userUpdateRequest.getIdApplicationType());
            if (applicationType.isPresent()) {
                userData.get().setApplicationType(applicationType.get());
            }
        }
        return userData.get();
    }



    @Override
    public PageDTO getFilteredUsers(int page, String sortBy, FiltreUserRequest filtreUserRequest) {
        Pageable pageable;
        if (sortBy != null) {
            pageable = PageRequest.of(page, 5, Sort.by(sortBy));
        } else {
            pageable = PageRequest.of(page, 5);
        }
        return new PageDTO(appUserRepository.findAll(userSpeicification.getUsers(filtreUserRequest), pageable));
    }

    @Override
    public PageDTO getArticles(int page, String sortBy) {
        Pageable pageable;
        if (sortBy != null) {
            pageable = PageRequest.of(page, 5, Sort.by(sortBy));
        } else {
            pageable = PageRequest.of(page, 5);
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<AppUser> appUser = appUserRepository.findByUsername(authentication.getName());
        if (appUser.isPresent() && appUser.get().getApplicationType() != null) {
            Page<Article> articles = articleRepository.findAllByApplicationTypesContains(appUser.get().getApplicationType(), pageable);
            Page<ArticleDto> dtoPage = articles.map(new Function<Article,ArticleDto>() {
                @Override
                public ArticleDto apply(Article article) {
                    ArticleDto dto = new ArticleDto();
                    dto.setId(article.getId());
                    dto.setTitle(article.getTitle());
                    dto.setDescription(article.getDescription());
                    dto.setUrl(article.getUrl());
                    dto.setImage(article.getImage());
                    Optional<Highlight> highlight=article.getHighlights().stream().filter(tmp -> tmp.getAppUser().getId() == appUser.get().getId()).findFirst();
                    if (highlight.isPresent()){
                        dto.setLiked(highlight.get().getLiked());
                    }else {
                        dto.setLiked(0);
                    }
                    return dto;
                }
            });
            return new PageDTO(dtoPage);
        } else {
            return new PageDTO(Page.empty());
        }
    }

    @Override
    public boolean updatActivitesPrefs(ActPrefUpdateRequest actPrefUpdateRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<AppUser> appUser = appUserRepository.findByUsername(authentication.getName());

        System.out.println(appUser.isPresent());
        System.out.println(appUser.get());

        Set<ActivitesPreferencesType> actPrefList = new HashSet<>();

        actPrefUpdateRequest.getActPrefsIds().forEach(actPref -> {
            ActivitesPreferencesType activitesPreferencesType = new ActivitesPreferencesType();
            activitesPreferencesType.setId(actPref.longValue());
            actPrefList.add(activitesPreferencesType);
        });

        if (appUser.isPresent()) {
            appUser.get().getActivitesPreferencesTypes().clear();
            appUser.get().setActivitesPreferencesTypes(actPrefList);
            System.out.println(appUser.get().getActivitesPreferencesTypes());
            return  true;
        }
        System.out.println(appUser.get().getActivitesPreferencesTypes());
        return false;

    }

    @Override
    public boolean updatePhysionomy(PhysionomyUpdateRequest physionomyUpdateRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<AppUser> appUser = appUserRepository.findByUsername(authentication.getName());

        System.out.println(appUser.isPresent());
        System.out.println(appUser.get());

        if (appUser.isPresent()) {

            if (physionomyUpdateRequest.getPoid() != 0f){
                appUser.get().setPoid(physionomyUpdateRequest.getPoid());
            }

            if (physionomyUpdateRequest.getTaille() != 0f){
                appUser.get().setTaille(physionomyUpdateRequest.getTaille());
            }
            return  true;
        }
        System.out.println(appUser.get().getActivitesPreferencesTypes());
        return false;

    }

    @Override
    public boolean updatePassword(PasswordUpdateRequest passwordUpdateRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<AppUser> appUser = appUserRepository.findByUsername(authentication.getName());

        if (appUser.isPresent()) {
            if(!passwordUpdateRequest.getPassword().equals("")){
                appUser.get().setPassword(passwordEncoder.encode(passwordUpdateRequest.getPassword()));
            }

            return  true;
        }

        return false;

    }

    @Override
    public Highlight pushHighlight(PushHighlightRequest pushHighlightRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<AppUser> appUser = appUserRepository.findByUsername(authentication.getName());
        Optional<Article> article = articleRepository.findById(pushHighlightRequest.getIdArticle());
        if (appUser.isPresent() && article.isPresent()){
            Optional<Highlight> highlightTmp = highlightRepository.findByArticleAndAppUser(article.get(),appUser.get());
            if (highlightTmp.isPresent()){
                highlightTmp.get().setLiked(pushHighlightRequest.getLike());
                highlightTmp.get().setViewed(pushHighlightRequest.getView());
                highlightTmp.get().setShared(pushHighlightRequest.getShare());
                return highlightTmp.get();
            }else {
                Highlight highlight = new Highlight();
                highlight.setLiked(pushHighlightRequest.getLike());
                highlight.setViewed(pushHighlightRequest.getView());
                highlight.setShared(pushHighlightRequest.getShare());
                highlight.setAppUser(appUser.get());
                highlight.setArticle(article.get());
                highlight =  highlightRepository.save(highlight);
                appUser.get().getHighlights().add(highlight);
                article.get().getHighlights().add(highlight);
                return highlight;
            }
        }else{
            return null;
        }

    }


}
