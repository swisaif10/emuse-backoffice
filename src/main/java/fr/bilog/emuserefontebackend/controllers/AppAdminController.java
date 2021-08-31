package fr.bilog.emuserefontebackend.controllers;

import fr.bilog.emuserefontebackend.entities.article.Article;
import fr.bilog.emuserefontebackend.entities.article.Provenance;
import fr.bilog.emuserefontebackend.entities.profiles.AppUser;
import fr.bilog.emuserefontebackend.models.*;
import fr.bilog.emuserefontebackend.services.AppDataService;
import fr.bilog.emuserefontebackend.services.AppUserService;
import fr.bilog.emuserefontebackend.services.ArticleService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/appAdmin")
public class AppAdminController {
    private ArticleService articleService;
    
    private AppUserService appUserService;
    private AppDataService appDataService;
    

    @GetMapping("/initAppAdminData")
    ResponseEntity<?> initData() {
        return ResponseEntity.ok().body(appDataService.initAppAdminData());
    }

    @GetMapping("/allUsers")
    ResponseEntity<?> getAllUsers() {
        return ResponseEntity.ok(appUserService.getAllUsers());
    }

    @GetMapping("/filtreUsers")
    ResponseEntity<?> getFilteredUsers(@RequestParam(defaultValue = "0", required = false) int page, @RequestParam(required = false) String sortBy, FiltreUserRequest filtreUserRequest) {
        return ResponseEntity.ok(appUserService.getFilteredUsers(page, sortBy, filtreUserRequest));
    }

    @PutMapping("/updateUser/{id}")
    ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody UserUpdateRequest userUpdateRequest) {
        AppUser appUser = appUserService.updateAppUser(id, userUpdateRequest);
        return ResponseEntity.ok(appUser);
    }

    @GetMapping("/getAllArticles")
    ResponseEntity<?> getArticles() {
        
        return ResponseEntity.ok(articleService.getArticles());
    }

    @PostMapping(value = "/addArticle")
    ResponseEntity<?> addArticle(ArticleCreationRequest articleCreationRequest) {
        Article article = articleService.addArticle(articleCreationRequest);
        if (article != null) {
            return ResponseEntity.ok().body(new HttpResponse("200", "Article ok"));
        } else {
            return ResponseEntity.badRequest().body(new HttpResponse("201", "Article ko"));
        }
    }

    @GetMapping("/filtreArticles")
    ResponseEntity<?> getFilteredArticles(@RequestParam(defaultValue = "0", required = false) int page, @RequestParam(required = false) String sortBy, FiltreArticleRquest filtreArticleRquest) {
        System.out.println(filtreArticleRquest.getTitle());
        return ResponseEntity.ok(articleService.getFilteredArticles(page, sortBy, filtreArticleRquest));
    }

    @PostMapping("/addProvenance")
    ResponseEntity<?> addProvenance(ProvenanceRequest provenanceRequest) {
        Provenance provenance = appDataService.addPovenance(provenanceRequest);
        if (provenance != null) {
            return ResponseEntity.ok().body(new HttpResponse("200", "provenance ok"));
        } else {
            return ResponseEntity.badRequest().body(new HttpResponse("201", "provenance ko"));
        }
    }

    @PutMapping(value = "/updateArticle/{id}")
    ResponseEntity<?> updateArticle(@PathVariable Long id, ArticleCreationRequest articleCreationRequest) {
        Article article = articleService.updateArticle(id, articleCreationRequest);
        return ResponseEntity.ok(article);
    }

    @PutMapping("/deleteArticle/{id}")
    ResponseEntity<?> deleteArticle(@PathVariable Long id) {
        if (articleService.deleteArticle(id)) {
            return ResponseEntity.ok("delete successfully");
        } else {
            return ResponseEntity.badRequest().body("error deleting");
        }
    }

}
