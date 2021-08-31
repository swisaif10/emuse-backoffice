package fr.bilog.emuserefontebackend.services;

import fr.bilog.emuserefontebackend.entities.article.Article;
import fr.bilog.emuserefontebackend.models.ArticleCreationRequest;
import fr.bilog.emuserefontebackend.models.FiltreArticleRquest;
import fr.bilog.emuserefontebackend.models.PageDTO;

import java.util.List;

public interface ArticleService {

    Article addArticle(ArticleCreationRequest articleCreationRequest);

    List<Article> getArticles();

    PageDTO getFilteredArticles(int page, String sortBy, FiltreArticleRquest filtreArticleRquest);

    Article updateArticle(Long id, ArticleCreationRequest articleCreationRequest);

    boolean deleteArticle(Long idArticle);


}
