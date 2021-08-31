package fr.bilog.emuserefontebackend.models;

import lombok.Data;

import java.util.Date;

@Data
public class AccountInfoUpdateRequest {

    private Date dateNaissance;
    private String username = "";

}
