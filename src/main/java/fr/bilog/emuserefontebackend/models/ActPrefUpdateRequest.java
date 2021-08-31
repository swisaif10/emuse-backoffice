package fr.bilog.emuserefontebackend.models;

import lombok.Data;

import java.util.List;

@Data
public class ActPrefUpdateRequest {

    private List<Integer> actPrefsIds;

}
