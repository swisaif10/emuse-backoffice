package fr.bilog.emuserefontebackend.services;
import fr.bilog.emuserefontebackend.models.*;


public interface TmpService {


    boolean updateAccountInfo(AccountInfoUpdateRequest accountInfoUpdateRequest);

    boolean updateAsthmeControl(AsthmeControlUpdateRequest asthmeControlUpdateRequest);

    boolean updatePhysicalActivities(UpdatePhyActRequest updatePhyActRequest);

    boolean updateClinicalSigns(ClinicalSignsRequest clinicalSignsRequest);


}
