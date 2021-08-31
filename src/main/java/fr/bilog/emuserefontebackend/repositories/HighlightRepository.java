package fr.bilog.emuserefontebackend.repositories;

import fr.bilog.emuserefontebackend.entities.article.Article;
import fr.bilog.emuserefontebackend.entities.article.Highlight;
import fr.bilog.emuserefontebackend.entities.profiles.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HighlightRepository extends JpaRepository<Highlight, Long> {
    Optional<Highlight> findByArticleAndAppUser(Article article, AppUser appUser);
}
