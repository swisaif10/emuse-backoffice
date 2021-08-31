package fr.bilog.emuserefontebackend.models;

import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleCreationRequest {

    private String title;
    private String desc;
    private String url;
    private MultipartFile file;
    private Long idProvenance;
    private Long idDepartement;
    private List<Integer> applicationTypesIds;

}
