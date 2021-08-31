package fr.bilog.emuserefontebackend.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PushHighlightRequest {
    Long idArticle;
    Integer like;
    Integer view;
    Integer share;
}
