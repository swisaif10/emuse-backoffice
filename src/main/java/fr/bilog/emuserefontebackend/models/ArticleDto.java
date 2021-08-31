package fr.bilog.emuserefontebackend.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleDto {
    private Long id;
    private String title;
    private String description;
    private String url;
    private int status;
    private String image;
    private Integer liked;
}
