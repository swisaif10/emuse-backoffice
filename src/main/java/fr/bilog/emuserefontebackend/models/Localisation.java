package fr.bilog.emuserefontebackend.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Localisation {

    private Long latitude;
    private Long longitude;


}
