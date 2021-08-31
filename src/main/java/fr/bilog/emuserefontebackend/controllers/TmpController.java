package fr.bilog.emuserefontebackend.controllers;

import fr.bilog.emuserefontebackend.entities.profiles.AppUser;
import fr.bilog.emuserefontebackend.models.*;
import fr.bilog.emuserefontebackend.services.TmpService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/appAdminTmp")
public class TmpController {

    private TmpService tmpService;



    @PutMapping("/updateAccountInfo")
    ResponseEntity<?> updateAccountInfo(@RequestBody AccountInfoUpdateRequest accountInfoUpdateRequest) {

        if (tmpService.updateAccountInfo(accountInfoUpdateRequest)) {
            return ResponseEntity.ok("updated successfully");
        } else {
            return ResponseEntity.badRequest().body("error updating " );
        }
    }

    @PostMapping("/updateAsthmeHistory")
    ResponseEntity<?> updateAsthmeHistory(@RequestBody AsthmeControlUpdateRequest asthmeControlUpdateRequest) {

        if (tmpService.updateAsthmeControl(asthmeControlUpdateRequest)) {
            return ResponseEntity.ok("updated successfully");
        } else {
            return ResponseEntity.badRequest().body("error updating " );
        }
    }

    @PutMapping("/updatePhysicalActivities")
    ResponseEntity<?> updatePhysicalActivities(@RequestBody UpdatePhyActRequest updatePhyActRequest) {

        if (tmpService.updatePhysicalActivities(updatePhyActRequest)) {
            return ResponseEntity.ok("updated successfully");
        } else {
            return ResponseEntity.badRequest().body("error updating " );
        }
    }

    @PutMapping("/updateClinicalSigns")
    ResponseEntity<?> updateClinicalSigns(@RequestBody ClinicalSignsRequest clinicalSignsRequest) {

        if (tmpService.updateClinicalSigns(clinicalSignsRequest)) {
            return ResponseEntity.ok("updated successfully");
        } else {
            return ResponseEntity.badRequest().body("error updating " );
        }
    }
}
