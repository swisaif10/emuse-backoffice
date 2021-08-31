package fr.bilog.emuserefontebackend.repositories;

import fr.bilog.emuserefontebackend.entities.article.Departement;
import fr.bilog.emuserefontebackend.entities.article.Region;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DepartementRepository extends JpaRepository<Departement, Long> {
    List<Departement> findAllByRegion(Region region);
}
