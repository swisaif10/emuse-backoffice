package fr.bilog.emuserefontebackend.controllers;

import fr.bilog.emuserefontebackend.entities.article.Highlight;
import fr.bilog.emuserefontebackend.entities.userProfile.FCMToken;
import fr.bilog.emuserefontebackend.models.*;
import fr.bilog.emuserefontebackend.services.AppUserService;
import fr.bilog.emuserefontebackend.services.FCMTokenService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/appUser")
public class AppUserController {
    private FCMTokenService FCMTokenService;
    private AppUserService appUserService;

    @GetMapping("/initData")
    ResponseEntity<?> initDataAppUser() {
        return ResponseEntity.ok().body("");
    }

    @PostMapping("/setFcmId")
    ResponseEntity<?> setFcmId(FcmIdRequest fcmIdRequest) {
        FCMToken fcmToken = FCMTokenService.setFcmToken(fcmIdRequest);
        if (fcmToken != null) {
            return ResponseEntity.ok().body(new HttpResponse("200", "FcmToken add successful"));
        } else {
            return ResponseEntity.ok().body(new HttpResponse("201", "FcmToken add error"));
        }
    }

    @GetMapping("/getArtilces")
    ResponseEntity<?> getArticles(@RequestParam(defaultValue = "0", required = false) int page, @RequestParam(required = false) String sortBy) {
        return ResponseEntity.ok().body(appUserService.getArticles(page, sortBy));
    }
    @PutMapping("/updateActivitiesPrefs")
    ResponseEntity<?> updateActivitiesPrefs(@RequestBody ActPrefUpdateRequest actPrefUpdateRequest) {

        if (appUserService.updatActivitesPrefs(actPrefUpdateRequest)) {
            return ResponseEntity.ok("updated successfully");
        } else {
            return ResponseEntity.badRequest().body("error updating " );
        }
    }

    @PutMapping("/updatePhysionomy")
    ResponseEntity<?> updatePhysionomy(@RequestBody PhysionomyUpdateRequest physionomyUpdateRequest) {

        if (appUserService.updatePhysionomy(physionomyUpdateRequest)) {
            return ResponseEntity.ok("updated successfully");
        } else {
            return ResponseEntity.badRequest().body("error updating " );
        }
    }

    @PutMapping("/updatePassword")
    ResponseEntity<?> updatePassword(@RequestBody PasswordUpdateRequest passwordUpdateRequest) {

        if (appUserService.updatePassword(passwordUpdateRequest)) {
            return ResponseEntity.ok("updated successfully");
        } else {
            return ResponseEntity.badRequest().body("error updating " );
        }
    }
   @PostMapping("/pushHighlight")
    ResponseEntity<?> pushHighlight(@RequestBody PushHighlightRequest pushHighlightRequest) {
        Highlight highlight = appUserService.pushHighlight(pushHighlightRequest);
        if (highlight != null) {
            return ResponseEntity.ok("highlight successfully");
        } else {
            return ResponseEntity.badRequest().body("error highlight " );
        }
    }

}
