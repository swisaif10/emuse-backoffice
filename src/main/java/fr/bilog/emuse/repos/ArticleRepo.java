package fr.bilog.emuse.repos;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
 import fr.bilog.emuse.model.*;

@Repository
@RepositoryRestResource
public interface ArticleRepo extends JpaRepository<Article, Long> {


}
