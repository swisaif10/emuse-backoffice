package fr.bilog.emuserefontebackend.models;

import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FiltreArticleRquest {

    private String title;
    private String description;
    private String url;
    @ApiParam(name = "creationDate", value = "yyyy-MM-dd")
    private String creationDate;
    private int status;
    @ApiParam(name = "modificationDate", value = "yyyy-MM-dd")
    private String modificationDate;
    @ApiParam(name = "publicationDate", value = "yyyy-MM-dd")
    private String publicationDate;
    @ApiParam(name = "expirationDate", value = "yyyy-MM-dd")
    private String expirationDate;
    @ApiParam(name = "provenanceId", value = "1")
    private Long provenanceId;
    @ApiParam(name = "departementId", value = "1")
    private Long departementId;
}
