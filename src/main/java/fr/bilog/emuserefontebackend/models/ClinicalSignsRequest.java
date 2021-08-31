package fr.bilog.emuserefontebackend.models;

import lombok.Data;

import javax.annotation.security.DenyAll;
import java.util.HashMap;
import java.util.List;

@Data
public class ClinicalSignsRequest {


    List<ClinicalSignRequestItem> clinicalSignRequestItems;

}


