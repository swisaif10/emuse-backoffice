package fr.bilog.emuserefontebackend.services.impl;

import fr.bilog.emuserefontebackend.entities.article.Region;
import fr.bilog.emuserefontebackend.entities.profiles.*;
import fr.bilog.emuserefontebackend.entities.userProfile.*;
import fr.bilog.emuserefontebackend.models.*;
import fr.bilog.emuserefontebackend.repositories.*;
import fr.bilog.emuserefontebackend.services.TmpService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@AllArgsConstructor
@Service
@Transactional
public class TmpServiceImpl implements TmpService {

    private AppUserRepository appUserRepository;
    private AsthmeHistroyRepository asthmeHistroyRepository;
    private DureeActivitesModeresTypeRepository dureeActivitesModeresTypeRepository;
    private DureeActivitesSoutenuesTypeRepository dureeActivitesSoutenuesTypeRepository;
    private ClinicalSignsRepository clinicalSignsRepository;
    private SignesCliniqueTypeRepository signesCliniqueTypeRepository;


    @Override
    public boolean updateAccountInfo(AccountInfoUpdateRequest accountInfoUpdateRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<AppUser> appUser = appUserRepository.findByUsername(authentication.getName());

        if (appUser.isPresent()) {

            if (accountInfoUpdateRequest.getDateNaissance() != null) {
                appUser.get().setDateNaissance(accountInfoUpdateRequest.getDateNaissance());
            }

            if (!accountInfoUpdateRequest.getUsername().isEmpty() && !accountInfoUpdateRequest.getUsername().equals("")) {
                appUser.get().setUsername(accountInfoUpdateRequest.getUsername());
            }

            return true;
        }
        return false;

    }

    @Override
    public boolean updateAsthmeControl(AsthmeControlUpdateRequest asthmeControlUpdateRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<AppUser> appUser = appUserRepository.findByUsername(authentication.getName());

        if (appUser.isPresent()) {


            if (asthmeControlUpdateRequest.getAsthmeResponses().size() == 5 && !asthmeControlUpdateRequest.getAsthmeResponses().stream().anyMatch(resp -> resp < 1) && !asthmeControlUpdateRequest.getAsthmeResponses().stream().anyMatch(resp -> resp > 5)) {

                int val1 = asthmeControlUpdateRequest.getAsthmeResponses().get(0);
                int val2 = asthmeControlUpdateRequest.getAsthmeResponses().get(1);
                int val3 = asthmeControlUpdateRequest.getAsthmeResponses().get(2);
                int val4 = asthmeControlUpdateRequest.getAsthmeResponses().get(3);
                int val5 = asthmeControlUpdateRequest.getAsthmeResponses().get(4);

                Optional<AshmeHistory> ashmeHistorySaved = asthmeHistroyRepository.findByValue1AndValue2AndValue3AAndValue4AndValue5(val1, val2, val3, val4, val5);
                AshmeHistory ashmeResp = new AshmeHistory();
                Set<AshmeHistory> asthmeHistorySet = new HashSet<>();

                if (ashmeHistorySaved.isPresent()) {


                    appUser.get().getAshmeHistories().forEach(asthmeHistory ->{
                        asthmeHistory.setValue1(ashmeHistorySaved.get().getValue1());
                        asthmeHistory.setValue2(ashmeHistorySaved.get().getValue2());
                        asthmeHistory.setValue3(ashmeHistorySaved.get().getValue3());
                        asthmeHistory.setValue4(ashmeHistorySaved.get().getValue4());
                        asthmeHistory.setValue5(ashmeHistorySaved.get().getValue5());
                        asthmeHistory.setCreationDate(new Date());
                    });


                } else {
                    asthmeHistorySet.clear();
                    ashmeResp.setValue1(asthmeControlUpdateRequest.getAsthmeResponses().get(0));
                    ashmeResp.setValue2(asthmeControlUpdateRequest.getAsthmeResponses().get(1));
                    ashmeResp.setValue3(asthmeControlUpdateRequest.getAsthmeResponses().get(2));
                    ashmeResp.setValue4(asthmeControlUpdateRequest.getAsthmeResponses().get(3));
                    ashmeResp.setValue5(asthmeControlUpdateRequest.getAsthmeResponses().get(4));
                    ashmeResp.setCreationDate(new Date());
                    asthmeHistorySet.add(ashmeResp);
                    asthmeHistroyRepository.save(ashmeResp);
                    appUser.get().getAshmeHistories().clear();
                    appUser.get().setAshmeHistories(asthmeHistorySet);

                }

                return true;
            }
        }

        return false;

    }

    @Override
    public boolean updatePhysicalActivities(UpdatePhyActRequest updatePhyActRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<AppUser> appUser = appUserRepository.findByUsername(authentication.getName());

        if (appUser.isPresent()) {
            if (updatePhyActRequest.getIdDureesActivitesModerees() != null) {
                Optional<DureeActivitesModeresType> dureeActivitesModeresType = dureeActivitesModeresTypeRepository.findById(updatePhyActRequest.getIdDureesActivitesModerees());
                if (dureeActivitesModeresType.isPresent()) {
                    appUser.get().setDureeActivitesModeresType(dureeActivitesModeresType.get());
                }
            }

            if (updatePhyActRequest.getIdDureesActivitesSoutenues() != null) {
                Optional<DureeActivitesSoutenuesType> dureeActivitesSoutenuesType = dureeActivitesSoutenuesTypeRepository.findById(updatePhyActRequest.getIdDureesActivitesSoutenues());
                if (dureeActivitesSoutenuesType.isPresent()) {
                    appUser.get().setDureeActivitesSoutenuesType(dureeActivitesSoutenuesType.get());
                }
            }

            return true;
        }
        return false;
    }

    @Override
    public boolean updateClinicalSigns(ClinicalSignsRequest clinicalSignsRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<AppUser> appUser = appUserRepository.findByUsername(authentication.getName());



        if (appUser.isPresent()) {


            if (clinicalSignsRequest.getClinicalSignRequestItems().size() == 10  ) {

                Set<SignesClinique> clinicalSignsSet = new HashSet<>();
                Set<SignesCliniqueItem> signesCliniqueItems = new HashSet<>(10);



                clinicalSignsRequest.getClinicalSignRequestItems().forEach( clinicalItem -> {

                    Optional<SignesCliniqueType> signesCliniqueTypeSaved = signesCliniqueTypeRepository.findById(clinicalItem.getId());

                    if (signesCliniqueTypeSaved.isPresent()){


                        SignesCliniqueItem signesCliniqueItem = new SignesCliniqueItem();
                        signesCliniqueItem.setValue(clinicalItem.getValue());
                        signesCliniqueItem.setSignesCliniqueType(signesCliniqueTypeSaved.get());

                        signesCliniqueItems.add(signesCliniqueItem);
                        SignesClinique signesClinique = new SignesClinique();
                        signesClinique.setCreationDate(new Date());


                        signesClinique.setSignesCliniqueItems(signesCliniqueItems);
                        clinicalSignsSet.add(signesClinique);

                        appUser.get().setSignesCliniques(clinicalSignsSet);




                    }

                 });

                return true;


            }
        }

        return false;
    }
}
