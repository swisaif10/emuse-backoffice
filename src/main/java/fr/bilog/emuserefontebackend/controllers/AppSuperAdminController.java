package fr.bilog.emuserefontebackend.controllers;

import fr.bilog.emuserefontebackend.entities.article.Departement;
import fr.bilog.emuserefontebackend.entities.article.Provenance;
import fr.bilog.emuserefontebackend.entities.article.Region;
import fr.bilog.emuserefontebackend.entities.profiles.AppAdmin;
import fr.bilog.emuserefontebackend.entities.profiles.ApplicationType;
import fr.bilog.emuserefontebackend.models.*;
import fr.bilog.emuserefontebackend.services.AccountService;
import fr.bilog.emuserefontebackend.services.AppDataService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/appSuperAdmin")
public class AppSuperAdminController {
    private AccountService accountService;
    private AppDataService appDataService;

    @PostMapping("/addProvenance")
    ResponseEntity<?> addProvenance(ProvenanceRequest provenanceRequest) {
        Provenance provenance = appDataService.addPovenance(provenanceRequest);
        if (provenance != null) {
            return ResponseEntity.ok().body(new HttpResponse("200", "provenance ok"));
        } else {
            return ResponseEntity.badRequest().body(new HttpResponse("201", "provenance ko"));
        }
    }


    @PutMapping(value = "/updateProvenance/{id}", consumes = {"multipart/form-data"})
    ResponseEntity<?> updateProvenance(@PathVariable Long id, @ModelAttribute ProvenanceRequest provenanceRequest) {
        Provenance provenance = appDataService.updateProvenance(provenanceRequest, id);
        if (provenance != null) {
            return ResponseEntity.ok(new HttpResponse("200", "provenance update successful"));
        } else {
            return ResponseEntity.ok(new HttpResponse("201", "provenance update error"));
        }
    }

    @PutMapping("/deleteProvenance/{id}")
    ResponseEntity<?> deleteProvenance(@PathVariable Long id) {
        if (appDataService.deleteProvenance(id)) {
            return ResponseEntity.ok(new HttpResponse("200", "provenance delete successful"));
        } else {
            return ResponseEntity.ok(new HttpResponse("201", "provenance delete error"));
        }
    }

    @PostMapping("/addDepartement")
    ResponseEntity<?> addDepartement(DepartementRequest departementRequest) {
        Departement departement = appDataService.addDepartement(departementRequest);
        if (departement != null) {
            return ResponseEntity.ok().body(new HttpResponse("200", "Departement add successful"));
        } else {
            return ResponseEntity.badRequest().body(new HttpResponse("201", "Departement add error"));
        }
    }

    @PutMapping(value = "/updateDepartement/{id}")
    ResponseEntity<?> updateDepartement(@PathVariable Long id, @ModelAttribute DepartementRequest departementRequest) {
        Departement departement = appDataService.updateDepartement(departementRequest, id);
        if (departement != null) {
            return ResponseEntity.ok(new HttpResponse("200", "Departement update successful"));
        } else {
            return ResponseEntity.ok(new HttpResponse("201", "Departement update error"));
        }
    }

    @PutMapping("/deleteDepartement/{id}")
    ResponseEntity<?> deleteDepartement(@PathVariable Long id) {
        if (appDataService.deleteDepartement(id)) {
            return ResponseEntity.ok(new HttpResponse("200", "Departement delete successful"));
        } else {
            return ResponseEntity.ok(new HttpResponse("201", "Departement delete error"));
        }
    }

    @PostMapping("/addRegion")
    ResponseEntity<?> addRegion(RegionRequest regionRequest) {
        Region region = appDataService.addRegion(regionRequest);
        if (region != null) {
            return ResponseEntity.ok().body(new HttpResponse("200", "Region add successful"));
        } else {
            return ResponseEntity.badRequest().body(new HttpResponse("201", "Region add error"));
        }
    }

    @PutMapping(value = "/updateRegion/{id}")
    ResponseEntity<?> updateRegion(@PathVariable Long id, @ModelAttribute RegionRequest regionRequest) {
        Region region = appDataService.updateRegion(regionRequest, id);
        if (region != null) {
            return ResponseEntity.ok(new HttpResponse("200", "Region update successful"));
        } else {
            return ResponseEntity.ok(new HttpResponse("201", "Region update error"));
        }
    }

    @PutMapping("/deleteRegion{id}")
    ResponseEntity<?> deleteRegion(@PathVariable Long id) {
        if (appDataService.deleteRegion(id)) {
            return ResponseEntity.ok(new HttpResponse("200", "Region delete successful"));
        } else {
            return ResponseEntity.ok(new HttpResponse("201", "Region delete error"));
        }
    }

    @GetMapping("/getAllAdmins")
    ResponseEntity<?> getAllAdmins() {
        return ResponseEntity.ok().body(accountService.getAllAdmins());
    }

    @PostMapping("/addAdmin")
    ResponseEntity<?> addAdmin(@RequestBody SignupAdminRequest signupAdminRequest) {
        AppAdmin appAdmin = accountService.addAppAdmin(signupAdminRequest.getNom(), signupAdminRequest.getPrenom(), signupAdminRequest.getEmail(), signupAdminRequest.getUsername(), signupAdminRequest.getPassword());
        if (appAdmin != null) {
            return ResponseEntity.ok("registre successful");
        } else {
            return ResponseEntity.badRequest().body("error while registre");
        }
    }

    @PutMapping(value = "/updateAdmin/{id}")
    ResponseEntity<?> updateAdminType(@PathVariable Long id, @ModelAttribute SignupAdminRequest signupAdminRequest) {
        ApplicationType applicationType = appDataService.updateApplicationType("libelle", id);
        if (applicationType != null) {
            return ResponseEntity.ok(new HttpResponse("200", "Admin update successful"));
        } else {
            return ResponseEntity.ok(new HttpResponse("201", "Admin update error"));
        }
    }

    @PutMapping("/deleteAdmin/{id}")
    ResponseEntity<?> deleteAdmin(@PathVariable Long id) {
        if (accountService.deleteAppAdmin(id)) {
            return ResponseEntity.ok("delete successful");
        } else {
            return ResponseEntity.badRequest().body("error while deleting");
        }
    }

    @PostMapping("/addApplicationType")
    ResponseEntity<?> addApplicationType(String libelle) {
        ApplicationType applicationType = appDataService.addApplicationType(libelle);
        if (applicationType != null) {
            return ResponseEntity.ok().body(new HttpResponse("200", "ApplicationType add successful"));
        } else {
            return ResponseEntity.badRequest().body(new HttpResponse("201", "ApplicationType add error"));
        }
    }

    @PutMapping(value = "/updateApplicationType/{id}")
    ResponseEntity<?> updateApplicationType(@PathVariable Long id, @ModelAttribute String libelle) {
        ApplicationType applicationType = appDataService.updateApplicationType(libelle, id);
        if (applicationType != null) {
            return ResponseEntity.ok(new HttpResponse("200", "ApplicationType update successful"));
        } else {
            return ResponseEntity.ok(new HttpResponse("201", "ApplicationType update error"));
        }
    }

    @PutMapping("/deleteApplicationType{id}")
    ResponseEntity<?> deleteApplicationType(@PathVariable Long id) {
        if (appDataService.deleteApplicationType(id)) {
            return ResponseEntity.ok(new HttpResponse("200", "Region delete successful"));
        } else {
            return ResponseEntity.ok(new HttpResponse("201", "Region delete error"));
        }
    }
}
