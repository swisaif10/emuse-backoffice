package fr.bilog.emuserefontebackend.entities.profiles;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fr.bilog.emuserefontebackend.entities.article.Article;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ApplicationType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String libelle;
    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "Article_ApplicationType_Associations",
            joinColumns = @JoinColumn(name = "idApplicationType"),
            inverseJoinColumns = @JoinColumn(name = "idArticle"))
    private Set<Article> articles = new HashSet<Article>();
}
